/**
 * @(#)MerchantSmsAccountManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sms.manager;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sms.dao.MerchantSmsAccountDao;
import com.vlives.boss.sms.domain.MerchantSmsAccount;
import com.vlives.boss.sms.domain.SmsAccountDetail.Type;
import com.vlives.core.exception.BusinessException;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-19
 */
@Service("merchantSmsAccountManager")
public class MerchantSmsAccountManagerImpl implements MerchantSmsAccountManager {
	@Resource
	private MerchantSmsAccountDao merchantSmsAccountDao;
	
	@Autowired
	SmsAccountDetailManager smsAccountDetailManager;
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void addSmsCount(Merchant merchant, int quantity, Type type,
			String desc, Operator operator)throws BusinessException {
		if(!type.isAddSms())
			throw new BusinessException("此类型不是增加短信的类型");
		MerchantSmsAccount merchantSmsAccount = merchant.getMerchantSmsAccount();
		merchantSmsAccount.setTotalCount(merchantSmsAccount.getTotalCount()+quantity);
		if(type == Type.TYPE_PRESEN)
			merchantSmsAccount.setSysPresenCount(quantity);
		if(type == Type.TYPE_BUY)
			merchantSmsAccount.setMerchantBuyCount(quantity);
		merchantSmsAccountDao.saveOrUpdate(merchantSmsAccount);
		smsAccountDetailManager.create(merchantSmsAccount, quantity, type, desc, operator);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void reduceSmsCount(Merchant merchant, int quantity, Type type,
			String desc, Operator operator)throws BusinessException {
		MerchantSmsAccount merchantSmsAccount = merchant.getMerchantSmsAccount();
		if(type.isAddSms()) {
			throw new BusinessException("此类型不是减少短信的类型");
		}
		 
		if(quantity>merchantSmsAccount.getRemainCount()){
			throw new BusinessException("短信总数少于要发送的短信数");
		}
		merchantSmsAccount.setConsumeCount(merchantSmsAccount.getConsumeCount()+quantity);
		merchantSmsAccountDao.saveOrUpdate(merchantSmsAccount);
		smsAccountDetailManager.create(merchantSmsAccount, 0-quantity, type, desc, operator);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void restore(Merchant merchant, int quantity, Type type,
			String desc, Operator operator) throws BusinessException {
		if(!type.isAddSms())
			throw new BusinessException("此类型不是增加短信的类型");
		MerchantSmsAccount merchantSmsAccount = merchant.getMerchantSmsAccount();
		merchantSmsAccount.setConsumeCount(merchantSmsAccount.getConsumeCount()-quantity);
		merchantSmsAccountDao.saveOrUpdate(merchantSmsAccount);
		smsAccountDetailManager.create(merchantSmsAccount, quantity, type, desc, operator);
		
	}
 
}
