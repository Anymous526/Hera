package com.justinmobile.endpoint.mina.filter.encode.binary;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class Hs8583BinaryDecoder  extends ProtocolDecoderAdapter {
	/**
	 * 把IoBuffer中的数据转换成字节数组
	 */
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		int messagelent=in.remaining();
		byte[] buf = new byte[messagelent];
		
		System.out.println(messagelent);
		 if (messagelent >= 4) {   
			
				in.get(buf);
				out.write(buf);
			
		 }else {   
		               // 如果消息包不完整   
		               // 将指针重新移动消息头的起始位置   
			 //  IoBuffer temp = IoBuffer.allocate(4000).setAutoExpand(true);   
	        //   in.put(buf);  
	          
	           in.flip();
		       //        in.free();//.reset();   
		              
		 } 
//		 if (in.hasRemaining()) {   
//	           // 将数据移到buffer的最前面   
//	           IoBuffer temp = IoBuffer.allocate(4000).setAutoExpand(true);   
//	           temp.put(in);   
//	           temp.flip();   
//	           in.clear();   
//	           in.put(temp);   
//	 
//	       } else {// 如果数据已经处理完毕，进行清空   
//	           in.clear();   
//	       }   
	//	  in.clear();   
	}

}
