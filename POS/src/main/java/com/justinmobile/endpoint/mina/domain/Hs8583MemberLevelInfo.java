package com.justinmobile.endpoint.mina.domain;

/**会员级别信息
 * @author ThinkPad7
 *
 */
public class Hs8583MemberLevelInfo extends Hs8583BaseDto {

	
	private String levelName;
	private String levelMemo;
	private String levelDiscount;
	
	
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevelMemo() {
		return levelMemo;
	}
	public void setLevelMemo(String levelMemo) {
		this.levelMemo = levelMemo;
	}
	public String getLevelDiscount() {
		return levelDiscount;
	}
	public void setLevelDiscount(String levelDiscount) {
		this.levelDiscount = levelDiscount;
	}
	
	
}
