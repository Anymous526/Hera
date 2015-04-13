package com.justinmobile.bmp.pos.dao;

import java.util.List;

import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.core.dao.EntityPageDao;

public interface PosAppVersionDao extends EntityPageDao<PosAppVersion> {

	List<PosAppVersion> getValidVersions();
}