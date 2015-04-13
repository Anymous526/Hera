package com.justinmobile.bmp.cloudboss.merchant.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.cloudboss.merchant.dao.NotifyInfoDao;
import com.justinmobile.bmp.cloudboss.merchant.domain.NotifyInfo;
import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.core.dao.EntityPageDaoHibernate;

@Repository("notifyInfoDao")
public class NotifyInfoDaoHibernate extends EntityPageDaoHibernate<NotifyInfo> implements NotifyInfoDao {

	@Override
	public NotifyInfo getByMerchantCode(String merchantCode) throws PlatformException{
//		return super.findUniqueByProperty("merchantCode", merchantCode);
		List<NotifyInfo> list = null;
		try {
			list = super.findByProperty("merchantCode", merchantCode);
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.DB_ERROR , e);
		}
		if(list.size() == 0){
			return null;
		}
		if(list.size() > 1){
			throw new PlatformException(PlatformErrorCode.DATA_ERROR);
		}
		return list.get(0);
	}

	@Override
	public List<NotifyInfo> getNotifyList() {
		String hql = "from NotifyInfo ni where ni.notifySuccess = ? and ni.notifyCount <= ? ";
		return super.find(hql, NotifyInfo.NOTIFY_FAULT , 20);
	}

}