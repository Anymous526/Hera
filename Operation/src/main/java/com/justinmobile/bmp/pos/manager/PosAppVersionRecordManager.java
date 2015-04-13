package com.justinmobile.bmp.pos.manager;

import java.util.Map;


import com.justinmobile.bmp.pos.domain.PosAppVersionRecord;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException;
import com.justinmobile.core.manager.EntityPageManager;

public interface PosAppVersionRecordManager extends EntityPageManager<PosAppVersionRecord>{
	
	/**
	 * @param serialNumber POS序列号
	 * @return 包含商户名称和商户编号的Map,POS编号:posCode，商户编号：merchantCode，商户名称：merchantName
	 * 查询失败返回null
	 */
	public Map<String ,String> getMerchantInfo(String posserialNumber) throws FieldException;
}