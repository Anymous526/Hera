/**
 * @(#)SunDesCoder.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 对称加密
 * @author  jianguo.xu
 * @version 1.0,2010-11-16
 */
public class DESUtils {
	/**
	 * 密钥算法
	 */
	public static final String DES_ALGORITHM="DES";
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	/**
	 * 生成二进制密钥
	 * @author jianguo.xu
	 * @return
	 */
	public static byte[] createDESKey() {
		KeyGenerator generator = null;
		try {
			generator = KeyGenerator.getInstance(DES_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		generator.init(56);
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
		byte[] keys = createDESKey();
		return Base64.encodeBase64URLSafeString(keys);
	}
	/**
	 * 转换密钥
	 * 把二进制密钥转换成Key对象
	 * @author jianguo.xu
	 * @param keys
	 * @return
	 * @throws InvalidKeyException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static Key parserKey(byte[] keys) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		DESKeySpec desKeySpec = new DESKeySpec(keys);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
		return keyFactory.generateSecret(desKeySpec);
	}
	/**
	 * 转换密钥
	 * 把经过BASE64编码后的加密密码换成Key对象
	 * @author jianguo.xu
	 * @param keyStr
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static Key parserKey(String base64Key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keys = Base64.decodeBase64(base64Key);
		DESKeySpec desKeySpec = new DESKeySpec(keys);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
		return keyFactory.generateSecret(desKeySpec);
	}
	/**
	 * 对数据加密
	 * @author jianguo.xu
	 * @param data 需要加密的数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return 二进制加密数据
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] encrypt(String data,String base64Key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Key key = parserKey(base64Key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(data.getBytes(SecurityPropertiesUtils.SECURITY_ENCODE));
	}
	/**
	 * 对数据加密
	 * @author jianguo.xu
	 * @param data 需要加密的数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return 经过base64编码后的加密数据  该数据可以作为url的参数传递
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptToBase64String(String data,String base64Key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] encryptBytes = encrypt(data,base64Key);
		return Base64.encodeBase64URLSafeString(encryptBytes);
	}
	/**
	 * 解密
	 * @author jianguo.xu
	 * @param encryptData 加密数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] decrypt(byte[] encryptData,String base64Key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Key key = parserKey(base64Key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(encryptData);
	}
	/**
	 * 解密
	 * @author jianguo.xu
	 * @param encryptData 加密数据
	 * @param base64Key 经过base64编码后的密钥字符串
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String decrypt(String encryptToBase64String,String base64Key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] encryptData = Base64.decodeBase64(encryptToBase64String);
		byte[] decryptData = decrypt(encryptData,base64Key);
		return new String(decryptData, SecurityPropertiesUtils.SECURITY_ENCODE);
	}
	
	/*public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String key = DESUtils.createDESKeyStr();
		System.out.println(key);
		 key = "TEXsOOY4EG0";
		System.out.println(key);
		String value="aes";
		String encValue = DESUtils.encryptToBase64String(value, key);
		System.out.println(encValue);
		String decValue = DESUtils.decrypt(encValue, key);
		System.out.println(decValue);
	}*/
}
