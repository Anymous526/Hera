package com.vlives.boss.coupon.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

@Repository("couponPloyDao")
public class CouponPloyDao extends BaseDaoHibernateImpl<CouponPloy, Long> {

}
