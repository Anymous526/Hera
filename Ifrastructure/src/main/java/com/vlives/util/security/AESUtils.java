/**
 * @(#)AESUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES对称加密算法
 * @author  jianguo.xu
 * @version 1.0,2011-7-13
 */
public class AESUtils {
	/**
	 * 加密算发
	 */
	public static final String AES_ALGORITHM="AES";
	/**
	 * 密钥长度
	 * 如果采用192或256必须下载jce
	 * jce地址：https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewProductDetail-Start?ProductRef=jce_policy-6-oth-JPR@CDS-CDS_Developer
	 * 下载后，解压，把解压后的local_policy.jar文件和US_export_policy.jar放到你的程序所使用的jre下的安全目录下，如：%jre%/lib/security
	 */
	public static final int AES_KEY_LENGTH=128;
	/**
	 * 加密、机密算法/工作模式/填充方式
	 */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	/**
	 * 生成二进制密钥
	 * @author jianguo.xu
	 * @return
	 */
	public static byte[] createAESKey() {
		KeyGenerator generator = null;
		try {
			generator = KeyGenerator.getInstance(AES_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		generator.init(AES_KEY_LENGTH);
		SecretKey  secretKey  = generator.generateKey();
		return secretKey.getEncoded();
	}
	/**
	 * 生成经过base64编码后的密钥字符串<br/>
	 * 改字符串可以作为url参数传递
	 * @author jianguo.xu
	 * @return
	 */
	public static String createDESKeyStr() {
		byte[] keys = createAESKey();
		return Base64.encodeBase64URLSafeString(keys);
	}
	/**
	 * 转换密钥
	 * 把二进制密钥转换成Key对象
	 * @author jianguo.xu
	 * @param keys
	 * @return
	 */
	public static Key parserKey(byte[] keys){
		return new SecretKeySpec(keys, AES_ALGORITHM);
		 
	}
	/**
	 * 转换密钥
	 * 把经过BASE64编码后的加密密码换成Key对象
	 * @author jianguo.xu
	 * @param base64Key
	 * @return
	 */
	public static Key parserKey(String base64Key) {
		byte[] keys = Base64.decodeBase64(base64Key);
		return parserKey(keys);
	}
	
	/**
	 * 对数据加密
	 * @author jianguo.xu
	 * @param data 需要加密的数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return 二进制加密数据
	 */
	public static byte[] encrypt(String data,String base64Key)  {
		Key key = parserKey(base64Key);
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(data.getBytes(SecurityPropertiesUtils.SECURITY_ENCODE));
		} catch (Exception e) {
			throw new RuntimeException("encrypt data error.",e);
		}
		
	}
	
	/**
	 * 对数据加密
	 * @author jianguo.xu
	 * @param data 需要加密的数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return 经过base64编码后的加密数据  该数据可以作为url的参数传递
	 */
	public static String encryptToBase64String(String data,String base64Key)  {
		byte[] encryptBytes = encrypt(data,base64Key);
		return Base64.encodeBase64URLSafeString(encryptBytes);
	}
	
	/**
	 * 解密
	 * @author jianguo.xu
	 * @param encryptData 加密数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return
	 */
	public static byte[] decrypt(byte[] encryptData,String base64Key)  {
		Key key = parserKey(base64Key);
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(encryptData);
		} catch (Exception e) {
			throw new RuntimeException("decrypt data error.",e);
		}
		
	}
	/**
	 * 解密
	 * @author jianguo.xu
	 * @param encryptData 加密数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return
	 */
	public static String decrypt(String encryptToBase64String,String base64Key) {
		byte[] encryptData = Base64.decodeBase64(encryptToBase64String);
		byte[] decryptData = decrypt(encryptData,base64Key);
		try {
			return new String(decryptData, SecurityPropertiesUtils.SECURITY_ENCODE);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("encoding data error.",e);
		}
	}
	
	/*public static void main(String[] args) {
		String key = AESUtils.createDESKeyStr();
		//key = "28X0swNE5DqgPbXee_KYhg";
		System.out.println(key);
		String value="aes";
		String encValue = AESUtils.encryptToBase64String(value, key);
		System.out.println(encValue);
		String decValue = AESUtils.decrypt(encValue, key);
		System.out.println(decValue);
	}*/
}
