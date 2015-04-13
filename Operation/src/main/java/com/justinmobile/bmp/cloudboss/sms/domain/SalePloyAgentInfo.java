package com.justinmobile.bmp.cloudboss.sms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "SALEPLOY_AGENT_INFO")
public class SalePloyAgentInfo extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long id;
	/** 签单人 */
	private String signer;
	/** 录入操作员 */
	private String oper;
	
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "SIGNER")
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
	}
	
	@Column(name = "OPER")
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}

}
