package com.justinmobile.bmp.pos.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.bmp.pos.dao.MerchantAppConfigDao;

@Repository("merchantAppConfigDao")
public class MerchantAppConfigDaoHibernate extends EntityPageDaoHibernate<MerchantAppConfig> implements MerchantAppConfigDao {
}