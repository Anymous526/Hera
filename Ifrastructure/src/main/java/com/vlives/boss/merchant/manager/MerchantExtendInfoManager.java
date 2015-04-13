/**
 * @(#)MerchantExtendManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.manager;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantExtendInfo;

/**
 * description
 * @author  unicorn
 * @version 1.0,2011-6-8
 */
public interface MerchantExtendInfoManager {
	/***
	 * 保存商户的扩展资料
	 * @author unicorn
	 * @param merchantExtendInfo
	 */
	public void create(MerchantExtendInfo merchantExtendInfo,Merchant merchant);
	
	/***
	 * 更新商户扩展信息
	 * @author unicorn
	 * @param merchantExtendInfo	更新的商户扩展信息
	 * @param merchant				商户
	 */
	public void update(MerchantExtendInfo merchantExtendInfo,Merchant merchant);
	
	/***
	 * 获得商户扩展信息详细
	 * @author unicorn
	 * @param merchant
	 * @return
	 */
	public MerchantExtendInfo getDetailBy(Merchant merchant);
}

