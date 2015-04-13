package com.justinmobile.bmp.pos.mina.dto.field;

import org.apache.mina.proxy.utils.ByteUtilities;


public class Field {

	/**标识    T*/
	private byte tag;
	/**值域   V*/
	private byte[] value;
	/**是否为强制编码域*/
	private boolean isForced;
	
	public Field(byte tag, boolean isForced){
	
		this.tag = tag;
		this.isForced = isForced;
	}
	
	public Field(byte tag){
		this.tag = tag;
		this.isForced = false;
	}

	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public boolean isForced() {
		return isForced;
	}

	public void setForced(boolean isForced) {
		this.isForced = isForced;
	}

	public int getLength() {
		if( value != null ){
			return value.length;
		}else{
			return 0;
		}
	}

	public boolean isValid(){
		return 0 != this.getLength();
	}
	
	public byte[] encode() throws FieldException{
		int length = this.getLength();
		if( length == 0 || this.isForced() ){
			throw new FieldException(FieldException.ExceptionCode.E_LENGTH_LOWER_LIMIT);
		}else if( length > 256 ){
			throw new FieldException(FieldException.ExceptionCode.E_LENGTH_UPPER_LIMIT);
		}
		byte[] out = new byte[length + 2];
		out[0] = this.tag;
		out[1] = ((Integer)length).byteValue();
		if( this.value != null ){
			System.arraycopy(this.value, 0, out, 2, length);
		}
		return out;
	}
	
	public int decode(byte[] binary) throws FieldException{
		if( binary == null ){
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_NULL);
		}
		int length = binary.length;
		if( length < 2 ){
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_ERROR);
		}
		//取出标识值
		this.tag = binary[0];//ByteUtilities.networkByteOrderToInt(binary, 0, 1);//((Byte)binary[0]).intValue();
		//取出域长度
		int valueLength = ByteUtilities.networkByteOrderToInt(binary, 1, 1);// ((Byte)binary[1]).intValue();
		if( valueLength > length - 1 ){
			throw new FieldException(FieldException.ExceptionCode.D_RAW_LENGTH_LOWER_LIMIT);
		}
		byte[] val = new byte[valueLength];
		System.arraycopy(binary, 2, val, 0, valueLength);
		this.value = val;
		return valueLength + 2;
	}

	@Override
	public String toString() {
		String str = "T[" + this.tag + "]-";
		if( this.getLength() != 0 ){
			str = str + "L[" + this.getLength() + "]-V[" + ByteUtilities.asHex(this.value, " ") + "]";
		}else{
			str = str + "L[0]";
		}
		return str;
	}

}
