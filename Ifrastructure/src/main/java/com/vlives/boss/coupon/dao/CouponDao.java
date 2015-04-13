package com.vlives.boss.coupon.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

@Repository("couponDao")
public class CouponDao extends BaseDaoHibernateImpl<Coupon, Long> {

}
