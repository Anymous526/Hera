/*
 * @(#)MemberStatusLog.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.domain;

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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.member.domain.Member.Status;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 会员状态变更日志
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.Member$Status") }) })
@Entity
@Table(name="MEMBER_LOG")
public class MemberStatusLog extends BaseEntity{
	
	/*** 编号*/
	private Long id;
	
	/*** 会员*/
	private Member member;
	
	/*** 开始状态*/
	private Status beginStatus;
	
	/*** 结束状态*/
	private Status endStatus;
	
	/*** 操作员*/
	private Operator operator;
	
	/*** 创建时间*/
	private Date createDate;
	
	/*** 变更描述*/
	private String description;
	

	@Override
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MEMBER_LOG") })
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

	@Type(type = "status")
	@Column(name="START_STATUS")
	public Status getBeginStatus() {
		return beginStatus;
	}


	public void setBeginStatus(Status beginStatus) {
		this.beginStatus = beginStatus;
	}

	@Type(type = "status")
	@Column(name="END_STATUS")
	public Status getEndStatus() {
		return endStatus;
	}


	public void setEndStatus(Status endStatus) {
		this.endStatus = endStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATOR_ID")
	public Operator getOperator() {
		return operator;
	}


	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


}

