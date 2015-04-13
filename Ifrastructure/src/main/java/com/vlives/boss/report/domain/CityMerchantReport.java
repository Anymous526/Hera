/**
 * @(#)CityMerchantReport.java
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

import com.vlives.boss.area.domain.Area;

/**
 * 城市商户报表
 * @author  jianguo.xu
 * @version 1.0,2011-9-16
 */
@Entity
@Table(name = "CITY_MERCHANT_REPORT")
public class CityMerchantReport {
	private Long id;
	/**
	 * 统计日期
	 */
	private java.sql.Date reportDate;
	/**
	 *  统计的城市
	 */
	private Area city;
	/**
	 * 当时新增商户数
	 */
	private int perDayAddMerchantCount;
	/**
	 * 商户总数
	 */
	private int totalMerchantCount;
	
	/**
	 * 当时新增POS数
	 */
	private int perDayAddPosCount;
	/**
	 * pos总数
	 */
	private int totalPosCount;
	
	/**
	 * 当时新增会员数
	 */
	private int perDayAddMemberCount;
	/**
	 * 会员总数
	 */
	private int totalMemberCount;
	
	/**
	 * 当日POS渠道新增会员数
	 */
	private int perDayPosAddMemberCount;
	/**
	 * POS渠道会员总数
	 */
	private int totalPosAddMemberCount;
	
	/**
	 * 当时新增运营平台渠道会员数
	 */
	private int perDayPlatformAddMemberCount;
	/**
	 * 运营平台渠道会员总数
	 */
	private int totalPlatformAddMemberCount;
	
	
	/**
	 * 当日商户平台新增会员数
	 */
	private int perDayCloudbossAddMemberCount;
	/**
	 * 商户平台新增会员总数
	 */
	private int totalCloudbossAddMemberCount;
	
	/**
	 * 当日赠送短信数
	 */
	private int perDaySysLargessSmsCount;
	/**
	 * 赠送短信总数
	 */
	private int totalSysLargessSmsCount;
	
	/**
	 * 当日商户购买短信数
	 */
	private int perDayMerchantBuySmsCount;
	/**
	 *商户购买短信总数
	 */
	private int totalMerchantBuySmsCount;
	
	/**
	 * 当日商户发送短信数
	 */
	private int perDaySentSmsCount;
	/**
	 *商户发送短信总数
	 */
	private int totalSentSmsCount;
	
	/**
	 * 当日现金消费笔数
	 */
	private int perDayCashTradeCount;
	
	/**
	 * 现金消费总笔数
	 */
	private int totalCashTradeCount;
	/**
	 * 当日刷卡消费笔数
	 */
	private int perDayCardTradeCount;
	
	/**
	 * 当日刷卡消费总笔数
	 */
	private int totalCardTradeCount;
	
	/**
	 * 当日现金消费金额
	 */
	private BigDecimal perDayCashTradeMoney = BigDecimal.ZERO;
	
	/**
	 * 现金消费总额
	 */
	private BigDecimal totalCashTradeMoney = BigDecimal.ZERO;
	/**
	 * 当日刷卡消费金额
	 */
	private BigDecimal perDayCardTradeMoney = BigDecimal.ZERO;
	
	/**
	 * 刷卡消费总额
	 */
	private BigDecimal totalCardTradeMoney = BigDecimal.ZERO;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_CITY_MERCHANT_REPORT") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="REPORT_DATE")
	 
