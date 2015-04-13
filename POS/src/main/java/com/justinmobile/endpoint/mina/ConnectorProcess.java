/**
 * @(#)ConnectorProcess.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeoutException;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


/**
 * @author ThinkPad7
 *
 */
public class ConnectorProcess {
	
	private IoHandler ioHandler;
	 
	private InetSocketAddress address;
	private IoConnector connector;
 
	
	private IoFilter sslFilter;
 
	private IoFilter hs8583BinaryCodecFilter;
	
	private IoFilter loggingFilter;
	
	
	
	public void setSslFilter(IoFilter sslFilter) {
		this.sslFilter = sslFilter;
	}
	
	public void setHs8583BinaryCodecFilter(IoFilter hs8583BinaryCodecFilter) {
		this.hs8583BinaryCodecFilter = hs8583BinaryCodecFilter;
	}

	public void setLoggingFilter(IoFilter loggingFilter) {
		this.loggingFilter = loggingFilter;
	}


	/**
	 * 链接超时(单位：秒)
	 */
	private int connectTimeout;
	/**
	 * 同步相应超时(单位：秒)
	 */
	private int syncResponseTimeout;
	
	public ConnectorProcess(IoHandler ioHandler,
			String defaultServerIP, int defaultServerPort,int connectTimeout,int syncResponseTimeout) {
		 
		this.ioHandler = ioHandler;
		this.address = new InetSocketAddress(defaultServerIP, defaultServerPort);
		this.connectTimeout = connectTimeout;
		this.syncResponseTimeout = syncResponseTimeout;
	}
	/**
	 * 初始化连接请求
	 * @author 
	 * @param sync 是否同步
	 * @param transferProtocol 传输协议
	 * @throws ConnectorProcessException 
	 */	
	private ConnectFuture init(boolean sync,TransferProtocol transferProtocol,InetSocketAddress address,Boolean useSSL) throws ConnectorProcessException {
		connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(connectTimeout*1000); 
		setFilters(transferProtocol,useSSL);
		if(sync){
			IoSessionConfig cfg = connector.getSessionConfig();
			cfg.setUseReadOperation(true);
		}else {
			connector.setHandler(ioHandler);
		}
		setAddress(transferProtocol,address);
		ConnectFuture connectFuture = connector.connect(this.address);
		connectFuture.awaitUninterruptibly();
		if(!connectFuture.isConnected()) 
			throw new ConnectorProcessException("connect remote address fail."+address.getHostName()+":"+address.getPort());
		return connectFuture;
	}
 
	
	private void setAddress(TransferProtocol transferProtocol,InetSocketAddress address) {
		if(address !=null) {
			this.address = address;
			return;
		}
		if(transferProtocol.getDefaultServerIp()!=null||transferProtocol.getDeaultServerPort()>0) {
			this.address =  new InetSocketAddress(transferProtocol.getDefaultServerIp(), transferProtocol.getDeaultServerPort());
		}
	}
	
	private void setFilters(TransferProtocol transferProtocol,Boolean useSSL) {
		DefaultIoFilterChainBuilder chainBuilder = connector.getFilterChain();
		boolean isUseSSL = useSSL == null?transferProtocol.isUseSSL():useSSL.booleanValue();
		if(isUseSSL) 
			chainBuilder.addLast("sslFilter", sslFilter);
		if(transferProtocol == TransferProtocol.HS8583_BINARY_PROTOCOL) {
			chainBuilder.addLast("hs8583BinaryCodecFilter", hs8583BinaryCodecFilter);
		}
		chainBuilder.addLast("loggingFilter", loggingFilter);
	}
	public IoHandler getIoHandler() {
		return ioHandler;
	}

	public void setIoHandler(IoHandler ioHandler) {
		this.ioHandler = ioHandler;
	}
 

	public InetSocketAddress getAddress() {
		return address;
	}
	public void setAddress(InetSocketAddress address) {
		this.address = address;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getSyncResponseTimeout() {
		return syncResponseTimeout;
	}
	public void setSyncResponseTimeout(int syncResponseTimeout) {
		this.syncResponseTimeout = syncResponseTimeout;
	}


	
	private void assertWriteDataSuccess(WriteFuture writeFuture) throws TimeoutException {
		if(!writeFuture.isWritten()) 
			throw new TimeoutException("sync write socket data time out.");
	}
	

}
