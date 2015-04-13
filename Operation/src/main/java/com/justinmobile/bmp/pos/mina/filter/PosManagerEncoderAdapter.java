package com.justinmobile.bmp.pos.mina.filter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.springframework.beans.factory.annotation.Autowired;

import com.justinmobile.bmp.pos.mina.dto.PosManagerDto;

public class PosManagerEncoderAdapter extends ProtocolEncoderAdapter {
	
	/**
	 * 把字节数据封装到的IoBuffer中
	 */
	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		buffer.put((byte[]) message);
		buffer.flip();
		out.write(buffer);
	}
}
