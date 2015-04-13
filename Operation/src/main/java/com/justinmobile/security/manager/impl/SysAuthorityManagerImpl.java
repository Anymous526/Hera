package com.justinmobile.security.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManagerImpl;
import com.justinmobile.security.dao.SysAuthorityDao;
import com.justinmobile.security.domain.SysAuthority;
import com.justinmobile.security.manager.SysAuthorityManager;

@Service("sysAuthorityManager")
public class SysAuthorityManagerImpl extends EntityPageManagerImpl<SysAuthority, SysAuthorityDao> implements
		SysAuthorityManager {
	
	@SuppressWarnings("unused")
	private SysAuthorityDao sysAuthorityDao;

	public void setSysAuthorityDao(SysAuthorityDao sysAuthorityDao) {
		this.sysAuthorityDao = sysAuthorityDao;
	}

	public List<SysAuthority> getSysAuthorityByUser(String userName)
			throws BusinessException {
		String hsql = "select auth from " + SysAuthority.class.getName() + " as auth " +
					 	"left join auth.sysRoles as role " +
						"left join role.sysUsers as user where user.userName = ?";
		return query(hsql, userName);
	}

	public List<SysAuthority> getSysAuthorityByResource(String resString)
			throws BusinessException {
		if (resString.indexOf("*") == resString.length() -1) {
			resString = StringUtils.replace(resString, "*", "%");
		}
		String hsql = "select auth from " + SysAuthority.class.getName() + " as auth " +
	 				 	"left join auth.sysResources as res where res.filterString like ?";
		
		return query(hsql, resString);
	}

}
