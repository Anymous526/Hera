package com.justinmobile.endpoint.mina.filter.encode.binary;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class Hs8583BinaryEncoder extends ProtocolEncoderAdapter{
	/**
	 * 把字节数据封装到的IoBuffer中
	 */
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		buffer.put((byte[]) message);
		buffer.flip();
		out.write(buffer);
	}

}
