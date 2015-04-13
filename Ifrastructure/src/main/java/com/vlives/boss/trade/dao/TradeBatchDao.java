/*
 * @(#)TradeDetailDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;


@Repository("tradeBatchDao")
public class TradeBatchDao extends BaseDaoHibernateImpl<TradeBatch, Long>{

}

