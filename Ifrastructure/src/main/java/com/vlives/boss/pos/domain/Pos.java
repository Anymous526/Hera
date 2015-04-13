/*
 * @(#)Pos.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.pos.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.pos.domain.Pos$Status") }) })
@Entity
@Table(name="POS")
public class Pos extends BaseEntity{

	/***编号*/
	private Long id;
	
	/**商户*/
	private Merchant merchant;
	/**
	 * POS机序列号
	 */
	private String serialNumber;
	/**
	 * POS机编码
	 */
	private String code;
	
	/**状态*/
	private Status status;
	
	/*** 描述*/
	private String description;
	
	/*** 创建日期*/
	private Date createDate;
	
	/***最后修改日期*/
	private Date lastModityDate;
	/**
	 * 当前交易批次号
	 */
	private int batchNumber;
	
	/**关联pos日志信息**/
	private Set<PosLog> posLogs;
	
	public static enum Status implements EnumTypeInterface {
		/** 注销状态 */
		STATUS_DISABLE(0, "注销"),
		/** 有效状态 */
		STATUS_ACTIVE(1, "有效"),
		/** 冻结状态 */
		STATUS_FREEZED(2, "冻结");
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
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_POS") })
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
	
	
	@Column(name="SERIAL_NUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Type(type="status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="LAST_MODIFY_DATE")
	public Date getLastModityDate() {
		return lastModityDate;
	}

	public void setLastModityDate(Date lastModityDate) {
		this.lastModityDate = lastModityDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@OneToMany( mappedBy="pos", fetch = FetchType.LAZY)
	@Cascade(value=CascadeType.ALL)	 
	public Set<PosLog> getPosLogs() {
		return posLogs;
	}

	public void setPosLogs(Set<PosLog> posLogs) {
		this.posLogs = posLogs;
	}
	@Column(name="BATCH_NUMBER")	
	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	/**
	 * 
	 * create by datao.wang 2011-6-3
	 *@param operator
	 *@param beginStatus
	 *@param endStatus
	 */
	@Transient
	public void addStatusLog(Operator operator,Status beginStatus,Status endStatus,String desc) {
		PosLog log=new PosLog();
		log.setPos(this);
		log.setBeginStatus(beginStatus);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setEndStatus(endStatus);
		log.setOperator(operator);
		if(posLogs==null){
			posLogs=new HashSet<PosLog>();
		}
		posLogs.add(log);
	}
	
	/**
	 * 判断商户是否有效
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isActive(){
		return this.getStatus() == Status.STATUS_ACTIVE;
	}
	/**
	 * 判断商户是否冻结
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isFreeze(){
		return this.getStatus() == Status.STATUS_FREEZED;
	}
	
	/**
	 * 判断商户是否注销
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isLogOut(){
		return this.getStatus() == Status.STATUS_DISABLE;
	}

	/**
	 * 递增批次号
	 */
	@Transient
	public void increaseBatchNumber() {
		this.batchNumber++;
	
		
	}

}

