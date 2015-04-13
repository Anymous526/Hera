/**
 * @(#)MerchantReoprt.java
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vlives.boss.merchant.domain.Merchant;

/**
 * 商户报表
 * @author  jianguo.xu
 * @version 1.0,2011-8-1
 */
@Entity
@Table(name="MERCHANT_REPORT")
public class MerchantReport {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 商户
	 */
	private Merchant merchant;
	/**
	 * 创建日期
	 */
	private java.sql.Date createDate;
	/**
	 * 会员当日新增数
	 */
	private int perDayAddMemberCount;
	/**
	 * 会员总数
	 */
	private int totalMemberCount;
	/**
	 * 现金消费金额
	 */
	private BigDecimal cashTradeMoney;
	/**
	 * 现金消费数
	 */
	private int cashTradeCount;
	/**
	 * 银行卡消费金额
	 */
	private BigDecimal cardTradeMoney;
	/**
	 * 银行卡交易数
	 */
	private int cardTradeCount;
	/**
	 * 每日有效营销活动日增数
	 */
	private int perDayAddSalePloyCount;
	/**
	 * 有效活动总数
	 */
	private int totalSalePloyCount;
	/**
	 * 已发短信数
	 */
	private int sendSmsCount;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_REPORT") })
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
	@Column(name="CREATE_DATE")
	public java.sql.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="PRE_DAY_ADD_MEMBER_COUNT")
	public int getPerDayAddMemberCount() {
		return perDayAddMemberCount;
	}
	public void setPerDayAddMemberCount(int perDayAddMemberCount) {
		this.perDayAddMemberCount = perDayAddMemberCount;
	}
	@Column(name="TOTAL_MEMBER_COUNT")
	public int getTotalMemberCount() {
		return totalMemberCount;
	}
	public void setTotalMemberCount(int totalMemberCount) {
		this.totalMemberCount = totalMemberCount;
	}
	@Column(name="CASH_TRADE_MONEY")
	public BigDecimal getCashTradeMoney() {
		return cashTradeMoney;
	}
	public void setCashTradeMoney(BigDecimal cashTradeMoney) {
		this.cashTradeMoney = cashTradeMoney;
	}
	@Column(name="CASH_TRADE_COUNT")
	public int getCashTradeCount() {
		return cashTradeCount;
	}
	public void setCashTradeCount(int cashTradeCount) {
		this.cashTradeCount = cashTradeCount;
	}
	@Column(name="CRAD_TRADE_MONEY")
	public BigDecimal getCardTradeMoney() {
		return cardTradeMoney;
	}
	public void setCardTradeMoney(BigDecimal cardTradeMoney) {
		this.cardTradeMoney = cardTradeMoney;
	}
	@Column(name="CARD_TRADE_COUNT")
	public int getCardTradeCount() {
		return cardTradeCount;
	}
	public void setCardTradeCount(int cardTradeCount) {
		this.cardTradeCount = cardTradeCount;
	}
	@Column(name="PRE_DAY_ADD_SALE_PLOY_COUNT")
	public int getPerDayAddSalePloyCount() {
		return perDayAddSalePloyCount;
	}
	public void setPerDayAddSalePloyCount(int perDayAddSalePloyCount) {
		this.perDayAddSalePloyCount = perDayAddSalePloyCount;
	}
	@Column(name="TOTAL_SALE_PLOY_COUNT")
	public int getTotalSalePloyCount() {
		return totalSalePloyCount;
	}
	public void setTotalSalePloyCount(int totalSalePloyCount) {
		this.totalSalePloyCount = totalSalePloyCount;
	}
	@Column(name="SEND_SMS_COUNT")
	public int getSendSmsCount() {
		return sendSmsCount;
	}
	public void setSendSmsCount(int sendSmsCount) {
		this.sendSmsCount = sendSmsCount;
	}
	
	
}
