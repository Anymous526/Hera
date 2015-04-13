/*
 * @(#)CreditGrade.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.merchant.domain;



/**
 * 商户 信用等级
 * 
 * @author xujianguo
 * @version 1.0,Jul 26, 2009
 */
public enum CreditGrade {
	
	ONE_HEART(1,1, "一心", 4, 10), TWO_HEART(2,1,  "两心", 11, 40), THREE_HEART(3, 1, "三心",
			41, 90), FOUR_HEART(4, 1, "四心", 91, 150), FIVE_HEART(5, 1, "五心", 151, 250),

	ONE_DIAMOND(6, 2, "一钻", 251, 500), TWO_DIAMOND(7, 2,"两钻", 501, 1000), THREE_DIAMOND(
			8, 2,"三钻", 1001, 2000), FOUR_DIAMOND(9,2, "四钻", 2001, 5000), FIVE_DIAMOND(
			10, 2,"五钻", 5001, 10000),

	ONE_CROWN(11, 3,"一冠", 10001, 20000), TWO_CROWN(12,3, "两冠", 20001, 50000), THREE_CROWN(
			13, 3,"三冠", 50001, 100000), FOUR_CROWN(14,3, "四冠", 100001, 200000), FIVE_CROWN(
			15,3, "五冠", 200001, 500000),

	ONE_GOLDENCROWN(16,4, "一皇冠", 500001, 1000000), TWO_GOLDENCROWN(17, 4,"两皇冠",
			1000001, 2000000), THREE_GOLDENCROWN(18,4, "三皇冠", 2000001, 5000000), FOUR_GOLDENCROWN(
			19,4, "四皇冠", 5000001, 10000000), FIVE_GOLDENCROWN(20,4, "五皇冠", 10000001,
			999999999);
	
	/**
	 * 等级
	 */
	private final int value;
	/**
	 * 等级水平（心，砖石，冠军，皇冠）
	 */
	private final int gradeLevel;
	/**
	 * 等级中文描述
	 */
	private final String label;
	/**
	 * 最低积分
	 */
	private final int minNum;
	/**
	 * 最高积分
	 */
	private final int maxMun;

	private CreditGrade(int value,int gradeLevel, String label, int minNum, int maxMun) {
		this.value = value;
		this.gradeLevel = gradeLevel;
		this.label = label;
		this.minNum = minNum;
		this.maxMun = maxMun;
	}
	

	public int getValue() {
		return value;
	}


	public String getLabel() {
		return label;
	}

	public int getMinNum() {
		return minNum;
	}

	public int getMaxMun() {
		return maxMun;
	}
	
	public String getHtmlImg() {
		int count = value%5==0?5:value%5;
		return  getCreditHtmlImg(gradeLevel, count);
	}

	public static CreditGrade get(int grade) {
		switch (grade) {
		case 1:
			return ONE_HEART;
		case 2:
			return TWO_HEART;
		case 3:
			return THREE_HEART;
		case 4:
			return FOUR_HEART;
		case 5:
			return FIVE_HEART;
		case 6:
			return ONE_DIAMOND;
		case 7:
			return TWO_DIAMOND;
		case 8:
			return THREE_DIAMOND;
		case 9:
			return FOUR_DIAMOND;
		case 10:
			return FIVE_DIAMOND;
		case 11:
			return ONE_CROWN;
		case 12:
			return TWO_CROWN;
		case 13:
			return THREE_CROWN;
		case 14:
			return FOUR_CROWN;
		case 15:
			return FIVE_CROWN;
		case 16:
			return ONE_GOLDENCROWN;
		case 17:
			return TWO_GOLDENCROWN;
		case 18:
			return THREE_GOLDENCROWN;
		case 19:
			return FOUR_GOLDENCROWN;
		case 20:
			return FIVE_GOLDENCROWN;
		default:
			throw new IllegalArgumentException("参数不正确");
		}
	}
	/**
	 * 根据信用积分得到信用等级
	 * @author jianguo.xu
	 * @param creditScore
	 * @return
	 */
	public static CreditGrade getByCreditScore(int creditScore) {
		for(CreditGrade grade : CreditGrade.values()) {
			if(creditScore>=grade.getMinNum()&&creditScore<=grade.getMaxMun())
				return grade;
		}
		return null;
	}
	
	private static final String HEART_SRC = "/images/dev/s_red_1.gif";
	private static final String DIAMOND_SRC = "/images/dev/s_blue_1.gif";
	private static final String CROWN_SRC = "/images/dev/s_cap_1.gif";
	private static final String GOLDENCROWN_SRC = "/images/dev/s_crown_1.gif";
	private static String getCreditHtmlImg(int gradeLevel,int count) {
		String imgSrc = null;
		switch(gradeLevel) {
			case 1 : imgSrc = HEART_SRC; break;
			case 2 : imgSrc = DIAMOND_SRC; break;
			case 3 : imgSrc = CROWN_SRC; break;
			case 4 : imgSrc = GOLDENCROWN_SRC; break;
			default : throw new IllegalArgumentException("参数不正确");
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<count;i++) {
			sb.append("<img style='vertical-align:middle;' align='top' src='"+imgSrc+"'/>");
		}
		return sb.toString();
	}

}
