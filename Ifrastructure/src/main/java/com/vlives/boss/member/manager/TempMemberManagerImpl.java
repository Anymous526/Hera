/*
 * @(#)TempMemberManagerImpl.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.manager;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.member.dao.TempMemberDao;
import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.exception.BusinessException;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-2
 */
@Service("tempMemberManager")
public class TempMemberManagerImpl implements TempMemberManager{

	@Resource
	private TempMemberDao tempMemberDao;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TempMember get(Long id) {
		return tempMemberDao.get(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TempMember getByCardNo(Merchant merchant,String cardNo) {
		PropertiesFinder finder  = new PropertiesFinder(TempMember.class);
		finder.add("cardNo", cardNo);
		finder.add("merchant", merchant);
		return tempMemberDao.getBy(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(TempMember tempMember) throws BusinessException {
		if(tempMember.getCardNo() == null) {
			throw new BusinessException("会员编号不能为空");
		}
		if(getByCardNo(tempMember.getMerchant(), tempMember.getCardNo())!=null) {
			throw new BusinessException("会员编号："+tempMember.getCardNo()+"已经存在！");
		}
		tempMemberDao.save(tempMember);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)	
	public void update(TempMember tempMember) {
		tempMemberDao.update(tempMember);
		
	}
}

