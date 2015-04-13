/**
 * @(#)KeyStoreParam.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * java keytool 命令参数的封装
 * 通过 该类的toString() 返回最终的命令字符串格式
 * @author  jianguo.xu
 * @version 1.0,2010-11-30
 */
public class KeyStoreParam {
	
	/**
	 * @param alias 证书别名
	 * @param cn 名字与姓氏
	 * @param storepass 密码
	 * @param fileName keystore文件名(完整路径) 
	 */
	public static KeyStoreParam getInsTanceByGenkey(String alias,String cn,String storepass,String fileName) {
		KeyStoreParam keyStoreParam = new KeyStoreParam();
		keyStoreParam.CN = cn;
		keyStoreParam.alias = alias;
		keyStoreParam.storepass = storepass;
		keyStoreParam.keystore = fileName;
		keyStoreParam.keyStoreType = KeyStoreType.GENKEYPAIR;
		return keyStoreParam;
	}
	/**
	 * java keytool 导出证书操作命令参数 
	 * @author jianguo.xu
	 * @param alias  证书别名
	 * @param storepass 证书密码
	 * @param keystore 证书存储位置
	 * @param cerFile 证书文件
	 * @return  
	 */
	public static KeyStoreParam getInsTanceByExportcert(String alias,String storepass,String keystore,String cerFile) {
		KeyStoreParam keyStoreParam = new KeyStoreParam();
		keyStoreParam.alias = alias;
		keyStoreParam.storepass = storepass;
		keyStoreParam.keystore = keystore;
		keyStoreParam.cerFile = cerFile;
		keyStoreParam.keyStoreType = KeyStoreType.EXPORTCERT;
		return keyStoreParam;
	}
	
	 
	/**
	 * 证书文件
	 */
	private String cerFile;
	
	/**
	 * keytool命令类型
	 */
	private KeyStoreType keyStoreType;
	/**
	 * 指定非对称加密密钥算法
	 */
	private String keyalg = SecurityPropertiesUtils.ASYMMETRIC_ENCRYPTION_ALGORITHM;
	/**
	 * 密钥长度
	 */
	private int keysize = 2048;
	/**
	 * 指定数字签名算法
	 */
	private String sigalg = SecurityPropertiesUtils.DIGITAL_SIGN_ALGORITHM;
	/**
	 * 证书有效期
	 */
	private int validity = SecurityPropertiesUtils.CERT_MAX_VALIDITY;
	/**
	 * 证书别名
	 */
	private String alias;
	/**
	 * 证书存储位置
	 */
	private String keystore;
	/**
	 * 证书密码
	 */
	private String storepass;
	/**
	 * 名字与姓氏
	 */
	private String CN;
	/**
	 * 组织单位名称
	 */
	private String OU=SecurityPropertiesUtils.ORGANIZATIONALUNIT;
	/**
	 * 组织名称
	 */
	private String O=SecurityPropertiesUtils.ORGANIZATION;
	/**
	 * 城市或区域
	 */
	private String L=SecurityPropertiesUtils.LOCATION;
	/**
	 * 州或省份
	 */
	private String ST=SecurityPropertiesUtils.STATE;
	/**
	 * 两个字母的国家代码
	 */
	private String C=SecurityPropertiesUtils.COUNTRY;
	
	public Map<String,Object> getParamMap() {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(keyStoreType==KeyStoreType.GENKEYPAIR) {
			paramMap.put(keyStoreType.getInstruct(), null);
			paramMap.put("-keyalg",keyalg);
			paramMap.put("-keysize",keysize);
			paramMap.put("-sigalg",sigalg);
			paramMap.put("-validity",validity);
			paramMap.put("-alias",alias);
			paramMap.put("-keystore",keystore);
			paramMap.put("-storepass",storepass);
			paramMap.put("-keypass",storepass);
			paramMap.put("-dname","\"CN="+CN+", OU="+OU+", O="+O+",L="+L+", ST="+ST+", C="+C+"\"");
		}
		if(keyStoreType==KeyStoreType.EXPORTCERT) {
			paramMap.put(keyStoreType.getInstruct(), null);
			paramMap.put("-trustcacerts",null);
			paramMap.put("-rfc",null);
			paramMap.put("-alias",alias);
			paramMap.put("-file",cerFile);
			paramMap.put("-keystore",keystore);
			paramMap.put("-storepass",storepass);
		}
		return paramMap;
	}
	/**
	 * 返回keytool命令的字符串
	 */
	public String toString() {
		Map<String,Object> paramMap = getParamMap();
		StringBuilder sb = new StringBuilder();
		for(String key : paramMap.keySet()) {
			sb.append(key).append(" ");
			Object value = paramMap.get(key);
			if(value!=null) sb.append(value).append(" ");
		}
		return sb.toString().trim();
	}
	
	/**
	 * keytool 具体命令类型
	 * @author tiger
	 *
	 */
	public static enum KeyStoreType{
		/**
		 * 生产密钥
		 */
		GENKEYPAIR("genkeypair","-genkeypair","表示生成密"),
		/**
		 * 证书导出
		 */
		EXPORTCERT("exportcert","-exportcert","表示证书导出操作");
		/**
		 * 命令值
		 */
		private final String value;
		/**
		 * 命令指令
		 */
		private final String instruct;
		/**
		 * 描述
		 */
		private final String desc;
		private KeyStoreType(String value, String instruct, String desc) {
			this.value = value;
			this.instruct = instruct;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getInstruct() {
			return instruct;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	/*public static void main(String[] args) throws InterruptedException {
		
		//KeyStoreParam keyStoreParam = KeyStoreParam.getInsTanceByGenkey("001","000000","tiger.keystore");
		KeyStoreParam keyStoreParam = KeyStoreParam.getInsTanceByExportcert("001", "000000", "tiger.keystore", "tiger.cer");
		System.out.println(keyStoreParam.toString());
		Runtime rt = Runtime.getRuntime();
		try {
			Process process = rt.exec("keytool "+keyStoreParam.toString());
			InputStream err = process.getErrorStream();
			InputStream in = process.getInputStream();

			BufferedReader errReader = new BufferedReader(
					new InputStreamReader(err));
			BufferedReader inReader = new BufferedReader(new InputStreamReader(in));

			String inLine = null;
			String errLine = null;
			try {
				while ((inLine = inReader.readLine()) != null) {
					System.out.println(inLine);
				}
				while ((errLine = errReader.readLine()) != null) {
					System.out.println(errLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			process.waitFor();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}*/
	
}
