package com.justinmobile.endpoint.mina.filter.encode.binary;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class Hs8583BinaryCodeFactory implements ProtocolCodecFactory{

	private Hs8583BinaryDecoder hs8583BinaryDecoder;
	
	private Hs8583BinaryEncoder hs8583BinaryEncoder;
	
	
	
	
	public void setHs8583BinaryDecoder(Hs8583BinaryDecoder hs8583BinaryDecoder) {
		this.hs8583BinaryDecoder = hs8583BinaryDecoder;
	}

	public void setHs8583BinaryEncoder(Hs8583BinaryEncoder hs8583BinaryEncoder) {
		this.hs8583BinaryEncoder = hs8583BinaryEncoder;
	}

	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return hs8583BinaryDecoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return hs8583BinaryEncoder;
	}

}
