package com.gdnybank.hnm.pub.security;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EncipherUtil_Ft {

	/**
	 * 加密字符串
	 *
	 * @param FT_CORPID
	 *            系统编号
	 * @param vName
	 *            原串变量
	 * @param vValue
	 *            原串参数
	 * @param encryptKey
	 *            密钥
	 * @return String 密文串
	 */
	public final static String TIME_PATTERN = "HHmmss";
	public final static String DATE_PATTERN = "";

	/**
	 * 加密字符串，适用于金融瓦片
	 *
	 * @param strSrc
	 *            源串
	 * @param FT_CORPID
	 *            系统编号
	 * @param encryptKey
	 *            密钥
	 * @return String 加密串
	 * @throws Exception
	 * @since 20190918
	 */
	public static String encipherWithRSASignandAES(String strSrc, String publicHexKey, String privateHexKey) throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String strDateTime = sdfDateTime.format(new Date());

		System.out.println("===>EncipherUtil_Ft.encipherWithRSASignandAES输入参数strSrc:"+strSrc);
		System.out.println("===>EncipherUtil_Ft.encipherWithRSASignandAES输入参数publicHexKey:"+publicHexKey);
		System.out.println("===>EncipherUtil_Ft.encipherWithRSASignandAES输入参数privateHexKey:"+privateHexKey);
		String strAssembled = strSrc + "&SYS_TIME=" + strDateTime;

		System.out.println("===>EncipherUtil_Ft.encipherWithRSASignandAES参数strAssembled:"+strAssembled);
		String strSign = SHA1withRSADigitalSignatureUtil.genSha1Sign(strAssembled, privateHexKey);
		System.out.println("===>EncipherUtil_Ft.encipherWithRSASignandAES参数strSign:"+strSign);

		strAssembled = strAssembled + "&SIGN=" + strSign;
		System.out.println("===>EncipherUtil_Ft.encipherWithRSASignandAES参数strAssembled:"+strAssembled);

		String encipheredResult = AESUtil.encrypt(strAssembled, publicHexKey.substring(publicHexKey.length() - 32));
		System.out.println("===>EncipherUtil_Ft.encipherWithRSASignandAES参数encipheredResult:"+encipheredResult);

		return encipheredResult;
	}

	public static void main_1(String[] args) throws Exception {
		String strSrc = "china counstruciton bank,ccb,中国建设银行";
		System.out.println("strSrc:" + strSrc);

		RSAKeyPairUtil rsaKeyPairUtil = new RSAKeyPairUtil(true);
		String strPublicHexKey = rsaKeyPairUtil.getPublicHexKey();
		String strPrivateHexKey = rsaKeyPairUtil.getPrivateHexKey();

		System.out.println("publicHexKey:" + strPublicHexKey);
		System.out.println("privateHexKey:" + strPrivateHexKey);


		String strEncrypted = EncipherUtil_Ft.encipherWithRSASignandAES(strSrc,strPublicHexKey, strPrivateHexKey);
		System.out.println("strEncrypted:" + strEncrypted);

		String strDecrypted = DecipherUtil_Ft.decipherWithRSASignandAES(strEncrypted, strPublicHexKey);
		System.out.println("strDecrypted:" + strDecrypted);
	}

	public static void main(String[] args) throws Exception {
		String strSrc = "china counstruciton bank,ccb,中国建设银行";
		System.out.println("strSrc:" + strSrc);

		//只初始化，不生成公私钥
		RSAKeyPairUtil rsaKeyPairUtil = new RSAKeyPairUtil(false);

		//设置公私钥
		rsaKeyPairUtil.setPublicHexKey("305c300d06092a864886f70d0101010500034b0030480241009e2c6173f1d288d777b1712d403ddeb49c476d6f93a60513f86ff159880422d644694fb502353fb9e5dcf600b6e17131317514c3962ef95854643b84bbd7e2570203010001");
		rsaKeyPairUtil.setPrivateHexKey("30820153020100300d06092a864886f70d01010105000482013d308201390201000241009e2c6173f1d288d777b1712d403ddeb49c476d6f93a60513f86ff159880422d644694fb502353fb9e5dcf600b6e17131317514c3962ef95854643b84bbd7e2570203010001024019005cdb0dd3406c067056ba575830368e2940240dcb852bf5ee03d12a0db54fb73b4f5aaf99c4bebe4da2774eff2fb025defcb9a87203e59adb69a1a27f44a9022100d6a6fcabefe309f9fea987d06a13e445d4d6328d69b87b351cdc9b94330b4553022100bca448a7f9de7255312831199a2aa06f2a8641440edd7015da75fe11cb6d6a6d0220638e378a974c3a0854f0d428f9ca1809bb594d410153aaf14b5d965afa01b43902207c1739b03ddf45a8a2e0848409f4f67a8a27acb13f720d3462f3f140f567b17502201502ba5b0a914b14ad377de7e960d91d239705c6e645c181e2147c746c14accb");

		String strPublicHexKey = rsaKeyPairUtil.getPublicHexKey();
		String strPrivateHexKey = rsaKeyPairUtil.getPrivateHexKey();

		System.out.println("publicHexKey:" + strPublicHexKey);
		System.out.println("privateHexKey:" + strPrivateHexKey);

//		String strEncrypted = EncipherUtil_Ft.encipherWithRSASignandAES(strSrc,strPublicHexKey, strPrivateHexKey);
//		System.out.println("strEncrypted:" + strEncrypted);

		String jdk18EncryptedStr = "bdXQ4Fhix+So630p7BC5Oi4M21x6yh2Oa/xQgZLJvqM9PnRoVuwBa/vl1NtR12trMlNuFpPF8K1daJy6ZBMMpFbu0xlap4oIfruWzmZfpl/KxzJF0pD/NO66HuMAj4r44coMDPvE4L2O0/sxLGlmqMAhIAsFTIVI4clpjSAM5pmMSXKooOP5mvAcSvu3luE5g3+oulksjU56u4M5v7maNkImpTo3tF1OadCANDodr3f83J6dXXRADv70fcquVrNk1ueFrlwUar2lTrWJdw8qKPvZD7wUNZ2P3kRlfJf/MKdUVeO24V6BKOZPZMmIgtxcVF2OyEmQCexiGSnejQFt0rS/unAaJb1NkL+0vVqiqPc=";

		String strDecrypted = DecipherUtil_Ft.decipherWithRSASignandAES(jdk18EncryptedStr, strPublicHexKey);
		System.out.println("strDecrypted:" + strDecrypted);
	}
}
