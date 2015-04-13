/**
 * @(#)Comparator.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.dao.generic.finder;
/**
 * 比较器
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public enum Comparator {
	
	/**
	 * 等于
	 */
	EQ((short)0,"="),
	/**
	 * 大于
	 */
	GT((short)1,">"),
	 

	/**
	 * 大于等于
	 */
	GTEQ((short)2,">="),
	 

	/**
	 * 小于
	 */
	LS((short)3,"<"),
	 
	/**
	 * 小于等于
	 */
	LSEQ((short)4,"<="),
	 
	/**
	 * like
	 */
	LK((short)5,"like"),
	 
	/**
	 * 大于等于 小于等于
	 */
	GTEQ_LSEQ((short)6,GTEQ,LSEQ),
 

	/**
	 * 大于 小于
	 */
	GT_LS((short)7,GT,LS),
	 

	/**
	 * 大于 小于等于
	 */
	GT_LSEQ((short)8,GT,LSEQ),
	 

	/**
	 * 大于等于 小于
	 */
	GTEQ_LS((short)9,GTEQ,LS),
 
	/**
	 * in
	 */
	IN((short)10,"in");
	 
	
	private final short key;
	private final String value;
	private final Comparator min;
	private final Comparator max;
	private Comparator(short key, String value) {
		this.key = key;
		this.value = value;
		this.min = null;
		this.max = null;
	}
	
	private Comparator(short key,Comparator min,Comparator max) {
		this.key = key;
		this.value = null;
		this.min = min;
		this.max = max;
	}

	public short getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}

	public Comparator getMin() {
		return min;
	}

	public Comparator getMax() {
		return max;
	}

	 
	
	
}
