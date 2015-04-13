/**
 * @(#)CouponPloyLog.java
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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.coupon.domain.Coupon.CouponStatus;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-9-6
 */
@TypeDefs({ @TypeDef(name = "couponStatus", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.Coupon$CouponStatus") }) })
@Entity
@Table(name = "COUPON_STATUS_LOG")
public class CouponLog extends BaseEntity {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 电子券活动
	 */
	private Coupon coupon;
	/**
	 * 电子券日志创建日期
	 */
	private Date createDate;
	/**
	 * 电子券开始状态
	 */
	private CouponStatus startStatus;
	/**
	 * 电子券改变以后状态
	 */
	private CouponStatus endStatus;
	/**
	 * 电子券状态改变描述
	 */
	private String description;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON_STATUS_LOG") })
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

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "START_STATUS")
	@Type(type = "couponStatus")
	public CouponStatus getStartStatus() {
		return startStatus;
	}

	public void setStartStatus(CouponStatus startStatus) {
		this.startStatus = startStatus;
	}

	@Column(name = "END_STATUS")
	@Type(type = "couponStatus")
	public CouponStatus getEndStatus() {
		return endStatus;
	}

	public void setEndStatus(CouponStatus endStatus) {
		this.endStatus = endStatus;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}