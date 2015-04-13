package com.justinmobile.security.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.justinmobile.core.manager.EntityPageManagerImpl;
import com.justinmobile.security.dao.SysRoleDao;
import com.justinmobile.security.domain.SysRole;
import com.justinmobile.security.manager.SysRoleManager;

@Service("sysRoleManager")
public class SysRoleManagerImpl extends EntityPageManagerImpl<SysRole, SysRoleDao> implements SysRoleManager {

	private SysRoleDao sysRoleDao;

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

	public SysRole getRoleByName(String roleName) {
		  
		 List<SysRole> li = sysRoleDao.findByProperty("roleName", roleName);
		 if(li.size()>0){
			 return li.get(0);
		 }
		 return null;
	}
}
