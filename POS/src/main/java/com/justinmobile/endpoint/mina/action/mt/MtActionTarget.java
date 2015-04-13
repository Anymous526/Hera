/**
 * @(#)ActionTarget.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.action.mt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.justinmobile.endpoint.mina.request.MtRequestCode;

/**
 * @author ThinkPad7
 *
 */
public class MtActionTarget {
	private Class<? extends MtRequestAction> actionClazz;
	private  Field[] fields;
	private Method method;
	private MtRequestCode code;
	public MtActionTarget(Class<? extends MtRequestAction> actionClazz, Method method,
			MtRequestCode code) {		 
		this.actionClazz = actionClazz;
		this.method = method;
		this.code = code;
		setFields();
	}
	
	private void setFields() {
		this.fields = actionClazz.getDeclaredFields();
		for(Field field: fields) field.setAccessible(true);
	}
	
	public Class<? extends MtRequestAction> getActionClazz() {
		return actionClazz;
	}
	public Method getMethod() {
		return method;
	}
	public MtRequestCode getCode() {
		return code;
	}
	
	
	public Field[] getFields() {
		return fields;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MtActionTarget other = (MtActionTarget) obj;
		if (code != other.code)
			return false;
		return true;
	}
	
	
}
