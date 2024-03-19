package com.gdnybank.hnm.pub.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class MessageUtils {
	private static Log logger = LogFactory.getLog(MessageUtils.class);
	private static Configuration config=new Configuration();
	static{
		config.setDefaultEncoding("utf-8");
		config.setClassForTemplateLoading(MessageUtils.class, "/messageTpl");
	}

	public static Map<String, Object> sendMessage(String url, String tplName, Map<String, Object> p) {
		Template tpl;
		try {
			tpl = config.getTemplate(tplName);
			//MessageContext ctx=new MessageContext(InArea,OutArea);
			StringWriter sw=new StringWriter(256);
			tpl.process(p, sw);
			logger.info("发送报文:"+sw.toString());
			CloseableHttpClient httpClient= HttpClientBuilder.create().build();
			HttpPost post=new HttpPost(url);
			StringEntity entity=new StringEntity(sw.toString(), "utf-8");
			post.setEntity(entity);
			CloseableHttpResponse response=httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {// 正常响应
				parseXmlToEvn(p, EntityUtils.toString(response.getEntity(), "utf-8"));
			} else {
				p.put("retCode", "00001");
				p.put("retMsg", "发送报文与" + url + ",响应状态码:" + response.getStatusLine().getStatusCode());
			}
			response.close();
			httpClient.close();
		} catch (Exception e) {
			p.put("retCode", "00002");
			p.put("retMsg", "系统异常，请稍后再试！");
		}
		return p;
	}

	/**
	 * 解析响应报文
	 * @param evn
	 * @param retXml
	 */
	private static void parseXmlToEvn(Map<String, Object> evn, String retXml) {
		logger.info("响应报文：" + retXml);
		try {
			Document document = DocumentHelper.parseText(retXml);
			Element root = document.getRootElement();
			Element header = (Element) root.elements("TX_HEADER").get(0);
			String respCode = header.elementTextTrim("SYS_TX_STATUS");
			evn.put("retCode", respCode);
			System.out.println("respCode:" + respCode);
			// SYS_TX_STATUS服务请求处理的结果。0或00－成功，01－失败,02-不确定。
			if(("0".equals(respCode)) || ("00".equals(respCode))){	//成功
				Element body = (Element) root.elements("TX_BODY").get(0);
				Element entity = (Element) body.elements("ENTITY").get(0);
				List list = entity.elements();
				for(int i=0;i<list.size();i++){
					Element element=(Element)list.get(i);
					evn.put(element.getName(), element.getText());
				}
			}else{
				String respMsg=header.elementTextTrim("SYS_RESP_DESC");//获取响应码
				String errorCode = header.elementTextTrim("SYS_RESP_CODE");//获取错误码
				logger.warn("调用报文返回处理失败:"+respCode+"|"+respMsg);
				evn.put("retMsg", respMsg);
				evn.put("errorCode", errorCode);
			}
		} catch (DocumentException e) {
			logger.error("响应报文XML格式读取异常！", e);
			evn.put("retCode", "00003");
			evn.put("retMsg", "响应报文XML格式读取异常");
		}catch (Exception e) {
			logger.error("内部错误！", e);
			evn.put("retCode", "00004");
			evn.put("retMsg", "内部错误！");
		}
		logger.info("响应报文：" + evn.toString());
	}
}
