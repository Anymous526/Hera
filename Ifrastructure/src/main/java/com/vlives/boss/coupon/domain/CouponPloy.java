/**
 * @(#)CouponPloy.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 电子券活动
 * @author  jianguo.xu
 * @version 1.0,2011-9-6
 */

@TypeDefs({ @TypeDef(name = "couponType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.CouponType") }),
	@TypeDef(name = "couponPloyType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.CouponPloyType") }),
	@TypeDef(name = "couponPloyStatus", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.CouponPloy$CouponPloyStatus") })
})
@Entity
@Table(name="COUPON_PLOY")
public class CouponPloy extends BaseEntity{
	
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 活动创建的商户
	 */
	private Merchant merchant;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 电子券活动类型
	 */
	private CouponPloyType couponPloyType;
	/**
	 * 电子券类型
	 */
	private CouponType couponType;
	/**
	 * 电子券活动状态
	 */
	private CouponPloyStatus couponPloyStatus;
	/**
	 * 电子券有效开始日期
	 */
	private Date validStartDate;
	/**
	 * 电子券有效结束日期
	 */
	private Date validEndDate;
	/**
	 * 活动发送开始日期
	 */
	private Date sendStartDate;
	/**
	 * 活动发送截至日期
	 */
	private Date sendEndDate;
	/**
	 * 券面值
	 */
	private String couponValue;
	/**
	 * 活动创建日期
	 */
	private Date createDate;
	/**
	 * 当前状态的变更描述
	 */
	private String currentStatusDesc;
	/**
	 * 是否在网站展示
	 */
	private boolean displayInWeb;
	/**
	 * 活动允许发送的最大数量,如果为null表示无限制
	 */
	private Long maxSendCount;
	/**
	 * 已发送数量
	 */
	private long sentCount;
	/**
	 * 已验票数
	 */
	private long validateCount;
	
	/**
	 * 用户可领用优惠券的最大数量,如果为0，
	 * 表示不能领用
	 */
	private long maxReceiveCountByEveryUser;
	
	/**
	 * 优惠券活动介绍
	 */
	private String introduction;
	
	private Set<CouponPloyLog> couponPloyLogs;
	/**
	 * 优惠券活动适用的商店
	 */
	private Set<CouponPloyApplyMerchant> couponPloyApplyMerchants;
	
	/**
	 * 商户扩展信息 用一对多代替一对一
	 */
	private Set<CouponSendRule> couponSendRules;
	
	/**
	 * 电子券活动状态
	 * @author tiger
	 *
	 */
	public static enum CouponPloyStatus implements EnumTypeInterface {
		/** 待审核状态*/
		WAIT_AUDIT(0, "创建待审核"),
		/** 审核通过，活动进行中*/
		AUDIT_SUCCESS(1, "活动进行中"),
		/** 审核未通过*/
		AUDIT_FAIL(2, "审核未通过"),
		/** 发送失败 */
		PAUSE(3, "暂停"),
		/** 注销 */
		LOGOUT(4, "删除"),
		/** 活动结束 */
		COMPLETE(5, "活动结束");
		private int value;
		private String desc;
		 
		CouponPloyStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static CouponPloyStatus get(int value) {
			for (CouponPloyStatus type : CouponPloyStatus.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
		
		public static List<CouponPloyStatus> valusBySelect() {
			List<CouponPloyStatus> list = new ArrayList<CouponPloy.CouponPloyStatus>();
			for (CouponPloyStatus type : CouponPloyStatus.values()) {
				 if(type!=LOGOUT)
					 list.add(type);
			}
			return list;
		}
		
	}
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON_PLOY") })
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="PLOY_TYPE")
	@Type(type="couponPloyType")
	public CouponPloyType getCouponPloyType() {
		return couponPloyType;
	}
	
	public void setCouponPloyType(CouponPloyType couponPloyType) {
		this.couponPloyType = couponPloyType;
	}
	
	@Column(name="COUPON_TYPE")
	@Type(type="couponType")
	public CouponType getCouponType() {
		return couponType;
	}
	
	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}
	
	@Column(name="STATUS")
	@Type(type="couponPloyStatus")
	public CouponPloyStatus getCouponPloyStatus() {
		return couponPloyStatus;
	}
	
	public void setCouponPloyStatus(CouponPloyStatus couponPloyStatus) {
		this.couponPloyStatus = couponPloyStatus;
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
	
	@Column(name="SEND_START_DATE")
	public Date getSendStartDate() {
		return sendStartDate;
	}
	
	public void setSendStartDate(Date sendStartDate) {
		this.sendStartDate = sendStartDate;
	}
	@Column(name="COUPON_VALUE")
	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="CURRENT_STATUS_DESC")
	public String getCurrentStatusDesc() {
		return currentStatusDesc;
	}
	
	public void setCurrentStatusDesc(String currentStatusDesc) {
		this.currentStatusDesc = currentStatusDesc;
	}
	@Column(name="DISPLAY_WEB")
	public boolean isDisplayInWeb() {
		return displayInWeb;
	}
	
	public void setDisplayInWeb(boolean displayInWeb) {
		this.displayInWeb = displayInWeb;
	}
	
	@Column(name="MAX_SEND_COUNT")
	public Long getMaxSendCount() {
		return maxSendCount;
	}
	
	public void setMaxSendCount(Long maxSendCount) {
		this.maxSendCount = maxSendCount;
	}
	
	@Column(name="SENT_COUNT")
	public long getSentCount() {
		return sentCount;
	}
	
