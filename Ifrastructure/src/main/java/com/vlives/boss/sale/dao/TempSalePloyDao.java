/*
 * @(#)SalePloyDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sale.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.sale.domain.TempSalePloy;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Repository("tempSalePloyDao")
public class TempSalePloyDao extends BaseDaoHibernateImpl<TempSalePloy, Long>{

}