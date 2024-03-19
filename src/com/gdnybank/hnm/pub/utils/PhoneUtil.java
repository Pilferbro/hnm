package com.gdnybank.hnm.pub.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhangwentao
 * @create: 2020-07-29 16:47
 */
public class PhoneUtil {

    private static final String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    public static boolean phoneIsTrueMatch(String phone){
        if (phone.length() != 11) {
           return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            } else {
                return false;
            }
        }
    }
}
