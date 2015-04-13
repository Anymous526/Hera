/**
 * @(#)OperatorManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.manager;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorRole;
import com.vlives.boss.operator.domain.Operator.Status;
import com.vlives.boss.operator.domain.Operator.Type;
import com.vlives.boss.security.domain.Role;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.security.AuthenticationException;
import com.vlives.core.security.Principal;
import com.vlives.core.security.Verifier;
import com.vlives.core.security.impl.PasswordVerifier;
import com.vlives.util.DateUtils;
import com.vlives.util.ObjectComparatorUtils;
import com.vlives.util.DateUtils.TimeUnit;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-3
 */
@Service("operatorManager")
public class OperatorManagerImpl implements OperatorManager{
	
	@Resource
	private BaseDao<Operator, Long> operatorDao;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Operator get(Long id) {
		return operatorDao.get(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Principal get(Serializable id) {
		return this.get((Long)id);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Operator getByMobile(String mobile) {
		return operatorDao.getByProperty("mobile", mobile);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Principal authenticate(Verifier verifier)
			throws AuthenticationException {
		try {
			return login(verifier);
		} catch (BusinessException e) {
			throw new AuthenticationException(e.getMessage());
		}
	}
	
	private Operator login(Verifier verifier) throws BusinessException {
		Operator operator = null;
		if(verifier instanceof PasswordVerifier){
			PasswordVerifier pv=(PasswordVerifier)verifier;
			operator = getByMobile(pv.getLoginName());;
			assertLoginTrue(operator,pv.getPassword());
		}
		 
		operator.setLastLoginDate(DateUtils.clear(new Date(), TimeUnit.MILLISECONDS));
		operatorDao.update(operator);
		return operator;
	}
	
	private void assertLoginTrue(Operator operator,String password) throws BusinessException{
		if(operator == null) {
			throw new BusinessException("管理员帐号不存在");
		}
		if(!operator.getMerchant().isActive()) {
			throw new BusinessException("商户被冻结");
		}		
		if(!operator.isEnable()) {
			throw new BusinessException("用户已被冻结");
		}		
		if(!operator.getPassword().equals(DigestUtils.shaHex(password))) {
			throw new BusinessException("密码错误");
		}
	}

	 
	 
	private void create(Operator operator, Merchant merchant,Operator creator)
			throws BusinessException {
		assertCanCreate(operator, merchant);
		operator.setPassword(DigestUtils.shaHex(operator.getPassword()));
		operator.setCreateDate(new Date());
		operator.setCreator(creator);
		operator.setMerchant(merchant);
		operator.setStatus(Status.STATUS_ACTIVE);
		operator.setType(Type.TYPE_MERCHANT);
		operator.addStatusLog(creator,null, Status.STATUS_ACTIVE, "创建管理员");
		operatorDao.save(operator);
	}
	private void assertCanCreate(Operator operator, Merchant merchant) throws BusinessException {
		if(getByMobile(operator.getMobile())!=null) {
			throw new BusinessException("手机号已存在");
		}
		if(!merchant.isCanCreateOperator()) {
			throw new BusinessException("商户状态异常");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Operator> findByMerchant(Merchant merchant) {
		Finder finder = new  SimpleParametersFinder("from Operator op where op.merchant=:merchant", "merchant", merchant);
		return operatorDao.find(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updatePassword(Operator operator, String oldPass, String newPass)
			throws BusinessException {
		if(!operator.getPassword().equals(DigestUtils.shaHex(oldPass))) {
			throw new BusinessException("密码不正确");
		}
		operator.setPassword(DigestUtils.shaHex(newPass));
		operatorDao.update(operator);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void resetPassword(Operator operator) {
		operator.setPassword(DigestUtils.shaHex(Operator.DEFAULT_PASSWORD));
		operatorDao.update(operator);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void freezeOperator(Operator manager, Operator operator)
			throws BusinessException {
		if(!manager.isEnable()) {
			throw new BusinessException(manager.getStatus().getDesc()+"状态 不能被冻结");
		}
		if(manager == operator) {
			throw new BusinessException("不能自己冻结自己");
		}
		updateStatus(manager, Status.STATUS_FREEZED, "冻结管理员", operator);
	}
	
	private void updateStatus(Operator manager, Status newStatus, String desc, Operator operator) {
		Status startStatus = manager.getStatus();
		manager.setStatus(newStatus);
		manager.addStatusLog(operator, startStatus, newStatus, desc);
		operatorDao.update(manager);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void unfreezeOperator(Operator manager, Operator operator)
			throws BusinessException {
		if(manager.isEnable()) {
			throw new BusinessException(manager.getStatus().getDesc()+"状态 不能被解冻");
		}
		updateStatus(manager, Status.STATUS_ACTIVE, "解冻管理员", operator);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void bindRole(Operator manager, List<Role> roles) {
		Set<OperatorRole> oldRoles = manager.getOperatorRoles();
		if(oldRoles!=null) {
			for(OperatorRole role : oldRoles) {
				role.setOperator(null);
			}
			manager.getOperatorRoles().removeAll(oldRoles);
		}
		String[][] roleSort = new String[][]{new String[]{"id",ObjectComparatorUtils.ASCE}};
		Collections.sort(roles, new ObjectComparatorUtils(Role.class, roleSort));
		for(Role role:roles) {
			manager.addRole(role);
		}
		operatorDao.update(manager);
	}
 
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(Operator operator, Merchant merchant,
			List<Role> roles, Operator creator)
			throws BusinessException {
		this.create(operator, merchant, creator);
		this.bindRole(operator, roles);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public  List<Operator> find(Map<String,Object> params,Pagination pagination) {
		String hql = "from Operator o where 1=1" +
				"{ and o.merchant =:merchant}" +
				"{ and o.mobile=:mobile}" +
				"{ and o.createDate>=:startCreateDate}" +
				"{ and o.createDate<=:endCreateDate}";
		Finder finder = new DynamicFinder(hql, params);
		return operatorDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Boolean isExistMobile(String mobile) {
		return operatorDao.isExisted("mobile", mobile);
	}

	@Override
	public Operator findMerchantOperator(Merchant merchant) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("type", Operator.Type.TYPE_MERCHANT);
		params.put("merchant", merchant);
		String hql = "from Operator o where 1=1" +
						"{ and o.type =:type }" +
						"{ and o.merchant =:merchant }" +
						" order by o.createDate";
		Finder finder = new DynamicFinder(hql,params);
		return operatorDao.find(finder).iterator().next();
	}
	 
}
