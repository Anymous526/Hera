/*
 * @(#)TradeDetailDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;


@Repository("posTradeLogDao")
public class PosTradeLogDao extends BaseDaoHibernateImpl<PosTradeLog, Long>{

}

