package com.justinmobile.bmp.pos.mina.dto.field;

import java.io.UnsupportedEncodingException;

public class StringField extends Field {
	private String charset = "gbk";
	private String str;

	public StringField(byte tag, boolean isForced) throws FieldException {
		super(tag, isForced);
	}
	
	public StringField(byte tag, boolean isForced, String str){
		super(tag, isForced);
		this.str = str;
	}
	
	public StringField(byte tag, boolean isForced, String charset, String str){
		super(tag, isForced);
		this.charset = charset;
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
			try {
				this.setValue( this.str.getBytes(charset) );
			} catch (UnsupportedEncodingException e) {
				throw new FieldException(FieldException.ExceptionCode.E_UNSURPORT_CHARSET);
			}
		}
		return super.encode();
	}

	@Override
	public int decode(byte[] binary) throws FieldException {
		int decodedBytesLength = super.decode(binary);
		try {
			this.str = new String(this.getValue(), this.charset);
		} catch (UnsupportedEncodingException e) {
			throw new FieldException(FieldException.ExceptionCode.D_UNSURPORT_CHARSET);
		}
		return decodedBytesLength;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str = str + "-String[" + this.charset + ":" + this.str + "]";
		return str;
	}
}
