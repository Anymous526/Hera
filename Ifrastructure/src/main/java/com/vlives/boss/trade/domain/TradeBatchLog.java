/*
 * @(#)TradeBatchLog.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.domain;

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
import com.vlives.boss.trade.domain.TradeBatch.Status;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;


@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.trade.domain.TradeBatch$Status") }) })
@Entity
@Table(name="TRADE_BATCH_LOG")
public class TradeBatchLog extends BaseEntity{

	/** 编号*/
	private Long id;
	
	/** 交易批次*/
	private TradeBatch tradeBatch;
	
	/** 开始状态*/
	private Status startStatus;
	
	/** 结束状态*/
	private Status endStatus;
	
	/** 创建日期*/
	private Date createDate;
	
	/** 操作人*/
	private Operator operator;
	
	/** 描述*/
	private String description;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TRADE_BATCH_LOG") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_BATCH_ID")
	public TradeBatch getTradeBatch() {
		return tradeBatch;
	}

	public void setTradeBatch(TradeBatch tradeBatch) {
		this.tradeBatch = tradeBatch;
	}
	
	@Type(type="status")
	@Column(name="START_STATUS")
	public Status getStartStatus() {
		return startStatus;
	}

	public void setStartStatus(Status startStatus) {
		this.startStatus = startStatus;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATOR_ID")
	public Operator getOperator() {
		return operator;
	}
	
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

