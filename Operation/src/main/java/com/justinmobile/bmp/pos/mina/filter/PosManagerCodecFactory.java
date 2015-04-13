package com.justinmobile.bmp.pos.mina.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.springframework.beans.factory.annotation.Autowired;

public class PosManagerCodecFactory implements ProtocolCodecFactory{
	
	@Autowired
	private PosManagerDecoderAdapter decoderAdapter;
	@Autowired
	private PosManagerEncoderAdapter encoderAdapter;

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoderAdapter;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoderAdapter;
	}

}
