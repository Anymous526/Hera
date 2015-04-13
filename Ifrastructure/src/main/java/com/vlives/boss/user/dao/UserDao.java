/*
 * @(#)UserDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.user.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.user.domain.User;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Repository("userDao")
public class UserDao extends BaseDaoHibernateImpl<User, Long>{

}

