/**
 * @(#)CouponConsumReport.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vlives.boss.coupon.domain.CouponPloy;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-15
 */
@Entity
@Table(name="COUPON_CONSUM_REPORT")
public class CouponConsumReport {
	private Long id;
	/**
	 * 统计日期
	 */
	private java.sql.Date createDate;
	/**
	 * 优惠券活动
	 */
	private CouponPloy couponPloy;
	/**
	 * 当日优惠券消费数
	 */
	private int couponConsumCount;
	/**
	 * 刷卡交易金额
	 */
	private BigDecimal brushConsumMoney = BigDecimal.ZERO;
	/**
	 * 现金交易金额
	 */
	private BigDecimal cashConsumMoney  = BigDecimal.ZERO;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON_CONSUM_REPORT") })
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="CREATE_DATE")
	public java.sql.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUPON_PLOY_ID")
	public CouponPloy getCouponPloy() {
		return couponPloy;
	}
	public void setCouponPloy(CouponPloy couponPloy) {
		this.couponPloy = couponPloy;
	}
	@Column(name="COUPON_CONSUM_COUNT")
	public int getCouponConsumCount() {
		return couponConsumCount;
	}
	public void setCouponConsumCount(int couponConsumCount) {
		this.couponConsumCount = couponConsumCount;
	}
	@Column(name="BRUSH_CONSUM_MONEY")
	public BigDecimal getBrushConsumMoney() {
		return brushConsumMoney;
	}
	public void setBrushConsumMoney(BigDecimal brushConsumMoney) {
		this.brushConsumMoney = brushConsumMoney;
	}
	@Column(name="CASH_CONSUM_MONEY")
	public BigDecimal getCashConsumMoney() {
		return cashConsumMoney;
	}
	public void setCashConsumMoney(BigDecimal cashConsumMoney) {
		this.cashConsumMoney = cashConsumMoney;
	}
	/**
	 * 得到总的消费金额
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public BigDecimal getTotalConsumMoney() {
		return cashConsumMoney.add(brushConsumMoney);
	}
}
