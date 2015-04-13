package com.vlives.boss.merchant.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.merchant.domain.DiscountRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.PropertiesFinder;

@Service("discountRuleManager")
public class DiscountRuleManagerImpl implements DiscountRuleManager {

	@Resource
	private BaseDao<DiscountRule,Long> discountRuleDao;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void synchronizeRule(Merchant merchant, List<DiscountRule> discountRules) {
		if(merchant.getDiscountRules()!=null&&merchant.getDiscountRules().size()>0) {
			deleteRule(merchant,discountRules);
			
		}
		for(DiscountRule discountRule : discountRules) {
			merchant.addDiscountRule(discountRule);
			discountRuleDao.saveOrUpdate(discountRule);
		}
	}
	/**
	 * 删除 merchant 中不存在的折扣规则
	 * @author jianguo.xu
	 * @param merchant
	 * @param discountRules
	 */
	private void deleteRule(Merchant merchant, List<DiscountRule> discountRules) {
		if(merchant.getDiscountRules() == null||merchant.getDiscountRules().size() == 0 )return;
		List<DiscountRule> needRemoveRules = new ArrayList<DiscountRule>();
		for(DiscountRule oldDiscountRule : merchant.getDiscountRules()) {
			addNeedRemoveRule(needRemoveRules, oldDiscountRule, discountRules);
		}
		merchant.getDiscountRules().removeAll(needRemoveRules);
	}
	
	private void addNeedRemoveRule(List<DiscountRule> needRemoveRules,DiscountRule oldDiscountRule,List<DiscountRule> discountRules){
		for(DiscountRule newDiscountRule : discountRules) {
			 if(oldDiscountRule.getType()==newDiscountRule.getType()) return;
		}
		oldDiscountRule.setMerchant(null);
		needRemoveRules.add(oldDiscountRule);
	}
	
	
	
	

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DiscountRule getByLevel(Merchant merchant,DiscountRule.Type discountType, Level level) {
		PropertiesFinder finder = new PropertiesFinder(DiscountRule.class);
		finder.add("paramerOne", level.getValue());
		finder.add("merchant", merchant);	
		finder.add("type", discountType);
		return  discountRuleDao.getBy(finder);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public DiscountRule getByBuss(Merchant merchant,DiscountRule.Type discountType, Level level){
		DiscountRule discountRule=this.getByLevel(merchant,discountType, level);		
		if(discountRule==null){
			if(merchant.isExistParent()){
				discountRule=this.getByLevel(merchant.getParent(),discountType, level);	
			}
		}
		return discountRule;
	}
}
