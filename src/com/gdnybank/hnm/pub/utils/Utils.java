package com.gdnybank.hnm.pub.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author duzx
 * @desc 项目中使用到的非平台提供的工具类
 *
 */
public class Utils {

	/*
	 * @Author duzx
	 *
	 * @desc 计算两个经纬度之间的地球曲线距离。
	 *
	 * @param lat1 纬度 lng1经度 lat2纬度 lng2经度
	 */
	private static double EARTH_RADIUS = 6378.137;
	private static String hexString = "0123456789ABCDEFabcdef";


	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = Math.abs(radLat1 - radLat2);
		double b = Math.abs(rad(lng1) - rad(lng2));
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof CharSequence){
			return ((CharSequence) obj).length() == 0;
		}

		if (obj instanceof Collection){
			return ((Collection) obj).isEmpty();
		}

		if (obj instanceof Map){
			return ((Map) obj).isEmpty();
		}

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	/*
	 * by duzx desc 统一处理list map里面的日期信息，把日期信息转化成string
	 *
	 * @param source:需要转换日期类型的数据源（list map） var ： 需要转换的字段名称
	 */
	public static void convdate4list(List<Map<String, Object>> source, String var) {
		String updatetime = null;
		for (Map<String, Object> temp : source) {
			updatetime = (String) DateUtils.getDate((Date) temp.get(var), DateUtils.datetimePattern);
			temp.put(var, updatetime);
		}

	}

	/*
	 * by duzx desc 统一处理 map里面的日期信息，把日期信息转化成string
	 *
	 * @param source:需要转换日期类型的数据源（ map） var ： 需要转换的字段名称
	 */
	public static void convdate4Map(Map<String, Object> source, String var) {
		String updatetime = null;
		updatetime = (String) DateUtils.getDate((Date) source.get(var), DateUtils.datetimePattern);
		source.put(var, updatetime);
	}

	/**
	 * 获取文件大小 将byte类型转换为M
	 *
	 * @param bytes
	 * @return
	 */
	public static String bytes2mb(long bytes) {
		java.math.BigDecimal filesize = new java.math.BigDecimal(bytes);
		java.math.BigDecimal megabyte = new java.math.BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, java.math.BigDecimal.ROUND_UP).floatValue();
		return returnValue + "";
	}

	/**
	 * 转换map的key值，将大写key值转换为小写key值。
	 *
	 * @param map
	 * @return
	 */
	public static Map<String, Object> changeKeyToLower(Map<String, Object> receMap) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(receMap)) {
			for (String key : receMap.keySet()) {
				Object obj = receMap.get(key);
				if (obj instanceof Map) {
					newMap.put(key.toLowerCase(), changeKeyToLower((Map<String, Object>) obj));
				} else if (obj instanceof List) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					for (Map<String, Object> map : (List<Map<String, Object>>) obj) {
						list.add(changeKeyToLower(map));
					}
					newMap.put(key.toLowerCase(), list);
				} else {
					newMap.put(key.toLowerCase(), obj);
				}
			}
		}
		return newMap;
	}

	/**
	 * desc: 从p 中获取key user:pzz date:2015年10月21日
	 *
	 * @param p
	 *            map
	 * @param key
	 */
	public static String getNotNullValueString(Map<String, Object> p, String key) {
		if (p == null) {
			return null;
		}
		if (!p.containsKey(key) || p.get(key) == null) {
			return null;
		}
		return !StringUtils.isBlank(p.get(key).toString()) ? p.get(key).toString().trim() : "";
	}

	/**
	 * 左填充方法
	 *
	 * @param string
	 *            原始数据
	 * @param padChar
	 *            补充数据
	 * @param maxLength
	 *            最大长度
	 * @return 返回补充后的结果
	 */
	public static String fillLeft(String string, char padChar, int maxLength) {
		if (string.length() >= maxLength) {
			return string;
		}
		StringBuilder sb = new StringBuilder(maxLength);
		for (int i = string.length(); i < maxLength; i++) {
			sb.append(padChar);
		}
		sb.append(string);
		return sb.toString();
	}

	/**
	 * 根据上送map获取交易码，如果上送map中拿不到交易码，则从mfp上下文中获取交易码；
	 *
	 * @author rzt
	 * @param p
	 * @return
	 */
	public static String getTxCode(Map<String, Object> p) {
		String txcode = "";
		if (!isNullOrEmpty(p)) {
			txcode = p.get("txcode") == null ? "" : String.valueOf(p.get("txcode"));
		} else {
			txcode = MfpContextHolder.getTxcode();
		}

		return txcode;
	}

	public static String replaceString(String strData, String regex, String replacement) {
		if (strData == null) {
			return null;
		}
		int index;
		index = strData.indexOf(regex);
		String strNew = "";
		if (index >= 0) {
			while (index >= 0) {
				strNew += strData.substring(0, index) + replacement;
				strData = strData.substring(index + regex.length());
				index = strData.indexOf(regex);
			}
			strNew += strData;
			return strNew;
		}
		return strData;
	}

	/**
	 * 替换字符串中特殊字符
	 */
	public static String encodeString(String strData) {
		if (strData == null) {
			return "";
		}
		strData = replaceString(strData, "&", "&amp;");
		strData = replaceString(strData, "<", "&lt;");
		strData = replaceString(strData, ">", "&gt;");
		strData = replaceString(strData, "’", "&apos;");
		strData = replaceString(strData, "”", "&quot;");
		strData = replaceString(strData, "\"", "&quot;");
		return strData;
	}

	/**
	 * 还原字符串中特殊字符
	 */
	public static String decodeString(String strData) {
		strData = replaceString(strData, "&lt;", "<");
		strData = replaceString(strData, "&gt;", ">");
		strData = replaceString(strData, "&quot;", "\"");
		strData = replaceString(strData, "&amp;", "&");
		return strData;
	}

	/**
	 * 创建一个纯数字类型的随机数
	 *
	 * @param size
	 *            随机数的位数
	 * @return 生成的随机数结果
	 */
	public static String getRandomString(int size) {
		String result = "";
		for (int i = 0; i < size; i++) {
			result += new Random().nextInt(10);
		}
		return result;
	}

	//把hex编码转换为string
    public static String decode(String bytes) {
        bytes = bytes.toUpperCase();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2) {
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
		}
        return new String(baos.toByteArray());
    }

}
