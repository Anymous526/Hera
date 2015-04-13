/*
 * @(#)TradeDetail.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.core.support.entity.BaseEntity;

/**
 * 交易明细
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
/**
 * @author tiger
 * 
 */
@Entity
@Table(name = "TRADE_DETAIL")
public class TradeDetail extends BaseEntity {

	/*** 编号 */
	private Long id;

	/*** 交易订单 */
	private TradeOrder tradeOrder;

	/*** 当次交易流水号 */
	private int tradeSerialNo;
	/**
	 * 原交易批次号
	 */
	private Integer originalSerailNo;

	/*** 当此交易批次 */
	private TradeBatch tradeBatch;
	/**
	 * 原交易批次
	 */
	private TradeBatch originalTradeBatch;

	/*** 是否是消费交易 */
	private boolean consumeTrade;

	/*** 银行卡号 */
	private String bankCard;
	/**
	 * 交易日期
	 */
	private Date tradeDate;

	/** 创建日期 */
	private Date createDate;

	/** 交易金额 */
	private BigDecimal amount;

	private Set<MerchantComment> merchantComments;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TRADE_DETAIL") })
	public Long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_ORDER_ID")
	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	@Column(name = "TRADE_SERIAL_NO")
	public int getTradeSerialNo() {
		return tradeSerialNo;
	}

	public void setTradeSerialNo(int tradeSerialNo) {
		this.tradeSerialNo = tradeSerialNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_BATCH_ID")
	public TradeBatch getTradeBatch() {
		return tradeBatch;
	}

	public void setTradeBatch(TradeBatch tradeBatch) {
		this.tradeBatch = tradeBatch;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORIGINAL_TRADE_BATCH_ID")
	public TradeBatch getOriginalTradeBatch() {
		return originalTradeBatch;
	}

	public void setOriginalTradeBatch(TradeBatch originalTradeBatch) {
		this.originalTradeBatch = originalTradeBatch;
	}

	@Column(name = "IS_CONSUME")
	public boolean isConsumeTrade() {
		return consumeTrade;
	}

	public void setConsumeTrade(boolean consumeTrade) {
		this.consumeTrade = consumeTrade;
	}

	@Column(name = "ORIGINAL_SERAIL_NO")
	public Integer getOriginalSerailNo() {
		return originalSerailNo;
	}

	public void setOriginalSerailNo(Integer originalSerailNo) {
		this.originalSerailNo = originalSerailNo;
	}

	@Column(name = "TRADE_DATE")
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	
	

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "BANK_CARD")
	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@OneToMany(mappedBy = "tradeDetail", fetch = FetchType.EAGER)
	public Set<MerchantComment> getMerchantComments() {
		return merchantComments;
	}

	public void setMerchantComments(Set<MerchantComment> merchantComments) {
		this.merchantComments = merchantComments;
	}

	/**
	 * 判断是否有评论
	 * 
	 * @return
	 */
	@Transient
	public boolean isCommented() {
		if (CollectionUtils.isEmpty(this.getMerchantComments()) || this.getMerchantComments().size() == 0) {

			return false;
		}
		return true;
	}

	/**
	 * 取得消费对应的评论
	 * 
	 * @return
	 */
	@Transient
	public MerchantComment getMerchantComment() {
		if (this.getMerchantComments() == null || this.getMerchantComments().size() == 0) {
			return null;
		}
		return this.getMerchantComments().iterator().next();
	}

}
