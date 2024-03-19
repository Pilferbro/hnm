package com.gdnybank.hnm.pub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nantian.mfp.dummy.service.DummyService;
import com.nantian.mfp.framework.communication.CommunicationFactory;
import com.nantian.mfp.framework.communication.HttpCommunication;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.JsonUtils;
import com.nantian.mfp.pub.dao.model.FwBacktrans;
import com.nantian.mfp.pub.service.FwBacktransService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * mfp框架的json请求通讯服务类
 *
 * @author guohan
 * @createDate 2018年5月3日
 */
@Service
public class MfpJsonMsgProcService {

	static Log log= LogFactory.getLog(MfpJsonMsgProcService.class);

	@Autowired
    FwBacktransService fwBacktransService;
	@Autowired
    DummyService dummyService;
	@Autowired
    CommunicationFactory communicationFactory;
	@Autowired
	private ObjectMapper objectMapper;


	/**
	 *
	 * @author guohan
	 * @param sendMsg 用于组装报文的数据集合
	 * @param backCode 其他系统请求路径,例如：“/param”、“/face”
	 * @return 请求返回数据集合
	 */
	public Map<String,Object> sendAndReceiveMsg(Map<String,Object> sendMsg,String backCode,String qusCode){
		//返回值
		Map<String,Object> rsMsg=null;
		try{
			//初始化后端交易参数
			FwBacktrans backTxPro=fwBacktransService.getTxPropertyByTxCode(backCode);
//			初始化通信组件
			HttpCommunication commuication = (HttpCommunication) communicationFactory.getCommBeanById(backTxPro.getBack_svr_key());

			// 动态拼接url
			String url = commuication.getBaseTagUrl();
			commuication.setTagUrl(url+= qusCode);

			//若走挡板，"/"不能命名文件，故改为"+"
			backCode = backCode.replace("/", "+");

			//-----------------执行部分
			//把传输的map转换成为json格式的字符串
			StringBuffer msg=new StringBuffer();
			ObjectMapper mapper = new ObjectMapper();
			msg.append(mapper.writeValueAsString(sendMsg));
			log.warn("sendmsg:"+msg.toString());

			//返回报文对象
			String returnMsg=null;
			// 判断是否走挡板
			if("true".equals(MfpContextHolder.getProps("dummy."+backCode))){
				// 挡板解析，原定是根据模版组装，但实际应用是短板内容固定的，但是仍需要传入一个xml格式的数据来源，所以这里给了个空的xml字符串
				backTxPro.setMsg_type("JSON");
				returnMsg=	dummyService.doDummy("<key></key>",backCode,backTxPro);
			}else{
				//执行通信
				returnMsg=commuication.communication(msg.toString());
			}
			log.warn("receivemsg:"+returnMsg);

			//将返回的json格式字符串转换为map,并进行通讯成功检查合法性检查
			rsMsg = (Map<String, Object>) JsonUtils.readJson2Map(returnMsg);
			//this.receiveMapCheck(receiveMap);
			//rsMsg = (Map<String, Object>) receiveMap.get("data");
		} catch(BusinessException e){
			//通信组件获取异常
			e.printStackTrace();
			log.error("通信过程类执行异常:"+e.getMessage());
			throw new BusinessException("MfpJsonMsgProcService005", e.getMessage());
		} catch(Exception e){
			//通信组件获取异常
			e.printStackTrace();
			log.error("通信过程类执行异常:"+e.getMessage());
			throw new BusinessException("MfpJsonMsgProcService001", "通信过程类执行异常"+e.getMessage());
		}
		return rsMsg;
	}

	/**
	 * 对返回报文进行合法性检查
	 * @param receiveMap 返回的数据Map
	 */
	private void receiveMapCheck(Map<String, Object> receiveMap){
		// 检查是否包含“data”
		if(null == receiveMap.get("data") || "".equals(receiveMap.get("data"))){
			String errMsg = "返回报文格式错误，没有包含data";
			log.error(errMsg);
			throw new BusinessException("MfpJsonMsgProcService002", errMsg);
		}
		// 检查data对应的数据是否是Map格式
		if(!(receiveMap.get("data") instanceof Map)){
			String errMsg = "返回报文格式错误，data的数据格式不是Map";
			log.error(errMsg);
			throw new BusinessException("MfpJsonMsgProcService003", errMsg);
		}
	}


}
