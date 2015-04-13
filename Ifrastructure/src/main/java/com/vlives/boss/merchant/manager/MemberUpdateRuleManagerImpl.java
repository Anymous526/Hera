package com.vlives.boss.merchant.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.member.manager.MemberPointDetailManager;
import com.vlives.boss.merchant.domain.MemberUpdateRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.UpdateRuleItem;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.PropertiesFinder;

@Service("memberUpdateRuleManager")
public class MemberUpdateRuleManagerImpl implements MemberUpdateRuleManager {

	@Resource
	private BaseDao<MemberUpdateRule, Long> memberUpdateRuleDao;
	@Autowired
	private MemberPointDetailManager memberPointDetailManager;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void synchronizeRule(Merchant merchant, Level level, int rewardPoint, List<UpdateRuleItem> items) {
		MemberUpdateRule memberUpdateRule = fromUpdateRule(merchant, level, rewardPoint);
		memberUpdateRuleDao.saveOrUpdate(memberUpdateRule);
		syncRuleItem(memberUpdateRule, items);

	}

	private void syncRuleItem(MemberUpdateRule memberUpdateRule, List<UpdateRuleItem> items) {
		deleteRule(memberUpdateRule, items);
		for (UpdateRuleItem updateRuleItem : items) {
			memberUpdateRule.addUpdateRuleItem(updateRuleItem);
		}
	}

	/**
	 * 删除 merchant 中不存在的升级规则
	 * 
	 * @author jianguo.xu
	 * @param merchant
	 * @param discountRules
	 */
	private void deleteRule(MemberUpdateRule memberUpdateRule, List<UpdateRuleItem> items) {
		if (memberUpdateRule.getUpdateRuleItems() == null || memberUpdateRule.getUpdateRuleItems().size() == 0)
			return;
		List<UpdateRuleItem> needRemoveRuleItems = new ArrayList<UpdateRuleItem>();
		for (UpdateRuleItem oldUpdateRuleItem : memberUpdateRule.getUpdateRuleItems()) {
			addNeedRemoveRule(needRemoveRuleItems, oldUpdateRuleItem, items);
		}
		memberUpdateRule.getUpdateRuleItems().removeAll(needRemoveRuleItems);
	}

	private void addNeedRemoveRule(List<UpdateRuleItem> needRemoveRuleItems, UpdateRuleItem oldUpdateRuleItem,
			List<UpdateRuleItem> items) {
		for (UpdateRuleItem newUpdateRuleItem : items) {
			if (oldUpdateRuleItem.getType() == newUpdateRuleItem.getType())
				return;
		}
		oldUpdateRuleItem.setMemberUpdateRole(null);
		needRemoveRuleItems.add(oldUpdateRuleItem);
	}

	/**
	 * 如果商户的升级规则已存在则直接获得该规则 如果规则不存在则创建规则
	 * 
	 * @author jianguo.xu
	 * @param merchant
	 * @param updateLevel
	 * @return
	 */
	private MemberUpdateRule fromUpdateRule(Merchant merchant, Level level, int rewardPoint) {
		Set<MemberUpdateRule> rules = merchant.getMemberUpdateRules();
		if (rules == null || rules.size() == 0) {
			return createNewRule(merchant, level, rewardPoint);
		}
		for (MemberUpdateRule memberUpdateRule : rules) {
			if (memberUpdateRule.getLevel() == level)
				return memberUpdateRule;
		}
		return createNewRule(merchant, level, rewardPoint);
	}

	private MemberUpdateRule createNewRule(Merchant merchant, Level level, int rewardPoint) {
		MemberUpdateRule rule = new MemberUpdateRule();
		rule.setLevel(level);
		rule.setMerchant(merchant);
		rule.setRewardPoint(rewardPoint);
		merchant.getMemberUpdateRules().add(rule);
		return rule;
	}

	@Override
	public MemberUpdateRule get(Long id) {
		return memberUpdateRuleDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(MemberUpdateRule memberUpdateRule) {
		memberUpdateRuleDao.update(memberUpdateRule);
	}

	@Override
	public MemberUpdateRule getByMerchantAndLevel(Merchant merchant, Level level) {
		PropertiesFinder finder = new PropertiesFinder(MemberUpdateRule.class);
		finder.add("merchant", merchant);
		finder.add("level", level);
		return memberUpdateRuleDao.getBy(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void givePoint(Merchant merchant, Member member) {
		MemberUpdateRule memberUpdateRule = getByMerchantAndLevel(merchant, member.getLevel());
		int rewardPoint = 0;
		if (memberUpdateRule != null) {
			rewardPoint = memberUpdateRule.getRewardPoint();
		}
		if (rewardPoint > 0) {
			member.increasePoint(rewardPoint);
			memberPointDetailManager.createDetail(member, merchant, rewardPoint,
					MemberPointDetail.Type.TYPE_ALL_CONSUME, "商户赠送积分");
		}
	}
}
