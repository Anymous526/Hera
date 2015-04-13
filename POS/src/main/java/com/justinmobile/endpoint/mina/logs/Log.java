package com.justinmobile.endpoint.mina.logs;

import java.nio.ByteBuffer;
import java.util.Date;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.vlives.util.DateUtils;



public class Log {

	private static EncapsulationLog4J los = EncapsulationLog4J.getInstance();
	public static String ls = (String) System.getProperties().get("line.separator");

	public static int LOG_FILE_ERRORFILE = 1;
	public static int LOG_FILE_PLATFORMFILE = 2;
	public static int LOG_FILE_TERMINALFILE = 3;	
	public static int LOG_FILE_BUSIFILE = 4;

	public Log() {
	}
	//生成
	public static String generateGramId() {
		//return CalendarUtils.fomatCalendar(Calendar.getInstance(), CalendarUtils.LONG_FORMAT);
		return DateUtils.format(new Date(), DateUtils.LONG_DATE_FORMAT_STR);
	}

	/**
	 * 错误日志报文记录
	 * 
	 * @param errorLogMessage
	 */
//	public static void logError(ErrorLogMessage errorLogMessage) {
//		los.log("ERROR", errorLogMessage.getLogMsg(), LOG_FILE_ERRORFILE);
//
//	}

	/**
	 * 业务日志报文记录
	 * 
	 * @param abstractDataMessage
	 * @param gramId
	 */

	
	public static void logBusi(Hs8583Dto hs8583, String gramId) {
		los.log("INFO",hs8583.toString(), LOG_FILE_BUSIFILE);
	}

	public static void logBinary(String gramid, byte[] msg, String receiveFlag, int logfile) {
		StringBuffer sb = new StringBuffer();
		sb.append(ls).append("DATAGRAMID:").append(gramid);
		sb.append(ls).append("记录日期时间:").append( DateUtils.format(new Date(), DateUtils.LONG_DATE_FORMAT_STR));
		       
		ByteBuffer buffer = ByteBuffer.allocate(msg.length);
		buffer.put(msg);
		buffer.flip();
		sb.append(ls).append("原始报文:" + buffer);
		sb.append(ls).append("收发标识:").append(receiveFlag);
		los.log("INFO", sb.toString(), logfile);
	}

	public static void logError(String msg, int logfile) {
		los.log("ERROR", msg, logfile);
	}

	public static void logError(Throwable e, int logfile) {
		los.log("ERROR", null, e, logfile);
	}

	public static void logMsg(String msg, int logfile) {
		los.log("WARN", msg, logfile);
	}

	public static void logMsg(Throwable e, int logfile) {
		los.log("WARN", null, e, logfile);
	}

	public static void logInfo(String msg, int logfile) {
		los.log("INFO", msg, logfile);
	}

	public static void logInfo(Throwable e, int logfile) {
		los.log("INFO", null, e, logfile);
	}

	public static void logDebug(String msg, int logfile) {
		los.log("DEBUG", msg, logfile);
	}

	public static void logDebug(Throwable e, int logfile) {
		los.log("DEBUG", null, e, logfile);
	}

	public static void logMyself(String msg, int logfile) {
		los.log("FATAL", msg, logfile);
	}

	public static void logMyself(Throwable e, int logfile) {
		los.log("FATAL", null, e, logfile);
	}

//	public static void main(String[] args) {
//		Log.logError(new ErrorLogMessage("131", "1231", "m,essage", 1));
//	}
}
