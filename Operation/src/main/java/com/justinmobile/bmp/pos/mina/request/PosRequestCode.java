/**
 * @(#)RequestCode.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.bmp.pos.mina.request;


/**
 * posp交易请求类型
 * @author ThinkPad7
 *
 */
public enum PosRequestCode {
	
	
	APP_VERSION_RESP("00","回响交易"),
	APP_VERSION_DOWNLOAD("01","应用版本下载更新"),
	;	
	private final String code;
	private final String desc;
	
	private PosRequestCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}


	public static PosRequestCode get(String code) {
		for (PosRequestCode requestCode : PosRequestCode.values()) {
			if (requestCode.getCode().equals(code))
				return requestCode;
		}
		throw new IllegalArgumentException("request code is not exist : " + code);
	}
}
