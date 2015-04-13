/*
 * @(#)TradeDetailDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.trade.domain.TradeTransaction;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;


@Repository("tradeTransactionDao")
public class TradeTransactionDao extends BaseDaoHibernateImpl<TradeTransaction, Long>{

}

