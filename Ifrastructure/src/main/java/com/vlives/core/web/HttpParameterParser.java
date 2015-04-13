/*
 * @(#)HttpParameterParser.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.vlives.core.web;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vlives.util.ClassUtils;
import com.vlives.util.StringUtils;



/**
 * description
 * 
 * @author jianguo
 * @version 1.0,2009-6-6
 */
public class HttpParameterParser {
	private static final Log LOG = LogFactory.getLog(HttpParameterParser.class);
	private static  DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	public static void setDateFormat(String format) {
		DEFAULT_DATE_FORMAT = new SimpleDateFormat(format);
	}
	
	private static Map<String,String[]> parameters;

	@SuppressWarnings("unchecked")
	public static void init(HttpServletRequest request) {
		parameters = request.getParameterMap();
	}
	
	public static Map<String, String[]> getParameters() {
		return parameters;
	}

	public static  String[] getStringArray(String key) {
		List<String> values = new ArrayList<String>();
		String[] params = parameters.get(key);
		if(params == null||params.length ==0) return values.toArray(new String[]{});
		for(String param : params) {
			if(StringUtils.isNullOrEmpty(param)) continue;
			values.add(param);
		}
		return  values.toArray(new String[]{});
	}
	
	public static String[] getStringArray(String key,String split){
		String string=getString(key);
		if(StringUtils.isNullOrEmpty(string)) return null;
		return string.split(split);
	}
	
	public static List<String> getStringList(String key){
		String[] stringArray=getStringArray(key);
		return getStringList(stringArray);
		 
	}
	
	public static List<String> getStringList(String key,String split){
		String[] stringArray=getStringArray(key,split);
		return getStringList(stringArray);
	}
	
