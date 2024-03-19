package com.nantian.mfp.framework.communication;

import com.nantian.mfp.framework.err.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Http协议通信实现类
 * @author gaop
 *
 */

public class HttpCommunication implements ICommunication {

	static Log log= LogFactory.getLog(HttpCommunication.class);

	private CloseableHttpClient httpclient;

	private HttpClientBuilder httpBuilder;

	private PoolingHttpClientConnectionManager connManager;

	private RequestConfig requestCfg;

	private HttpRoute tagRoute;

	private  String tagUrl;

	private  String baseTagUrl;

	private  String requestMethod;

	private  String keepAlive="close";

	private  int requestTimeOut;

	private  int connectTimeOut;

	private  int socketTimeOut;

	private  int maxConn;

	private  String charset;

	private  String paramName="msg";

	private  Map<String,String> staticHeader;




	/**
	 * HTTP链接管理类构造函数，每次构造一个通道的HTTPCLIENT对象及管理器
	 */
	public HttpCommunication(int requestTimeOut,int connectTimeOut,int socketTimeOut,String url,String requestMethod,int maxConn,String keepAlive,String charset,String paramName,Map<String,String> staticHeader){
		this.tagUrl=url;
		this.baseTagUrl = url;
		this.requestMethod=requestMethod;

		this.keepAlive=keepAlive;

		this.requestTimeOut=requestTimeOut;

		this.connectTimeOut=connectTimeOut;

		this.socketTimeOut=socketTimeOut;

		this.maxConn=maxConn;

		this.charset=charset;

		this.paramName=paramName;

		this.staticHeader=staticHeader;
		init();
	}

	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		try{
			this.connManager=new PoolingHttpClientConnectionManager();

			this.httpBuilder= HttpClientBuilder.create().setConnectionManager(this.connManager);

			this.connManager.setMaxTotal(this.maxConn);
			this.tagRoute=new HttpRoute(new HttpHost(tagUrl));
			this.connManager.setMaxPerRoute(tagRoute, this.maxConn);

			this.requestCfg= RequestConfig.custom().setConnectionRequestTimeout(this.requestTimeOut).setConnectTimeout(this.connectTimeOut).setSocketTimeout(this.socketTimeOut).build();

			this.httpclient=httpBuilder.build();
			return true;
		}catch(Exception e){
			log.error("httpclient 创建异常");
			return false;
		}
	}

	@Override
	public String communication(Object obj) throws Exception{
		String sendMsg = String.valueOf(obj);
		String returnMsg=null;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		//可能需要对sendMsg执行encoding操作
		//将发生信息放入POST表单



		if(this.requestMethod.equals("post")){


			try{
				HttpPost post=this.getHttpPost();
				post.setHeader("Connection", this.keepAlive);
				for(String key:this.staticHeader.keySet()){
					post.setHeader(key,this.staticHeader.get(key));
				}

				//需对应写入相关HTTP头信息
				//httppost.addHeader("", "");
				if("none".equals(this.paramName)){
					StringEntity myEntity = new StringEntity(sendMsg, ContentType.APPLICATION_XML.withCharset(this.charset));
					post.setEntity(myEntity);
				}else{
					formparams.add(new BasicNameValuePair(this.paramName, sendMsg));
					UrlEncodedFormEntity uefEntity;
					uefEntity = new UrlEncodedFormEntity(formparams, charset);
					post.setEntity(uefEntity);
				}

				log.debug("http comm " + post.getURI());
	            CloseableHttpResponse response = this.httpclient.execute(post);

	            int resStatus=response.getStatusLine().getStatusCode();

	            if (resStatus == HttpStatus.SC_OK) {
	              // get result data
	            	HttpEntity entity = response.getEntity();
		            if (entity != null) {
		                returnMsg= EntityUtils.toString(entity, charset);
		                log.warn("Response content: " + returnMsg);
		            }
	            }
	            else {
	              log.error(this.tagUrl + ": resStatus is " + resStatus);
	              // 失败后需要释放，不然会有 “Timeout waiting for connection from pool”异常
	              post.abort();
	              throw new BusinessException("HttpCommunication160",this.tagUrl+":http请求状态码"+resStatus);
	            }

			}catch (ClientProtocolException e) {
				log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication166", "通信协议异常:"+e.getMessage());
	        } catch (UnsupportedEncodingException e) {
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication169", "不可支持的字符集:"+e.getMessage());
	        } catch (ConnectionPoolTimeoutException e) {
	            log.error(e.getMessage(), e);
	            throw new BusinessException("HttpCommunication173", "链接等待超时:"+e.getMessage());
	         }catch (ConnectTimeoutException e){
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication175", "请求超时:"+e.getMessage());
	        } catch (SocketTimeoutException e){
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication178", "响应超时:"+e.getMessage());
	        } catch (IOException e) {
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication181", "IO异常:"+e.getMessage());
	        } catch (Exception e){
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication184", "其他异常:"+e.getMessage());
	        }

		}else if(this.requestMethod.equals("get")){

			try {
				String msg = "";
				if(!"none".equals(this.paramName)){
					StringBuffer getmsg=new StringBuffer("?");
					formparams.add(new BasicNameValuePair(this.paramName, sendMsg));
					getmsg.append(URLEncodedUtils.format(formparams,charset));
					msg = getmsg.toString();
				}
				HttpGet get=this.getHttpGet(msg);
				get.setHeader("Connection", this.keepAlive);
				for(String key:this.staticHeader.keySet()){
					get.setHeader(key,this.staticHeader.get(key));
				}
				log.debug("http comm " + get.getURI());
				CloseableHttpResponse response = httpclient.execute(get);

				try{
		            int resStatus=response.getStatusLine().getStatusCode();
		            if (resStatus == HttpStatus.SC_OK) {
		              // get result data
		            	HttpEntity entity = response.getEntity();
			            if (entity != null) {
			                returnMsg= EntityUtils.toString(entity, charset);
			                log.warn("Response content: " + returnMsg);
			            }
		            }
		            else {
		              log.error(this.tagUrl + ": resStatus is " + resStatus);
		              throw new BusinessException("HttpCommunication160",this.tagUrl+":http请求状态码"+resStatus);
		            }
				}finally{
					response.close();
				}

			}catch (ClientProtocolException e) {
				log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication213", "通信协议异常:"+e.getMessage());
	        } catch (UnsupportedEncodingException e) {
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication216", "不可支持的字符集:"+e.getMessage());
	        } catch (ConnectionPoolTimeoutException e) {
	            log.error(e.getMessage(), e);
	            throw new BusinessException("HttpCommunication219", "链接等待超时:"+e.getMessage());
	         }catch (ConnectTimeoutException e){
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication222", "请求超时:"+e.getMessage());
	        } catch (SocketTimeoutException e){
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication225", "响应超时:"+e.getMessage());
	        } catch (IOException e) {
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication228", "IO异常:"+e.getMessage());
	        } catch (Exception e){
	        	log.error(e.getMessage(), e);
				throw new BusinessException("HttpCommunication231", "其他异常:"+e.getMessage());
	        }
		}else{
			throw new BusinessException("HttpCommunication234", "请求类型未设置");
		}
		log.debug(formatStats(this.tagRoute));
		return returnMsg;
	}


	/**
	 * 根据报文及通道，获取对应通道httpclinetManager，执行请求
	 */

	private HttpGet getHttpGet(String msg){
		HttpGet httpGet=new HttpGet(this.tagUrl+msg);
		httpGet.setConfig(this.requestCfg);
		return httpGet;
	}

	private HttpPost getHttpPost(){
		HttpPost httpPost=new HttpPost(this.tagUrl);
		httpPost.setConfig(this.requestCfg);
		return httpPost;
	}



	public CloseableHttpClient getHttpClient()
	{
		return this.httpclient;
	}

	private String formatStats(final HttpRoute route) {
	        StringBuilder buf = new StringBuilder();
	        PoolStats totals = connManager.getTotalStats();
	        PoolStats stats = connManager.getStats(route);
	        buf.append("[total kept alive: ").append(totals.getAvailable()).append("; ");
	        buf.append("route allocated: ").append(stats.getLeased() + stats.getAvailable());
	        buf.append(" of ").append(stats.getMax()).append("; ");
	        buf.append("total allocated: ").append(totals.getLeased() + totals.getAvailable());
	        buf.append(" of ").append(totals.getMax()).append("]");
	        return buf.toString();
	    }



	@Override
	public Map<String,Object> getState() {
		// TODO Auto-generated method stub
		PoolStats totals = connManager.getTotalStats();
		int allocated=totals.getLeased()+totals.getAvailable();
		Map<String,Object> state=new HashMap<String,Object>();
		state.put(CURRENT_CONN_COUNT, allocated);
		state.put(MAX_CONN_COUNT, totals.getMax());
		return state;
	}



	@Override
	public String watchSelf() {
		// TODO Auto-generated method stub
		return null;
	}




	public String getTagUrl() {
		return tagUrl;
	}



	public void setTagUrl(String tagUrl) {
		this.tagUrl = tagUrl;
	}



	public String getRequestMethod() {
		return requestMethod;
	}



	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}



	public String getKeepAlive() {
		return keepAlive;
	}



	public void setKeepAlive(String keepAlive) {
		this.keepAlive = keepAlive;
	}



	public int getRequestTimeOut() {
		return requestTimeOut;
	}



	public void setRequestTimeOut(int requestTimeOut) {
		this.requestTimeOut = requestTimeOut;
	}



	public int getConnectTimeOut() {
		return connectTimeOut;
	}



	public void setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}



	public int getSocketTimeOut() {
		return socketTimeOut;
	}



	public void setSocketTimeOut(int socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}



	public int getMaxConn() {
		return maxConn;
	}



	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}


	public String getParamName() {
		return paramName;
	}



	public void setParamName(String paramName) {
		this.paramName = paramName;
	}



	public Map<String,String> getStaticHeader() {
		return staticHeader;
	}



	public void setStaticHeader(Map<String,String> staticHeader) {
		this.staticHeader = staticHeader;
	}

	public String getBaseTagUrl() {
		return baseTagUrl;
	}

}
