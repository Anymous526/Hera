package com.vlives.boss.coupon.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.coupon.domain.CouponPloyLog;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

@Repository("couponPloyLogDao")
public class CouponPloyLogDao extends BaseDaoHibernateImpl<CouponPloyLog, Long> {

}
