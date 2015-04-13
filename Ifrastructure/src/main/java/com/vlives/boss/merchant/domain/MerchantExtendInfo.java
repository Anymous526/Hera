package com.vlives.boss.merchant.domain;

import java.math.BigDecimal;
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

import com.vlives.boss.merchant.domain.MerchantEnumType.BusinessLandNature;
import com.vlives.boss.merchant.domain.MerchantEnumType.BusinessLocation;
import com.vlives.boss.merchant.domain.MerchantEnumType.MerchantProperty;
import com.vlives.boss.merchant.domain.MerchantEnumType.PayType;
import com.vlives.boss.merchant.domain.MerchantEnumType.Regions;
import com.vlives.boss.merchant.domain.MerchantEnumType.TransactionType;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

@TypeDefs({ 
	@TypeDef(name = "paytype", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.MerchantEnumType$PayType") }),
	@TypeDef(name = "transactiontype", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.MerchantEnumType$TransactionType") }),
	@TypeDef(name = "merchantproperty", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.MerchantEnumType$MerchantProperty") }),
	@TypeDef(name = "businesslandnature", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.MerchantEnumType$BusinessLandNature") }),
	@TypeDef(name = "businesslocation", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.MerchantEnumType$BusinessLocation") }),
	@TypeDef(name = "regions", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.MerchantEnumType$Regions") })
	})
@Entity
@Table(name="MERCHANT_EXTEND_INFO")
public class MerchantExtendInfo extends BaseEntity {

	/** 主键 */
	private Long id;
	/** 商户ID*/
	private Merchant merchant;
	/** 是否是签约商户*/
	private boolean signMerchant;
	/** 注册登记号*/
	private String registNo;
	/** 税务登记号*/
	private String taxNo;
	/** 营业执照*/
	private String businessLicense;
	/** 税务登记证*/
	private String taxCertificate;
	/** 机构代码证*/
	private String organizationCard;
	/** 注册地址*/
	private String registAddress;
	/** 法人代表*/
	private String legal;
	/** 商户性质*/
	private MerchantProperty property;
	/** 注册资本*/
	private BigDecimal registCapital;
	/** 营业用地性质*/
	private BusinessLandNature businessLand;
	/** 营业用地面积*/
	private Integer businessArea;
	/** 经营地段*/
	private BusinessLocation businessLocation;
	/** 经营区域*/
	private Regions regions;
	/** 员工人数*/
	private Integer employeesNumber;
	/** 经营范围（主业）*/
	private String rangeMain;
	/** 经营范围（副业）*/
	private String rangeSideline;
	/** 分支机构*/
	private Integer branch;
	/** 前一年营业额 单位为万*/
	private BigDecimal turnoverYear;
	/** 前一年利润 单位为万*/
	private BigDecimal profitYear;
	/** 预计月平均银行卡营业额 单位为万*/
	private BigDecimal turnoverBankCard;
	/** 预计每张签购单平均交易额 单位为元*/
	private BigDecimal signTrading;
	/** 会员系统情况*/
	private String memberSys;
	/** 储值卡系统情况*/
	private String cardSys;
	/** 是否是银行卡收单商户*/
	private boolean bankCardMerchant;
	/** 收单机构*/
	private String acceptOrganization;
	/** 银行商户编号*/
	private String bankMerchantCode;
	/** 商户类型*/
	private String merchantType;
	/** 商户开户银行*/
	private String merchantBank;
	/** 商户结算账号*/
	private String accountSettlement;
	/** 商户交易类型*/
	private TransactionType transactionType;
	/** 商户付款方式*/
	private PayType payType;
	/** 对账单地址*/
	private String billAddress;
	/** 对账单邮编*/
	private String billZipcode;
	/** 对账单收件人*/
	private String billPerson;
	/** 对账单邮件地址*/
	private String billEmail;
	/** 对账单传真*/
	private String billFax;
	/** 签约日期*/
	private Date signDate;
	/**
	 * 费率
	 */
	private BigDecimal rating;
	/**
	 * 首次合作赠送短信数
	 */
	private int presentSmsCount;
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_EXTEND_INFO") })
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	@Column(name="SIGN_MERCHANT")
	public boolean getSignMerchant() {
		return signMerchant;
	}
	public void setSignMerchant(boolean signMerchant) {
		this.signMerchant = signMerchant;
	}
	
	@Column(name="REGIST_NO")
	public String getRegistNo() {
		return registNo;
	}
	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}
	
	@Column(name="TAX_NO")
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	
	@Column(name="BUSINESS_LICENSE")
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	
	@Column(name="TAX_CERTIFICATE")
	public String getTaxCertificate() {
		return taxCertificate;
	}
	public void setTaxCertificate(String taxCertificate) {
		this.taxCertificate = taxCertificate;
	}
	
	@Column(name="ORGANIZATION_CARD")
	public String getOrganizationCard() {
		return organizationCard;
	}
	public void setOrganizationCard(String organizationCard) {
		this.organizationCard = organizationCard;
	}
	
	@Column(name="REGIST_ADDRESS")
	public String getRegistAddress() {
		return registAddress;
	}
	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}
	
	@Column(name="LEGAL")
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	
	@Type(type="merchantproperty")
	@Column(name="PROPERTY")
	public MerchantProperty getProperty() {
		return property;
	}
	public void setProperty(MerchantProperty property) {
		this.property = property;
	}
	
	@Column(name="REGIST_CAPITAL")
	public BigDecimal getRegistCapital() {
		return registCapital;
	}
	public void setRegistCapital(BigDecimal registCapital) {
		this.registCapital = registCapital;
	}
	
	@Type(type="businesslandnature")
	@Column(name="BUSINESS_LAND")
	public BusinessLandNature getBusinessLand() {
		return businessLand;
	}
	public void setBusinessLand(BusinessLandNature businessLand) {
		this.businessLand = businessLand;
	}
	
	@Column(name="BUSINESS_AREA")
	public Integer getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(Integer businessArea) {
		this.businessArea = businessArea;
	}
	
	@Type(type="businesslocation")
	@Column(name="BUSINESS_LOCATION")
	public BusinessLocation getBusinessLocation() {
		return businessLocation;
	}
	public void setBusinessLocation(BusinessLocation businessLocation) {
		this.businessLocation = businessLocation;
	}
	
	@Type(type="regions")
	@Column(name="REGIONS")
	public Regions getRegions() {
		return regions;
	}
	public void setRegions(Regions regions) {
		this.regions = regions;
	}
	
	@Column(name="EMPLOYEES_NUMBER")
	public Integer getEmployeesNumber() {
		return employeesNumber;
	}
	public void setEmployeesNumber(Integer employeesNumber) {
		this.employeesNumber = employeesNumber;
	}
	
	@Column(name="RANGE_MAIN")
	public String getRangeMain() {
		return rangeMain;
	}
	public void setRangeMain(String rangeMain) {
		this.rangeMain = rangeMain;
	}
	
	@Column(name="RANGE_SIDELINE")
	public String getRangeSideline() {
		return rangeSideline;
	}
	public void setRangeSideline(String rangeSideline) {
		this.rangeSideline = rangeSideline;
	}
	
	@Column(name="BRANCH")
	public Integer getBranch() {
		return branch;
	}
	public void setBranch(Integer branch) {
		this.branch = branch;
	}
	
	@Column(name="TURNOVER_YEAR")
	public BigDecimal getTurnoverYear() {
		return turnoverYear;
	}
	public void setTurnoverYear(BigDecimal turnoverYear) {
		this.turnoverYear = turnoverYear;
	}
	
	@Column(name="PROFIT_YEAR")
	public BigDecimal getProfitYear() {
		return profitYear;
	}
	public void setProfitYear(BigDecimal profitYear) {
		this.profitYear = profitYear;
	}
	
	@Column(name="TURNOVER_BANK_CARD")
	public BigDecimal getTurnoverBankCard() {
		return turnoverBankCard;
	}
	public void setTurnoverBankCard(BigDecimal turnoverBankCard) {
		this.turnoverBankCard = turnoverBankCard;
	}
	
	@Column(name="SIGN_TRADING")
	public BigDecimal getSignTrading() {
		return signTrading;
	}
	public void setSignTrading(BigDecimal signTrading) {
		this.signTrading = signTrading;
	}
	
	@Column(name="MEMBER_SYS")
	public String getMemberSys() {
		return memberSys;
	}
	public void setMemberSys(String memberSys) {
		this.memberSys = memberSys;
	}
	
	@Column(name="CARD_SYS")
	public String getCardSys() {
		return cardSys;
	}
	public void setCardSys(String cardSys) {
		this.cardSys = cardSys;
	}
	
	@Column(name="BANK_CARD_MERCHANT")
	public boolean isBankCardMerchant() {
		return bankCardMerchant;
	}
	public void setBankCardMerchant(boolean bankCardMerchant) {
		this.bankCardMerchant = bankCardMerchant;
	}
	
	@Column(name="ACCEPT_ORGANIZATION")
	public String getAcceptOrganization() {
		return acceptOrganization;
	}
	public void setAcceptOrganization(String acceptOrganization) {
		this.acceptOrganization = acceptOrganization;
	}
	
	@Column(name="BANK_MERCHANT_CODE")
	public String getBankMerchantCode() {
		return bankMerchantCode;
	}
	public void setBankMerchantCode(String bankMerchantCode) {
		this.bankMerchantCode = bankMerchantCode;
	}
	
	@Column(name="MERCHANT_TYPE")
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	
	@Column(name="MERCHANT_BANK")
	public String getMerchantBank() {
		return merchantBank;
	}
	public void setMerchantBank(String merchantBank) {
		this.merchantBank = merchantBank;
	}
	
	@Column(name="ACCOUNT_SETTLEMENT")
	public String getAccountSettlement() {
		return accountSettlement;
	}
	public void setAccountSettlement(String accountSettlement) {
		this.accountSettlement = accountSettlement;
	}
	
	@Type(type="transactiontype")
	@Column(name="TRANSACTION_TYPE")
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	@Type(type="paytype")
	@Column(name="PAY_TYPE")
	public PayType getPayType() {
		return payType;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}
	
	@Column(name="BILL_ADDRESS")
	public String getBillAddress() {
		return billAddress;
	}
	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}
	
	@Column(name="BILL_ZIPCODE")
	public String getBillZipcode() {
		return billZipcode;
	}
	public void setBillZipcode(String billZipcode) {
		this.billZipcode = billZipcode;
	}
	
	@Column(name="BILL_PERSON")
	public String getBillPerson() {
		return billPerson;
	}
	public void setBillPerson(String billPerson) {
		this.billPerson = billPerson;
	}
	
	@Column(name="BILL_EMAIL")
	public String getBillEmail() {
		return billEmail;
	}
	public void setBillEmail(String billEmail) {
		this.billEmail = billEmail;
	}
	
	@Column(name="BILL_FAX")
	public String getBillFax() {
		return billFax;
	}
	public void setBillFax(String billFax) {
		this.billFax = billFax;
	}
	
	@Column(name="SIGN_DATE")
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public BigDecimal getRating() {
		return rating;
	}
	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	@Column(name="PRESENT_SMS_COUNT")
	public int getPresentSmsCount() {
		return presentSmsCount;
	}
	public void setPresentSmsCount(int presentSmsCount) {
		this.presentSmsCount = presentSmsCount;
	}

	
}	
