/**
 * @(#)CouponPloyApplyMerchant.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vlives.boss.merchant.domain.Merchant;

/**
 * 电子券活动适用商户
 * @author  jianguo.xu
 * @version 1.0,2011-9-6
 */
@Entity
@Table(name="COUPON_PLOY_MERCHANT")
public class CouponPloyApplyMerchant {
	
	private Long id;
	
	private Merchant merchant;
	
	private CouponPloy couponPloy;
	
	
	public CouponPloyApplyMerchant() {
	}
	
	public CouponPloyApplyMerchant(Merchant merchant, CouponPloy couponPloy) {
		this.merchant = merchant;
		this.couponPloy = couponPloy;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON_PLOY_MERCHANT") })
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}
	
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUPON_PLOY_ID")
	public CouponPloy getCouponPloy() {
		return couponPloy;
	}
	
	public void setCouponPloy(CouponPloy couponPloy) {
		this.couponPloy = couponPloy;
	}
	
}
