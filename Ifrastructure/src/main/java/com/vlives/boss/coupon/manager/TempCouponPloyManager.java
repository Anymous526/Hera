/**
 * @(#)TempCouponPloyManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.manager;

import com.vlives.boss.coupon.domain.TempCouponPloy;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-8
 */
public interface TempCouponPloyManager {
	public TempCouponPloy get(Long id);
	/**
	 * 创建临时的活动
	 * @author jianguo.xu
	 * @param tempSalePloy
	 * @return
	 */
	public void create(TempCouponPloy tempCouponPloy);
	public void update(TempCouponPloy tempCouponPloy);
}
