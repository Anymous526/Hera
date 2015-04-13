package com.justinmobile.bmp.common.exception;

public enum PlatformErrorCode {
	
	SUCCESS("000000", "操作成功"),
	UNKNOWN_ERROR("000001", "未确认的系统错误"),
	DB_ERROR("000002", "数据库操作异常"),
	DATA_ERROR("000003", "数据异常"), 
	PARAM_ERROR("000004", "传入参数错误"),
	CALLER_MISS_ERROR("000005", "没有配置对应的接口地址"),
	CALLER_PARSE_ERROR("000006", "接口地址配置错误"),
	QUERY_ERROR("000010", "查询失败"),
	NO_CITY_ERROR("000011", "当前用户尚未配置城市"),
	
	MERCHANT_STATUS_ERROR ("10001" , "商户状态不正确"),
	MERCHANT_CODE_EXIST ("10002","商户编号已存在"),
	MERCHANT_NOTIFY_ERROR ("10003","通知PMS失败"),
	MERCHANT_APP_ERROR ("10010","商户应用信息异常"),
	
	EXCEL_PARSE_ERROR ("30001"  , "excel解析失败"),
	EXCEL_FIELD_ERROR ("30002"  , "excel字段格式不正确"),
	
	POSAPP_STATUS_ERROR ("40001" , "应用状态异常"),
	POSAPPVERSION_NOT_DISABLED_ALL ("40002","尚有未停用的应用版本")
	;
	private String code;

	private String defaultMessage;

	PlatformErrorCode(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	public String getErrorCode() {
		return this.code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

}
