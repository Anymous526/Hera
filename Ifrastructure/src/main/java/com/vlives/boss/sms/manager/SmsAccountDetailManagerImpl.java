/**
 * @(#)SmsAccountDetailManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sms.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sms.domain.MerchantSmsAccount;
import com.vlives.boss.sms.domain.SmsAccountDetail;
import com.vlives.boss.sms.domain.SmsAccountDetail.Type;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-10
 */
@Service("smsAccountDetailManager")
public class SmsAccountDetailManagerImpl implements SmsAccountDetailManager{
	@Resource
	private BaseDao<SmsAccountDetail, Long> smsAccountDetailDao;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(MerchantSmsAccount merchantSmsAccount, int quantity, Type type, String desc,
			Operator operator) {
		SmsAccountDetail detail = new SmsAccountDetail();
		detail.setCreateDate(new Date());
		detail.setDescription(desc);
		detail.setMerchantSmsAccount(merchantSmsAccount);
		detail.setOperator(operator);
		detail.setQuantity(quantity);
		detail.setType(type);
		smsAccountDetailDao.save(detail);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SmsAccountDetail> find(Map<String, Object> params,
			Pagination pagination) {
		String hql = "from SmsAccountDetail s where 1=1" +
				"{ and s..merchantSmsAccount.merchant=:merchant}" +
				"{ and s.type in:types}" +
				"{ and s.createDate>=:startCreateDate}" +
				"{ and s.createDate<=:endCreateDate}";
		Finder finder = new DynamicFinder(hql, params);
		return smsAccountDetailDao.find(finder, pagination);
	}
	

}
