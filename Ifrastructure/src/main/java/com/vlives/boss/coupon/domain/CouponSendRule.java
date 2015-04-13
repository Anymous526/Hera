/**
 * @(#)CouponSendRule.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

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

import com.vlives.boss.coupon.domain.rulesupport.SendRule;
import com.vlives.boss.coupon.domain.rulesupport.SendRuleHibernateType;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 电子券发放规则
 * 
 * @author jianguo.xu
 * @version 1.0,2011-9-6
 */
@TypeDefs({ @TypeDef(name = "ruleType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.CouponSendRule$RuleType") }),
	@TypeDef(name = "sendRule", typeClass = SendRuleHibernateType.class)	
})

 
@Entity
@Table(name = "COUPON_SEND_RULE")
public class CouponSendRule extends BaseEntity {
	private Long id;
	/**
	 * 电子券活动
	 */
	private CouponPloy couponPloy;
	/**
	 * 规则类型
	 */
	private RuleType ruleType;
	/**
	 * 规则定义
	 */
	private SendRule sendRule;

	/**
	 * 规则类型
	 * 
	 * @author tiger
	 * 
	 */
	public static enum RuleType implements EnumTypeInterface {
		QUOTA_SEND(1, "消费满额发送"), 
		REGISTER_SEND(2, "注册会员发送");
		private int value;
		private String desc;

		RuleType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static RuleType get(int value) {
			for (RuleType type : RuleType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON_SEND_RULE") })
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

	@Type(type = "ruleType")
	@Column(name = "RULE_TYPE")
	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}
	@Type(type="sendRule")
	@Column(name = "RULE_DEFINE")
	public SendRule getSendRule() {
		return sendRule;
	}

	public void setSendRule(SendRule sendRule) {
		this.sendRule = sendRule;
	}
	/**
	 * 得到发送规则的描述信息
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public String getRuleDescription() {
		return this.getSendRule().getRuleDescription(this.getRuleType());
	}
}
