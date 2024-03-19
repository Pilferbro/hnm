package com.gdnybank.hnm.pub.utils;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.当证件类型为居民身份证、临时身份证和户口簿时，证件号码必须为18位；最后一位如为字母，必须为大写X；
 * 2.当证件类型为港澳居民通行证时，证件号码必须为9位，且首位必须为大写字母H或M；
 * 3.当证件类型为台湾居民通行证时，证件号码必须为8位数字；
 * 4.当证件类型为外国人永久居留证时，证件号码必须为15位，前3位必须是大写字母；
 * 5.当证件类型为k.港澳居民居住证；L.台湾居民居住证时，证件号码必须为18位数字，且前6位810000为香港居民、820000为澳门居民、830000为台湾居民。
 */
@Service
@Transactional
public class CustInfoVerify {

    public String checkCustInfo(String certType, String cert_num) {

        if (ObjectUtil.isEmpty(certType) || ObjectUtil.isEmpty(cert_num)){
            return "证件号码非法";
        }
        //当证件类型为居民身份证、临时身份证和户口簿时，证件号码必须为18位；最后一位如为字母，必须为大写X；
        if ("110001".equals(certType) || "110003".equals(certType) || "110005".equals(certType)) {
            return IdCardUtil.IdentityCardVerification(cert_num);
        }

        //当证件类型为港澳居民通行证时，证件号码必须为9位，且首位必须为大写字母H或M；
        if ("110019".equals(certType)) {
            return IdentityHMCard(cert_num);
        }

        //当证件类型为台湾居民通行证时，证件号码必须为8位；
        if ("110021".equals(certType)) {
            return IdentityTCard(cert_num);
        }

        //当证件类型为外国人永久居留证时，证件号码必须为15位，前3位必须是大写字母；
        if ("110029".equals(certType)) {
            return IdentityFCard(cert_num);
        }

        if ("119019".equals(certType) || "119021".equals(certType)) {
            return IdentityLCard(certType, cert_num);
        }
        return "correct";
    }

    private String IdentityLCard(String certType, String cert_num) {

        if (cert_num.length() != 18) {
            return "港澳/台湾居民居住证非法";
        }
        String startNum = cert_num.substring(0, 6);
        String endNum = cert_num.substring(6);
        // 当证件类型为119019.港澳居民居住证,前6位810000为香港居民、820000为澳门居民、
        if ("119019".equals(certType) && !("810000".equals(startNum) || "820000".equals(startNum))) {
            return "港澳居民居住证非法";
        }
        //119021.台湾居民居住证时，证件号码必须为18位数字，且830000为台湾居民
        if ("119021".equals(certType) && !"830000".equals(startNum)) {
            return "港澳居民居住证非法";
        }
        if (!isStrNum(endNum)) {
            return "港澳/台湾居民居住证非法";
        }
        return "correct";
    }

    private String IdentityFCard(String cert_num) {
        //判断长度是否为15
        if (cert_num.length() != 15) {
            return "外国人永久居留证非法";
        }
        String str = cert_num.substring(0, 3);
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isUpperCase(str.charAt(i))) {
                return "外国人永久居留证非法";
            }
        }

        String strNum = cert_num.substring(3, cert_num.length());
        if (!isStrNum(strNum)) {
            return "外国人永久居留证非法";
        }
        return "correct";
    }

    private String IdentityTCard(String cert_num) {
        //判断长度是否为8
        if (cert_num.length() != 8 || !isStrNum(cert_num)) {
            return "台湾居民通行证非法";
        }
        return "correct";
    }

    private String IdentityHMCard(String cert_num) {

        //判断长度是否为9
        if (cert_num.length() != 9) {
            return "港澳居民通行证非法";
        }
        //判断首位是否为字母H或M
        char c = cert_num.charAt(0);
        if (c != 'H' && c != 'M') {
            return "港澳居民通行证非法";
        }
        //判断后8位是否为数字
        String str = cert_num.substring(1, cert_num.length());
        if (!isStrNum(str)) {
            return "港澳居民通行证非法";
        }
        return "correct";
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isStrNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        return isNum.matches();
    }
}
