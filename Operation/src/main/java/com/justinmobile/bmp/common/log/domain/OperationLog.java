/**
 * @(#)Bank.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.bmp.common.log.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.justinmobile.bmp.util.CalendarUtils;
import com.justinmobile.core.domain.AbstractEntity;

/**
 * description
 * @author  gao
 * @version 1.0,2011-6-1
 */
@Entity
@Table(name = "OPERATION_LOG")
public class OperationLog extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long id;
	/** 发生时间 */
	private Calendar opTime;
	/** 操作员编号 */
	private Long opId;
	/** 操作员 */
	private String opName;
	/** 手机号 */
	private String opMobile;
	/** 操作描述 */
	private String opDescription;
	/** 操作日期时间 */
	private String opDateTime;
		

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_OPERATION_LOG") })
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "OP_TIME")
	public Calendar getOpTime() {
		return opTime;
	}
	public void setOpTime(Calendar opTime) {
		this.opTime = opTime;
	}
	
	@Column(name = "OP_ID")
	public Long getOpId() {
		return opId;
	}
	public void setOpId(Long opId) {
		this.opId = opId;
	}
	
	@Column(name = "OP_NAME")
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	
	@Column(name = "OP_MOBILE")
	public String getOpMobile() {
		return opMobile;
	}
	public void setOpMobile(String opMobile) {
		this.opMobile = opMobile;
	}
	
	@Column(name = "OP_DESCRIPTION")
	public String getOpDescription() {
		return opDescription;
	}
	public void setOpDescription(String opDescription) {
		this.opDescription = opDescription;
	}
	
	@Transient
	public String getOpDateTime() {
		if(opTime!=null){
			opDateTime = CalendarUtils.fomatCalendar(opTime, CalendarUtils.SHORT_FORMAT_LINE);
		}
		return opDateTime;
	}
	public void setOpDateTime(String opDateTime) {
		this.opDateTime = opDateTime;
	}

	
}
