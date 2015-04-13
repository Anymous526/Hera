/**
 * @(#)ConstantUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ThinkPad7
 * 
 */
public final class ConstantUtils {

	private static String propertiesFile = "/config/constant.properties";

	private static final Log LOG = LogFactory.getLog(ConstantUtils.class);

	private static Map<String, String> propertiesMap;

	static {
		initProperty();
	}

	private static void initProperty() {
		InputStream ins = null;
		Properties properties = new Properties();
		try {
			ins = ConstantUtils.class.getResourceAsStream(propertiesFile);
			properties.load(ins);
			propertiesMap = new HashMap<String, String>();
			Set<Entry<Object, Object>> entrySet = properties.entrySet();

			for (Entry<Object, Object> entry : entrySet) {
				propertiesMap.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (FileNotFoundException e) {
			LOG.info("file not found." + propertiesFile, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOG.info(e);
			throw new RuntimeException(e);
		}

	}

	public static String getSocketEncode() {
		String value = propertiesMap.get("socket_encode");
		return value == null ? "GBK" : value;
	}

	public static String getCpayXmlProtocolIp() {
		return propertiesMap.get("SM_ADMIN_SOCKET_ADDRESS").split(":")[0];
	}

	public static int getCpayXmlProtocolPort() {
		return Integer.parseInt(propertiesMap.get("SM_ADMIN_SOCKET_ADDRESS").split(":")[1]);
	}

	public static boolean getCpayXmlProtocolUseSSL() {
		return Boolean.parseBoolean(propertiesMap.get("SM_ADMIN_SOCKET_ADDRESS").split(":")[2]);
	}

	public static String getCpayBinaryProtocolIp() {
		return propertiesMap.get("HS8583_BINARY_PROTOCOL_SOCKET_ADDRESS").split(":")[0];
	}

	public static int getCpayBinaryProtocolPort() {
		return Integer.parseInt(propertiesMap.get("HS8583_BINARY_PROTOCOL_SOCKET_ADDRESS").split(":")[1]);
	}

	public static boolean getCpayBinaryProtocolUseSSL() {
		return Boolean.parseBoolean(propertiesMap.get("HS8583_BINARY_PROTOCOL_SOCKET_ADDRESS").split(":")[2]);
	}

	

	public static boolean getTestJunitMode() {
		return Boolean.parseBoolean("TEST_JUNIT_MODE");
	}

	public static void main(String args[]) {
	
	}
}
