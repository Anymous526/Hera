package com.justinmobile.bmp.pos.mina.dto;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.CRC32;

import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.proxy.utils.ByteUtilities;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.pos.mina.dto.field.Field;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException;
import com.justinmobile.bmp.pos.mina.dto.field.NumberToStriField;
import com.justinmobile.bmp.pos.mina.dto.field.PosAppDownloadInfoField;
import com.justinmobile.bmp.pos.mina.dto.field.PosAppInfosField;
import com.justinmobile.bmp.pos.mina.dto.field.StringField;

//@Service("posManagerDto")
public class PosManagerDto {
	
	private Map<Byte, Field> fields;

	private byte[] extraData;
 
	private String crc32;

	private boolean isValid;
	
	
	private boolean isDong=true;

	@SuppressWarnings("unchecked")
	public PosManagerDto(){
		this.isValid = false;
		this.fields = new TreeMap(
				new Comparator<Byte>() {
			
			@Override
			public int compare(Byte o1, Byte o2) {
				return o1.compareTo(o2);
			}
		}
				);
	}

	
	
	public boolean isDong() {
		return isDong;
	}



	public void setDong(boolean isDong) {
		this.isDong = isDong;
	}



	public byte[] getExtraData() {
		return extraData;
	}

	public void setExtraData(byte[] extraData) {
		this.extraData = extraData;
	}

	public Map<Byte, Field> getFields() {
		return fields;
	}
	
	public void addField(Field field){
		if( field != null ){
			if( !this.fields.containsKey(field.getTag()) ){
				this.isValid = true;
				fields.put(field.getTag(), field);
			}
		}
	}

	public String getCrc32() {
		return crc32;
	}
	
	public byte[] encode() throws FieldException{
		if( !this.isValid ){
			return null;
		}
		byte[] dataGramBody = new byte[0];
		Iterator<Byte> iter = this.fields.keySet().iterator();
		while( iter.hasNext() ){
			byte tag = iter.next();
			Field field = fields.get(tag);
			dataGramBody = ArrayUtils.addAll(dataGramBody, field.encode());
		}
		
		byte[] dataGramLength = ByteUtilities.intToNetworkByteOrder(dataGramBody.length, 4);
		byte[] dataGramExtraData = null;
		if( this.extraData == null || this.extraData.length == 0 ){
			dataGramExtraData = ByteUtilities.intToNetworkByteOrder(0, 2);
		}else{
			dataGramExtraData = ArrayUtils.addAll(ByteUtilities.intToNetworkByteOrder(this.extraData.length, 2), this.extraData);
		}
		byte[] dataGramCrc32 = ByteUtilities.asByteArray(this.crc32=mkCrc(dataGramBody));//.intToNetworkByteOrder(this.crc32, 4);
		byte[] dataall=ArrayUtils.addAll(dataGramLength, ArrayUtils.addAll(dataGramBody, ArrayUtils.addAll(dataGramExtraData, dataGramCrc32)));
		dataall=ArrayUtils.addAll(new byte[]{0x00,0x00,0x00,0x00,0x00},dataall);
		byte[] dataHead=ByteUtilities.intToNetworkByteOrder(dataall.length, 2);
		
		return ArrayUtils.addAll(dataHead,dataall);
	}
	
	public void decode(byte[] dataGram) throws FieldException{
		if( dataGram == null || dataGram.length < 4){
			this.isValid = false;
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_ERROR);
		}
		byte[] head= ArrayUtils.subarray(dataGram, 0, 2);
		if(ByteUtilities.asHex(head).endsWith("0000")){
			dataGram= ArrayUtils.subarray(dataGram, 5, dataGram.length);
		}else{
			dataGram= ArrayUtils.subarray(dataGram, 7, dataGram.length);
		}
		//报文体长度
		int length = ByteUtilities.networkByteOrderToInt(dataGram, 0, 4);
		//取出报文体的 数据
		byte[] dataGramBoby = ArrayUtils.subarray(dataGram, 4, length + 4);
		this.doParse(dataGramBoby);
		//额外的数据部分
		
