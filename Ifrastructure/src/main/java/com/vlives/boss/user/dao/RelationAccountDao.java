/*
 * @(#)UserDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.user.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.user.domain.RelationAccount;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Repository("relationAccountDao")
public class RelationAccountDao extends BaseDaoHibernateImpl<RelationAccount, Long>{

}