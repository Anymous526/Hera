/*
 * @(#)PosDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.pos.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.pos.domain.Pos;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Repository("posDao")
public class PosDao extends BaseDaoHibernateImpl<Pos, Long>{

}

