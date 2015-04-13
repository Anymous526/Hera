package com.vlives.boss.merchant.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.UpdateRuleItem;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.manager.OperatorManager;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;

@Service("updateRuleItemManager")
public class UpdateRuleItemManagerImpl implements UpdateRuleItemManager {

	private static final Log LOG = LogFactory.getLog(UpdateRuleItemManagerImpl.class);
	@Resource
	private BaseDao<UpdateRuleItem, Long> updateRuleItemDao;
	@Autowired
	private MemberUpdateRuleManager memberUpdateRuleManager;
	@Autowired
	private OperatorManager operatorManager;
	@Autowired
	private MemberManager memberManager;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UpdateRuleItem get(Long id) {
		return updateRuleItemDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public UpdateRuleItem getByMerAndType(Merchant merchant, UpdateRuleItem.Type ruleType, Level level) {
		String hql = "select up from UpdateRuleItem up  left join  up.memberUpdateRole  m  where"
				+ "  m.merchant=:merchant  and  up.type=:type and m.level=:level ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchant", merchant);
		map.put("type", ruleType);
		map.put("level", level);
		Finder finder = new SimpleParametersFinder(hql, map);
		return updateRuleItemDao.getBy(finder);
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public UpdateRuleItem getByBuss(Merchant merchant, UpdateRuleItem.Type ruleType, Level level){
		UpdateRuleItem updateRuleItem=this.getByMerAndType(merchant,ruleType, level);
		if(updateRuleItem==null){
			if(merchant.isExistParent()){
				updateRuleItem=this.getByMerAndType(merchant.getParent(),ruleType, level);	
			}
		}
		return updateRuleItem;
	}
	
	
	

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void upMemberGrade(Member member, BigDecimal amount,Merchant merchant) {
		if (member.getLevel() == Level.DIAMOND) {
			return;
		}		
		Level levelAmount=this.upGradeByOnceConsume(member, amount,merchant);
		Level levelPoint=this.upGradeByPoint(merchant, member);
		updateMemberLevel(member, merchant, levelAmount, levelPoint);
	}

	private void updateMemberLevel(Member member, Merchant merchant, Level levelAmount, Level levelPoint) {
		Level upLevel=null;
		
		if(levelAmount.getValue()>=levelPoint.getValue()){
			upLevel=levelAmount;
		}else{
			upLevel=levelPoint;
		}
		LOG.info("会员升级前的："+member.getLevel().getDesc());
		
		if(upLevel.getValue()>member.getLevel().getValue()){			
			Operator operator=operatorManager.get(2L);
			member.addLevelLog(operator, upLevel, "等级升级");
			member.setLevel(upLevel);
			member.setLastConsumeDate(new Date());
			
			memberUpdateRuleManager.givePoint(merchant, member);
			memberManager.update(member);
			
		}
		LOG.info("会员升级后的："+member.getLevel().getDesc());
	}
	/**
	 * 按照金额等级升级原则
	 * 
	 * @param member
	 * @param amount
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private Level upGradeByOnceConsume(Member member, BigDecimal amount,Merchant merchant) {		
		Level memberLevel = member.getLevel();
		Level upLevel = memberLevel;
		if (memberLevel == Level.GENERAL) {

			boolean up = compareAmount(amount, merchant,Level.GENERAL);
			if (up) {
				upLevel = Level.SILVER;
			}

		} else if (memberLevel == Level.SILVER) {
			boolean up = compareAmount(amount, merchant,Level.SILVER);
			if (up) {
				upLevel = Level.GOLD;
			}

		} else if (memberLevel == Level.GOLD) {
			boolean up = compareAmount(amount, merchant,Level.GOLD);
			if (up) {
				upLevel = Level.DIAMOND;
			}
		} else if (memberLevel == Level.DIAMOND) {

		}
		return upLevel;

	}
	
	/**
	 * 根据金额升级零界点，判断是否该升级到下级。
	 * @param amount
	 * @param merchant
	 * @param level
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private boolean compareAmount(BigDecimal amount, Merchant merchant,Level level) {
		UpdateRuleItem ruleOne = this.getByBuss(merchant, UpdateRuleItem.Type.TYPE_ONCE_CONSUME, level);
		if(ruleOne==null){
			return false;
		}
		BigDecimal amounyPoint = new BigDecimal(String.valueOf(ruleOne.getUpdatePoint()));
		if (amount.compareTo(amounyPoint) >= 0) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * 根据积分判断 会员该升级何等级。
	 * @param merchant
	 * @param member
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private Level upGradeByPoint(Merchant merchant, Member member) {
		Level memberLevel = member.getLevel();
		int totalPoint = member.getTotalPoint();
		Level upLevel = memberLevel;	
			 if (memberLevel.getValue() <= Level.GOLD.getValue()) {
					boolean up = comparePoint(merchant, totalPoint, Level.GOLD);
					if (up) {
					  return upLevel = Level.DIAMOND;
					} 

			}
			 if (memberLevel.getValue() <= Level.SILVER.getValue()) {
				boolean up = comparePoint(merchant, totalPoint, Level.SILVER);
				if (up) {
					return upLevel = Level.GOLD;
				} 

			 } 
			 if (memberLevel.getValue() <= Level.GENERAL.getValue()) {
					boolean up = comparePoint(merchant, totalPoint, Level.GENERAL);
					if (up) {
						upLevel = Level.SILVER;
					} else {
						return upLevel;
					}

				}		
		return upLevel;
	}
	
	/**
	 * 比较总积分与积分零界点
	 * @param merchant
	 * @param totalPoint
	 * @param level
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private boolean comparePoint(Merchant merchant, int totalPoint, Level level) {
		UpdateRuleItem ruleOne = this.getByBuss(merchant, UpdateRuleItem.Type.TYPE_POINT, level);
		if(ruleOne==null){
			return false;
		}
		int upPoint = ruleOne.getUpdatePoint();
		if (totalPoint >= upPoint) {
			return true;
		}
		return false;
	}

}
