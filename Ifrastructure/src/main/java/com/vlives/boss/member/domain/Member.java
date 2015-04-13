/*
 * @(#)Member.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.domain;

import java.util.Date;
import java.util.HashSet;
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
import com.vlives.boss.user.domain.User;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 会员
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.Member$Status") }),
	@TypeDef(name = "level", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.Level") }),
	@TypeDef(name = "memberSource", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.MemberSource") }) })
@Entity
@Table(name="MEMBER")
public class Member extends BaseEntity{

	/*** 编号*/
	private Long id;
	
	/***用户*/
	private User user;
	
	/*** 创建该会员的商户*/
	private Merchant createMerchant;
	
	/***商户会员组*/
	private MemberGroup memberGroup;
	
	/*** 等级*/
	private Level level;
	
	/*** 状态 */
	private Status status;
	
	/*** 可用积分*/
	private int point;
	
	/*** 总积分*/
	private int totalPoint;
	
	/*** 创建时间*/
	private Date createDate;
	/**
	 * 会员最后消费日期
	 */
	private Date lastConsumeDate;
	
	private Set<MemberStatusLog> statusLogs;
	
	private Set<MemberLevelLog> levelLogs;
	
	/** 会员来源*/
	private MemberSource source;
	
	public static enum Status implements EnumTypeInterface {
		/** 有效状态*/
		STATUS_ACTIVE(1, "有效"),
		/** 冻结状态*/
		STATUS_FREEZE(3, "冻结"),
		/** 注销 */
		STATUS_LOGOUT(0, "注销");

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
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MEMBER") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE})
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getCreateMerchant() {
		return createMerchant;
	}

	public void setCreateMerchant(Merchant createMerchant) {
		this.createMerchant = createMerchant;
	}
	
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE} )
	@JoinColumn(name = "MEMBER_GROUP_ID")
	public MemberGroup getMemberGroup() {
		return memberGroup;
	}

	public void setMemberGroup(MemberGroup memberGroup) {
		this.memberGroup = memberGroup;
	}

	@Type(type="level")
	@Column(name="MEMBER_LEVEL")
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Type(type = "status")
	public Status getStatus() {
		return status;
	}

	

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name="CAN_USE_POINT")
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Column(name="TOTAL_POINT")
	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="LAST_CONSUME_DATE")
	public Date getLastConsumeDate() {
		return lastConsumeDate;
	}

	public void setLastConsumeDate(Date lastConsumeDate) {
		this.lastConsumeDate = lastConsumeDate;
	}
	
	@OneToMany(mappedBy = "member",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@OrderBy("id")
	public Set<MemberStatusLog> getStatusLogs() {
		return statusLogs;
	}

	public void setStatusLogs(Set<MemberStatusLog> statusLogs) {
		this.statusLogs = statusLogs;
	}

	@OneToMany(mappedBy = "member",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	public Set<MemberLevelLog> getLevelLogs() {
		return levelLogs;
	}

	public void setLevelLogs(Set<MemberLevelLog> levelLogs) {
		this.levelLogs = levelLogs;
	}
	
	@Type(type="memberSource")
	@Column(name="SOURCE")
	public MemberSource getSource() {
		return source;
	}

	public void setSource(MemberSource source) {
		this.source = source;
	}

	/**
	 * 添加状态更改日志
	 * @param operator 操作这
	 * @param startStatus 开始状态
	 * @param endStatus 结束状态
	 * @param desc 变更原因
	 */
	@Transient
	public void addStatusLog(Operator operator,Status startStatus,Status endStatus,String desc) {
		MemberStatusLog log = new MemberStatusLog();
		log.setBeginStatus(startStatus);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setEndStatus(endStatus);
		log.setMember(this);
		log.setOperator(operator);
		if(statusLogs ==null)
			statusLogs = new HashSet<MemberStatusLog>();
		statusLogs.add(log);
	}
	
	/**
	 * 添加等级变更日志 
	 * @param operator 操作这
	 * @param startLevel 开始等级
	 * @param endLevel 结束等级
	 * @param desc 变更描述
	 */
	@Transient
	public void addLevelLog(Operator operator,Level startLevel,Level endLevel,String desc) {
		MemberLevelLog log = new MemberLevelLog();
		log.setBeginLevel(startLevel);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setEndLevel(endLevel);
		log.setMember(this);
		log.setOperator(operator);
		if(levelLogs == null) {
			levelLogs = new HashSet<MemberLevelLog>();
		}
		levelLogs.add(log);
	}
	
	@Transient
	public void addLevelLog(Operator operator, Level endLevel,String desc){
		 this.addLevelLog(operator, this.level, endLevel, desc);
	}

	/**
	 * 判断会员是否有效
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isEnable(){
		return this.getStatus() == Status.STATUS_ACTIVE;
	}
	/**
	 * 判断会员是否冻结
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isFreeze(){
		return this.getStatus() == Status.STATUS_FREEZE;
	}
	
	/**
	 * 判断会员是否注销
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isLogOut(){
		return this.getStatus() == Status.STATUS_LOGOUT;
	}
	 
	/**
	 * 判断会员是否消费过
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isConsume() {
		return lastConsumeDate!=null;
	}
	/**
	 * 得到会员活跃度
	 * 如果是活跃用户就返回null
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public ActiveRate getActiveRate() {
		return ActiveRate.getByDate(lastConsumeDate);
		 
	}
	
	/**
	 * 增加积分
	 */
	@Transient
	public void increasePoint(int point){
		this.point+=point;
		this.totalPoint+=point;
	}
	/**
	 * 减少积分
	 */
	@Transient
	public void decrease(int point){
		this.point-=point;
		this.totalPoint-=point;
	}
}

