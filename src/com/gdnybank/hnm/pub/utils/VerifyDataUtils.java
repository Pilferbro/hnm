package com.gdnybank.hnm.pub.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据校验工具类
 *
 * @author chenhao
 * @createDate 2016年5月17日
 */
public class VerifyDataUtils {

	/**
	 * 手机号验证,支持号码段为“13、14、15、17、18”
	 *
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean checkMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		// p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 验证邮箱合法性
	 *
	 * @param email
	 * @return 验证通过返回true
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证身份证合法性
	 *
	 * @param idcard
	 * @return 验证通过返回true
	 */
	public static boolean checkIdcard(String idcard) {
		return IdcardValidator.isValidatedAllIdcard(idcard);
	}

}