	public java.sql.Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(java.sql.Date reportDate) {
		this.reportDate = reportDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY")
	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	@Column(name = "PER_DAY_ADD_MERCHANT_COUNT")
	public int getPerDayAddMerchantCount() {
		return perDayAddMerchantCount;
	}

	public void setPerDayAddMerchantCount(int perDayAddMerchantCount) {
		this.perDayAddMerchantCount = perDayAddMerchantCount;
	}

	@Column(name = "TOTAL_MERCHANT_COUNT")
	public int getTotalMerchantCount() {
		return totalMerchantCount;
	}

	public void setTotalMerchantCount(int totalMerchantCount) {
		this.totalMerchantCount = totalMerchantCount;
	}

	@Column(name = "PER_DAY_ADD_POS_COUNT")
	public int getPerDayAddPosCount() {
		return perDayAddPosCount;
	}

	public void setPerDayAddPosCount(int perDayAddPosCount) {
		this.perDayAddPosCount = perDayAddPosCount;
	}

	@Column(name = "TOTAL_POS_COUNT")
	public int getTotalPosCount() {
		return totalPosCount;
	}

	public void setTotalPosCount(int totalPosCount) {
		this.totalPosCount = totalPosCount;
	}

	@Column(name = "PER_DAY_ADD_MEMBER_COUNT")
	public int getPerDayAddMemberCount() {
		return perDayAddMemberCount;
	}

	public void setPerDayAddMemberCount(int perDayAddMemberCount) {
		this.perDayAddMemberCount = perDayAddMemberCount;
	}

	@Column(name = "TOTAL_MEMBER_COUNT")
	public int getTotalMemberCount() {
		return totalMemberCount;
	}

	public void setTotalMemberCount(int totalMemberCount) {
		this.totalMemberCount = totalMemberCount;
	}

	@Column(name = "PER_DAY_POS_ADD_MEMBER_COUNT")
	public int getPerDayPosAddMemberCount() {
		return perDayPosAddMemberCount;
	}

	public void setPerDayPosAddMemberCount(int perDayPosAddMemberCount) {
		this.perDayPosAddMemberCount = perDayPosAddMemberCount;
	}

	@Column(name = "TOTAL_POS_ADD_MEMBER_COUNT")
	public int getTotalPosAddMemberCount() {
		return totalPosAddMemberCount;
	}

	public void setTotalPosAddMemberCount(int totalPosAddMemberCount) {
		this.totalPosAddMemberCount = totalPosAddMemberCount;
	}

	@Column(name = "PER_DAY_PLATFORM_ADD_MEMBER_CT")
	public int getPerDayPlatformAddMemberCount() {
		return perDayPlatformAddMemberCount;
	}

	public void setPerDayPlatformAddMemberCount(int perDayPlatformAddMemberCount) {
		this.perDayPlatformAddMemberCount = perDayPlatformAddMemberCount;
	}

	@Column(name = "TOTAL_PLATFORM_ADD_MEMBER_CT")
	public int getTotalPlatformAddMemberCount() {
		return totalPlatformAddMemberCount;
	}

	public void setTotalPlatformAddMemberCount(int totalPlatformAddMemberCount) {
		this.totalPlatformAddMemberCount = totalPlatformAddMemberCount;
	}

	@Column(name = "PER_DAY_CLOUD_ADD_MEMBER_CT")
	public int getPerDayCloudbossAddMemberCount() {
		return perDayCloudbossAddMemberCount;
	}

	public void setPerDayCloudbossAddMemberCount(int perDayCloudbossAddMemberCount) {
		this.perDayCloudbossAddMemberCount = perDayCloudbossAddMemberCount;
	}

	@Column(name = "TOTAL_CLOUDBOSS_ADD_MEMBER_CT")
	public int getTotalCloudbossAddMemberCount() {
		return totalCloudbossAddMemberCount;
	}

	public void setTotalCloudbossAddMemberCount(int totalCloudbossAddMemberCount) {
		this.totalCloudbossAddMemberCount = totalCloudbossAddMemberCount;
	}

	@Column(name = "PER_DAY_SYS_LARGESS_SMS_COUNT")
	public int getPerDaySysLargessSmsCount() {
		return perDaySysLargessSmsCount;
	}

	public void setPerDaySysLargessSmsCount(int perDaySysLargessSmsCount) {
		this.perDaySysLargessSmsCount = perDaySysLargessSmsCount;
	}

	@Column(name = "TOTAL_SYS_LARGESS_SMS_COUNT")
	public int getTotalSysLargessSmsCount() {
		return totalSysLargessSmsCount;
	}

	public void setTotalSysLargessSmsCount(int totalSysLargessSmsCount) {
		this.totalSysLargessSmsCount = totalSysLargessSmsCount;
	}

	@Column(name = "PER_DAY_MERCHANT_BUY_SMS_COUNT")
	public int getPerDayMerchantBuySmsCount() {
		return perDayMerchantBuySmsCount;
	}

	public void setPerDayMerchantBuySmsCount(int perDayMerchantBuySmsCount) {
		this.perDayMerchantBuySmsCount = perDayMerchantBuySmsCount;
	}

	@Column(name = "TOTAL_MERCHANT_BUY_SMS_COUNT")
	public int getTotalMerchantBuySmsCount() {
		return totalMerchantBuySmsCount;
	}

	public void setTotalMerchantBuySmsCount(int totalMerchantBuySmsCount) {
		this.totalMerchantBuySmsCount = totalMerchantBuySmsCount;
	}

	@Column(name = "PER_DAY_SENT_SMS_COUNT")
	public int getPerDaySentSmsCount() {
		return perDaySentSmsCount;
	}

	public void setPerDaySentSmsCount(int perDaySentSmsCount) {
		this.perDaySentSmsCount = perDaySentSmsCount;
	}

	@Column(name = "TOTAL_SENT_SMS_COUNT")
	public int getTotalSentSmsCount() {
		return totalSentSmsCount;
	}

	public void setTotalSentSmsCount(int totalSentSmsCount) {
		this.totalSentSmsCount = totalSentSmsCount;
	}

	@Column(name = "PER_DAY_CASH_TRADE_COUNT")
	public int getPerDayCashTradeCount() {
		return perDayCashTradeCount;
	}

	public void setPerDayCashTradeCount(int perDayCashTradeCount) {
		this.perDayCashTradeCount = perDayCashTradeCount;
	}

	@Column(name = "TOTAL_CASH_TRADE_COUNT")
	public int getTotalCashTradeCount() {
		return totalCashTradeCount;
	}

	public void setTotalCashTradeCount(int totalCashTradeCount) {
		this.totalCashTradeCount = totalCashTradeCount;
	}

	@Column(name = "PER_DAY_CARD_TRADE_COUNT")
	public int getPerDayCardTradeCount() {
		return perDayCardTradeCount;
	}

	public void setPerDayCardTradeCount(int perDayCardTradeCount) {
		this.perDayCardTradeCount = perDayCardTradeCount;
	}

	@Column(name = "TOTAL_CARD_TRADE_COUNT")
	public int getTotalCardTradeCount() {
		return totalCardTradeCount;
	}

	public void setTotalCardTradeCount(int totalCardTradeCount) {
		this.totalCardTradeCount = totalCardTradeCount;
	}

	@Column(name = "PER_DAY_CASH_TRADE_MONEY")
	public BigDecimal getPerDayCashTradeMoney() {
		return perDayCashTradeMoney;
	}

	public void setPerDayCashTradeMoney(BigDecimal perDayCashTradeMoney) {
		this.perDayCashTradeMoney = perDayCashTradeMoney;
	}

	@Column(name = "TOTAL_CASH_TRADE_MONEY")
	public BigDecimal getTotalCashTradeMoney() {
		return totalCashTradeMoney;
	}

	public void setTotalCashTradeMoney(BigDecimal totalCashTradeMoney) {
		this.totalCashTradeMoney = totalCashTradeMoney;
	}
	
	@Column(name = "PER_DAY_CARD_TRADE_MONEY")
	public BigDecimal getPerDayCardTradeMoney() {
		return perDayCardTradeMoney;
	}

	public void setPerDayCardTradeMoney(BigDecimal perDayCardTradeMoney) {
		this.perDayCardTradeMoney = perDayCardTradeMoney;
	}

	@Column(name = "TOTAL_CARD_TRADE_MONEY")
	public BigDecimal getTotalCardTradeMoney() {
		return totalCardTradeMoney;
	}

	public void setTotalCardTradeMoney(BigDecimal totalCardTradeMoney) {
		this.totalCardTradeMoney = totalCardTradeMoney;
	}
}
