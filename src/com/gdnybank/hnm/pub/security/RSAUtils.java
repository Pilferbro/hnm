package com.gdnybank.hnm.pub.security;


import com.gdnybank.hnm.pub.utils.HexUtils;
import com.nantian.mfp.framework.utils.Base64Utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 *
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;//117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;//128;

    private static final int KEY_SIZE=512;

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        /*20160219*/
        SecureRandom secure=new SecureRandom();
        keyPairGen.initialize(KEY_SIZE,secure);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        byte[] modulus1 = publicKey.getModulus().toByteArray();
        byte[] Exponent1 = publicKey.getPublicExponent().toByteArray();
        //转化为16进制字符串
        String modulus= HexUtils.encode(modulus1);
        String Exponent= HexUtils.encode(Exponent1);
        System.out.println("modulus:"+modulus);
        System.out.println("Exponent:"+Exponent);
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
       // byte[] keyBytes = Base64Utils.decode(privateKey);
    	byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64Utils.encode(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64Utils.decode(sign));
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        //Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        //Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        System.out.println(keyFactory.getAlgorithm());
        // 对数据加密
        //Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        System.out.println(keyFactory.getAlgorithm());
        //Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64Utils.encode(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key =  (Key) keyMap.get(PUBLIC_KEY);
        return Base64Utils.encode(key.getEncoded());
    }

	 private static List<String> getAllLocalHostIP() {
	        List<String> res = new ArrayList<String>();
	        Enumeration netInterfaces;
	        try {
	            netInterfaces = NetworkInterface.getNetworkInterfaces();
	            InetAddress ip = null;
	            while (netInterfaces.hasMoreElements()) {
	                NetworkInterface ni = (NetworkInterface) netInterfaces
	                        .nextElement();
	                Enumeration nii = ni.getInetAddresses();
	                while (nii.hasMoreElements()) {
	                    ip = (InetAddress) nii.nextElement();
	                    if (ip.getHostAddress().indexOf(":") == -1) {
	                        res.add(ip.getHostAddress());
	                        System.out.println("本机的ip=" + ip.getHostAddress());
	                    }
	                }
	            }
	        } catch (SocketException e) {
	            e.printStackTrace();
	        }
	        return  res;
	    }




 public static void main(String [] args){

Map<String, Object> map=null;


	 try {
//		 String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDWHn8x4YIR8dLm6i2DDobTIB5sxb+22Y7TVmlGhtHJSQ6liwhlm+gmEDrb6Y3ed/NsQUw1WTnCdDgV2v8M6t/UYHyEdfDFATPa5wGeGyUS/HjyVdFVy+IJ6F2DrVwCgAzcnUtn3LlsBN50FbmgqU7J+r7vVJow4vUCtgGAEWotwIDAQAB";
//		 String privatekey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAINYefzHhghHx0ubqLYMOhtMgHmzFv7bZjtNWaUaG0clJDqWLCGWb6CYQOtvpjd5382xBTDVZOcJ0OBXa/wzq39RgfIR18MUBM9rnAZ4bJRL8ePJV0VXL4gnoXYOtXAKADNydS2fcuWwE3nQVuaCpTsn6vu9UmjDi9QK2AYARai3AgMBAAECgYA9/4p9ZlT/HZqWNuCZJgZbkm3mhEGrhTapcWaKR1tu5rpGKVKlfBQu8w9Z0rbb4txghFmV5nSdlgWRj9J6Fo2xexFlsWk7ctKr21ytYUvopBqrL4Ma9j6MBkvv3XEWyTceLXSr7LqtsT2QDAW2rq7xj/G5VV7QP1JIoYbYugahMQJBAMG2htT/FHN6bfiizxRWasXo/X3oIXCrjjyWfMN3/A+gvaW58/E/HKmPnP9q0OWEghchx1LUBa8VxtRpEToCxc8CQQCtlC62fuMCkfSPl/+48tXAXZiYZKKC7MpaMtXMKcORhi+T/PINrdX8RJx2LQyEDFJtL9eqEo+/36vQA060V5CZAkAOCXj5xEYqHAwOAVY6Jgz0rG56CjEqJfRXpZwC2hX01/QGJ1Rq/eTI0LRSud3LU+/NV1BHSz/iMRTIXZi2+G53AkBEHPLbsBz6pDWYp2e0tq3EJ7Y6jrrqr2qUTIBy9iyFDWfcJZUCYyWmMXJIOVNA+ejaabyyQ0hfXbD5FmMrB3V5AkAUtDDA6HBA8SkrnO3/QRFX5yww+8ADrTYQ9So6jSpwzWtnIjYrLyd9BDxb5IBMfyg5BPjjN4WBY4lXc/8VuNWH";
		 map=genKeyPair();
		 String publickey=getPublicKey(map);
		 System.out.println("公钥是"+getPublicKey(map));
		 System.out.println("私钥是"+getPrivateKey(map));
		 String privatekey=getPrivateKey(map);
		 String password1="khfzb";
//		 String pwd2="sIiTWaC7wpCYJXT7KG1jiwsbR4LNCwL41JKg1%2BI2US7MTTV9GaZ2X8uhH3x27xdJm1X7odnTSO40GesWVOkA%2BW96uSDAUaOWTxU34treOGvB/TXWJrsUMEfyXA9ZLg25A15ZT4DgDmBeIdtd%2BX4EGlJY%2B2wg5GEQoKMDPFEBSNx7GsSymwnlVwcngwu68M3RNCG%2BN%2B2PbxLPTuXVqQIuyaifWVCHgtDbOMT2dhSjO59vcJNRpeBwiv0Ygh5Cbs7dmeHQakYUxAzmYSj0P7WnPNuHBNRajI4cTF6ddHWWG35bMsBm5WfjHUXQwo7mmUPG4TFAEBYvy7IuWDl8Gc5aCg==";
//		 pwd2=pwd2.replace("%2B", "+");
		 byte [] b=encryptByPublicKey(password1.getBytes(), publickey);
		 System.out.println("用公钥加密后:"+ Base64Utils.encode(b));
		 byte [] c=decryptByPrivateKey(b, privatekey);
		 System.out.println("用私钥解密后:"+new String(c).trim());
	} catch (Exception e) {
		e.printStackTrace();
	}

 }
}
