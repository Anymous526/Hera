/**
 * @(#)ConnectorProcessException.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina;

/**
 * @author ThinkPad7
 *
 */
public class ConnectorProcessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectorProcessException() {
		super();
	}

	public ConnectorProcessException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ConnectorProcessException(String s) {
		super(s);
	}

	public ConnectorProcessException(Throwable throwable) {
		super(throwable);
	}
	
	

}
