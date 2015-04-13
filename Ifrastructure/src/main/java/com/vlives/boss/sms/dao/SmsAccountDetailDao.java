/*
 * @(#)SalePloyDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sms.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.sms.domain.SmsAccountDetail;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Repository("smsAccountDetailDao")
public class SmsAccountDetailDao extends BaseDaoHibernateImpl<SmsAccountDetail, Long>{

}

