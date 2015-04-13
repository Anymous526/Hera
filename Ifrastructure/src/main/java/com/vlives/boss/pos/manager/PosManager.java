/*
 * @(#)PosManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.pos.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.pos.dto.PosInfo;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * description pos的管理
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
public interface PosManager {
	/**
	 * 根据编号得到POS
	 * 
	 * @author jianguo.xu
	 * @param code
	 * @return
	 */
	public Pos getByCode(String code);

	/**
	 * 创建商户POS机
	 * 
	 * @author jianguo.xu
	 * @param code
	 *            --pos编号
	 * @param merchant
	 *            --pos机关联的商户
	 * @param posDesc
	 *            --pos机的状态 要对传入的状态进行判断
	 */
	public void create(Operator operator, String posSerialNumber, String code,
			Merchant merchant, String posDesc) throws BusinessException;

	/**
	 * 冻结
	 * 
	 * @author jianguo.xu
	 * @param operator
	 *            --冻结的操作员
	 */
	public void freeze(Pos pos, Operator operator) throws BusinessException;

	/**
	 * 解冻
	 * 
	 * @author jianguo.xu
	 * @param operator
	 *            --解冻的操作员
	 */
	public void unFreeze(Pos pos, Operator operator) throws BusinessException;

	/**
	 * 注销
	 * 
	 * @author jianguo.xu
	 * @param operator
	 *            --注销的操作员
	 */
	public void logOut(Pos pos, Operator operator) throws BusinessException;

	public Pos get(Long id);

	/**
	 * 根据条件查找pos return list
	 */
	public List<Pos> find(Map<String, Object> map, Pagination pagination);
	
	/**
	 * 查询POS的商户信息
	 * @param map
	 * @param pagination
	 * @return
	 */
	public List<PosInfo> findPosInfo(Map<String, Object> map, Pagination pagination);
	
	public void update(Pos pos);
	
	  /**
     * 根据POS出产编号
     * @param serialNumber
     * @return
     */
	public Pos getByPosSNSerial(String serialNumber);
}
