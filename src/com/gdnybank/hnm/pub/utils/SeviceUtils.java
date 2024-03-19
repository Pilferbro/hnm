package com.gdnybank.hnm.pub.utils;

import java.util.Map;

/**
 * 工具类
 * @author yjc
 *
 */
public class SeviceUtils {

	public static final String COOKIE = "cookie";

	public static final String SESSION = "SESSION";

	/**
	 * 从header参数集合中提取sessionId
	 * @param env
	 * @return
	 */
	public static String getSessionIdFromHeader(Map<String, Object> env){

		String cookie = (String) env.get(COOKIE);
		String [] cookies = cookie.split(";");
		String sessionId = "";
		for(String object : cookies){
			if(object.startsWith(SESSION)){
				sessionId = object.substring(object.indexOf("=")+1);
			}
		}
		return sessionId;
	}

}
