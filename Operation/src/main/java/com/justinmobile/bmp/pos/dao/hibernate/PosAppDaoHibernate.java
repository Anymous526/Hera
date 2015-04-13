package com.justinmobile.bmp.pos.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.bmp.pos.dao.PosAppDao;

@Repository("posAppDao")
public class PosAppDaoHibernate extends EntityPageDaoHibernate<PosApp> implements PosAppDao {
}