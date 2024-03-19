package com.gdnybank.hnm.pub.security;

import com.nantian.mfp.framework.context.MfpContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * TripleDes加解密工具
 * @author RZT
 *
 */
public class TripleDesUtils {
	private static final Log log = LogFactory
			.getLog(TripleDesUtils.class);

	//加密密钥 key size must be 16 or 24 bytes
	private static byte[] key = "GDNYBANKMSAPENCODD010404".getBytes();
	//初始化向量 IV must be 8 bytes long
	private static byte[] ivs = "NY010404".getBytes();
	public static final String ISONAME="ISO8859_1";
	public static final String CODE="UTF-8";

	static {
		if (Security.getProvider("BC") == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
	}
	TripleDesUtils(){
		key = MfpContextHolder.getSysParam("DES", "DES_KEY", "GDNYBANKMSAPENCODD010404").getBytes();
		ivs = MfpContextHolder.getSysParam("DES", "DES_IVS", "NY010404").getBytes();
	}
	/**
	 * 设置密钥
	 * @param newkey  size must be 16 or 24 bytes
	 */
	public static void setKey(byte[] newkey){
		try{
			key = newkey;
		}catch(Exception e){
			System.out.println("set key error");
		}
	}

	/**
	 * 设置向量
	 * @param newivs must be 8 bytes
	 */
	public static void setIvs(byte[] newivs){
		try{
			ivs = newivs;
		}catch(Exception e){
			System.out.println("set ivs error");
		}
	}
	/**
	 * DES解密
	 * @param srcStr 密文
	 * @return
	 */
	public static String tripleDesDecrypt(String srcStr)
	{

		SecretKeySpec desKey = new SecretKeySpec(key, "DESede");

		IvParameterSpec iv = new IvParameterSpec(ivs);

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DESede/CBC/PKCS7Padding","BC");
		} catch (NoSuchAlgorithmException e) {
			log.error("1", e);
			return null;
		} catch (NoSuchProviderException e) {
			log.error("2", e);
			return null;
		} catch (NoSuchPaddingException e) {
			log.error("3", e);
			return null;
		}

		try {
			cipher.init(Cipher.DECRYPT_MODE, desKey, iv);
		} catch (InvalidKeyException e) {
			log.error("4", e);
		} catch (InvalidAlgorithmParameterException e) {
			log.error("5", e);
		}

		byte[] cipherText = null;
		try {
			cipherText = cipher.doFinal(Base64.decode(srcStr));
		} catch (IllegalBlockSizeException e) {
			log.error("6", e);
		} catch (BadPaddingException e) {
			log.error("7", e);
		}

		try {
			return new String(cipherText,CODE);
		} catch (UnsupportedEncodingException e) {
			log.error("8", e);
			return null;
		}

	}

	/**
	 * DES加密
	 * @param srcStr 明文
	 * @return
	 */
	public static String tripleDesEncrypt(String srcStr)
	{

		SecretKeySpec desKey = new SecretKeySpec(key, "DESede");

		IvParameterSpec iv = new IvParameterSpec(ivs);

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DESede/CBC/PKCS7Padding","BC");
		} catch (NoSuchAlgorithmException e) {
			log.error("21", e);
			return null;
		} catch (NoSuchProviderException e) {
			log.error("22", e);
			return null;
		} catch (NoSuchPaddingException e) {
			log.error("23", e);
			return null;
		}

		try {
			cipher.init(Cipher.ENCRYPT_MODE, desKey, iv);
		} catch (InvalidKeyException e) {
			log.error("24", e);
		} catch (InvalidAlgorithmParameterException e) {
			log.error("25", e);
		}

		byte[] cipherText = null;
		try {
			cipherText = cipher.doFinal(srcStr.getBytes(CODE));
		} catch (IllegalBlockSizeException e) {
			log.error("26", e);
		} catch (BadPaddingException e) {
			log.error("27", e);
		} catch (UnsupportedEncodingException e) {
			log.error("28", e);
		}

		return new String(Base64.encode(cipherText));

	}


	public static void main(String[] args) throws Exception
	{

//		String urlencodestr = "skqCBoaPQphyplBhS%2FELMcfW8i6uWbuai%2FFHTSSZ1QLC%2FNLqS28jdcJIZTVJoxqXI0%2FQ5YoLVYxICQyqA7EGIG41uYp%2FPGGHXj6YyeUYS97WGqKdUzfMUJ7XgCVsG7A%2BAQ17CJYrpo72Ywf6idZFUb8ry%2FKp5cTD2kBr%2B2zCuFSasQ0YUqCZ53LqkD2p%2FEyFX66Zeisn3DGHoosgNeE9Sg%3D%3D";
//		String urldecodestr = URLDecoder.decode(urlencodestr,"UTF-8");
//		System.out.println("===="+urldecodestr);

		String str = "888888";
		String enStr = tripleDesEncrypt(str);
		String decStr = tripleDesDecrypt(enStr);

		System.out.println(enStr);
		System.out.println(decStr);
	}
}
