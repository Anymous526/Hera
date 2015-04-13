package com.vlives.core.exception;

import org.apache.commons.lang.StringUtils;

public class PosBussinessException extends BusinessException {

	private static final long serialVersionUID = 7946023196149777499L;

	protected PosReturnCode errorCode;
	protected String realCode;
	protected String message;
	protected Integer fieldNo = null;

	public PosReturnCode getErrorCode() {
		return errorCode;
	}

	public PosBussinessException(String message) {
		super(message);
		this.message = message;
	}

	public PosBussinessException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public PosBussinessException(PosReturnCode errorCode) {
		super();
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
		this.realCode = errorCode.getErrorCode();
	}

	public PosBussinessException(PosReturnCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
		this.realCode = errorCode.getErrorCode();
	}

	public PosBussinessException(PosReturnCode errorCode, Integer fieldNo) {
		super();
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
		this.realCode = errorCode.getErrorCode();
		this.fieldNo = fieldNo;
	}

	/**
	 * 取得配置文件的内容，或者使用默认值
	 */
	@Override
	public String getMessage() {
		String notMessage = "no error, no message";
		if (StringUtils.isNotBlank(this.message)) {
			return this.message;
		} else if (StringUtils.isNotBlank(super.getMessage())) {
			return super.getMessage();
		} else {
			return notMessage;
		}
	}

}
