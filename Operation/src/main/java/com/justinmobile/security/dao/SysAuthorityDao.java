package com.justinmobile.security.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.justinmobile.core.dao.EntityPageDao;
import com.justinmobile.security.domain.SysAuthority;

public interface SysAuthorityDao extends EntityPageDao<SysAuthority> {
	
	public List<SysAuthority> getAllEnableAuthorities() throws DataAccessException;

}
