package com.gdnybank.hnm.pub.security;

public class generateKeyParis {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		RSAKeyPairUtil rsaKeyPairUtil = new RSAKeyPairUtil(true);
		String strPublicHexKey = rsaKeyPairUtil.getPublicHexKey();
		String strPrivateHexKey = rsaKeyPairUtil.getPrivateHexKey();

		System.out.println("publicHexKey:" + strPublicHexKey);
		System.out.println("privateHexKey:" + strPrivateHexKey);

	}

}
