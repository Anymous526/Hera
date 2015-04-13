package com.justinmobile.bmp.common.log.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.common.log.dao.LogDao;
import com.justinmobile.bmp.common.log.domain.OperationLog;
import com.justinmobile.core.dao.EntityPageDaoHibernate;

@Repository("logDao")
public class LogDaoHibernate extends EntityPageDaoHibernate<OperationLog> implements LogDao {

}
