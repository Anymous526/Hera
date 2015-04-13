/*
 * @(#)SalePloyUser.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sale.domain;

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

import com.vlives.boss.member.domain.Member;

/**
 * 营销活动目标用户
 * @author  fyuan
 * @version 1.0,2011-6-1
 */

@Entity
@Table(name="SALE_PLOY_USER")
public class SalePloyUser {

	/*** 编号*/
	private Long id;
	
	/*** 营销活动*/
	private SalePloy salePloy;
	
	/***会员*/
	private Member member;
	
	/*** 是否发送成功*/
	private Boolean sendSuccess;
	
	/***发送短信次数*/
	private int sendCount;
	
	/***发送短信时间*/
	private Date sendDate;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SALE_PLOY_USER") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	//(fetch = FetchType.LAZY)  quaryz要用
	@JoinColumn(name = "SALE_PLOY_ID")
	public SalePloy getSalePloy() {
		return salePloy;
	}

	public void setSalePloy(SalePloy salePloy) {
		this.salePloy = salePloy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name="SUCCESS")
	public Boolean isSendSuccess() {
		return sendSuccess;
	}

	public void setSendSuccess(Boolean sendSuccess) {
		this.sendSuccess = sendSuccess;
	}

	@Column(name="SEND_COUNT")
	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	@Column(name="SEND_DATE")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
	
}

