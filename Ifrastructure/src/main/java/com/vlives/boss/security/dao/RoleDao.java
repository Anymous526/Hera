/**
 * @(#)ResourceDao.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.security.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.security.domain.Role;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
@Repository("roleDao")
public class RoleDao extends BaseDaoHibernateImpl<Role, Long> {
	
}
