/**
 * 
 */
package com.vlives.boss.operator.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.operator.domain.Operator.Status;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 管理员状态变更日志
 * @author unicorn
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.operator.domain.Operator$Status") })})

@Entity
@Table(name = "MANAGER_STATUS_LOG")
public class OperatorStatusLog extends BaseEntity {
	/** ID */
	private Long id;
	/** 管理员 */
	private Operator manager;
	/** 开始状态 */
	private Status startSatus;
	/** 结束状态 */
	private Status endStatus;
	/** 创建日期 */
	private Date createDate;
	/** 操作员 */
	private Operator operator;
	/** 变更原因*/
	private String description;
	
	

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MANAGER_STATUS_LOG") })
	public Long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	@Cascade(value = CascadeType.ALL)
	public Operator getManager() {
		return manager;
	}


	public void setManager(Operator manager) {
		this.manager = manager;
	}

	@Type(type="status")
	@Column(name="START_STATUS")
	public Status getStartSatus() {
		return startSatus;
	}


	public void setStartSatus(Status startSatus) {
		this.startSatus = startSatus;
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
	@Cascade(value = CascadeType.ALL)
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
}
