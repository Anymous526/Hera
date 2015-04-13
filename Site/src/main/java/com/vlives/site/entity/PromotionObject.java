package com.vlives.site.entity;

import java.text.SimpleDateFormat;

import com.vlives.boss.coupon.domain.CouponPloy;

/**
 * Domain object wrapper used to provide web specific data.
 * @author User
 *
 */
public class PromotionObject {

	private CouponPloy domain;
	
	public PromotionObject(CouponPloy couponPloy) {
		this.domain = couponPloy;
	}
	
	public CouponPloy getDomain() {
		return this.domain;
	}
	
	public String getMerchantName() {
		if (this.domain.getMerchant().getShortName().length() > 8) {
			return this.domain.getMerchant().getShortName().substring(0, 7) + "..";
		} else {
			return this.domain.getMerchant().getShortName();
		}
	}

	public String getStartDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.domain.getValidStartDate());
	}
	
	public String getEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.domain.getValidEndDate());
	}
	
	public long getRemain() {
		if (this.domain.getMaxSendCount() == null) {
			return -1;
		} else {
			return this.domain.getMaxSendCount() - this.domain.getSentCount();
		}
	}
	
	public boolean isDownloadable() {
		return (domain.getMaxReceiveCountByEveryUser() > 0);
	}
}
