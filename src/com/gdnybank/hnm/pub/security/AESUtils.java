package com.gdnybank.hnm.pub.security;

import com.nantian.mfp.framework.utils.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AESUtils {


    /**
     * 获取AES加密key
     * @return
     * @throws Exception
     */
    public static String generateKey() throws Exception{

        //生成Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        //keyGenerator.init(128, new SecureRandom("seedseedseed".getBytes()));
        //使用上面这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        String key = Base64Utils.encode(keyBytes);
        return key;
    }

    /**
     * AES加密
     * @param data 待加密数据
     * @param key  加密秘钥
     * @return
     * @throws Exception
     */
    public static String encryptData(String data,String key) throws Exception{
         Key secretKey = new SecretKeySpec(Base64Utils.decode(key), "AES");
         Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
         cipher.init(Cipher.ENCRYPT_MODE, secretKey);
         byte[] encodeResult = cipher.doFinal(data.getBytes());
         String encryptData= Base64Utils.encode(encodeResult);
         return encryptData;
    }

    /**
     * AES解密算法
     * @param data 待解密数据
     * @param key  加密秘钥
     * @return
     * @throws Exception
     *
     */
    public static String decryptData(String data,String key) throws Exception{
         Key secretKey = new SecretKeySpec(Base64Utils.decode(key), "AES");
              Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
         cipher.init(Cipher.DECRYPT_MODE, secretKey);
         byte[] decodeResult = cipher.doFinal(Base64Utils.decode(data));
         String decryptData=new String(decodeResult,"UTF-8");
         return decryptData;


    }

    public static void main(String[] args) throws Exception{
        String key = generateKey();
        System.out.println("------生成的key:"+key);
        String encryptData = encryptData("upl123", key);
        System.out.println("------加密后的数据:"+encryptData);
        String decryptData = decryptData(encryptData, key);
        System.out.println("------解密后的数据:"+decryptData);


    }

}
