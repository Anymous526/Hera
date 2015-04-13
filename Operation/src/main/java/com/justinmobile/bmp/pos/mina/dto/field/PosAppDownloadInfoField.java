package com.justinmobile.bmp.pos.mina.dto.field;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.proxy.utils.ByteUtilities;

/**应用下载信息域    0x05
 * @author ThinkPad7
 *
 */
public class PosAppDownloadInfoField extends Field {
	private int location;
	private String appCode;
	private String version;
	private int offset;
	private int size;
	private boolean hasExtraData;
	private boolean isDone;

	public static int EXDATA_MAX_LENT=900;
	public PosAppDownloadInfoField(byte tag, boolean isForced) {
		super(tag, isForced);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isHasExtraData() {
		return hasExtraData;
	}

	public void setHasExtraData(boolean hasExtraData) {
		this.hasExtraData = hasExtraData;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public byte[] encode() throws FieldException {
		byte[] val = new byte[27];
		System.arraycopy(ByteUtilities.intToNetworkByteOrder(this.location, 1), 0, val, 0, 1);
		System.arraycopy(this.appCode.getBytes(), 0, val, 1, 6);
		System.arraycopy(this.version.getBytes(), 0, val, 7, 10);
		System.arraycopy(ByteUtilities.intToNetworkByteOrder(this.offset, 4), 0, val, 17, 4);
		System.arraycopy(ByteUtilities.intToNetworkByteOrder(this.size, 4), 0, val, 21, 4);
		val[25] = (byte) (this.hasExtraData? 1: 0);
		val[26] = (byte) (this.isDone? 1: 0);
		this.setValue(val);
		return super.encode();
	}

	@Override
	public int decode(byte[] binary) throws FieldException {
		int decodedBytesLength = super.decode(binary);
		int valueLength = this.getLength();
		if( valueLength != 21 ){
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_ERROR);
		}
		byte[] val = this.getValue();
		this.location = ByteUtilities.networkByteOrderToInt(val, 0, 1);
		this.appCode = new String(ArrayUtils.subarray(val, 1, 7));
		this.version = new String(ArrayUtils.subarray(val, 7, 17));
		this.offset = ByteUtilities.networkByteOrderToInt(val, 17, 4);
		//this.size = ByteUtilities.networkByteOrderToInt(val, 21, 4);
		
		//this.hasExtraData = val[25]==0x01;
		//this.isDone = val[26]==0x01;
		return decodedBytesLength;
	}
	@Override
	public String toString() {
		String str = "T[" + super.getTag() + "]-";

				str = str + "L["  + "]-V[ location:"+
						this.location+",appCode:"+
						this.appCode+",size:"+
						this.size+",offset:"+
						this.offset+ ",version:"+
						this.version+",hasExtraData:"+
						this.hasExtraData+",isDone:"+
						this.isDone+"]";
			

		return str;
	}
}
