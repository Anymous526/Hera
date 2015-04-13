package com.justinmobile.bmp.common.exception;

import org.apache.commons.lang.StringUtils;

public class PlatformException extends RuntimeException {

	private static final long serialVersionUID = 7946023196149777499L;

	protected PlatformErrorCode errorCode;

	protected String realCode;
	
	private String message;

	public PlatformErrorCode getErrorCode() {
		return errorCode;
	}

	public PlatformException(PlatformErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
	}

	public PlatformException(PlatformErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
	}

	public PlatformException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		if(this.message != null){
			return this.message;
		}
		String notMessage = "not error, not message";
		if (errorCode == null || StringUtils.isBlank(realCode)) {
			return notMessage;
		} else {
			String defaultMessage = errorCode.getDefaultMessage();
			if (StringUtils.isBlank(defaultMessage)) {
				return notMessage;
			} else {
				return defaultMessage;
			}
		}
	}

}