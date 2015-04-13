package com.vlives.boss.member.manager.exception;

import org.apache.commons.lang.StringUtils;

import com.vlives.core.exception.PosReturnCode;

public class MemberException extends Exception {

	private static final long serialVersionUID = 7946023196149777499L;

	protected PosReturnCode errorCode;
	protected String realCode;
	protected String message;
	protected Integer fieldNo = null;

	public PosReturnCode getErrorCode() {
		return errorCode;
	}

	public MemberException(String message) {
		super(message);
		this.message = message;
	}

	public MemberException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public MemberException(PosReturnCode errorCode) {
		super();
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
		this.realCode = errorCode.getErrorCode();
	}

	public MemberException(PosReturnCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
		this.realCode = errorCode.getErrorCode();
	}

	public MemberException(PosReturnCode errorCode, Integer fieldNo) {
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
