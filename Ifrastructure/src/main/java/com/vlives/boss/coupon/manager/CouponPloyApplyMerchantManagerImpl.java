package com.vlives.boss.coupon.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vlives.boss.coupon.dao.CouponPloyApplyMerchantDao;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloyApplyMerchant;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
@Service("couponPloyApplyMerchantManager")
public class CouponPloyApplyMerchantManagerImpl implements CouponPloyApplyMerchantManager{

	@Resource
	private CouponPloyApplyMerchantDao couponPloyApplyMerchantDao;
	
	public CouponPloyApplyMerchant getApplyPloyMer(Merchant merchant, CouponPloy couponPloy) {
		PropertiesFinder finder = new PropertiesFinder(CouponPloyApplyMerchant.class);
		finder.add("merchant", merchant);
		finder.add("couponPloy", couponPloy);
		return couponPloyApplyMerchantDao.getBy(finder);
	}
}
