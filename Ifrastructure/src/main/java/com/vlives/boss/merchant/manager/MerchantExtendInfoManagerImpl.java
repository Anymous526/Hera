/**
 * @(#)MerchantExtendInfoManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantExtendInfo;
import com.vlives.core.dao.generic.BaseDao;

/**
 * description
 * @author  unicorn
 * @version 1.0,2011-6-8
 */
@Service("merchantExtendInfoManager")
public class MerchantExtendInfoManagerImpl implements MerchantExtendInfoManager {

	@Resource
	private BaseDao<MerchantExtendInfo, Long> merchantExtendInfoDao;
	
	@Override
	public void create(MerchantExtendInfo merchantExtendInfo, Merchant merchant) {
		merchantExtendInfo.setMerchant(merchant);
		merchantExtendInfoDao.save(merchantExtendInfo);
	}

	@Override
	public MerchantExtendInfo getDetailBy(Merchant merchant) {
		return merchantExtendInfoDao.getByProperty("merchant", merchant);
	}

	@Override
	public void update(MerchantExtendInfo merchantExtendInfo, Merchant merchant) {
		// TODO Auto-generated method stub

	}

}

