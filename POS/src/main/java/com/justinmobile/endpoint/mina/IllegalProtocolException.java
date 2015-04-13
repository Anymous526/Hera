/**
 * @(#)IllegalProtocolException.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina;

/**
 * @author ThinkPad7
 *
 */
public class IllegalProtocolException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalProtocolException() {
		super();
	}

	public IllegalProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalProtocolException(String message) {
		super(message);
	}

	public IllegalProtocolException(Throwable cause) {
		super(cause);
	}
	
}
