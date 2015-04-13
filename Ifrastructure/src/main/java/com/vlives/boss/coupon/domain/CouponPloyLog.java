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
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.coupon.domain.CouponPloy.CouponPloyStatus;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-9-6
 */
@TypeDefs({ @TypeDef(name = "couponPloyStatus", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.CouponPloy$CouponPloyStatus") }) })
@Entity
@Table(name = "COUPON_PLOY_LOG")
public class CouponPloyLog extends BaseEntity {
	private Long id;
	/** 电子卷活动 */
	private CouponPloy couponPloy;
	/** 创建时间 */
	private Date createDate;
	/** 电子卷活动状态 */
	private CouponPloyStatus startStatus;
	/** 电子卷活动状态 */
	private CouponPloyStatus endStatus;
	/** 描述 */
	private String description;
	/**
	 * 操作员
	 */
	private Operator operator;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON_PLOY_LOG") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUPON_PLOY_ID")
	public CouponPloy getCouponPloy() {
		return couponPloy;
	}

	public void setCouponPloy(CouponPloy couponPloy) {
		this.couponPloy = couponPloy;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@org.hibernate.annotations.Type(type = "couponPloyStatus")
	@Column(name = "START_STATUS")
	public CouponPloyStatus getStartStatus() {
		return startStatus;
	}

	public void setStartStatus(CouponPloyStatus startStatus) {
		this.startStatus = startStatus;
	}

	@org.hibernate.annotations.Type(type = "couponPloyStatus")
	@Column(name = "END_STATUS")
	public CouponPloyStatus getEndStatus() {
		return endStatus;
	}

	public void setEndStatus(CouponPloyStatus endStatus) {
		this.endStatus = endStatus;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATOR_ID")
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

}