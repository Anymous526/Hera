/**
 * @(#)OperatorDao.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
@Repository("operatorDao")
public class OperatorDao extends BaseDaoHibernateImpl<Operator, Long>{

}