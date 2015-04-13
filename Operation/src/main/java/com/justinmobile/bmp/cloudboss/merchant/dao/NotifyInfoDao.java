package com.justinmobile.bmp.cloudboss.merchant.dao;

import java.util.List;

import com.justinmobile.bmp.cloudboss.merchant.domain.NotifyInfo;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.core.dao.EntityPageDao;

public interface NotifyInfoDao extends EntityPageDao<NotifyInfo> {
	
	public NotifyInfo getByMerchantCode(String merchantCode) throws PlatformException;
	
	public List<NotifyInfo> getNotifyList();
}