package com.justinmobile.bmp.cloudboss.merchant.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "MERCHANT_CONTRACT_INFO")
public class MerchantContractInfo extends AbstractEntity{

	private static final long serialVersionUID = -7547243815449766190L;

	private Long id;

	/** 商户编号 */
	private String merchantCode;
	
	/** 合同编号 */
	private String contractNumber;
	
	/** 合同签订人 */
	private String contractSigner;
	
	/** 合同复审员 */
	private String contractAuditor;
	
	/** 合同签订时间 */
	private Calendar signDate;
	
	/** 合同有效期开始日期 */
	private Calendar validDateBegin;
	
	/** 合同有效期结束日期 */
	private Calendar validDateEnd;
	
	/** 合同扫描件文件路径 */
	private String contractFilePath;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_CONTRACT_INFO") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="MERCHANT_CODE")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	@Column(name="CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name="CONTRACT_SIGNER")
	public String getContractSigner() {
		return contractSigner;
	}

	public void setContractSigner(String contractSigner) {
		this.contractSigner = contractSigner;
	}

	@Column(name="CONTRACT_AUDITOR")
	public String getContractAuditor() {
		return contractAuditor;
	}

	public void setContractAuditor(String contractAuditor) {
		this.contractAuditor = contractAuditor;
	}

	@Column(name="SIGN_DATE")
	public Calendar getSignDate() {
		return signDate;
	}

	public void setSignDate(Calendar signDate) {
		this.signDate = signDate;
	}

	@Column(name="VALID_DATE_BEGIN")
	public Calendar getValidDateBegin() {
		return validDateBegin;
	}

	public void setValidDateBegin(Calendar validDateBegin) {
		this.validDateBegin = validDateBegin;
	}

	@Column(name="VALID_DATE_END")
	public Calendar getValidDateEnd() {
		return validDateEnd;
	}

	public void setValidDateEnd(Calendar validDateEnd) {
		this.validDateEnd = validDateEnd;
	}

	@Column(name="CONTRACT_FILE_PATH")
	public String getContractFilePath() {
		return contractFilePath;
	}

	public void setContractFilePath(String contractFilePath) {
		this.contractFilePath = contractFilePath;
	}
	
	
}
