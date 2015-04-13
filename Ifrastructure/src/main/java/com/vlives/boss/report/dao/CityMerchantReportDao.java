/*
 * @(#)PosDao.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.report.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.report.domain.CityMerchantReport;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * 
 * @author  tiger
 * @version 1.0,2011-6-1
 */
@Repository("cityMerchantReportDao")
public class CityMerchantReportDao extends BaseDaoHibernateImpl<CityMerchantReport, Long>{

}

