package com.justinmobile.bmp.pos.mina.filter;

import java.util.Calendar;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilter.NextFilter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import com.justinmobile.bmp.pos.mina.log.PosManagerLog;


public class PosManagerLogFilter extends IoFilterAdapter {


	org.slf4j.Logger logger = LoggerFactory.getLogger(PosManagerLogFilter.class.getName());

	// 记录接收原始报文
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		Calendar c = Calendar.getInstance();

		PosManagerLog.logMsg("本平台收到 POS请求: 请求类型匹配"+(message instanceof byte[]), PosManagerLog.LOG_FILE_PLATFORMFILE);
		byte[] buf=null;
		
		try {
		//	if(!(message instanceof ByteBuffer))throw new Exception();
			buf = (byte[]) message;
		
		String gramid = PosManagerLog.generateGramId();
		PosManagerLog.logBinary( buf, "<RECEIVED:>", PosManagerLog.LOG_FILE_PLATFORMFILE);
		System.out.println("---PospLogFilter println--:"
				+ (Calendar.getInstance().getTimeInMillis() - c
						.getTimeInMillis()));
		nextFilter.messageReceived(session, message);
		
		} catch (Exception e) {
			PosManagerLog.logError(getRemoteAddressPort(session)  +MessageFormatter.format("RECEIVED INVALID MESSAGE: {}", message), PosManagerLog.LOG_FILE_PLATFORMFILE);
			session.close(true);
		}
	}

	// 记录返回原始报文
	
	public void messageSent(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		PosManagerLog.logMsg("本平 返回 给 POS: ", PosManagerLog.LOG_FILE_PLATFORMFILE);
		byte[] buf = (byte[]) message;
	
		Calendar c = Calendar.getInstance();
		PosManagerLog.logBinary(buf, "<SEND BACK:>", PosManagerLog.LOG_FILE_PLATFORMFILE);
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
