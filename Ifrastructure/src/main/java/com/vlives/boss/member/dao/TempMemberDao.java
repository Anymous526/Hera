/*
 * @(#)TempMemberDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.member.domain.TempMember;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-2
 */
@Repository("tempMemberDao")
public class TempMemberDao extends BaseDaoHibernateImpl<TempMember, Long>{

}

