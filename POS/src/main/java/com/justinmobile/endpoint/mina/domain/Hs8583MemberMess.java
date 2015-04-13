package com.justinmobile.endpoint.mina.domain;

/**会员信息列表
 * @author ThinkPad7
 *
 */
public class Hs8583MemberMess extends Hs8583BaseDto {

	private String memberMobile;
	private String bankCardNo;
	private String memberLevelNo;
	
	
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getMemberLevelNo() {
		return memberLevelNo;
	}
	public void setMemberLevelNo(String memberLevelNo) {
		this.memberLevelNo = memberLevelNo;
	}
	
	
}
