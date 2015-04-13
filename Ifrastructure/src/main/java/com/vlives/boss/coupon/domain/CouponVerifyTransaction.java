/**
 * @(#)CouponVerifyTransaction.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

import java.util.Date;

import javax.persistence.Column;
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
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.core.support.entity.BaseEntity;

/**
 * POS验券流水
 * 
 * @author jianguo.xu
 * @version 1.0,2011-9-6
 */
@Entity
@Table(name = "COUPON_VERIFY_TRANSACTION")
public class CouponVerifyTransaction extends BaseEntity {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 验证的电子券
	 */
	private Coupon coupon;
	/**
	 * 验券序列号
	 */
	private String serialNo;
	/**
	 * 交易批次号
	 */
	private TradeBatch tradeBatch;
	
	/**检劵时间*/
	private Date verifyDate;
	
	/**检劵商户*/
	private Merchant verifyMerchant;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON_VERIFY_TRANSACTION") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUPON_ID")
	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_BATCH_ID")
	public TradeBatch getTradeBatch() {
		return tradeBatch;
	}
    
	@Column(name = "SERIAL_NO")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setTradeBatch(TradeBatch tradeBatch) {
		this.tradeBatch = tradeBatch;
	}

	@Column(name = "VERIFY_DATE")
	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VERIFY_MERCHANT_ID")
	public Merchant getVerifyMerchant() {
		return verifyMerchant;
	}

	public void setVerifyMerchant(Merchant verifyMerchant) {
		this.verifyMerchant = verifyMerchant;
	}
	
	
	

}
