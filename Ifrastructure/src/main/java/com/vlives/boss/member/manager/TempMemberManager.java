/*
 * @(#)TempMemberManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.manager;

import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.core.exception.BusinessException;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-2
 */
public interface TempMemberManager {

	/**
	 * 通过id获得临时会员
	 * @param id
	 * @return
	 */
	public TempMember get(Long id);
	
	
	public void update(TempMember tempMember);
	
	/**
	 * 通过商户和卡号查询临时会员
	 * @param cardNo 临时会员卡号
	 * @return
	 */
	public TempMember getByCardNo(Merchant merchant,String cardNo);
	/**
	 * 创建临时会员
	 * @author jianguo.xu
	 * @param tempMember
	 * @throws BusinessException 如果同一个商户的会员卡重复抛此异常
	 */
	public void create(TempMember tempMember) throws BusinessException;
}

