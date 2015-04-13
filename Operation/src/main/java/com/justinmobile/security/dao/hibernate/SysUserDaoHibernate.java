package com.justinmobile.security.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.security.dao.SysUserDao;
import com.justinmobile.security.domain.SysUser;

@Repository("sysUserDao")
public class SysUserDaoHibernate extends EntityPageDaoHibernate<SysUser> implements SysUserDao {

}
