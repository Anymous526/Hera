package com.justinmobile.endpoint.mina.logs;

import java.util.Calendar;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

/**
 * Log过滤器
 * 
 * @author slhe
 * 
 */
public class PospLogFilter extends IoFilterAdapter {

	org.slf4j.Logger logger = LoggerFactory.getLogger(PospLogFilter.class.getName());
	/** mac 验证结果 值为 boolean*/
	public static String MAC_RESULT = "macResult";
	
	/** 业务日志唯一标识*/
	public static String DATAGRAMID = "datagramId";	
	// 记录接收原始报文
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		Calendar c = Calendar.getInstance();

		Log.logMsg("本平台收到 POS请求: 请求类型匹配"+(message instanceof byte[]), Log.LOG_FILE_TERMINALFILE);
		byte[] buf=null;
		
		try {
		//	if(!(message instanceof ByteBuffer))throw new Exception();
			buf = (byte[]) message;
		
		String gramid = Log.generateGramId();
		session.setAttribute(DATAGRAMID, gramid);
		Log.logBinary(gramid, buf, "<RECEIVED:>", Log.LOG_FILE_TERMINALFILE);
		System.out.println("---PospLogFilter println--:"
				+ (Calendar.getInstance().getTimeInMillis() - c
						.getTimeInMillis()));
		nextFilter.messageReceived(session, message);
		
		} catch (Exception e) {
			//e.printStackTrace();
			Log.logError(getRemoteAddressPort(session)  +MessageFormatter.format("RECEIVED INVALID MESSAGE: {}", message), Log.LOG_FILE_ERRORFILE);
			session.close(true);
		}
	}

	// 记录返回原始报文
	
	public void messageSent(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		Log.logMsg("本平 返回 给 POS: ", Log.LOG_FILE_TERMINALFILE);
		byte[] buf = (byte[]) message;
	
		String gramid = (String) session
				.getAttribute(DATAGRAMID);
	
		Calendar c = Calendar.getInstance();
		Log.logBinary(gramid, buf, "<SEND BACK:>", Log.LOG_FILE_TERMINALFILE);
		System.out.println("----PospLogFilter println---:"
				+ (Calendar.getInstance().getTimeInMillis() - c
						.getTimeInMillis()));

		nextFilter.messageSent(session, (WriteRequest) message);
	}

	@Override
	public void exceptionCaught(NextFilter nextFilter, IoSession session,
			Throwable cause) throws Exception {
		
		super.exceptionCaught(nextFilter, session, cause);
	}
	
	public static String getRemoteAddressPort(IoSession session) {
		return "[" + session.getRemoteAddress() + "]";
	}
	
}
