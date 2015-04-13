package com.justinmobile.bmp.pos.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.dao.MerchantAppConfigDao;
import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.manager.MerchantAppConfigManager;
import com.justinmobile.core.manager.EntityPageManagerImpl;

@Service("merchantAppConfigManager")
public class MerchantAppConfigManagerImpl extends EntityPageManagerImpl<MerchantAppConfig, MerchantAppConfigDao> implements MerchantAppConfigManager {

	@Autowired
	private MerchantAppConfigDao merchantAppConfigDao;
	
	
	@Override
	public List<MerchantAppConfig> getAllConfigByMerchantCode(String merchantCode) throws PlatformException {
		try {
			return this.merchantAppConfigDao.findByProperty("merchantCode", merchantCode);
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.QUERY_ERROR , e);
		}
	}
	
	public List<MerchantAppConfig> getAppConfigByMerno(String merNo){
		String hql="from MerchantAppConfig conf where conf.merchantCode=? and conf.posApp.status=? order by conf.posApp asc";
		
		return merchantAppConfigDao.find(hql, merNo ,PosApp.STATUS_VALID);
		
	}
	

}