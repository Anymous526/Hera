package com.justinmobile.security.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.security.dao.SysAuthorityDao;
import com.justinmobile.security.domain.SysAuthority;

@Repository("sysAuthorityDao")
public class SysAuthorityDaoHibernate extends EntityPageDaoHibernate<SysAuthority> implements SysAuthorityDao {

	public List<SysAuthority> getAllEnableAuthorities()
			throws DataAccessException {
		Map<String, Object> filterMap = new HashMap<String,Object>();
		filterMap.put("status", SysAuthority.ENABLE);
		return findBy(filterMap);
	}

}
