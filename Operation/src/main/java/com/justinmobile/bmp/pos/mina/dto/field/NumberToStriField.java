package com.justinmobile.bmp.pos.mina.dto.field;

import java.io.UnsupportedEncodingException;

import org.apache.mina.proxy.utils.ByteUtilities;

public class NumberToStriField extends Field{

	private String str;

	public NumberToStriField(byte tag, boolean isForced){
		super(tag, isForced);
	}
	
	public NumberToStriField(byte tag, boolean isForced, String str){
		super(tag, isForced);
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public byte[] encode() throws FieldException {
		if( str == null ){
			this.setValue(null);
		}else{
				this.setValue(ByteUtilities.asByteArray(this.str));
		}
		return super.encode();
	}

	@Override
	public int decode(byte[] binary) throws FieldException {
		int decodedBytesLength = super.decode(binary);
			this.str = ByteUtilities.asHex(this.getValue());
		
		return decodedBytesLength;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str = str + "-String[" + this.str + "]";
		return str;
	}

	
	
}
