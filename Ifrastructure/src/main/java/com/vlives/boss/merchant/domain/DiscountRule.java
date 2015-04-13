/*
 * @(#)PointRole.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.merchant.domain;

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

import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 折扣规则
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.DiscountRule$Type") }) })
@Entity
@Table(name="DISCOUNT_RULE")
public class DiscountRule extends BaseEntity{

	
	/*** 编号*/
	private Long id;
	
	/*** 商户*/
	private Merchant merchant;
	
	/*** 类型*/
	private Type type;
	
	/***第一个参数*/
	private int paramerOne;
	
	/*** 第二个参数*/
	private int paramerTwo;
	
	public static enum Type implements EnumTypeInterface {
		/** 按等级打折*/
		BY_MEMBER_LEVEL(1, "按等级打折"),
		/** 按消费金额打折*/
		BY_CONSUME_MONEY(2, "按消费金额打折"),
		/** 按消费次数打折 */
		TYPE_CONSUME_COUNT(3, "按消费次数打折");

		private final int value;
		private final String desc;

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
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_DISCOUNT_RULE") })
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

	@org.hibernate.annotations.Type(type="type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name="PARAMTER_ONE")
	public int getParamerOne() {
		return paramerOne;
	}

	public void setParamerOne(int paramerOne) {
		this.paramerOne = paramerOne;
	}

	@Column(name="PARAMTER_TWO")
	public int getParamerTwo() {
		return paramerTwo;
	}

	public void setParamerTwo(int paramerTwo) {
		this.paramerTwo = paramerTwo;
	}
	
	
}