	private static List<String> getStringList(String[] stringArray){
		if(stringArray==null||stringArray.length == 0) return null;
		List<String> result = new ArrayList<String>();
		for(int i=0;i<stringArray.length;i++)
			result.add(stringArray[i]);
		return result;
	}
	/**
	 * 根据请求参数返回int类型数组
	 * 如果参数为 null,则返回null
	 * 如果某个参数值转换失败则该参数值返回0
	 * @author jianguo.xu
	 * @param key
	 * @return
	 */
	public static int[] getIntArray(String key) {
		String[] values = getStringArray(key);
		if(values==null||values.length == 0) return null;
		int[] results = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Integer.parseInt(string);
			}
			catch (Exception e) {
				results[i] = 0; 
				LOG.info("parse int error : "+string, e);
			}
		}
		return results;
	}
	public static String getString(String key) {
		String[] values = getStringArray(key);
		if (values != null && values.length > 0)
			return values[0].trim();
		return null;
	}
	/**
	 * 当参数值为 ：true、是、yes、1 的时候返回True (不区分大小写)<br/>
	 * 当参数值为空是返回null
	 * 否则返回false
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(String key) {
		String str = getString(key);
		if(StringUtils.isNullOrEmpty(str)) return null;
		if(str.equalsIgnoreCase("true")||str.equals("1")||str.equals("是")||str.equalsIgnoreCase("yes"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	/**
	 * 当参数值为 ：true、是、yes、1 的时候返回True (不区分大小写)<br/>
	 * 否则返回false
	 * 当参数值为空时 返回 false
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(String key) {
		Boolean value = getBoolean(key);
		if(value == null) return false;
		return value.booleanValue();
	}
	/**
	 * 参数值转换为Integer
	 * 如果转换失败则返回null
	 * @author jianguo.xu
	 * @param key
	 * @return
	 */
	public static Integer getInteger(String key) {
		String str = getString(key);
		if(StringUtils.isNullOrEmpty(str)) return null;
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse int error : "+str, e);
			return null;
		}
	}
	/**
	 * 参数值转换为Integer数组
	 * 如果转换失败则返回null
	 * @author jianguo.xu
	 * @param key
	 * @return
	 */
	public static Integer[] getIntegerArray(String key){
		String[] values = getStringArray(key);
		if(values==null||values.length == 0) return null;
		Integer[] results = new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Integer.valueOf(string);
			} catch (Exception e) {
				LOG.info("parse int error : "+string, e);
				results[i] = null;
			}
		}
		return results;
	}
	/**
	 * 参数值转换为 int 
	 * 如果转换失败或参数值为 null则返回defaultValue
	 * @author jianguo.xu
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntValue(String key, int defaultValue) {
		Integer integer = getInteger(key);
		if (integer != null)
			return integer.intValue();
		return defaultValue;
	}
	/**
	 * 参数值转换为 int 
	 * 如果转换失败或参数值为 null则返回 0
	 * @author jianguo.xu
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntValue(String key) {
		return getIntValue(key, 0);
	}
	
	/**
	 * 参数值转换为 Double
	 * 如果转换失败则返回 null
	 * @author jianguo.xu
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Double getDouble(String key) {
		String str = getString(key);
		if(StringUtils.isNullOrEmpty(str)) return null;
		try {
			return Double.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse double error : "+str, e);
			return null;
		}
	}
	
	public static double getDoubleValue(String key, double defaultValue) {
		Double d = getDouble(key);
		if (d != null)
			return d.doubleValue();
		return defaultValue;
	}

	public static double getDoubleValue(String key) {
		return getDoubleValue(key, 0d);
	}
	
	
	/**
	 * 参数值转换为 Long 
	 * 如果转换失败则返回 null
	 * @author jianguo.xu
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Long getLong(String key) {
		String str = getString(key);
		if(StringUtils.isNullOrEmpty(str)) return null;
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse long error : "+str, e);
			return null;
		}
	}

	public static long getLongValue(String key, long defaultValue) {
		Long l = getLong(key);
		if (l != null)
			return l.longValue();
		return defaultValue;
	}

	public static long getLongValue(String key) {
		return getLongValue(key, 0);
	}
	
	/**
	 * 参数值转换为Long数组
	 * 如果转换失败则返回null
	 * @author jianguo.xu
	 * @param key
	 * @return
	 */
	public static Long[] getLongWrapperArray(String key){
		String[] values = getStringArray(key);
		if(values==null||values.length == 0) return null;
		Long[] results = new Long[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Long.valueOf(string);
			} catch (Exception e) {
				LOG.info("parse int error : "+string, e);
				results[i] = null;
			}
		}
		return results;
	}
	
	/**
	 * 根据请求参数返回long类型数组
	 * 如果参数为 null,则返回null
	 * 如果某个参数值转换失败则该参数值返回0
	 * @author jianguo.xu
	 * @param key
	 * @return
	 */
	public static long[] getLongArray(String key) {
		String[] values = getStringArray(key);
		if(values==null||values.length == 0) return null;
		long[] results = new long[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Long.parseLong(string);
			}
			catch (Exception e) {
				results[i] = 0; 
				LOG.info("parse int error : "+string, e);
			}
		}
		return results;
	}

	public static Short getShort(String key) {
		String str = getString(key);
		if(StringUtils.isNullOrEmpty(str)) return null;
		try {
			return Short.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse short error : "+str, e);
			return null;
		}
	}
	
	public static Short[] getShortArray(String key){
		String[] values = getStringArray(key);
		if(values==null||values.length == 0) return null;
		Short[] results = new Short[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Short.valueOf(string);
			} catch (Exception e) {
				LOG.info("parse short error : "+string, e);
				results[i] = null;
			}
		}
		return results;
	}
	
	public static short getShortValue(String key, short defaultValue) {
		Short s = getShort(key);
		if (s != null)
			return s.shortValue();
		return defaultValue;
	}

	public static short getShortValue(String key) {
		return getShortValue(key, (short) 0);
	}
	/**
	 * 参数值转换为日期 
	 * @author jianguo.xu
	 * @param key 
	 * @param format 日期格式:如果为null则采用默认的日期格式"yyyy-MM-dd"
	 * @return
	 */
	public static Date getDate(String key, String format) {
		Date date = null;
		String str = getString(key);
		if(StringUtils.isNullOrEmpty(str)) return null;
		DateFormat dateFormat = format == null ? DEFAULT_DATE_FORMAT
				: new SimpleDateFormat(format);
		try {
			date = dateFormat.parse(str);
		} catch (Exception e) {
			LOG.info("parse date error : "+str, e);
			return null;
		}
		return date;
	}
	/**
	 * 参数值转换为日期 (采用默认的日期格式"yyyy-MM-dd")
	 * @author jianguo.xu
	 * @param key 
	 * @return
	 */
	public static Date getDate(String key) {
		return getDate(key, null);
	}

	public static java.sql.Date getSqlDate(String key, String format) {
		Date date = getDate(key, format);
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	public static java.sql.Date getSqlDate(String key) {
		return getSqlDate(key, null);
	}

	public static BigDecimal getBigDecimal(String key) {
		String value = getString(key);
		if(StringUtils.isNullOrEmpty(value)) return null;
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			LOG.info("parse date error : "+value, e);
			return null;
		}
	}
	
	public static Calendar getCalendar(String key) {
		Date date = getDate(key);
		if (date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	/**
	 * 不推荐使用
	 * @author jianguo.xu
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@Deprecated
	public static<T> T getObject(Class<T> clazz) {
		T object =  ClassUtils.instanceObject(clazz);
		Map<Method,Field> map = ClassUtils.paraserSet(clazz);
		for(Method method : map.keySet()) {
			 Field field = map.get(method);
			 Object requestVlaue = getRequestValue(field);
			 if(requestVlaue == null) continue;
			 try {
				method.invoke(object, requestVlaue);
			} catch (Exception e) {
				LOG.info("method invoke error.",e);
				continue;
			}
		}
		return object;
	}
	
	private static Object getRequestValue(Field field) {
		 String fieldType = field.getType().getName();
		 String fieldName = field.getName();
		 
		 if (fieldType.equals("java.lang.String")) {
			 return getString(fieldName);
		 }
		 if (fieldType.equals("long")
					|| fieldType.equals("java.lang.Long")) {
			 return getLong(fieldName);
		 }
		 if (fieldType.equals("int")
					|| fieldType.equals("java.lang.Integer")) {
			 return getInteger(fieldName);
		 }
		 
		 if (fieldType.equals("short")
					|| fieldType.equals("java.lang.Short")) {
			 return getShort(fieldName);
		 }
		 if (fieldType.equals("double")
					|| fieldType.equals("java.lang.Double")) {
			 return getDouble(fieldName);
		 }
		 if (fieldType.equals("boolean")
					|| fieldType.equals("java.lang.Boolean")) {
			 return getBoolean(fieldName);
		 }
		 if (fieldType.equals("java.math.BigDecimal")) {
			 return getBigDecimal(fieldName);
		 }
		 if (fieldType.equals("java.sql.Date")) {
			 return getSqlDate(fieldName);
		 }
		 if (fieldType.equals("java.util.Date")) {
			 return getDate(fieldName);
		 }
		 if (fieldType.equals("java.util.Calendar")) {
			 return getCalendar(fieldName);
		 }
		 
		 return null;
	}
	
	
	 
}
