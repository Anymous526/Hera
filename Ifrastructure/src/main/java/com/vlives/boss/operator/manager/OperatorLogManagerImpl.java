/**
 * @(#)OperatorLogManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-3
 */
@Service("operatorLogManager")
public class OperatorLogManagerImpl implements OperatorLogManager{
	@Resource
	private BaseDao<OperatorLog, Long> operatorLogDao;
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void createLog(Operator operator, OperatorType operatorType) {
		OperatorLog log = new OperatorLog();
		log.setCreateDate(new Date());
		log.setOperator(operator);
		log.setOperatorType(operatorType);
		operatorLogDao.save(log);
	}
 

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<OperatorLog> find(Map<String, Object> params,Pagination pagination) {
		String hql = "from OperatorLog o where 1=1" +
		"{ and o.operator.merchant =:merchant}" +
		"{ and o.operator.mobile like :mobile}" +
		"{ and o.operator.name =:name}" +
		"{ and o.createDate>=:startCreateDate}" +
		"{ and o.createDate<=:endCreateDate}"+
		" order by o.createDate desc ";
		Finder finder = new DynamicFinder(hql, params);
		return operatorLogDao.find(finder, pagination);
	}

}
