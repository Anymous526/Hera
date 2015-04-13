/*
 * @(#)SalePloyDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sms.dao;
import org.springframework.stereotype.Repository;

import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;


@Repository("smsRecordDao")
public class SmsRecordDao extends BaseDaoHibernateImpl<SmsRecord, Long>{

}

