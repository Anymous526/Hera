package com.vlives.boss.coupon.manager;

import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloyApplyMerchant;
import com.vlives.boss.merchant.domain.Merchant;

public interface CouponPloyApplyMerchantManager {

	
	/**取得接受活动的商户关系
	 * @param merchant
	 * @param couponPloy
	 * @return
	 */
	public CouponPloyApplyMerchant getApplyPloyMer(Merchant merchant, CouponPloy couponPloy);

	
}
