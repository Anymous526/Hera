/*
 * @(#)SalePloyLog.java
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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sale.domain.SalePloy.Status;
import com.vlives.core.support.hibernate.EnumType;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sale.domain.SalePloy$Status") })})

@Entity
@Table(name="SALE_PLOY_LOG")
public class SalePloyLog {

	/** 编号*/
	private Long id;
	
	/**营销活动*/
	private SalePloy salePloy;
	
	/**开始状态*/
	private Status beginStatus;
	
	/**结束状态*/
	private Status endStatus;
	
	/**创建时间*/
	private Date createDate;
	
	/**操作员*/
	private Operator operator;
	
	/**描述*/
	private String description;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SALE_PLOY_LOG") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALE_PLOY_ID")
	public SalePloy getSalePloy() {
		return salePloy;
	}

	public void setSalePloy(SalePloy salePloy) {
		this.salePloy = salePloy;
	}

	@Type(type="status")
	@Column(name="START_STATUS")
	public Status getBeginStatus() {
		return beginStatus;
	}

	public void setBeginStatus(Status beginStatus) {
		this.beginStatus = beginStatus;
	}

	@Type(type="status")
	@Column(name="END_STATUS")
	public Status getEndStatus() {
		return endStatus;
	}

	public void setEndStatus(Status endStatus) {
		this.endStatus = endStatus;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATOR_ID")
	public Operator getOperator() {
		return operator;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}