	public void setSentCount(long sentCount) {
		this.sentCount = sentCount;
	}
	@Column(name="VALIDATE_COUNT")
	public long getValidateCount() {
		return validateCount;
	}
	
	public void setValidateCount(long validateCount) {
		this.validateCount = validateCount;
	}
	@Column(name="MAX_WEB_RECEIVE_COUNT")
	public long getMaxReceiveCountByEveryUser() {
		return maxReceiveCountByEveryUser;
	}

	public void setMaxReceiveCountByEveryUser(long maxReceiveCountByEveryUser) {
		this.maxReceiveCountByEveryUser = maxReceiveCountByEveryUser;
	}

	@Column(name="INTRODUCTION")
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	 
	
	@OneToMany(mappedBy = "couponPloy", cascade = { CascadeType.ALL })
	@OrderBy("id desc")
	public Set<CouponPloyLog> getCouponPloyLogs() {
		return couponPloyLogs;
	}

	public void setCouponPloyLogs(Set<CouponPloyLog> couponPloyLogs) {
		this.couponPloyLogs = couponPloyLogs;
	}
	@OneToMany(mappedBy = "couponPloy", cascade = { CascadeType.ALL })
	@OrderBy("id")
	public Set<CouponPloyApplyMerchant> getCouponPloyApplyMerchants() {
		return couponPloyApplyMerchants;
	}

	public void setCouponPloyApplyMerchants(
			Set<CouponPloyApplyMerchant> couponPloyApplyMerchants) {
		this.couponPloyApplyMerchants = couponPloyApplyMerchants;
	}
	
	@Column(name="SEND_END_DATE")
	public Date getSendEndDate() {
		return sendEndDate;
	}

	public void setSendEndDate(Date sendEndDate) {
		this.sendEndDate = sendEndDate;
	}

	@SuppressWarnings("unused")
	@OneToMany(mappedBy = "couponPloy", cascade = { CascadeType.ALL })
	private Set<CouponSendRule> getCouponSendRules() {
		return couponSendRules;
	}
	@SuppressWarnings("unused")
	private void setCouponSendRules(Set<CouponSendRule> couponSendRules) {
		this.couponSendRules = couponSendRules;
	}
	@Transient
	public CouponSendRule getCouponSendRule() {
		if (couponSendRules == null || couponSendRules.size() == 0)
			return null;
		return couponSendRules.iterator().next();
	}

	public void setCouponSendRule(CouponSendRule couponSendRule) {
		couponSendRules = new HashSet<CouponSendRule>();
		couponSendRules.add(couponSendRule);
		couponSendRule.setCouponPloy(this);
	}
  

	/**
	 * 添加状态变更日志
	 * 
	 * @param operator
	 *            操作者
	 * @param startStatus
	 *            开始状态
	 * @param endStatus
	 *            结束状态
	 * @param desc
	 *            变更原因
	 */
	@Transient
	public void addStatusLog(Operator operator, CouponPloyStatus startStatus, CouponPloyStatus endStatus, String desc) {
		CouponPloyLog log = new CouponPloyLog();
		log.setStartStatus(startStatus);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setEndStatus(endStatus);
		log.setCouponPloy(this);
		log.setOperator(operator);
		if (couponPloyLogs == null) {
			this.couponPloyLogs = new HashSet<CouponPloyLog>();
		}
		couponPloyLogs.add(log);
	}
	

	@Transient
	public boolean isActive() {
		return this.getCouponPloyStatus() == CouponPloyStatus.AUDIT_SUCCESS;
	}
	
	@Transient
	public boolean isPause() {
		return this.getCouponPloyStatus() == CouponPloyStatus.PAUSE;
	}
	
	@Transient
	public boolean isCanLogout() {
		return this.getCouponPloyStatus() == CouponPloyStatus.WAIT_AUDIT || this.getCouponPloyStatus() == CouponPloyStatus.AUDIT_FAIL;
	}
	/**
	 * 判断是否可以修改
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isCanModify() {
		return this.getCouponPloyStatus() == CouponPloyStatus.AUDIT_FAIL;
	}
	
	/**
	 * 判断是否审核未通过
	 * 
	 * @return
	 */
	@Transient
	public boolean isAduitFail() {
		return this.getCouponPloyStatus() == CouponPloyStatus.AUDIT_FAIL;
	}
	/**
	 * 判断活动是否结束
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isComPlete() {
		return this.getCouponPloyStatus() == CouponPloyStatus.COMPLETE||isExpired();
	}
	/**
	 * 判断活动是否过期
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isExpired() {
		return new Date().compareTo(this.getValidEndDate())>0;
	}
	
	@Transient
	public boolean isAduitWait() {
		return this.getCouponPloyStatus() == CouponPloyStatus.WAIT_AUDIT;
	}
	 
	public void  increaseSentCount(){
		this.sentCount++;
	}
	
	@Transient
	public String getApplyMerchantName() {
		StringBuffer merchantNameStr = new StringBuffer();
		for(CouponPloyApplyMerchant merchant :this.getCouponPloyApplyMerchants()) {
			merchantNameStr.append(merchant.getMerchant().getName());
			merchantNameStr.append(",");
		}
		return merchantNameStr.toString();
	}
	/**
	 * 得到 优惠券的使用率（已使用张数/实际发送张数）<br/>
	 * 返回百分比
	 */
	@Transient
	public BigDecimal getCouponUseRate(){
		if(this.getValidateCount() ==0||this.getSentCount()==0) return BigDecimal.ZERO;
		return new BigDecimal(this.getValidateCount()).divide(new BigDecimal(this.getSentCount()), 4,RoundingMode.HALF_UP).movePointRight(2);
	}
	
}
