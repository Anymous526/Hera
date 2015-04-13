/**
 * @(#)SecurityPropertiesUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2010-12-6
 */
public final class SecurityPropertiesUtils {
	private static String propertiesFile = "/base/security/security.properties";
	private static final Log LOG = LogFactory
	.getLog(SecurityPropertiesUtils.class);
	private static Map<String, String> propertiesMap;
	static {
		initProperty(); 
	}
	private static void initProperty() {
		InputStream ins = null;
		Properties properties = new Properties();
		try {
			ins = SecurityPropertiesUtils.class
					.getResourceAsStream(propertiesFile);
			properties.load(ins);
			propertiesMap = new HashMap<String, String>();
			Set<Entry<Object, Object>> entrySet = properties.entrySet();

			for (Entry<Object, Object> entry : entrySet) {
				propertiesMap.put((String) entry.getKey(),
						(String) entry.getValue());
			}
		} catch (FileNotFoundException e) {
			LOG.error("file not found." + propertiesFile, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}
	
	private static final String getString(String key) {
		return propertiesMap.get(key);
	}
	/**
	 * 得到文件或目录的正式路径<br/>
	 * 如果文件以 "/"那么表示绝对路径，直接返回<br/>
	 * 否则表示相对于当前classpath的相对路径
	 * @author jianguo.xu
	 * @param pathKey
	 * @return
	 */
	private static final String getRealPath(String pathKey) {
		String path = propertiesMap.get(pathKey);
		if(path.startsWith("/")) {
			return path;
		}
		else {
			URL url =  SecurityPropertiesUtils.class.getResource("/"+path);
			return url.getPath();
		}
	}
	 
	
	
	private static final int getInt(String key) {
		return Integer.parseInt(propertiesMap.get(key));
	}
	
	/**
	 * keytool命令
	 */
	public static final String KEY_TOOL_CMD=getString("keytoolCommand");
	/**
	 * 服务器端密钥库别名
	 */
	public static final String ROOT_KEYSTORE_ALIAS=getString("rootKye_Alias");
	/**
	 * 服务器端密钥库-密码
	 */
	public static final String ROOT_KEYSTORE_CN=getString("rootKye_CN");
	
	/**
	 * 服务器端密钥库文件名
	 * rootKeyStoreFileName
	 */
	public static final String ROOT_KEYSTORE_FILE=getRealPath("rootKeyStoreFileName");
	/**
	 * 服务器端密钥库密码
	 * rootKeyStorePassword
	 */
	public static final String ROOT_KEYSTORE_PASSWORD=getString("rootKeyStorePassword");
	 
	/**
	 * keystore 类型
	 * rootKeyStoreType
	 */
	public static final String ROOT_KEYSTORE_TYPE=getString("rootKeyStoreType");
	 
	/**
	 * 数字签名算法
	 * digital_sign_algorithm
	 */
	public static final String DIGITAL_SIGN_ALGORITHM=getString("digital_sign_algorithm");
	/**
	 * 和安全相关的字符串编码格式
	 * encode
	 */
	public static final String SECURITY_ENCODE=getString("encode");
	/**
	 * 得到证书申请最大有效期
	 * @author jianguo.xu
	 * @return
	 */
	public static final int CERT_APPLY_MAX_VALIDITY=getInt("certificate_Apply_max_validity");
	
	/**
	 * keystore_temp_directory
	 * 得到keystore保存的临时目录
	 * @author jianguo.xu
	 * @return
	 */
	public static final String CERT_TEMP_DIRECTORY = getRealPath("certificate_temp_directory");
	 
	/**
	 * 得到证书最大有效期
	 * @author jianguo.xu
	 * @return
	 */
	public static final int CERT_MAX_VALIDITY = getInt("certificate_max_validity");
	/**
	 * 得到非对称加密算法
	 * @author jianguo.xu
	 * @return
	 */
	public static final String ASYMMETRIC_ENCRYPTION_ALGORITHM = getString("asymmetric_encryption_algorithm");
	/**
	 * 得到对称加密算法
	 * @author jianguo.xu
	 * @return
	 */
	public static final String SYMMETRIC_ENCRYPTION_ALGORITHM = getString("symmetric_encryption_algorithm");
	 
	
	/**
	 * 组织单位名称
	 * @author jianguo.xu
	 * @return
	 */
	public static final String ORGANIZATIONALUNIT = getString("OU");
	
	/**
	 * 组织名称
	 * @author jianguo.xu
	 * @return
	 */
	public static final String ORGANIZATION = getString("O");
	 
	/**
	 * 城市或区域
	 * @author jianguo.xu
	 * @return
	 */
	public static final String LOCATION  = getString("L");
	 
	/**
	 * 州或省份
	 * @author jianguo.xu
	 * @return
	 */
	public static final String STATE=getString("ST");
	/**
	 * 两个字母的国家代码
	 * @author jianguo.xu
	 * @return
	 */
	public static final String COUNTRY = getString("C");
	 
	 
}
