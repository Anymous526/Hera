/**
 * @(#)JSONUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;
import com.vlives.util.JsonConfigUtils.JsonConfig;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-1-12
 */
public class JSONUtils {
	private static final Log LOG = LogFactory.getLog(JSONUtils.class);
	 private static JSONConfig jsonConfig;

	public static final class JSONConfig {
		private static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd hh:mm:ss";
		 /**
		 * 日期格式
		 */  
		private String dateFormat = DEFAULT_DATE_FORMATE;
		 /**
		 * 要转换成json的对象包含的关联对象（可以是关联对象或关联集合）
		 */ 
		 
		public void setDateFormat(String dateFormat) {
			this.dateFormat = dateFormat;
		}
		 
	}

 

	 /**
	 * json字符串转换为对象
	 * 
	 * @author jianguo.xu
	 * @param <T>
	 * @param clazz
	 * @param jsonString
	 * @return
	 */ 
	public static <T> T converToObject(Class<T> clazz, String jsonString) {
		return converToObject(clazz, jsonString, null);
	}

	public static <T> T converToObject(Class<T> clazz, String jsonString, JSONConfig config) {
		jsonConfig = config == null?new JSONConfig():config;
		JSONObject json = JSONObject.fromObject(jsonString);
		T object = ClassUtils.instanceObject(clazz);
		Map<Method, Field> setMap = ClassUtils.paraserSet(clazz);
		for (Method method : setMap.keySet()) {
			Field field = setMap.get(method);
			Object value = json.get(field.getName());
			if (value != null)
				invokeMethod(object, method, field, value.toString());
		}
		return object;
	}

	private static void invokeMethod(Object object, Method method, Field field, String jsonValue) {
		Object value = convertValue(field, jsonValue);
		if (value == null)
			return;
		try {
			method.invoke(object, value);
		} catch (Exception e) {
			LOG.info("set field value error. field:" + field.getName() + " value:" + jsonValue, e);
		}
	}

	private static Object convertValue(Field field, String jsonValue) {
		String fieldType = field.getType().getName();
		if (StringUtils.isNullOrEmpty(jsonValue))
			return null;
		if (fieldType.equals("java.lang.String")) {
			return jsonValue;
		}
		if (fieldType.equals("long") || fieldType.equals("java.lang.Long")) {
			return new Long(jsonValue);
		}
		if (fieldType.equals("int") || fieldType.equals("java.lang.Integer")) {
			return new Integer(jsonValue);
		}

		if (fieldType.equals("short") || fieldType.equals("java.lang.Short")) {
			return new Short(jsonValue);
		}
		if (fieldType.equals("double") || fieldType.equals("java.lang.Double")) {
			return new Double(jsonValue);
		}
		if (fieldType.equals("boolean") || fieldType.equals("java.lang.Boolean")) {
			if (jsonValue.equalsIgnoreCase("true") || jsonValue.equals("1") || jsonValue.equals("是")
					|| jsonValue.equalsIgnoreCase("yes") || jsonValue.equalsIgnoreCase("y"))
				return Boolean.TRUE;
			return Boolean.FALSE;
		}
		if (fieldType.equals("java.math.BigDecimal")) {
			return new BigDecimal(jsonValue);
		}
		if (fieldType.equals("java.sql.Date")) {
			Date date = DateUtils.parserDate(jsonValue, jsonConfig.dateFormat);
			return new java.sql.Date(date.getTime());
		}
		if (fieldType.equals("java.util.Date")) {
			return DateUtils.parserDate(jsonValue, jsonConfig.dateFormat);
		}
		if (fieldType.equals("java.util.Calendar")) {
			Calendar cal = Calendar.getInstance();
			Date date = DateUtils.parserDate(jsonValue, jsonConfig.dateFormat);
			cal.setTime(date);
			return cal;
		}
		return null;
	} 
	
	/**
	 * 根据 对象和jsonconfig.xml配置得到json
	 * @author jianguo.xu
	 * @param object
	 * @param parmKey
	 * @return
	 */
	public static final String serialize(Object object, String paramKey) {
		JsonConfig jsonConfig = JsonConfigUtils.getConfig(paramKey);
		if(jsonConfig == null) {
			throw new RuntimeException("jsonconfig error. param key not found "+paramKey);
		}
		try {
			return JSONUtil.serialize(object, getExcludePropertiesList(jsonConfig), getIncludePropertiesList(jsonConfig), 
					jsonConfig.isIgnoreHierarchy(), jsonConfig.isEnumAsBean(), jsonConfig.isExcludeNullProperties());
		} catch (JSONException e) {
			LOG.error("parser json error.");
			throw new RuntimeException(e);
		}
	}
	/**
	 * please see google json-plugin JSONResult.class
	 * @author jianguo.xu
	 * @param jsonConfig
	 * @return
	 */
	private static List<Pattern> getExcludePropertiesList(JsonConfig jsonConfig) {

		List<String> excludePatterns = JSONUtil.asList(jsonConfig
				.getExcludeProperties());
		if (excludePatterns == null)
			return null;

		List<Pattern> excludeProperties = new ArrayList<Pattern>(
				excludePatterns.size());
		String pattern;
		for (Iterator<String> iterator = excludePatterns.iterator(); iterator
				.hasNext(); excludeProperties.add(Pattern.compile(pattern)))
			pattern = iterator.next();
		return excludeProperties;
	}
	/**
	 * please see google json-plugin JSONResult.class
	 * @author jianguo.xu
	 * @param jsonConfig
	 * @return
	 */
	private static List<Pattern> getIncludePropertiesList(JsonConfig jsonConfig) {
		List<String> includePatterns = JSONUtil.asList(jsonConfig
				.getIncludeProperties());
		if (includePatterns == null)
			return null;
		List<Pattern> includeProperties = new ArrayList<Pattern>(
				includePatterns.size());
		Map<String, String> existingPatterns = new HashMap<String, String>();
		for (Iterator<String> iterator = includePatterns.iterator(); iterator
				.hasNext();) {
			String pattern = (String) iterator.next();
			String patternPieces[] = pattern.split("\\\\\\.");
			String patternExpr = "";
			String as[];
			int j = (as = patternPieces).length;
			for (int i = 0; i < j; i++) {
				String patternPiece = as[i];
				if (patternExpr.length() > 0)
					patternExpr = (new StringBuilder(
							String.valueOf(patternExpr))).append("\\.")
							.toString();
				patternExpr = (new StringBuilder(String.valueOf(patternExpr)))
						.append(patternPiece).toString();
				if (!existingPatterns.containsKey(patternExpr)) {
					existingPatterns.put(patternExpr, patternExpr);
					if (patternPiece.endsWith("\\]")) {
						includeProperties
								.add(Pattern.compile(patternExpr.substring(0,
										patternPiece.lastIndexOf("\\["))));
						if (LOG.isDebugEnabled())
							LOG.debug((new StringBuilder(
									"Adding include property expression:  "))
									.append(patternExpr.substring(0,
											patternPiece.lastIndexOf("\\[")))
									.toString());
					}
					includeProperties.add(Pattern.compile(patternExpr));
					if (LOG.isDebugEnabled())
						LOG.debug((new StringBuilder(
								"Adding include property expression:  "))
								.append(patternExpr).toString());
				}
			}
		}
		return includeProperties;
	}
 
	
}
