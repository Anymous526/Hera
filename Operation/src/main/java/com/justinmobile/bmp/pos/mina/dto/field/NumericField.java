package com.justinmobile.bmp.pos.mina.dto.field;

import org.apache.mina.proxy.utils.ByteUtilities;

/**
 * @author ThinkPad7
 *
 */
public class NumericField extends Field {
	private int number;
	private int size;

	public NumericField(byte tag, boolean isForced) throws FieldException {
		super(tag, isForced);
		size = 0;
	}
	
	public NumericField(byte tag, boolean isForced, int number, int size) throws FieldException {
		super(tag, isForced);
		this.number = number;
		this.size = size;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public byte[] encode() throws FieldException {
		this.setValue(ByteUtilities.intToNetworkByteOrder(this.number, this.size));
		return super.encode();
	}

	@Override
	public int decode(byte[] binary) throws FieldException {
		int decodedBytesLength = super.decode(binary);
		this.size = this.getValue().length;
		switch(this.size){
		case 1:
			this.number = this.getValue()[0];
			break;
		case 2:
			this.number = ByteUtilities.networkByteOrderToInt(this.getValue(), 0, 2);
			break;
		case 4:
			this.number = ByteUtilities.networkByteOrderToInt(this.getValue(), 0, 4);
			break;
		default:
			throw new FieldException(FieldException.ExceptionCode.D_NUMBERIC_FIELD_LENGTH_INVALID);
		}
		return decodedBytesLength;
	}
	
	@Override
	public String toString() {
		String str = super.toString();
		str = str + "-Value[" + this.number + "]" + "-Size[" + this.size + "]";
		return str;
	}

}
