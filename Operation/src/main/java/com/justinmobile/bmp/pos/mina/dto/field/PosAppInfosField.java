package com.justinmobile.bmp.pos.mina.dto.field;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.proxy.utils.ByteUtilities;

import edu.emory.mathcs.backport.java.util.Collections;

/**应用信息域   0x04
 * @author ThinkPad7
 *
 */
public class PosAppInfosField extends Field {
	
	private List<JSONObject> infos;

	public static String APPCODE="appCode";
	
	public static String LOCATION="location";
	
	public static String VERSION="version";
	
	public static String FLAG="flag";
	/**	0x00-选择更新
		0x01-强制更新
		0x02-不更新
		0x03-停止使用
	*/
	public static int FLAG_CHOOSE=0;
	public static int FLAG_YES=1;
	public static int FLAG_NO=2;
	public static int FLAG_STOP=3;
	
	public PosAppInfosField(byte tag, boolean isForced) throws FieldException {
		super(tag, isForced);
		infos = new ArrayList<JSONObject>();
	}

	public void addInfo(int location, String appCode, String version, int flag) throws FieldException {
		for(JSONObject json : this.infos){
			if( json.getInt("location") == location ){
				throw new FieldException(FieldException.ExceptionCode.S_APP_LOCATION_REPEAT);
			}
		}
		JSONObject json = new JSONObject();
		json.put("location", location);
		appCode=appCode.trim();
		if( appCode.length() < 6 ){
			appCode = appCode + "      ".substring(appCode.length());
		}
		json.put("appCode", appCode);
		version=version.trim();
		if( version.length() < 10 ){
			version = version + "          ".substring(version.length());
		}
		json.put("version", version);
		json.put("flag", flag);
		this.infos.add(json);
	}
	
	public void removeInfo(String appCode) {
		if( appCode == null || "".equals(appCode) ){
			return;
		}
		for( JSONObject json : infos ){
			if( json.getString("appCode").equals(appCode) ){
				this.infos.remove(json);
			}
		}
	}

	public List<JSONObject> getInfos() {
		return this.infos;
	}
	


	@Override
	public byte[] encode() throws FieldException {
		int count = this.infos.size();
		if( count == 0 ){
			return null;
		}
		byte[] val = new byte[count*18];
		Collections.sort(infos, new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject o1, JSONObject o2) {
				int location1 = o1.getInt("location");
				int location2 = o2.getInt("location");
				return location1 > location2? 1: 0;
			}
		});
		for(int i = 0; i < count; i++ ){
			JSONObject json = infos.get(i);
			int location = json.getInt("location");
			byte[] appCodeBinary = json.getString("appCode").getBytes();
			byte[] versionBinary = json.getString("version").getBytes();
			int flag = json.getInt("flag");
			val[i*18] = ByteUtilities.intToNetworkByteOrder(location, 1)[0];
			System.arraycopy(appCodeBinary, 0, val, i*18 + 1, 6);
			System.arraycopy(versionBinary, 0, val, i*18 + 7, 10);
			val[i*18 + 17] = ByteUtilities.intToNetworkByteOrder(flag, 1)[0];
		}
		this.setValue(val);
		return super.encode();
	}

	@Override
	public int decode(byte[] binary) throws FieldException {
		int decodedBytesLength = super.decode(binary);
		int valueLength = this.getLength();
		if( valueLength % 18 != 0 ){
			throw new FieldException(FieldException.ExceptionCode.D_RAW_DATA_ERROR);
		}
		byte[] val = this.getValue();
		try{
		for(int i = 0, count = valueLength/18; i < count; i++){
			int location = val[i*18];
			String appCode = new String(ArrayUtils.subarray(val, i*18 + 1, i*18 + 7),"gbk");
			String version = new String(ArrayUtils.subarray(val, i*18 + 7, i*18 + 17),"gbk");
			int flag = val[i*18 + 17];
			JSONObject json = new JSONObject();
			json.put("location", location);
			json.put("appCode", appCode.trim());
			json.put("version", version.trim());
			json.put("flag", flag);
			this.infos.add(json);
		}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return decodedBytesLength;
	}
	
	
	@Override
	public String toString() {
		String str = "T[" + super.getTag() + "]-";
		int count = this.infos.size();
		if( count != 0 ){
			for(int i = 0; i < count; i++ ){
				JSONObject json = infos.get(i);
				str = str + "L[" + count + "]-V[" +LOCATION+":"+
						json.getInt("location")+","+ APPCODE+":"+
						json.getString("appCode")+","+VERSION+":"+
						json.getString("version")+","+FLAG+":"+
						json.getInt("flag")+ "]";
			}
		}else{
			str = str + "L[0]";
		}
		return str;
	}
}
