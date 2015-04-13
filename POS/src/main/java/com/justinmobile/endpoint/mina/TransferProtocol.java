/**
 * @(#)TransferProtocol.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.util.ConstantUtils;

/**
 * 传输协议
 * @author ThinkPad7
 *
 */
public enum TransferProtocol {
	
	/**
	 * 中心二进制支付协议
	 * 报文头为二进制字节数组,报文体为XML
	 */
	HS8583_BINARY_PROTOCOL("HS8583_BINARY_PROTOCOL","HS8583报文协议",ConstantUtils.getCpayBinaryProtocolIp(),ConstantUtils.getCpayBinaryProtocolPort(),ConstantUtils.getCpayBinaryProtocolUseSSL());

	private final String protocol;
	private final String desc;
	private final String defaultServerIp;
	private final int deaultServerPort;
	private final boolean useSSL;
	
	private TransferProtocol(String protocol, String desc,String defaultServerIp,int deaultServerPort,boolean useSSL) {
		this.protocol = protocol;
		this.desc = desc;
		this.defaultServerIp = defaultServerIp;
		this.deaultServerPort = deaultServerPort;
		this.useSSL = useSSL;
	}
	public String getProtocol() {
		return protocol;
	}
	public String getDesc() {
		return desc;
	}
	
	public String getDefaultServerIp() {
		return defaultServerIp;
	}
	public int getDeaultServerPort() {
		return deaultServerPort;
	}
	
	public boolean isUseSSL() {
		return useSSL;
	}
	/**
	 * 根据报文的类型不同 ------〉得到协议
	 * @author 
	 * @param message
	 * @return
	 */
	public static TransferProtocol getTransferProtocol(Object message) {
		
		if(message instanceof byte[]) {
			byte[] bytes = (byte[]) message;
			if(bytes.length !=0) return HS8583_BINARY_PROTOCOL;
		}
		
		throw new IllegalProtocolException("protocol can not parse, protocol is not exist : " +message.toString());
	}
	/**
	 * 得到MtRequestCode
	 * @author 
	 * @param message
	 * @return
	 */
	public static MtRequestCode getMtRequestCode(Hs8583Dto hs8583Dto,Object message) {
		if(getTransferProtocol(message) == HS8583_BINARY_PROTOCOL) {
			String code = getCapyRequestCode(hs8583Dto);
			return MtRequestCode.get(code);
		}
		throw new IllegalProtocolException("protocol can not parse, request code is not exist : " +message.toString());
	}
	
	private static String getCapyRequestCode(Hs8583Dto hs8583Dto) {
		return new String(hs8583Dto.getBm0MessageType()+hs8583Dto.getBm3TradeCode());
	}
	
	/**
	 * 得到支付中心的业务请求码
	 * @author 
	 * @param msg
	 * @return
	 */
	private static String getCapyRequestCode(String msg) {
		String tempMsg = msg.toUpperCase();
		int startIndex = tempMsg.indexOf("<MCODE>")+"<MCODE>".length();
		int endIndex = tempMsg.indexOf("</MCODE>");
		String code = msg.substring(startIndex, endIndex);
		return code;
	}
	

	
}
