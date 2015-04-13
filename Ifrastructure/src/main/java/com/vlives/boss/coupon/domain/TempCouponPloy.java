/**
 * @(#)CouponPloy.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

import java.math.BigDecimal;
import java.util.Date;

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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;
import com.vlives.util.StringUtils;

/**
 * 电子券活动
 * @author  jianguo.xu
 * @version 1.0,2011-9-6
 */

@TypeDefs({ @TypeDef(name = "couponType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.CouponType") }),
	@TypeDef(name = "couponPloyType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.CouponPloyType") })
})
@Entity
@Table(name="TEMP_COUPON_PLOY")
public class TempCouponPloy extends BaseEntity{
	
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 活动创建的商户
	 */
	private Merchant merchant;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 电子券活动类型
	 */
	private CouponPloyType couponPloyType;
	/**
	 * 电子券类型
	 */
	private CouponType couponType;
	 
	/**
	 * 电子券有效开始日期
	 */
	private Date validStartDate;
	/**
	 * 电子券有效结束日期
	 */
	private Date validEndDate;
	/**
	 * 活动发送开始日期
	 */
	private Date sendStartDate;
  
	/**
	 * 是否在网站展示
	 */
	private boolean displayInWeb;
	/**
	 * 活动允许发送的最大数量,如果为null表示无限制
	 */
	private Long maxSendCount;
 
	/**
	 * 优惠券活动介绍
	 */
	private String introduction;
	/**
	 * 适用商店ID集合 ,集合之间用','分隔
	 */
	private String applyMerchantIds; 
	/**
	 * 用户可领用优惠啊的最大数量,如果为0，
	 * 表示不能领用
	 */
	private long maxReceiveCountByEveryUser;
	/**
	 * 电子券活动忽略的会员ID,多个会员ID用','分隔
	 */
	private String excludeMemberIds;
	/**
	 * 券面值
	 */
	private String couponValue;
	
	private Date sendEndDate;
	/**
	 * 触发式赠送下发数量
	 */
	private int sendCount;
	/**
	 * 赠送电子券的最小消费金额
	 */
	private BigDecimal minConsumMoney;
	
	 
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TEMP_COUPON_PLOY") })
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="PLOY_TYPE")
	@Type(type="couponPloyType")
	public CouponPloyType getCouponPloyType() {
		return couponPloyType;
	}
	
	public void setCouponPloyType(CouponPloyType couponPloyType) {
		this.couponPloyType = couponPloyType;
	}
	
	@Column(name="COUPON_TYPE")
	@Type(type="couponType")
	public CouponType getCouponType() {
		return couponType;
	}
	
	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}
	 
	
	@Column(name="VALID_START_DATE")
	public Date getValidStartDate() {
		return validStartDate;
	}
	
	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}
	@Column(name="VALID_END_DATE")
	public Date getValidEndDate() {
		return validEndDate;
	}
	
	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}
	
	@Column(name="SEND_START_DATE")
	public Date getSendStartDate() {
		return sendStartDate;
	}
	
	public void setSendStartDate(Date sendStartDate) {
		this.sendStartDate = sendStartDate;
	}
	
	@Column(name="SEND_END_DATE")

	public Date getSendEndDate() {
		return sendEndDate;
	}

	public void setSendEndDate(Date sendEndDate) {
		this.sendEndDate = sendEndDate;
	}

	@Column(name="DISPLAY_WEB")
	public boolean isDisplayInWeb() {
		return displayInWeb;
	}
	
	public void setDisplayInWeb(boolean displayInWeb) {
		this.displayInWeb = displayInWeb;
	}
	
	@Column(name="MAX_SEND_COUNT")
	public Long getMaxSendCount() {
		return maxSendCount;
	}
	
	public void setMaxSendCount(Long maxSendCount) {
		this.maxSendCount = maxSendCount;
	}
 
	@Column(name="INTRODUCTION")
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	@Column(name="APPLY_MERCHANT_IDS")
	public String getApplyMerchantIds() {
		return applyMerchantIds;
	}

	public void setApplyMerchantIds(String applyMerchantIds) {
		this.applyMerchantIds = applyMerchantIds;
	}
	@Column(name="MAX_WEB_RECEIVE_COUNT")
	public long getMaxReceiveCountByEveryUser() {
		return maxReceiveCountByEveryUser;
	}

	public void setMaxReceiveCountByEveryUser(long maxReceiveCountByEveryUser) {
		this.maxReceiveCountByEveryUser = maxReceiveCountByEveryUser;
	}
	
	@Column(name="EXCLUD_EMEMBER_IDS")
	public String getExcludeMemberIds() {
		return excludeMemberIds;
	}

	public void setExcludeMemberIds(String excludeMemberIds) {
		this.excludeMemberIds = excludeMemberIds;
	}
	
	
	@Column(name="COUPON_VALUE")
	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	
	@Column(name="SEND_COUNT")
	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	@Column(name="MIN_CONSUM_MONEY")
	public BigDecimal getMinConsumMoney() {
		return minConsumMoney;
	}

	public void setMinConsumMoney(BigDecimal minConsumMoney) {
		this.minConsumMoney = minConsumMoney;
	}

	@Transient
	public int getExcludeMemberCount() {
		return StringUtils.isNullOrEmpty(excludeMemberIds)?0:excludeMemberIds.split(",").length;
	}
	
}
