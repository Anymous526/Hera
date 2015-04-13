/**
 * @(#)PointRuleDao.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.merchant.domain.MerchantReferenceStatistic;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;


@Repository("merchantStatisticDao")
public class MerchantStatisticDao extends BaseDaoHibernateImpl<MerchantReferenceStatistic, Long> {

}

