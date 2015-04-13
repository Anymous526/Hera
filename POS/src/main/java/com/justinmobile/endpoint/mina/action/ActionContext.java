/**
 * @(#)MinaContext.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.action;

import java.util.Map;

import org.apache.mina.core.session.IoSession;


/**
 * description
 * @author  
 * @version 1.0,2010-9-25
 */
public final class ActionContext {
	/**
	 * mina 请求 iosession
	 */
	private static IoSession mtSession;
	/**
	 * mina 响应 iosession
	 */
	private static IoSession moSession;
	
	/**
	 * session属性
	 */
	private static Map<String, Object> attrubutes;
	
	private ActionContext(){}

	public static IoSession getMtSession() {
		return mtSession;
	}

	public static void setMtSession(IoSession mtSession) {
		ActionContext.mtSession = mtSession;
	}

	public static IoSession getMoSession() {
		return moSession;
	}

	public static void setMoSession(IoSession moSession) {
		ActionContext.moSession = moSession;
	}

	public static Map<String, Object> getAttrubutes() {
		return attrubutes;
	}

	public static void setAttrubutes(Map<String, Object> attrubutes) {
		ActionContext.attrubutes = attrubutes;
	}
	
	
	
}
