/**
 * @(#)MerchantDto.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.dto;

import com.vlives.boss.merchant.domain.Merchant;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-22
 */
public class MerchantDto {
	/**
	 * 商户
	 */
	private Merchant merchant;
	/**
	 * 商户创建的会员
	 */
	private long createMemberCount;
	
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public long getCreateMemberCount() {
		return createMemberCount;
	}
	public void setCreateMemberCount(long createMemberCount) {
		this.createMemberCount = createMemberCount;
	}
	
}
