package com.justinmobile.bmp.pos.mina.filter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class PosManagerDecoderAdapter extends ProtocolDecoderAdapter {

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		/**
		 * 把IoBuffer中的数据转换成字节数组
		 */
			int messagelent=in.remaining();
			byte[] buf = new byte[messagelent];
			System.out.println(messagelent);
			 if (messagelent >= 4) {   
					in.get(buf);
					out.write(buf);
			 }else {   

		           in.flip();
			              
			 } 		
		
	}

}
