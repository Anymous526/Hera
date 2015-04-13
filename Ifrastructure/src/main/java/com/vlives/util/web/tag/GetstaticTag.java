/**
 * @(#)staticTag.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.web.tag;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.apache.taglibs.standard.tag.rt.core.SetTag;

import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;

/**
 * 得到静态属性和静态方法
 * 
 * @author jianguo.xu
 * @version 1.0,2011-3-17
 */
public class GetstaticTag extends SetTag {
	private static final Log LOG = LogFactory.getLog(GetstaticTag.class);
	private static final long serialVersionUID = 1L;
	private String value;
	private String var;
	private int scope = PageContext.PAGE_SCOPE;;

	public void setValue(String value) {
		this.value = value;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}

	@Override
	public int doEndTag() throws JspException {
	 
		Object objValue = parserValue();
		pageContext.setAttribute(var, objValue, scope);
		return EVAL_PAGE;
	}

	private Object parserValue() {
		
		if (!assertTrue())
			return null;
		String[] splits = value.split("@");
		String objectName = splits[0];
		String filedOrMethodString = splits[1];
		Class<?> clazz = null;
		try {
			clazz = Class.forName(objectName);
		} catch (ClassNotFoundException e) {
			LOG.error("class not found : " + objectName, e);
			return null;
		}
		if (isMethod(filedOrMethodString)) {
			return getMethoeVlaue(clazz, filedOrMethodString);
		} else {
			return getFileValue(clazz, filedOrMethodString);
		}
	}
	private Object getFileValue(Class<?> clazz, String filedString) {
		try {
			Field field = clazz.getField(filedString);
			return field.get(null);
		} catch (Exception e) {
			LOG.error("field not found : " + filedString, e);
			return null;
		}
	}
	 
	private Object getMethoeVlaue(Class<?> clazz, String methodString) {
		String methodName = methodString.split("\\(")[0];
		String[] spilts = methodString.split("\\(")[1].split("\\)");
		
	
		if(spilts.length == 0||spilts[0].trim().length() == 0) {
			try {
				Method method = clazz.getMethod(methodName);
				return method.invoke(null);
			} catch (Exception e) {
				LOG.error("method not found : " + methodName, e);
				return null;
			}
		} 
		String paramStr = spilts[0].trim();	
		String [] params = paramStr.split(",");
		Method[] methods = clazz.getMethods();
		for(Method method : methods) {
			if(!method.getName().equals(methodName)) continue;
			Class<?>[] paramTypes = method.getParameterTypes();
			if(paramTypes.length!=params.length) continue;
			List<Object> paramValues = new ArrayList<Object>();
			for(int i =0;i<paramTypes.length;i++) {
				paramValues.add(parserValue(paramTypes[i], params[i]));
			}
			try {
				return method.invoke(null, paramValues.toArray());
			} catch (Exception e) {
				continue;
			}
		}
		return null;
	}
	
	private static Object parserValue(Class<?> clazz,String value) {
		 String clazzType = clazz.getName();
		 
		 if (clazzType.equals("java.lang.String")) {
			 return value;
		 }
		 if (clazzType.equals("long")
					|| clazzType.equals("java.lang.Long")) {
			 return new Long(value);
		 }
		 if (clazzType.equals("int")
					|| clazzType.equals("java.lang.Integer")) {
			 return new  Integer(value);
		 }
		 
		 if (clazzType.equals("short")
					|| clazzType.equals("java.lang.Short")) {
			 return new Short(value);
		 }
		 if (clazzType.equals("double")
					|| clazzType.equals("java.lang.Double")) {
			 return new Double(value);
		 }
		 if (clazzType.equals("boolean")
					|| clazzType.equals("java.lang.Boolean")) {
			 return Boolean.valueOf(value);
		 }
		 if (clazzType.equals("java.math.BigDecimal")) {
			 return new BigDecimal(value);
		 }
		 if (clazzType.equals("java.sql.Date")) {
			 return DateUtils.parserShortDate(value, "yyyy-MM-dd");
		 }
		 if (clazzType.equals("java.util.Date")) {
			 return DateUtils.parserDate(value, DateUtils.DEFAULT_DATE_FORMATE);
		 }
		 if (clazzType.equals("java.util.Calendar")) {
			Date date =  DateUtils.parserDate(value, DateUtils.DEFAULT_DATE_FORMATE);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			 return  cal;
		 }
		 
		 return null;
	}
	
	/*private Object[] getMethodParams(String methodString) {
		String paramStr = methodString.split("\\(")[1].split("\\)")[0].trim();
		if(paramStr.length() == 0) return null;
		String [] paramValues = paramStr.split(",");
		return paramValues;
	}*/
	 
	
	private boolean assertTrue() {
		if (StringUtils.isNullOrEmpty(value))
			return false;
		String[] splits = value.split("@");
		if (splits.length != 2) {
			LOG.error("static type define error");
			return false;
		}
		String suffix = splits[1];
		if (suffix.indexOf("(") > 0 && suffix.indexOf(")") <= 0) {
			LOG.error("static method define error");
			return false;
		}
		return true;
	}

	private boolean isMethod(String filedOrMethodString) {
		return filedOrMethodString.indexOf("(") > 0 ? true : false;
	}

}
