/**
 * @(#)ResourceDao.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.security.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.security.domain.Resource;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
@Repository("resourceDao")
public class ResourceDao extends BaseDaoHibernateImpl<Resource, Long> {
	
}
