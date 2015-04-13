/*
 * @(#)TradeDetailDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Repository("tradeDetailDao")
public class TradeDetailDao extends BaseDaoHibernateImpl<TradeDetail, Long>{

}

