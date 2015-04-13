/*
 * @(#)PosDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.report.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.report.domain.MerchantReport;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * 
 * @author  tiger
 * @version 1.0,2011-6-1
 */
@Repository("merchantReportDao")
public class MerchantReportDao extends BaseDaoHibernateImpl<MerchantReport, Long>{

}

