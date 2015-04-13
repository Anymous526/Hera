package com.justinmobile.bmp.pos.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.bmp.pos.dao.PosAppVersionDao;

@Repository("posAppVersionDao")
public class PosAppVersionDaoHibernate extends EntityPageDaoHibernate<PosAppVersion> implements PosAppVersionDao {

	@Override
	public List<PosAppVersion> getValidVersions() {
		String hql = "from PosAppVersion as version where version.status = ?";
		return super.find(hql, PosAppVersion.STATUS_VALID);
	}
}