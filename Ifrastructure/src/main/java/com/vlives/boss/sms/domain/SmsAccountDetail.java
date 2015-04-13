/*
 * @(#)SmsAccountDetail.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sms.domain;

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

import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 短信账户明细
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sms.domain.SmsAccountDetail$Type") }) })
@Entity
@Table(name="SMS_ACCOUNT_DETAIL")
public class SmsAccountDetail extends BaseEntity{

	/*** 编号*/
	private Long id;
	
	/*** 商户*/
	private MerchantSmsAccount merchantSmsAccount;
	
	/*** 变更类型*/
	private Type type;
	
	/*** 变更数量*/
	private int quantity;
	
	/*** 变更描述*/
	private String description;
	
	private Date createDate;
	
	private Operator operator;
	
	public static enum Type implements EnumTypeInterface {
		/** 赠送短信*/
		TYPE_PRESEN(1, "系统赠送短信",true),
		/** 购买短信*/
		TYPE_BUY(2, "商户购买短信",true),
		/** 创建营销活动减少短信 */
		TYPE_DECREASE_BY_PLOY(3, "创建营销活动减少短信",false),
		/** 取消营销活动增加短信*/
		TYPE_CANCEL_PLOY(4, "取消营销活动增加短信",true),
		/** 审核营销活动失败增加短信*/
		TYPE_AUDIT_FAIL(5, "审核营销活动失败增加短信",true),
		/** 商户临时发送短信*/
		TYPE_TEMP_SEND(6, "商户临时发送短信",false),
		/** 短信发送完成后回复未发送完的短信数*/
		TYPE_BACK_REMAIN(7, "恢复未发送完的短信数",true),
		/**电子卷发送*/
		TYPE_COUPON_SEND(8,"发送电子券减少短信",false);
		
		
		private int value;
		private boolean addSms;
		private String desc;

		Type(int value, String desc,boolean addSms) {
			this.value = value;
			this.desc = desc;
			this.addSms = addSms;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}
		
		public boolean isAddSms() {
			return addSms;
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
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SMS_ACCOUNT_DETAIL") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SMS_ACCOUNT_ID")
	public MerchantSmsAccount getMerchantSmsAccount() {
		return merchantSmsAccount;
	}

	public void setMerchantSmsAccount(MerchantSmsAccount merchantSmsAccount) {
		this.merchantSmsAccount = merchantSmsAccount;
	}

	@org.hibernate.annotations.Type(type = "type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name="COUNT")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OPERATOR_ID")
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}

