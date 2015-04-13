/*
 * @(#)MemberPointDetail.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.domain;

import java.util.Date;
import java.util.Iterator;

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
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.PointRule;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 会员积分明细
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.MemberPointDetail$Type") }) })
@Entity
@Table(name = "MEMBER_POINT_DETAIL")
public class MemberPointDetail extends BaseEntity {

	/** 编号 */
	private Long id;

	/** 交易订单 */
	private TradeOrder tradeOrder;

	/**
	 * 商户
	 */
	private Merchant merchant;

	/** 会员 */
	private Member member;

	/** 积分 */
	private int point;

	/** 能使用的积分 */
	private int usablePoint;

	/** 总积分 */
	private int totalPoint;

	/** 积分变更类型 */
	private Type type;

	/** 描述 */
	private String description;

	/** 创建日期 */
	private Date createDate;

	public static enum Type implements EnumTypeInterface{
		/** 交易增加积分*/
		TYPE_REGISTER(1,true, "交易增加积分"),
		/** 退货减少积分*/
		TYPE_BUY_POINT(2,false, "退货减少积分"),
		/** 营销活动增加积分 */
		TYPE_CANCEL_POLY(3,true, "营销活动增加积分"),
		/** 商户赠送*/
		TYPE_ALL_CONSUME(4,true, "商户赠送"),
		/** 会员绑定初始积分*/
		TYPE_INIT_POINT(5,true,"会员绑定初始积分");

		private final int value;
		private final boolean addPoint;
		private final String desc;

		Type(int value,  boolean addPoint,String desc) {
			this.value = value;
			this.addPoint = addPoint;
			this.desc = desc;
		}
		
		public boolean isAddPoint() {
			return addPoint;
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
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MEMBER_POINT_DETAIL") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_ORDER_ID")
	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "POINT")
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Column(name = "USEABLE_POINT")
	public int getUsablePoint() {
		return usablePoint;
	}

	public void setUsablePoint(int usablePoint) {
		this.usablePoint = usablePoint;
	}

	@Column(name = "TOTAL_POINT")
	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

	@org.hibernate.annotations.Type(type = "type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Transient
	public int getLevel() {
		int level = 1;

		Iterator<PointRule> iterator = merchant.getPointRules().iterator();
		while(iterator.hasNext()) {
			PointRule rule = iterator.next();
			if (this.point > rule.getParamerTwo() && level < rule.getParamerOne()) {
				level = rule.getParamerOne();
			}
		}
		
		return level;
	}
	
	@Transient
	public String getLevelDesc() {
		int level = this.getLevel();
		switch (level) {
		case 2:
			return "银卡";
		case 3:
			return "金卡";
		case 4:
			return "钻石";
		default:
			return "普通";
		}
	}
}
