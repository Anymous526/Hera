/**
 * @(#)CouponPloyManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloy.CouponPloyStatus;
import com.vlives.boss.coupon.domain.CouponPloyType;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.dao.OperatorDao;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.user.domain.User;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-9-7
 */
@Service("couponPloyManager")
public class CouponPloyManagerImpl implements CouponPloyManager {
	@Resource
	private BaseDao<CouponPloy, Long> couponPloyDao;
	@Resource
	private OperatorDao operatorDao;
	@Autowired
	private CouponManager couponManager;

	@Override
	public CouponPloy get(Long id) {
		return couponPloyDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(CouponPloy couponPloy, List<User> ployUsers,
			Operator operator) throws BusinessException {
		assertCanCreate(couponPloy, ployUsers);
		create(couponPloy, operator);
		if (ployUsers != null && ployUsers.size() > 0)
			couponManager.createCoupon(couponPloy, ployUsers);
	}

	private void assertCanCreate(CouponPloy couponPloy, List<User> ployUsers)
			throws BusinessException {
		if (ployUsers == null || ployUsers.size() == 0) {
			throw new BusinessException("优惠券活动没有针对的活动用户");
		}
		if (ployUsers.size() > couponPloy.getMerchant().getMerchantSmsAccount()
				.getRemainCount()) {
			throw new BusinessException("营销短信不够");
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(CouponPloy couponPloy, Operator operator) {
		couponPloy.setCouponPloyStatus(CouponPloyStatus.WAIT_AUDIT);
		couponPloy.setCreateDate(new Date());
		couponPloy.setCurrentStatusDesc("创建优惠券活动等待审核");
		couponPloyDao.save(couponPloy);
		couponPloy.addStatusLog(operator, null, CouponPloyStatus.WAIT_AUDIT,
				"创建优惠券活动等待审核");
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void logout(CouponPloy couponPloy, Operator operator)
			throws BusinessException {
		if (!couponPloy.isCanLogout()) {
			throw new BusinessException("电子券活动状态非待审核或审核失败，不能删除！");
		}
		CouponPloyStatus startStatus = couponPloy.getCouponPloyStatus();
		couponPloy.setCouponPloyStatus(CouponPloyStatus.LOGOUT);
		couponPloyDao.update(couponPloy);
		couponPloy.addStatusLog(operator, startStatus,
				couponPloy.getCouponPloyStatus(), "注销电子券活动 ");
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void modify(CouponPloy couponPloy, Operator operator)
			throws BusinessException {
		if (!couponPloy.isAduitFail()) {
			throw new BusinessException("活动类型不是审核未通过状态,不能修改短信内容");
		}
		CouponPloyStatus startStatus = couponPloy.getCouponPloyStatus();
		couponPloy.setCouponPloyStatus(CouponPloyStatus.WAIT_AUDIT);
		couponPloyDao.update(couponPloy);
		couponPloy.addStatusLog(operator, startStatus,
				couponPloy.getCouponPloyStatus(), "修改活动 ");

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CouponPloy> findValidPloyByType(Merchant merchant,
			CouponPloyType couponPloyType) {
		String hql = "select c.couponPloy from CouponPloyApplyMerchant c "
				+ "where c.merchant =:merchant and c.couponPloy.couponPloyType =:couponPloyType " +
						"and c.couponPloy.couponPloyStatus = 1 and c.couponPloy.validEndDate >=:today " +
						"and c.couponPloy.sendStartDate<=:today and c.couponPloy.sendEndDate>=:today";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", merchant);
		params.put("couponPloyType", couponPloyType);
		params.put("today", new Date());
		Finder finder = new SimpleParametersFinder(hql, params);
		return couponPloyDao.find(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CouponPloy> find(Map<String, Object> params,
			Pagination pagination) {
		Finder finder = constFinder(params);
		return couponPloyDao.find(finder, pagination);
	}

	private Finder constFinder(Map<String, Object> params) {
		String hql = "select distinct(ploy) from CouponPloy ploy ,CouponPloyApplyMerchant cpam where cpam.couponPloy = ploy"
				+ "{ and cpam.merchant = :applyMerchant}"
				+ "{ and ploy.couponPloyType = :couponPloyType}"
				+ "{ and ploy.couponType = :couponType}"
				+ "{ and ploy.couponPloyStatus in (:couponPloyStatus)}"
				+ "{ and ploy.validStartDate >= :validStartDate}"
				+ "{ and ploy.validEndDate <= :validEndDate}"
				+ "{ and ploy.createDate >= :createStartDate}"
				+ "{ and ploy.createDate <= :createEndDate}"
				+ "{ and ploy.merchant =:merchant}"
				+ "{ and ploy.merchant.code =:code}"
				+ "{ and ploy.merchant.name like :merchantName}"
				+ "{ and ploy.merchant.area in (:areas)}" 
				+ " order by ploy.createDate desc";
		Finder finder = new DynamicFinder(hql, params);
		return finder;
	}

	public List<CouponPloy> findByMerchant(Map<String, Object> params,
			Pagination pagination) {
		String hql = "select ploy from CouponPloy ploy ,CouponPloyApplyMerchant cpam where cpam.couponPloy = ploy"
				+ "{ and ploy.merchant =:merchant}"
				+ "{ and cpam.merchant =:applyMerchant}"
				+ "{ and ploy.couponPloyStatus in (:couponPloyStatus)}";
		Finder finder = new DynamicFinder(hql, params);
		return couponPloyDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void auditPloy(CouponPloy couponPloy, boolean auditPass,
			String desc, Operator operator) throws BusinessException {
		CouponPloyStatus startStatus = couponPloy.getCouponPloyStatus();
		if (!couponPloy.isAduitWait()) {
			throw new BusinessException("电子券活动不能被审核");
		}
		if (auditPass) {
			couponPloy.setCouponPloyStatus(CouponPloyStatus.AUDIT_SUCCESS);
			if (couponPloy.getSendStartDate() == null) {
				couponPloy.setSendStartDate(new Date());
			}
		} else {
			couponPloy.setCouponPloyStatus(CouponPloyStatus.AUDIT_FAIL);
		}
		couponPloy.setCurrentStatusDesc(desc);
		couponPloyDao.update(couponPloy);
		couponPloy.addStatusLog(operator, startStatus,
				couponPloy.getCouponPloyStatus(), "审核电子券活动 " + desc);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void pausePloy(CouponPloy couponPloy, String pauseDesc,
			Operator operator) throws BusinessException {
		if (!couponPloy.isActive()) {
			throw new BusinessException("电子券活动为非有效状态，不能暂停");
		}
		CouponPloyStatus startStatus = couponPloy.getCouponPloyStatus();
		couponPloy.setCouponPloyStatus(CouponPloyStatus.PAUSE);
		couponPloy.setCurrentStatusDesc(pauseDesc);
		couponPloyDao.update(couponPloy);
		couponPloy.addStatusLog(operator, startStatus,
				couponPloy.getCouponPloyStatus(), "暂停电子券活动 " + pauseDesc);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void restorePloy(CouponPloy couponPloy, String restoreDesc,
			Operator operator) throws BusinessException {
		if (!couponPloy.isPause()) {
			throw new BusinessException("电子券活动为非暂停状态，不能恢复");
		}
		CouponPloyStatus startStatus = couponPloy.getCouponPloyStatus();
		couponPloy.setCouponPloyStatus(CouponPloyStatus.AUDIT_SUCCESS);
		couponPloy.setCurrentStatusDesc(restoreDesc);
		couponPloyDao.update(couponPloy);
		couponPloy.addStatusLog(operator, startStatus,
				couponPloy.getCouponPloyStatus(), "恢复电子券活动 " + restoreDesc);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CouponPloy> getMerchantCouponPloys(Merchant merchant) {
		String hql = "from CouponPloy ploy where ploy.merchant = :merchant"
				+ " and ploy.couponPloyStatus = :status"
				+ " and :currentDate between ploy.sendStartDate and ploy.sendEndDate"
				+ " and ploy.displayInWeb = :displayInWeb"
				+ " order by ploy.createDate desc";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", merchant);
		params.put("status", CouponPloyStatus.AUDIT_SUCCESS);
		params.put("currentDate", new Date());
		params.put("displayInWeb", true);
		Finder finder = new SimpleParametersFinder(hql, params);
		return couponPloyDao.find(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Boolean isMerchantInPromotion(Merchant merchant) {
		String hql = "from CouponPloy ploy where ploy.merchant = :merchant"
				+ " and ploy.couponPloyStatus = :status"
				+ " and :currentDate between ploy.sendStartDate and ploy.sendEndDate"
				+ " and ploy.displayInWeb = :displayInWeb"
				+ " order by ploy.createDate desc";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", merchant);
		params.put("status", CouponPloyStatus.AUDIT_SUCCESS);
		params.put("currentDate", new Date());
		params.put("displayInWeb", true);
		Finder finder = new SimpleParametersFinder(hql, params);
		return (couponPloyDao.count(finder) > 0);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CouponPloy> getLastestPromotions(Area city, int number) {
		String hql = "from CouponPloy ploy where ploy.couponPloyStatus = :status"
				+ " and :currentDate between ploy.sendStartDate and ploy.sendEndDate"
				+ " and ploy.displayInWeb = :displayInWeb"
				+ " and (ploy.merchant.area = :city or ploy.merchant.area.parent = :city)"
				+ " order by ploy.createDate desc";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", CouponPloyStatus.AUDIT_SUCCESS);
		params.put("currentDate", new Date());
		params.put("displayInWeb", true);
		params.put("city", city);
		Finder finder = new SimpleParametersFinder(hql, params);
		return couponPloyDao.find(finder, number);
	}

	@Override
	public List<CouponPloy> findDuePloyToEnd() {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from CouponPloy c where  c.couponPloyStatus in(0,1,3) and   c.validEndDate<:today";
		map.put("today", new Date());
		Finder finder = new SimpleParametersFinder(hql, map);
		return couponPloyDao.find(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updateDuePloyToEnd(CouponPloy couponPloy)
			throws BusinessException {
		if (!couponPloy.isExpired()) {
			throw new BusinessException("电子劵活动未过期");
		}
		Operator operator = operatorDao.get(Operator.SYS_OPERATOR_ID);
		couponPloy.addStatusLog(operator, couponPloy.getCouponPloyStatus(),
				CouponPloyStatus.COMPLETE, "活动结束");
		couponPloy.setCouponPloyStatus(CouponPloyStatus.COMPLETE);
		couponPloyDao.update(couponPloy);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CouponPloy> findValidPloy(Merchant merchant) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = " select  c  from  CouponPloyApplyMerchant m inner join m.couponPloy c where c.couponPloyStatus in(1,3) and m.merchant=:merchant ";
		map.put("merchant", merchant);
		Finder finder = new SimpleParametersFinder(hql, map);
		return couponPloyDao.find(finder);
	}

}
