/*
 * @(#)TradeOrder.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 交易订单
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.trade.domain.TradeOrder$Type") }) })
@Entity
@Table(name = "TRADE_ORDER")
public class TradeOrder extends BaseEntity {

	/** 编号 */
	private Long id;

	/** 会员 */
	private Member member;

	/** 交易类型 */
	private Type type;

	/** 是否是消费交易 */
	private boolean consumeTrade;

	/** 交易金额 */
	private BigDecimal money;

	/** 创建时间 */
	private Date createDate;

	/**
	 * 交易时间
	 */
	private Date tradeDate;

	/**
	 * 商家
	 */
	private Merchant merchant;

	private Set<MemberPointDetail> memberPointDetails;

	public static enum Type implements EnumTypeInterface {
		/** 刷卡交易 */
		TYPE_BRUSH(1, "刷卡交易"),
		/** 现金交易 */
		TYPE_CASH(2, "现金交易"),
		/** 积分交易 */
		TYPE_POINT(3, "积分交易");

		private int value;
		private String desc;

		Type(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static Type get(int value) {
			for (Type type : Type.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TRADE_ORDER") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@org.hibernate.annotations.Type(type = "type")
	@Column(name="TYPE")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
	

	@Column(name = "IS_CONSUME")
	public boolean isConsumeTrade() {
		return consumeTrade;
	}

	public void setConsumeTrade(boolean consumeTrade) {
		this.consumeTrade = consumeTrade;
	}

	@Column(name = "MONEY")
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "TRADE_DATE")
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	/**
	 * 的到消费明细
	 * 
	 * @return
	 */
	@Transient
	public MemberPointDetail getMemberPointDetail() {
		if (memberPointDetails == null || memberPointDetails.size() == 0) {
			MemberPointDetail memberPointDetail = new MemberPointDetail();
			memberPointDetail.setTradeOrder(this);
			this.setMemberPointDetail(memberPointDetail);
			return memberPointDetail;
		}
		return memberPointDetails.iterator().next();
	}
	
	@Transient
	public MemberPointDetail getConsumePointDetail() {
		for (MemberPointDetail memberPointDetail : memberPointDetails) {
			if (memberPointDetail.getType().equals(MemberPointDetail.Type.TYPE_REGISTER)) {
				return memberPointDetail;
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	@OneToMany(mappedBy = "tradeOrder", fetch = FetchType.LAZY)
	private Set<MemberPointDetail> getMemberPointDetails() {
		return memberPointDetails;
	}

	@SuppressWarnings("unused")
	private void setMemberPointDetails(Set<MemberPointDetail> memberPointDetails) {
		this.memberPointDetails = memberPointDetails;
	}

	public void setMemberPointDetail(MemberPointDetail memberPointDetail) {
		this.memberPointDetails = new HashSet<MemberPointDetail>();
		this.memberPointDetails.add(memberPointDetail);
	}

}
