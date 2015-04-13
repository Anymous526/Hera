package com.justinmobile.bmp.cloudboss.member.domain;

public class MemberUploadQuery {
	
	/**会员编号 */
	private String memberNo;	
	/**姓名 */
	private String memberName;
	/** 手机号码 */
	private String mobile;
	/** 会员等级*/
	private String level;
	/** 所属商户*/
	private String merchant;
	/** 会员等级 */
	private String useIntegral;
	/** 总积分 */
	private String integral;
	/** 注册时间 */
	private String setDate;
	/** 有效期起 */
	private String sDate;
	/** 有效期止 */
	private String eDate;
	/** 会员生日 */
	private String birthday;
	/** 邮箱 */
	private String eMail;
	/** 联系地址 */
	private String address;
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getUseIntegral() {
		return useIntegral;
	}
	public void setUseIntegral(String useIntegral) {
		this.useIntegral = useIntegral;
	}
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getSetDate() {
		return setDate;
	}
	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}
	
}
