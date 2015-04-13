package com.justinmobile.endpoint.mina.filter.encode.binary;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.justinmobile.util.ByteUtil;

public class Hs8583CumulativeBinaryDecoder  extends CumulativeProtocolDecoder{

	private final AttributeKey CONTEXT = new AttributeKey(getClass(), "context");
	private class Context {
		private int msgLength;
		private byte[] totalBytes;
		
		public Context(int msgLength, byte[] totalBytes) {
			super();
			this.msgLength = msgLength;
			this.totalBytes = totalBytes;
		}
		
		
		public void setTotalBytes(byte[] totalBytes) {
			this.totalBytes = totalBytes;
		}
		public byte[] getTotalBytes() {
			return totalBytes;
		}
		
		public boolean isEnd() {
			return msgLength == totalBytes.length;
		}
		
		public void reset() {
			msgLength = 0;
			totalBytes = null;
		}
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		byte[] bytes = new byte[in.remaining()];
		in.get(bytes);
		Context ctx = getContext(session,bytes);
		if(ctx.isEnd()) {
			out.write(ctx.getTotalBytes());
			ctx.reset();
			session.removeAttribute(CONTEXT);
			return true;
		}
		else
			return false;
	}
	
	public Context getContext(IoSession session,byte[] bytes) {
		Context ctx = (Context) session.getAttribute(CONTEXT);
		if (ctx == null) {
			int msgLength = ByteUtil.binaryToInt(ByteUtil.subArray(bytes, 0, 4));
			ctx = new Context(msgLength,bytes);
		}
		else {
			ctx.setTotalBytes(ByteUtil.contactArray(ctx.getTotalBytes(), bytes));
		}
		
		session.setAttribute(CONTEXT, ctx);
		return ctx;
	}
}
