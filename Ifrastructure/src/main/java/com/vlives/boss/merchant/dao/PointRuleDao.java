/**
 * @(#)PointRuleDao.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.merchant.domain.PointRule;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  unicorn
 * @version 1.0,2011-6-3
 */
@Repository("pointRuleDao")
public class PointRuleDao extends BaseDaoHibernateImpl<PointRule, Long> {

}

