/**
 * @(#)TempSalePloy.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sale.domain;

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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.sale.domain.SalePloy.SalePloyType;
import com.vlives.core.support.hibernate.EnumType;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-17
 */
@TypeDefs({
	@TypeDef(name = "salePloyType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sale.domain.SalePloy$SalePloyType") })
})
@Entity
@Table(name="TEMP_SALE_PLOY")
public class TempSalePloy {
	private Long id;
	/*** 活动名称*/
	private String name;
	
	/*** 商户*/
	private Merchant merchant;
	/*** 内容模板*/
	private String template;
	
	/**
	 * 有效开始日期
	 */
	private Date validStartDate;
	/**
	 * 有效结束日期
	 */
	private Date validEndDate;
	/**
	 * 适用门店如果是父店则填充改字段，否则（独立门店和子门店）不填充
	 */
	private String applyMerchant;
	
	/**
	 * 营销活动类型
	 */
	private SalePloyType salePloyType;
	
	/**
	 * 营销活动类型为单次消费满额对应的最小金额
	 */
	private BigDecimal tradeMinMoney;
	
	/*** 定时发送时间*/
	private Date timingTime;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TEMP_SALE_PLOY") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	 
	@Column(name="TIMING_TIME")
	public Date getTimingTime() {
		return timingTime;
	}

	public void setTimingTime(Date timingTime) {
		this.timingTime = timingTime;
	}
	@org.hibernate.annotations.Type(type="salePloyType")
	@Column(name="SALE_PLOY_TYPE")
	public SalePloyType getSalePloyType() {
		return salePloyType;
	}

	public void setSalePloyType(SalePloyType salePloyType) {
		this.salePloyType = salePloyType;
	}
	@Column(name="TRADE_MIN_MONEY")
	public BigDecimal getTradeMinMoney() {
		return tradeMinMoney;
	}

	public void setTradeMinMoney(BigDecimal tradeMinMoney) {
		this.tradeMinMoney = tradeMinMoney;
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
	@Column(name="APPLY_MERCHANT")
	public String getApplyMerchant() {
		return applyMerchant;
	}

	public void setApplyMerchant(String applyMerchant) {
		this.applyMerchant = applyMerchant;
	}
	
	
}
