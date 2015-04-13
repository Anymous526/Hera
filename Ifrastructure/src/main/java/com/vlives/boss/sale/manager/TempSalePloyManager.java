/*
 * @(#)SalePloyManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sale.manager;

import com.vlives.boss.sale.domain.TempSalePloy;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
public interface TempSalePloyManager {
	public TempSalePloy get(Long id);
	/**
	 * 创建临时的活动
	 * @author jianguo.xu
	 * @param tempSalePloy
	 * @return
	 */
	public void create(TempSalePloy tempSalePloy);
	public void update(TempSalePloy tempSalePloy);
	
}

