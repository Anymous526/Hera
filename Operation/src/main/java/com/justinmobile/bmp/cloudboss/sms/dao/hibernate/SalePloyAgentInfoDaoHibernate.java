package com.justinmobile.bmp.cloudboss.sms.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.cloudboss.sms.dao.SalePloyAgentInfoDao;
import com.justinmobile.bmp.cloudboss.sms.domain.SalePloyAgentInfo;
import com.justinmobile.core.dao.EntityPageDaoHibernate;

@Repository("salePloyAgentInfoDao")
public class SalePloyAgentInfoDaoHibernate extends EntityPageDaoHibernate<SalePloyAgentInfo> implements SalePloyAgentInfoDao {

}
