package com.gdnybank.hnm.esb.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.FreeMarkerService;
import com.nantian.mfp.pub.service.TXBaseService;

/**
 * @desc
 * @author wxm
 * @date   2021.04.21
 *
 */
@SuppressWarnings("unchecked")
public abstract class EsbBaseService extends TXBaseService {

	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FreeMarkerService freeMarkerService;

	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p) {


		Map<String,Object> SYS_HEADER = (Map<String, Object>) p.get("SYS_HEAD");
		Map<String,Object> APP_HEADER = (Map<String, Object>) p.get("APP_HEAD");
		Map<String,Object> LOCAL_HEADER = (Map<String, Object>) p.get("LOCAL_HEAD");
		Map<String,Object> sendMap = (Map<String, Object>) p.get("BODY");
		//将接收到的map的key统一转为小写
		SYS_HEADER = changeMapKeyToLowerCase(SYS_HEADER);
		APP_HEADER = changeMapKeyToLowerCase(APP_HEADER);
		LOCAL_HEADER = changeMapKeyToLowerCase(LOCAL_HEADER);
		sendMap = changeMapKeyToLowerCase(sendMap);

		//根据ESB交易码和场景号获取返回给ESB的xml报文模板
		String backXmlTemplate = "BACK_"+SYS_HEADER.get("service_code")+"_"+SYS_HEADER.get("service_scene")+".ftl";

		SYS_HEADER.put("tran_timestamp",DateUtils.getDate("HHmmssSSS"));
		SYS_HEADER.put("tran_date",DateUtils.getDate("yyMMdd"));

		//1.执行对应的业务
		sendMap = doBusiness(SYS_HEADER, APP_HEADER, LOCAL_HEADER, sendMap);
		//2.根据报文模板生成返回给ESB的xml报文
		String msg = null;
		try {
			msg = freeMarkerService.toString(backXmlTemplate, sendMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;


	}

	public abstract Map<String,Object> doBusiness(Map<String,Object> SYS_HEADER,Map<String,Object> APP_HEADER,Map<String,Object> LOCAL_HEADER,Map<String,Object> sendMap);

	/**
	 * 将map的key统一转为小写
	 * @param p
	 * @return map
	 */
	private Map<String,Object> changeMapKeyToLowerCase(Map<String,Object> p){
		Map<String,Object> map =new HashMap<String,Object>();

		for(Entry<String, Object> entry:p.entrySet()){
			String key = entry.getKey();
			Object value = entry.getValue();
			map.put(key.toLowerCase(), value);
		}

		return map;
	}

	/**
	 * 组装返回给ESB的报文
	 * @param SYS_HEADER
	 * @param APP_HEADER
	 * @param LOCAL_HEADER
	 * @param BODY
	 * @param retCode
	 * @param retMsg
	 * @param status
	 * @param message
	 * @return
	 */
	public Map<String,Object> assembleBackData(Map<String,Object> SYS_HEADER,Map<String,Object> APP_HEADER,Map<String,Object> LOCAL_HEADER,Map<String,Object> BODY,
			String retCode,String retMsg,String retStatus){
		SYS_HEADER.put("ret_code", retCode);
		SYS_HEADER.put("ret_msg", retMsg);
		SYS_HEADER.put("ret_status", retStatus);
		return BaseUtils.map("SYS_HEADER",SYS_HEADER,"APP_HEADER",APP_HEADER,"LOCAL_HEADER",LOCAL_HEADER,"BODY",BODY);

	}

}
