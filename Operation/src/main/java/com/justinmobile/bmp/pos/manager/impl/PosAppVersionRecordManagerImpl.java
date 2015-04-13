package com.justinmobile.bmp.pos.manager.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller.HTTP;
import com.justinmobile.bmp.pos.dao.PosAppVersionRecordDao;
import com.justinmobile.bmp.pos.domain.PosAppVersionRecord;
import com.justinmobile.bmp.pos.manager.PosAppVersionRecordManager;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException.ExceptionCode;
import com.justinmobile.core.manager.EntityPageManagerImpl;

@Service("posAppVersionRecordManager")
public class PosAppVersionRecordManagerImpl extends EntityPageManagerImpl<PosAppVersionRecord, PosAppVersionRecordDao> implements PosAppVersionRecordManager {

	@SuppressWarnings("unused")
	@Autowired
	private PosAppVersionRecordDao posAppVersionRecordDao;

	@Override
	public Map<String, String> getMerchantInfo(String serialNumber) throws FieldException {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("serialNumber", serialNumber);
		try{
			JSONObject json = HTTP.MERCHANT_POS_READ.invoke(null,params);
			JSONArray posInfo =json.getJSONArray("posInfo");
			JSONObject  json2=(JSONObject) posInfo.get(0);
			Map<String,String> map = new HashMap<String ,String>();
			map.put("posCode", json2.getString("posCode"));//
			map.put("merchantCode", json2.getString("merchantCode"));//
			map.put("merchantName", json2.getString("merchantName"));//
			return map;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new FieldException(ExceptionCode.B_CONNECT_EXPTION);
		}
	}
	
}