/**
 * @(#)ProtocolProcessFilter.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.bmp.pos.mina.filter;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import com.justinmobile.bmp.pos.mina.dto.PosManagerDto;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException;
import com.justinmobile.bmp.pos.mina.log.PosManagerLog;
import com.justinmobile.core.util.ByteUtil;




/**最后一道过滤器
 * @author ThinkPad7
 *
 */
public class PosProDecodeFilter extends IoFilterAdapter{


//	@Autowired
//	private PosManagerDto posManagerDto;
	
	
	
	@Override
	public void sessionCreated(NextFilter nextFilter,IoSession session) throws Exception {
		IoSessionConfig cfg = session.getConfig();
		if (cfg instanceof SocketSessionConfig) {
			SocketSessionConfig sCfg = (SocketSessionConfig) cfg;
			sCfg.setKeepAlive(true);
//			if (session.getLocalAddress() == TransportType.SOCKET) {
				sCfg.setReceiveBufferSize(20480);
			//	sCfg.setIdleTime(IdleStatus.BOTH_IDLE, 30);
			
//			}
		}
	}
	
	
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		try{
			
			PosManagerLog.logMsg(ByteUtil.toHexString((byte[]) message), PosManagerLog.LOG_FILE_PLATFORMFILE);
			//解析报文
			PosManagerDto posManagerDto =new PosManagerDto();
			posManagerDto.decode((byte[]) message);
			PosManagerLog.logMsg( posManagerDto.toString(), PosManagerLog.LOG_FILE_PLATFORMFILE);
			super.messageReceived(nextFilter, session, posManagerDto);
			}catch(FieldException pex){
				PosManagerLog.logError(getRemoteAddressPort(session)+MessageFormatter.format("RECEIVED INVALID MESSAGE: {}", message), PosManagerLog.LOG_FILE_PLATFORMFILE);
				PosManagerLog.logError(pex.getExceptionCode()+"==>"+pex.getExceptionMsg() , PosManagerLog.LOG_FILE_PLATFORMFILE);
				session.close(true);
				throw pex;
			}catch(Exception ex){
				PosManagerLog.logError(getRemoteAddressPort(session)+MessageFormatter.format("RECEIVED INVALID MESSAGE: {}", message), PosManagerLog.LOG_FILE_PLATFORMFILE);
				PosManagerLog.logError(ex.getMessage(), PosManagerLog.LOG_FILE_PLATFORMFILE);
				session.close(true);
				throw ex;
			}finally{
//				session.close(true);
			}
			
	}

	
	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		super.messageSent(nextFilter, session, writeRequest);
	}
	
	public static String getRemoteAddressPort(IoSession session) {
		return "[" + session.getRemoteAddress() + "]";
	}
	
}
