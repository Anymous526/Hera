package com.justinmobile.bmp.pos.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.pos.domain.PosAppVersionRecord;
import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.bmp.pos.dao.PosAppVersionRecordDao;

@Repository("posAppVersionRecordDao")
public class PosAppVersionRecordDaoHibernate extends EntityPageDaoHibernate<PosAppVersionRecord> implements PosAppVersionRecordDao {
}