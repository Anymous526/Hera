/**
 * @(#)MerchantCategoryDto.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.dto;

import com.vlives.boss.merchant.domain.MerchantCategory;

/**
 * description
 * @author  unicorn
 * @version 1.0,2011-7-14
 */
public class MerchantCategoryDto {
	/**
	 * 商户行业类型
	 */
	private MerchantCategory category;
	/**
	 * 某行业商户数量
	 */
	private long merchantCount;
	
	
	public MerchantCategory getCategory() {
		return category;
	}
	public void setCategory(MerchantCategory category) {
		this.category = category;
	}
	public long getMerchantCount() {
		return merchantCount;
	}
	public void setMerchantCount(long merchantCount) {
		this.merchantCount = merchantCount;
	}
}