		int extraDataLength = ByteUtilities.networkByteOrderToInt(dataGram, length + 4, 2);
		this.extraData = ArrayUtils.subarray(dataGram, length + 6, length + 6 + extraDataLength);
		if( this.extraData.length != extraDataLength ){
			this.isValid = false;
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_ERROR);
		}
			//校验码---
		this.crc32 = ByteUtilities.asHex( ArrayUtils.subarray(dataGram, length + 6 + extraDataLength, length + 6 + extraDataLength+4));//.networkByteOrderToInt(dataGram, length + 6 + extraDataLength, 4);

		if(!this.crc32.toLowerCase().equals(mkCrc(dataGramBoby).toLowerCase())){
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_CRC_ERROR);
		}
		
		if( (length + extraDataLength + 10) != dataGram.length ){
			this.isValid = false;
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_ERROR);
		}
		this.isValid = true;
		
		
	}
	
	/**报文体解析
	 * @param data
	 * @throws FieldException
	 */
	private void doParse(byte[] data) throws FieldException{
		for(int offset = 0; offset < data.length; ){		
			//ByteUtilities.networkByteOrderToInt(new byte[]{data[offset]}, 0, 1) ;
			byte tag =data[offset];
			Field field=null;
				switch(tag){
				case 0x00://交易码。
					field = new NumberToStriField(tag, false);
					break;
				case 0x01://终端序列号
					field = new StringField(tag, false);
					break;
				case 0x02://商户编号
					field = new StringField(tag, false);
					break;
				case 0x03://平台逻辑编号
					field = new StringField(tag, false);
					break;
				case 0x04:
					field = new PosAppInfosField(tag, false);
					break;
				case 0x05:
					field = new PosAppDownloadInfoField(tag, false);
					break;
				case (byte) 0xfe:
					field = new NumberToStriField(tag, false);
					break;
				case (byte) 0xff:
					field = new StringField(tag, false);
					break;
				}
				offset += invokAndAdd( field, offset, data, tag);
		}
	}

	
	private int invokAndAdd(Field field,int offset,byte[] data,int tag) throws FieldException{
		offset=field.decode(ArrayUtils.subarray(data, offset, data.length));
		addField(field);
		return offset;
	}
	
	@Override
	public String toString() {
		String str = "--报文内容--开始--";
		Iterator<Byte> iter = this.fields.keySet().iterator();
		while( iter.hasNext() ){
			byte tag = iter.next();
			Field field = fields.get(tag);
			if("".equals(str)){
				str = field.toString();
			}else{
				str = str + System.getProperty("line.separator") + field.toString();
			}
		}
		str = str + System.getProperty("line.separator") + "--报文内容--结束--";
		return super.toString();
	}
	
	 private static String mkCrc(byte[] dataBody) {

		  CRC32 crc32 = new CRC32();
		  crc32.update(dataBody);
		 return  String.format("%8s", Long.toHexString(crc32.getValue())).replace(" ", "0") ;
	}
	 

	
	
	
	
	public static byte[] resource=new byte[]{
		0x00,0x00,0x00,(byte) 0x80,
		0x00,0x01,0x00,
		0x01,0x10,0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x30,0x31,0x32,0x33,0x34,0x35,
		0x02,0x0f,0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x30,0x31,0x32,0x33,0x34,
		0x03,0x08,0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,
		0x04,0x24,
				  0x11,
				  0x30,0x31,0x32,0x33,0x34,0x35,
				  0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,
				  0x00,
				  0x10,
				  0x30,0x31,0x32,0x33,0x34,0x35,
				  0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,
				  0x02,
	    0x05,0x1B,
	              0x11,
	              0x30,0x31,0x32,0x33,0x34,0x35,
	              0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,
	              0x14,0x13,0x12,0x11,
	              0x14,0x13,0x12,0x11,
	              0x00,
	              0x00,
	    (byte) 0xfe,0x01,0x00,
	    (byte) 0xff,0x08,(byte)0xb2,(byte)0xd9,(byte)0xd7,(byte)0xf7,(byte)0xb3,(byte)0xc9,(byte)0xb9,(byte) 0xa6,
	    0x00,0x0A,0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,
	    0x11,0x12,0x13,0x14
	};
}
