package com.justinmobile.security.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.security.dao.SysRoleDao;
import com.justinmobile.security.domain.SysRole;

@Repository("sysRoleDao")
public class SysRoleDaoHibernate extends EntityPageDaoHibernate<SysRole> implements SysRoleDao {

}
