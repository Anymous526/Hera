/*
 * @(#)TempUser.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.user.domain;

import com.vlives.boss.member.domain.Level;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-13
 */
public class TempUser extends User{

	/**
	 * 会员等级
	 */
	private Level level;
	
	/**
	 * 会员卡号
	 */
	private String cardNo;
	
	/**
	 * 会员地区
	 */
	private String areaStr;
	
	/**
	 * 积分
	 */
	private int point;

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAreaStr() {
		return areaStr;
	}

	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	
}

