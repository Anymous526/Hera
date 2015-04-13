/**
 * 
 */
package com.vlives.boss.merchant.domain;

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

import com.vlives.boss.merchant.domain.Merchant.Status;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 商户状态变更日志
 * @author unicorn
 *
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.Merchant$Status") }) })
@Entity
@Table(name="MERCHANT_LOG")
public class MerchantLog extends BaseEntity {

	private Long id;
	/***
	 * 商户
	 */
	private Merchant merchant;
	/***
	 * 商户开始状态
	 */
	private Status startStatus;
	/***
	 * 商户结束状态
	 */
	private Status endStatus;
	/***
	 * 日志创建时间
	 */
	private Date createDate;
	/***
	 * 操作员
	 */
	private Operator operator;
	/***
	 *变更原因
	 */
	private String description;
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_LOG") })
	public Long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Column(name="START_STATUS")
	@Type(type="status")
	public Status getStartStatus() {
		return startStatus;
	}

	public void setStartStatus(Status startStatus) {
		this.startStatus = startStatus;
	}

	@Column(name="END_STATUS")
	@Type(type="status")
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
