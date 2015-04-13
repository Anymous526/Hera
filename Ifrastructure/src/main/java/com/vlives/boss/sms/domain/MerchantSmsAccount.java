/**
 * @(#)MerchantSmsAccount.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sms.domain;

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

import com.vlives.boss.merchant.domain.Merchant;

/**
 * 短线账户
 * @author  jianguo.xu
 * @version 1.0,2011-6-19
 */
@Entity
@Table(name="SMS_ACCOUNT")
public class MerchantSmsAccount {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 对应商户
	 */
	private Merchant merchant;
	/**
	 * 总数量
	 */
	private int totalCount;
	/**
	 * 消费的短信数
	 */
	private int consumeCount; 
	/**
	 * 系统赠送数量
	 */
	private int sysPresenCount;
	/**
	 * 商户购买数量
	 */
	private int merchantBuyCount;
 
	 
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SMS_ACCOUNT") })
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
	@Column(name="TOTAL_COUNT")
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Column(name="CONSUME_COUNT")
	public int getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}
	@Column(name="SYS_PRESEN_COUNT")
	public int getSysPresenCount() {
		return sysPresenCount;
	}
	public void setSysPresenCount(int sysPresenCount) {
		this.sysPresenCount = sysPresenCount;
	}
	@Column(name="MERCHANT_BUY_COUNT")
	public int getMerchantBuyCount() {
		return merchantBuyCount;
	}
	public void setMerchantBuyCount(int merchantBuyCount) {
		this.merchantBuyCount = merchantBuyCount;
	}
	 
	/**
	 * 得到剩余的短信数
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public int getRemainCount() {
		return this.getTotalCount() - this.getConsumeCount();
	}
}
