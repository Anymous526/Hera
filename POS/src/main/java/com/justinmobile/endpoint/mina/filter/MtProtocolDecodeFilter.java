/**
 * @(#)ProtocolProcessFilter.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.filter;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.helpers.MessageFormatter;

import com.justinmobile.endpoint.mina.TransferProtocol;
import com.justinmobile.endpoint.mina.action.ActionContext;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.logs.Log;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.request.MtRequestDtoFactory;
import com.justinmobile.endpoint.mina.service.PosServiceException;
import com.justinmobile.util.ByteUtil;


public class MtProtocolDecodeFilter extends IoFilterAdapter{
	private MtRequestDtoFactory mtRequestDtoFactory;
	
	
	public void setMtRequestDtoFactory(MtRequestDtoFactory mtRequestDtoFactory) {
		this.mtRequestDtoFactory = mtRequestDtoFactory;
	}
	@Override
	public void sessionCreated(NextFilter nextFilter,IoSession session) throws Exception {
		IoSessionConfig cfg = session.getConfig();
		if (cfg instanceof SocketSessionConfig) {
			SocketSessionConfig sCfg = (SocketSessionConfig) cfg;
			sCfg.setKeepAlive(true);
//			if (session.getLocalAddress() == TransportType.SOCKET) {
				sCfg.setReceiveBufferSize(20480);
				sCfg.setIdleTime(IdleStatus.BOTH_IDLE, 30);
			
//			}
		}
	}
	
	
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		try{
			
				
				TransferProtocol protocol= TransferProtocol.getTransferProtocol(message);
				Log.logMsg(ByteUtil.toHexString((byte[]) message), Log.LOG_FILE_PLATFORMFILE);
				Hs8583Dto hs8583Dto = mtRequestDtoFactory.createRequestDto(message, protocol);
				Log.logMsg( hs8583Dto.toString(), Log.LOG_FILE_BUSIFILE);
				
				MtRequestCode requestCode = TransferProtocol.getMtRequestCode(hs8583Dto,message);
				hs8583Dto.setRequestCode(requestCode);
				setActionContext(session);
				super.messageReceived(nextFilter, session, hs8583Dto);
			}catch(PosServiceException pex){
				Log.logError(getRemoteAddressPort(session)+MessageFormatter.format("RECEIVED INVALID MESSAGE: {}", message), Log.LOG_FILE_ERRORFILE);
				Log.logError(pex.getErrorCode().getErrorCode()+"==>"+pex.getErrorCode().getMessage() , Log.LOG_FILE_ERRORFILE);
				throw pex;
			}catch(Exception ex){
				Log.logError(getRemoteAddressPort(session)+MessageFormatter.format("RECEIVED INVALID MESSAGE: {}", message), Log.LOG_FILE_ERRORFILE);
				Log.logError(ex.getMessage(), Log.LOG_FILE_ERRORFILE);

				
				throw ex;
			}finally{
				session.close(true);
			}
			
	}
	private void setActionContext(IoSession session) {
		ActionContext.setMtSession(session);
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
