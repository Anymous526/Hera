package com.justinmobile.security.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.security.dao.SysResourceDao;
import com.justinmobile.security.domain.SysResource;

@Repository("sysResourceDao")
public class SysResourceDaoHibernate extends EntityPageDaoHibernate<SysResource> implements SysResourceDao {

}
