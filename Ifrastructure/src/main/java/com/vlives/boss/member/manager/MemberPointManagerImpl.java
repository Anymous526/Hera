/*
 * @(#)MemberPointManagerImpl.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.member.dao.MemberPointDetailDao;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-20
 */
@Service("memberPointManager")
public class MemberPointManagerImpl implements MemberPointManager{

	@Autowired
	private MemberPointDetailDao memberPointDetailDao;
	
	@Override
	public List<MemberPointDetail> find(Map<String, Object> params,
			Pagination pagination) {
		DynamicFinder finder = constFinder(params);
		return memberPointDetailDao.find(finder,pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<MemberPointDetail> find(Map<String, Object> params) {
		DynamicFinder finder = constFinder(params);
		return memberPointDetailDao.find(finder);
	}
	
	private DynamicFinder constFinder(Map<String, Object> params) {
		if (params.get("name") != null) {
			params.put("name", params.get("name") + "%");
		}
		String hql = "from MemberPointDetail mp where 1=1"+
		"{ and mp.type = :pointType }"+
		"{ and mp.merchant.id = :merchantId}" +
		"{ and mp.member.memberGroup = :memberGroup}"+
		"{ and mp.member.user.id = :userId}" +
		"{ and mp.member.user.name like :name }"+
		"{ and mp.member.user.mobile = :mobile}" +
		"{ and mp.member.level = :level}" +
		"{ and mp.tradeOrder.merchant = :merchant}" +
		"{ and mp.tradeOrder.type = :type}" +
		"{ and mp.member.createDate>=:startCreateDate}" +
		"{ and mp.member.createDate<=:endCreateDate}"+
		"{ and mp.tradeOrder.consumeDate>=:startConsumeDate}" +
		"{ and mp.tradeOrder.consumeDate<=:endConsumeDate}";
		DynamicFinder finder = new DynamicFinder(hql,params);
		return finder;
	}

}

