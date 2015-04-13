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

import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 会员等级变更日志
 * @author  fyuan
 * @version 1.0,2011-6-1
 */

@TypeDefs({@TypeDef(name = "level", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.Level") })})
@Entity
@Table(name="MEMBER_LEVEL_LOG")
public class MemberLevelLog extends BaseEntity{
	
	/*** 编号*/
	private Long id;
	
	/*** 会员*/
	private Member member;
	
	/*** 开始等级*/
	private Level beginLevel;
	
	/*** 结束等级*/
	private Level endLevel;
	
	/*** 操作员*/
	private Operator operator;
	
	/*** 创建时间*/
	private Date createDate;
	
	/*** 变更描述*/
	private String description;
	

	 
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MEMBER_LEVEL_LOG") })
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

	@Type(type = "level")
	@Column(name="START_LEVEL")
	public Level getBeginLevel() {
		return beginLevel;
	}
	public void setBeginLevel(Level beginLevel) {
		this.beginLevel = beginLevel;
	}

	@Type(type = "level")
	@Column(name="END_LEVEL")
	public Level getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(Level endLevel) {
		this.endLevel = endLevel;
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

