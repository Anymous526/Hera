/**
 * @(#)TempCouponPloyManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.coupon.domain.TempCouponPloy;
import com.vlives.core.dao.generic.BaseDao;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-8
 */
@Service("tempCouponPloyManager")
public class TempCouponPloyManagerImpl implements TempCouponPloyManager{
	@Resource
	private BaseDao<TempCouponPloy, Long> tempCouponPloyDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public TempCouponPloy get(Long id) {
		return tempCouponPloyDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void create(TempCouponPloy tempCouponPloy) {
		tempCouponPloyDao.saveOrUpdate(tempCouponPloy);
		
	}

	 
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(TempCouponPloy tempCouponPloy) {
		tempCouponPloyDao.update(tempCouponPloy);
	}

}
