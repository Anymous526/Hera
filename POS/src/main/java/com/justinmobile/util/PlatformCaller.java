/**
 * @(#)ConstantUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.justinmobile.util.HttpClientUtil.MethodType;

/**
 * 云掌柜接口调用类
 * 
 * @author wei.zhang
 * 
 */
public final class PlatformCaller {

	/**
	 * 云掌柜HTTP接口
	 * 
	 */
	public static enum HttpAddress {
		/**
		 * 测试例子
		 */
		CREATE_MEMBER_SMS("create_member_sms");
		
		private String interfaceName;
		private String subUrl;
		private String method;

		HttpAddress(String name) {
			this.interfaceName = name;
			String interfaceInfo = propertiesMap.get(name);
			String params[] = interfaceInfo.split(":");
			if (params.length < 2) {
				throw new RuntimeException("Illegal argument found.");
			}
			this.subUrl = params[0];
			this.method = params[1];
		}

		public String getInterfaceName() {
			return interfaceName;
		}

		public String getSubUrl() {
			return subUrl;
		}

		public void setSubUrl(String subUrl) {
			this.subUrl = subUrl;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

	}

	/**
	 * 统一调用接口的方法：CloudBossCaller.Interface.接口名.invoke(headers, params)
	 * 
	 * @param headers
	 *            请求时头信息参数map
	 * @param params
	 *            请求时一般参数map
	 * @return json对象
	 * @throws ClientProtocolException
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject invoke(Map<String, Object> headers, Map<String, Object> params, HttpAddress type)
			throws ClientProtocolException, IOException {
		MethodType methodType = null;
		String method = type.method;
		methodType = getMethodType(type, method);
		return HttpClientUtil.getResponseByJson(baseUrl + type.getSubUrl(), headers, params, methodType);
	}

	public static void invokeMulti(Map<String, Object> headers, Map<String, Object> params, HttpAddress type,
			ResponseHandler<String> handler) throws ClientProtocolException, IOException {
		MethodType methodType = null;
		String method = type.method;
		methodType = getMethodType(type, method);
		HttpClientUtil.requestMulti(baseUrl + type.getSubUrl(), headers, params, methodType, handler);
	}

	private static MethodType getMethodType(HttpAddress type, String method) {
		MethodType methodType = null;
		if (method.equalsIgnoreCase("GET")) {
			methodType = MethodType.GET;
		} else if (method.equalsIgnoreCase("POST")) {
			methodType = MethodType.POST;
		} else if (method.equalsIgnoreCase("PUT")) {
			methodType = MethodType.PUT;
		} else if (method.equalsIgnoreCase("DELETE")) {
			methodType = MethodType.DELETE;
		} else {
			throw new IllegalArgumentException(type.interfaceName + "has a illegal http method type.");
		}
		return methodType;
	}

	/**
	 * 统一调用接口的方法：CloudBossCaller.Interface.接口名.invokeFile(headers, params,file)
	 * 
	 * @param headers
	 *            请求时头信息参数map
	 * @param params
	 *            请求时一般参数map * @param params 上传文件
	 * @return json对象
	 * @throws ClientProtocolException
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject invokeFile(Map<String, Object> headers, Map<String, Object> params, File file,
			HttpAddress type) throws ClientProtocolException, IOException {
		return HttpClientUtil.getResponseByFile(baseUrl + type.subUrl, headers, params, file);
	}

	private static String propertiesFile = "/config/playform.properties";

	private static final Log LOG = LogFactory.getLog(PlatformCaller.class);

	private static Map<String, String> propertiesMap;

	private static String baseUrl;

	static {
		initProperty();
		baseUrl = propertiesMap.get("baseUrl");
		if (baseUrl == null) {
			throw new RuntimeException("the property baseUrl can not be NULL.");
		}
	}

	private static void initProperty() {
		InputStream ins = null;
		Properties properties = new Properties();
		try {
			ins = PlatformCaller.class.getResourceAsStream(propertiesFile);
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

	public static String getCloudbossCookieDomain() {
		try {
			URL url = new URL(baseUrl);
			return url.getHost();
		} catch (MalformedURLException e) {
			return "127.0.0.1";
		}
	}

	public static String getCloudbossCookieName() {
		String value = propertiesMap.get("cloudbossCookieName");
		return value == null ? "auto_login" : value;
	}

	public static String getCloudbossCookieValue() {
		String value = propertiesMap.get("cloudbossCookieValue");
		return value == null ? "MSYxMzg4MDI4NDE3Mg" : value;
	}

	public static String getCloudbossCookiePath() {
		String value = propertiesMap.get("cloudbossCookiePath");
		return value == null ? "/" : value;
	}

 

	public static boolean getSysSmsTemplate() {
		String value = propertiesMap.get("sys.sms.template");
		return value == null ? true : Boolean.parseBoolean(value);
	}

	public static boolean getCheckAppVersion() {
		String value = propertiesMap.get("check.app.version");
		return value == null ? true : Boolean.parseBoolean(value);
	}

	public static String getManagerPlatformUrl() {
		return propertiesMap.get("manage.paltform.url");
	}

	public static String getCloudPosPathUrl() {
		return propertiesMap.get("cloudpos.url");
	}

	/**
	 * pos创建会员
	 */
	public static void createPosMemberSms(Long memberId) {
		if(memberId == null) return;
		String uri = "/pos/sms/?memberId="+memberId+"&method=createMember";
		HttpClientUtils.doGet(getCloudPosPathUrl()+uri);
	}

	/**
	 * pos交易
	 * 
	 */
	public static void posTradeSms(Long tradeDetailId) {
		if(tradeDetailId== null) return;
		String uri = "/pos/sms/?tradeDetailId="+tradeDetailId+"&method=posTrade";
		HttpClientUtils.doGet(getCloudPosPathUrl()+uri);
	}

}
