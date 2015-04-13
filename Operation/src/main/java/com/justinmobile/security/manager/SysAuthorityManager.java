package com.justinmobile.security.manager;

import java.util.List;

import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManager;
import com.justinmobile.security.domain.SysAuthority;

public interface SysAuthorityManager extends EntityPageManager<SysAuthority> {

	public List<SysAuthority> getSysAuthorityByUser(String userName) throws BusinessException;
	
	public List<SysAuthority> getSysAuthorityByResource(String resString) throws BusinessException;
	
}
