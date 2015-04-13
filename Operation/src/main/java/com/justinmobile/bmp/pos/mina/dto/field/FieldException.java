package com.justinmobile.bmp.pos.mina.dto.field;

public class FieldException extends Exception {
	private static final long serialVersionUID = 684405264221333178L;
	
	private static final String DEFAULT_SYSTEM_ERROR = "系统错误";
	private static final String DEFAULT_DATAGRAM_ERROR = "报文错误";
	public static final String SUCESS_RESP = "00";

	
	public enum ExceptionCode{
		B_SUCESS_RESP("00","操作成功","操作成功"),
		E_LENGTH_UPPER_LIMIT("01", "编码时域长度超上限", DEFAULT_SYSTEM_ERROR),
		E_LENGTH_LOWER_LIMIT("02", "编码时域长度超下限", DEFAULT_SYSTEM_ERROR),
		E_UNSURPORT_CHARSET("03", "编码时字符串域遇到不支持的字符集", DEFAULT_SYSTEM_ERROR),
		D_RAW_LENGTH_LOWER_LIMIT("04", "解码时原始数据长度不足", DEFAULT_DATAGRAM_ERROR),
		D_RAW_DATA_NULL("05", "解码时原始数据为空", DEFAULT_DATAGRAM_ERROR),
		D_RAW_DATA_ERROR("06", "解码时原始数据出错", DEFAULT_DATAGRAM_ERROR),
		D_RAW_DATA_CRC_ERROR("06", "CRC校验出错", DEFAULT_DATAGRAM_ERROR),
		D_TAG_INVALID("07", "解码时域标识非法", DEFAULT_DATAGRAM_ERROR),
		D_UNSURPORT_CHARSET("08", "解码时字符串域遇到不支持的字符集", DEFAULT_DATAGRAM_ERROR),
		D_NUMBERIC_FIELD_LENGTH_INVALID("09", "整数类型域长度无效", DEFAULT_DATAGRAM_ERROR),
		B_POS_SERIAL_RONG("0a","pos终端序列号不匹配",DEFAULT_SYSTEM_ERROR),
		B_CONNECT_EXPTION("0b","接口调用失败",DEFAULT_SYSTEM_ERROR),
		
		S_TAG_INVALID("0c", "域标识非法", DEFAULT_SYSTEM_ERROR),
		S_APP_LOCATION_REPEAT("0d", "POS应用设置位置重复", DEFAULT_SYSTEM_ERROR),
		B_APP_INVALID("0E","应用无效",DEFAULT_SYSTEM_ERROR),
		B_APPVERSION_INVALID("0F","应用版本无效",DEFAULT_SYSTEM_ERROR),
		B_FILE_READ_NOCOMPLEATE("10","文件不能完整读取",DEFAULT_SYSTEM_ERROR),
		B_CURRENT_APPVERSION_INVALID("11","当前版本无效",DEFAULT_SYSTEM_ERROR),
		;
		private String code;
		private String logMsg;
		private String returnMsg;

		ExceptionCode(String code, String logMsg, String returnMsg){
			this.code = code;
			this.logMsg = logMsg;
			this.returnMsg = returnMsg;
		}

		public String getCode() {
			return code;
		}

		public String getLogMsg() {
			return logMsg;
		}
		
		public String getReturnMsg() {
			return returnMsg;
		}
		
	}
	
	private ExceptionCode exceptionCode;
	
	public FieldException(ExceptionCode exceptionCode){
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionCode() {
		return exceptionCode.getCode();
	}
	
	public String getExceptionMsg() {
		return exceptionCode.getLogMsg();
	}
}
