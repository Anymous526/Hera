package com.vlives.boss.merchant.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.PointRule;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;


@Service("pointRuleManager")
public class PointRuleManagerImpl implements PointRuleManager {

	@Resource
	private BaseDao<PointRule,Long> pointRuleDao;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void synchronizeRule(Merchant merchant,List<PointRule> pointRules) {
		deleteRule(merchant,pointRules);
		for(PointRule pointRule : pointRules) {
			merchant.addPointRule(pointRule);
			pointRuleDao.saveOrUpdate(pointRule);
		}
	}	
	/**
	 * 删除 merchant 中不存在的积分规则
	 * @author jianguo.xu
	 * @param merchant
	 * @param discountRules
	 */
	private void deleteRule(Merchant merchant, List<PointRule> pointRules) {
		if(merchant.getPointRules() == null||merchant.getPointRules().size() == 0 )return;
		List<PointRule> needRemoveRules = new ArrayList<PointRule>();
		for(PointRule oldPointRule : merchant.getPointRules()) {
			addNeedRemoveRule(needRemoveRules, oldPointRule, pointRules);
		}
		merchant.getPointRules().removeAll(needRemoveRules);
	}
	private void addNeedRemoveRule(List<PointRule> needRemoveRules,PointRule oldPointRule,List<PointRule> pointRules){
		for(PointRule newPointRule : pointRules) {
			 if(oldPointRule.getType()==newPointRule.getType()) return;
		}
		oldPointRule.setMerchant(null);
		needRemoveRules.add(oldPointRule);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<PointRule> findPointRule(Merchant merchant) {
		String hql = " from PointRule where merchant = ? ";
		Finder finder = new SimpleParametersFinder(hql, "merchant", merchant);
	    return pointRuleDao.find(finder);

	}
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PointRule get(Long id) {		
		return pointRuleDao.get(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PointRule getByMerAndLevel(Merchant merchant, Level level) {		
		PropertiesFinder finder = new PropertiesFinder(PointRule.class);
		finder.add("paramerOne", level.getValue());
		finder.add("merchant", merchant);
		finder.add("type", PointRule.Type.BY_MEMBER_LEVEL);
		return  pointRuleDao.getBy(finder);
	}
	
	
	@Override	
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public  PointRule getByBuss(Merchant merchant, Level level){
		PointRule pointRule=this.getByMerAndLevel(merchant, level);
		if(pointRule==null){
			if(merchant.isExistParent()){
				pointRule=this.getByMerAndLevel(merchant.getParent(), level);	
			}
		}
		return pointRule;
	}
	
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int getIncreasePoint(Member member, BigDecimal amount,Merchant merchant) {
		PointRule pointRule= getByBuss(merchant, member.getLevel());
		BigDecimal scale=new BigDecimal("100");
		if(pointRule!=null){
			scale=new BigDecimal(String.valueOf(pointRule.getParamerTwo()));
		}
		BigDecimal point=amount.multiply(scale).divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP);	
		return point.intValue();
	}
	
}
