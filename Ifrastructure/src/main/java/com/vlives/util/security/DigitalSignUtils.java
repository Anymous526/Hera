/**
 * @(#)DigitalSignUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.security;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-12-6
 */
public class DigitalSignUtils {
	
	private static final Log LOG = LogFactory.getLog(DigitalSignUtils.class);
	/**
	 * 数字签名
	 * @author jianguo.xu
	 * @param srcData 源数据
	 * @param privateKey 私钥
	 * @param algorithm 签名算法
	 * @return 返回经过base64编码后签名
	 * @throws Exception
	 */
	public static String signByBase64(String srcData,PrivateKey privateKey,String algorithm){
		try {
			Signature signature = Signature.getInstance(algorithm);
			signature.initSign(privateKey);
			signature.update(srcData.getBytes(SecurityPropertiesUtils.SECURITY_ENCODE));
			byte[] signaBytes = signature.sign();
			return Base64.encodeBase64String(signaBytes);
		}
		catch (Exception e) {
			throw new RuntimeException("sign error : "+srcData, e);
		}
		
	}
	/**
	 * 验证签名
	 * @author jianguo.xu
	 * @param srcData 源数据
	 * @param signByBase64 经过base64编码的签名
	 * @param publicKey 公钥
	 * @param algorithm 签名算法
	 * @return
	 * @throws Exception
	 */
	public static boolean verifySign(String srcData,String signByBase64, PublicKey publicKey,String algorithm){
		try {
			Signature signature = Signature.getInstance(algorithm);
			signature.initVerify(publicKey);
			signature.update(srcData.getBytes(SecurityPropertiesUtils.SECURITY_ENCODE));
			byte[] signaBytes = Base64.decodeBase64(signByBase64);
			return signature.verify(signaBytes);
		}
		catch (Exception e) {
			LOG.error("verify sign error : "+srcData, e);
			return false;
		}
		
	}
	
	/**
	 * 数字签名<br/>
	 * 
	 * @author jianguo.xu
	 * @param srcData 源数据
	 * @param privateKey 私钥
	 * @param algorithm 签名算法
	 * @return  返回经过base64编码后签名,该签名可以作为url参数传递
	 * @throws Exception
	 */
	public static String signByBase64ToUrl(String srcData,PrivateKey privateKey,String algorithm){
		try {
			Signature signature = Signature.getInstance(algorithm);
			signature.initSign(privateKey);
			signature.update(srcData.getBytes(SecurityPropertiesUtils.SECURITY_ENCODE));
			byte[] signaBytes = signature.sign();
			return Base64.encodeBase64URLSafeString(signaBytes);
		}
		catch (Exception e) {
			throw new RuntimeException("sign error : "+srcData, e);
		}
	}
	
	/*public static void main(String[] args) {
		String srcData = "61291798801000";
		String signByBase64 = signByBase64(srcData, KeyStoreUtils.ROOT_PRIVATE_KEY, SecurityPropertiesUtils.DIGITAL_SIGN_ALGORITHM);
		System.out.println("signByBase64 : "+signByBase64);
		boolean result = verifySign(srcData, signByBase64, KeyStoreUtils.ROOT_PUBLIC_KEY, SecurityPropertiesUtils.DIGITAL_SIGN_ALGORITHM);
		System.out.println(result);
		
		String signByBase64ToUrl = signByBase64ToUrl(srcData, KeyStoreUtils.ROOT_PRIVATE_KEY, SecurityPropertiesUtils.DIGITAL_SIGN_ALGORITHM);
		System.out.println(signByBase64ToUrl);
		
		result = verifySign(srcData, signByBase64ToUrl, KeyStoreUtils.ROOT_PUBLIC_KEY, SecurityPropertiesUtils.DIGITAL_SIGN_ALGORITHM);
		
		System.out.println(result);
	}*/
	
}
