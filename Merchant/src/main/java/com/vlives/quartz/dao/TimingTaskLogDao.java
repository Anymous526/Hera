/*
 * @(#)SalePloyDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.quartz.dao;
import org.springframework.stereotype.Repository;

import com.vlives.core.dao.generic.BaseDaoHibernateImpl;
import com.vlives.quartz.domain.TimingTaskLog;


@Repository("timingTaskLogDao")
public class TimingTaskLogDao extends BaseDaoHibernateImpl<TimingTaskLog, Long>{

}

