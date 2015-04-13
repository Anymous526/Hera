/*
 * @(#)SalePloy.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sale.domain;

import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.member.domain.ActiveRate;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;
import com.vlives.util.StringUtils;

/**
 * 营销活动
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sale.domain.SalePloy$Status") }),
	@TypeDef(name = "salePloyType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sale.domain.SalePloy$SalePloyType") }),
	@TypeDef(name = "activeRate", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.ActiveRate") }),
	@TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sale.domain.SalePloy$Type") })})
@Entity
@Table(name="SALE_PLOY")
public class SalePloy {
	/*** 编号*/
	private Long id;
	
	/*** 活动名称(活动主题)*/
	private String name;
	
	/*** 商户*/
	private Merchant merchant;
	/**
	 * 有效开始日期
	 */
	private Date validStartDate;
	/**
	 * 有效结束日期
	 */
	private Date validEndDate;
	/**
	 * 适用门店如果是父店则填充改字段，否则（独立门店和子门店）不填充
	 */
	private String applyMerchant;
 
	/**创建类型*/
	private Type type;
	/*** 状态*/
	private Status status;
	
	/*** 等级范围*/
	private String memberLevel;
	
	/*** 最小积分数*/
	private Integer minPoint;
	
	/*** 最大积分数*/
	private Integer maxPoint;
	
	/*** 活跃度(最近没有有交易的会员)*/
	private ActiveRate activeRate;
	
	/*** 内容模板*/
	private String template;
	/**
	 * 营销活动类型
	 */
	private SalePloyType salePloyType;
	
	/**
	 * 营销活动类型为单次消费满额对应的最小金额
	 */
	private BigDecimal tradeMinMoney;
	/** 
	 * 营销活动类型定时发送的发送时间
	 */
	private Date timingTime;
	
	/*** 预计发送短信数*/
	private int smsCount;
	/**
	 * 已发送数
	 */
	private int sendCount;
	private Date createDate;
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
	@Column(name="APPLY_MERCHANT")
	public String getApplyMerchant() {
		return applyMerchant;
	}

	public void setApplyMerchant(String applyMerchant) {
		this.applyMerchant = applyMerchant;
	}
	private Set<SalePloyLog> salePloyLogs;
	/**
	 * 营销活动类型
	 * @author tiger
	 *
	 */
	public static enum SalePloyType implements EnumTypeInterface {
		/**
		 * 审核通过自动发送
		 */
		AUDIT_PASS_SEND(1,true, "自动发送"),
		/**
		 * 定时发送
		 */
		TIMING_SEND(2, true,"定时发送"),
		/**
		 * 会员注册发送
		 */
		CREATE_MEMBER_SEND(3, false,"注册会员发送"),
		/**
		 * 单次消费满额发送
		 */
		TRADE_MONEY_SEND(4, false,"单次消费满额发送");
		private final int value;
		/**
		 * 是否是单次营销活动
		 */
		private final boolean singlePloy;
		private final String desc;

		SalePloyType(int value,boolean singlePloy, String desc) {
			this.value = value;
			this.singlePloy = singlePloy;
			this.desc = desc;
		}
		
		public boolean isSinglePloy() {
			return singlePloy;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static SalePloyType get(int value) {
			for (SalePloyType type : SalePloyType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}
	/**
	 * 创建类型
	 * @author tiger
	 *
	 */
	public static enum Type implements EnumTypeInterface {
		MERCHANT_CREATE(0, "商户自建"),
		SYS_CREATE(1, "系统代建");

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
	
	
	public static enum Status implements EnumTypeInterface {
		/** 待审核状态*/
		WAIT_AUDIT(0, "创建待审核"),
		/** 审核通过待发送*/
		AUDIT_SUCCESS(1, "审核通过"),
		/** 审核未通过*/
		AUDIT_FAIL(2, "审核未通过"),
		/** 发送成功 */
		SEND_SUCCESS(3, "发送中"),
		/** 发送失败 */
		SEND_FAIL(4, "发送失败"),
		/** 注销 */
		LOGOUT(5, "注销"),
		/** 发送完成 */
		SEND_COMPLETE(6, "发送完成"),
		/** 已过期 */
		EXPIREDED(7, "已过期");
		private int value;
		private String desc;

		Status(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static Status get(int value) {
			for (Status status : Status.values()) {
				if (status.value == value) {
					return status;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}

	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SALE_PLOY") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@org.hibernate.annotations.Type(type="status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name="MEMBER_LEVEL")
	@SuppressWarnings("unused")
	private String getMemberLevel() {
		return memberLevel;
	}

	@SuppressWarnings("unused")
	private void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Column(name="MEMBER_MIN_POINT")
	public Integer getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(Integer minPoint) {
		this.minPoint = minPoint;
	}

	@Column(name="MEMBER_MAX_POINT")
	public Integer getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(Integer maxPoint) {
		this.maxPoint = maxPoint;
	}

	@org.hibernate.annotations.Type(type="activeRate")
	@Column(name="ACTIVE_RATE")
	public ActiveRate getActiveRate() {
		return activeRate;
	}

	public void setActiveRate(ActiveRate activeRate) {
		this.activeRate = activeRate;
	}

	@Column(name="TEMPLATE")
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	
	@Column(name="TRADE_MIN_MONEY")
	public BigDecimal getTradeMinMoney() {
		return tradeMinMoney;
	}

	public void setTradeMinMoney(BigDecimal tradeMinMoney) {
		this.tradeMinMoney = tradeMinMoney;
	}

	@Column(name="TIMING_TIME")
	public Date getTimingTime() {
		return timingTime;
	}

	public void setTimingTime(Date timingTime) {
		this.timingTime = timingTime;
	}

	@Column(name="SMS_COUNT")
	public int getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="salePloy",cascade={CascadeType.ALL})
	public Set<SalePloyLog> getSalePloyLogs() {
		return salePloyLogs;
	}

	public void setSalePloyLogs(Set<SalePloyLog> salePloyLogs) {
		this.salePloyLogs = salePloyLogs;
	}
	@Column(name="SEND_COUNT")
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	
	@org.hibernate.annotations.Type(type="salePloyType")
	@Column(name="SALE_PLOY_TYPE")
	public SalePloyType getSalePloyType() {
		return salePloyType;
	}

	public void setSalePloyType(SalePloyType salePloyType) {
		this.salePloyType = salePloyType;
	}

	/**
	 * 添加营销活动变更日志
	 * @param operator
	 * @param endLevel
	 * @param desc
	 */
	@Transient
	public void addSalePloyLog(Operator operator,Status startStatus, Status endStatus,String desc) {
		if(this.salePloyLogs ==null) this.salePloyLogs = new HashSet<SalePloyLog>();
		SalePloyLog log = new SalePloyLog();
		log.setBeginStatus(startStatus);
		log.setEndStatus(endStatus);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setSalePloy(this);
		log.setOperator(operator);
		this.salePloyLogs.add(log);
		
	}
	
	/**
	 * 判断营销活动是否待审核状态
	 * @return
	 */
	@Transient
	public boolean isWaitAudit(){
		return this.getStatus() == Status.WAIT_AUDIT;
	}
	/**
	 * 判断营销活动是否审核通过状态
	 * @return
	 */
	@Transient
	public boolean isAuditSuccess(){
		return this.getStatus() == Status.AUDIT_SUCCESS;
	}
	/**
	 * 是否能够注销
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isCanLogOut() {
		return isAuditSuccess()||isAuditFail()||isWaitAudit();
	}
	
	/**
	 * 判断营销活动是否待审核状态
	 * @return
	 */
	@Transient
	public boolean isAuditFail(){
		return this.getStatus() == Status.AUDIT_FAIL;
	}
	
	/**
	 * 判断营销活动是否发送成功状态
	 * @return
	 */
	@Transient
	public boolean isSendSuccess(){
		return this.getStatus() == Status.SEND_SUCCESS;
	}
	
	/**
	 * 判断营销活动是否待发送失败状态
	 * @return
	 */
	@Transient
	public boolean isSendFail(){
		return this.getStatus() == Status.SEND_FAIL;
	}
	
	/**
	 * 判断营销活动是否注销状态
	 * @return
	 */
	@Transient
	public boolean isLogout(){
		return this.getStatus() == Status.LOGOUT;
	}
	
	/**
	 * 得到活动对应的会员等级范围
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public List<Level> getMemberLevels() {
		List<Level> levels = new ArrayList<Level>();
		if(StringUtils.isNullOrEmpty(this.memberLevel)) return levels;
		String[] levelStrs = this.memberLevel.split("_");
		for(String levelStr : levelStrs) {
			int value = Integer.parseInt(levelStr);
			Level level = Level.get(value);
			levels.add(level);
		}
		return levels;
	}
	
	/**
	 * 获得等级范围描述 
	 * @return
	 */
	@Transient
	public String getMemberLevelDesc(){
		List<Level> levels =  getMemberLevels();
		 
		String levelDesc = "";
		if(levels != null && levels.size()>0){
			if(levels.size()==4) return "全部等级 ";
			for (Level level : levels) {
				levelDesc +=level.getDesc()+" ";
			}
		}
		return levelDesc;
	}

	public void setMemberLevels(List<Level> memberLevels) {
		if(memberLevels ==null||memberLevels.size() ==0) 
			this.memberLevel = null;
		StringBuilder sb = new StringBuilder();
		for(Level level : memberLevels) {
			sb.append(level.getValue()+"_");
		}
		this.memberLevel = sb.substring(0, sb.length()-1);
	}

	/**
	 * 是否是定时发送
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isTimingSend() {
		return this.salePloyType == SalePloyType.TIMING_SEND;
	}
	/**
	 * 是否是单次消费满额发送
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isTradeMoneySend() {
		return this.salePloyType == SalePloyType.TRADE_MONEY_SEND;
	}

	
}

