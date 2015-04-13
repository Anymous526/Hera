/*
 * @(#)SalePloyDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sms.dao;
import org.springframework.stereotype.Repository;

import com.vlives.boss.sms.domain.MerchantSmsAccount;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;


@Repository("merchantSmsAccountDao")
public class MerchantSmsAccountDao extends BaseDaoHibernateImpl<MerchantSmsAccount, Long>{

}

