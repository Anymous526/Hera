/**
 * @(#)Coupon.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.coupon.domain.CouponLog;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * @author unicorn
 *
 */
@Repository("couponLogDao")
public class CouponLogDao extends BaseDaoHibernateImpl<CouponLog, Long> {

}
