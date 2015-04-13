package com.vlives.boss.sale.manager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.illegal.domain.IllegalWord;
import com.vlives.boss.illegal.manager.IllegalWordManager;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.manager.OperatorManager;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.domain.SalePloy.SalePloyType;
import com.vlives.boss.sale.domain.SalePloy.Status;
import com.vlives.boss.sale.domain.SalePloy.Type;
import com.vlives.boss.sale.domain.SalePloyUser;
import com.vlives.boss.sms.domain.SmsAccountDetail;
import com.vlives.boss.sms.manager.MerchantSmsAccountManager;
import com.vlives.boss.sms.manager.SmsSendManager;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PlaceholderFinder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.dao.generic.finder.SimpleQueryStringFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.hibernate.HibernateLazyResolver;
import com.vlives.core.support.thread.ExecutorServiceUtil;


@Service("salePloyManager")
public class SalePloyManagerImpl implements SalePloyManager {
	private static final Log LOG = LogFactory.getLog(SalePloyManagerImpl.class);
	
	@Resource
	private BaseDao<SalePloy, Long> salePloyDao;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private SalePloyUserManager salePloyUserManager;
	@Autowired
	private HibernateLazyResolver hibernateLazyResolver;
	@Autowired
	private MerchantSmsAccountManager merchantSmsAccountManager;
	@Autowired
	private SmsSendManager smsSendManager;
	@Autowired
	private IllegalWordManager illegalWordManager;
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private OperatorManager operatorManager;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SalePloy get(Long id) {
		return salePloyDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public SalePloy create(SalePloy salePloy, List<Member> excludeMembers, boolean mechantCreate, Operator operator)
			throws BusinessException {
		assertCanCreate(salePloy, excludeMembers);
		salePloy.setStatus(Status.WAIT_AUDIT);
		salePloy.setCreateDate(new Date());
		salePloy.setType(mechantCreate ? Type.MERCHANT_CREATE : Type.SYS_CREATE);
		salePloy.addSalePloyLog(operator, null, Status.WAIT_AUDIT, "创建营销活动成功，等待审核");
		salePloyDao.save(salePloy);
		createSalePloyMember(salePloy, excludeMembers);
		if (salePloy.getSalePloyType().isSinglePloy())
			merchantSmsAccountManager.reduceSmsCount(salePloy.getMerchant(), salePloy.getSmsCount(),
					com.vlives.boss.sms.domain.SmsAccountDetail.Type.TYPE_DECREASE_BY_PLOY, "创建营销活动减少短信",
					operator);
		return salePloy;
	}

	private void assertCanCreate(SalePloy salePloy, List<Member> excludeMembers) throws BusinessException {
		List<IllegalWord> words = illegalWordManager.findAll();
		for (IllegalWord word : words) {
			if (salePloy.getTemplate().indexOf(word.getContent()) >= 0) {
				throw new BusinessException("营销短信存在非法字：" + word.getContent());
			}
		}
		if (!salePloy.getSalePloyType().isSinglePloy())
			return;
		long count = memberManager.countBySalePloy(salePloy, excludeMembers);
		if (count == 0) {
			throw new BusinessException("营销活动没有针对的活动用户");
		}
		if (count > salePloy.getMerchant().getMerchantSmsAccount().getRemainCount()) {
			throw new BusinessException("营销短信不够");
		}

	}

	private void createSalePloyMember(SalePloy salePloy, List<Member> excludeMembers) {
		List<Member> members = memberManager.findBySalePloy(salePloy, excludeMembers);
		List<SalePloyUser> salePloyUsers = new ArrayList<SalePloyUser>();
		for (Member member : members) {
			createSalePloyMember(salePloyUsers, salePloy, member);
		}
		if (salePloy.getSalePloyType().isSinglePloy())
			salePloy.setSmsCount(members.size());
		salePloyUserManager.create(salePloyUsers);
	}

	private void createSalePloyMember(List<SalePloyUser> salePloyUsers, SalePloy salePloy, Member member) {
		SalePloyUser salePloyUser = new SalePloyUser();
		salePloyUser.setMember(member);
		salePloyUser.setSalePloy(salePloy);
		salePloyUser.setSendDate(new Date());		
		salePloyUsers.add(salePloyUser);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void modity(SalePloy salePloy, String template, Operator operator) throws BusinessException {
		if (!salePloy.isAuditFail())
			throw new BusinessException("状态不正常");
		List<IllegalWord> words = illegalWordManager.findAll();
		for (IllegalWord word : words) {
			if (template.indexOf(word.getContent()) >= 0) {
				throw new BusinessException("营销短信存在非法字：" + word.getContent());
			}
		}
		Status startStatus = salePloy.getStatus();
		salePloy.setStatus(Status.WAIT_AUDIT);
		salePloy.setTemplate(template);
		salePloy.addSalePloyLog(operator, startStatus, Status.WAIT_AUDIT, "修改营销活动，等待审核");
		salePloyDao.update(salePloy);
		if (salePloy.getSalePloyType().isSinglePloy())
			merchantSmsAccountManager.reduceSmsCount(salePloy.getMerchant(), salePloy.getSmsCount(),
					com.vlives.boss.sms.domain.SmsAccountDetail.Type.TYPE_DECREASE_BY_PLOY, "创建营销活动减少短信",
					operator);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void logOut(SalePloy salePloy, Operator operator) throws BusinessException {
		if (!salePloy.isCanLogOut())
			throw new BusinessException("该状态下不能注销");
		if (salePloy.getSalePloyType().isSinglePloy() && (salePloy.isAuditSuccess() || salePloy.isWaitAudit()))
			merchantSmsAccountManager.restore(salePloy.getMerchant(), salePloy.getSmsCount(),
					com.vlives.boss.sms.domain.SmsAccountDetail.Type.TYPE_CANCEL_PLOY, "取消营销活动退回短信", operator);

		Status startStatus = salePloy.getStatus();
		salePloy.setStatus(Status.LOGOUT);
		salePloy.addSalePloyLog(operator, startStatus, Status.LOGOUT, "注销营销活动");
		salePloyDao.update(salePloy);

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SalePloy> find(Map<String, Object> params, Pagination pagination) {
		return salePloyDao.find(getDynamicFinder(params), pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long count(Map<String, Object> params) {
		return salePloyDao.count(getDynamicFinder(params));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long sum(Map<String, Object> params, String sumProName) {
		Long sum = (Long) salePloyDao.sum(getDynamicFinder(params), "s." + sumProName);
		return sum == null ? 0l : sum;
	}

	private static Finder getDynamicFinder(Map<String, Object> params) {
		if (params.get("merchantName") != null) {
			params.put("merchantName", params.get("merchantName") + "%");
		}
		if (params.get("name") != null) {
			params.put("name", params.get("name") + "%");
		}
		if (params.get("memberLevel") != null)
			params.put("memberLevel", "%" + params.get("memberLevel") + "%");
		String hql = " from SalePloy s where 1=1" + "{ and s.name like :name}" + "{ and s.merchant =:merchant}"
				+ "{ and s.merchant.memberGroup =:memberGroup}" + "{ and s.merchant.name like :merchantName}"
				+ "{ and (s.memberLevel like :memberLevel or s.memberLevel is null)}"
				+ "{ and s.createDate >=:startCreateDate}" + "{ and s.createDate <=:endCreateDate}"
				+ "{ and s.salePloyType =:salePloyType}" + "{ and s.status =:status}"
				+ "{ and s.status not in (:notInstatus)}" + " order by s.createDate desc";
		return new DynamicFinder(hql, params);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void auditPloy(SalePloy salePloy, boolean pass, String desc, Operator operator) throws BusinessException {
		if (!salePloy.isWaitAudit())
			throw new BusinessException("状态不正常");
		Status startStatus = salePloy.getStatus();
		if (pass) {
			salePloy.setStatus(Status.AUDIT_SUCCESS);
		} else {
			salePloy.setStatus(Status.AUDIT_FAIL);
			merchantSmsAccountManager.restore(salePloy.getMerchant(), salePloy.getSmsCount(),
					com.vlives.boss.sms.domain.SmsAccountDetail.Type.TYPE_AUDIT_FAIL, "审核不通过退回营销短信数", operator);
		}
		salePloy.addSalePloyLog(operator, startStatus, salePloy.getStatus(), desc);
		salePloyDao.update(salePloy);
	}
 

	/**
	 * 更新活动短信发送结果
	 * 
	 * @param salePloy
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateSendResult(SalePloy salePloy) {
		if(salePloy.getStatus()!=Status.SEND_SUCCESS){
			return;
		}
		int sendCount = (int) salePloyUserManager.getSendCount(salePloy);
		if (sendCount == 0) {
			salePloy.setStatus(Status.SEND_FAIL);
		} else {
			salePloy.setSendCount(sendCount);
			salePloy.setStatus(Status.SEND_COMPLETE);
		}
		int quantity = salePloy.getSmsCount() - sendCount;
		if (quantity != 0) {			
			try {
				Operator operator = operatorManager.get(Operator.SYS_OPERATOR_ID);
				merchantSmsAccountManager.restore(salePloy.getMerchant(), quantity,
						SmsAccountDetail.Type.TYPE_BACK_REMAIN, "退回未发送完的短信数", operator);
			} catch (BusinessException e) {

			}
		}
		salePloyDao.update(salePloy);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SalePloy> findBySendSuccess(Pagination pagination) {
		String hql = "select distinct p from  SalePloyUser s inner join  s.salePloy p  where  s.sendSuccess is not null and   p.status in ( :auditStatus ,:successStatus) and p.salePloyType in ( :audit,:timing)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("auditStatus", Status.AUDIT_SUCCESS);
		map.put("successStatus", Status.SEND_SUCCESS);

		map.put("audit", SalePloyType.AUDIT_PASS_SEND);
		map.put("timing", SalePloyType.TIMING_SEND);
		Finder finder = new SimpleParametersFinder(hql, map);
		return salePloyDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SalePloy> find(Map<String, Object> params) {
		Finder finder = getDynamicFinder(params);
		return salePloyDao.find(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean existSalePloyByType(Merchant merchant, SalePloyType salePloyType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", merchant);
		params.put("salePloyType", salePloyType);
		params.put("status", Status.AUDIT_SUCCESS);
		Finder finder = getDynamicFinder(params);
		return salePloyDao.count(finder) > 0 ? true : false;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void sendSmsByCreateMember(final Member noSessionMember, final Operator operaotr) {

		Runnable command = new Runnable() {
			@Override
			public void run() {
				try {
					hibernateLazyResolver.openSession();
					Member member = memberManager.get(noSessionMember.getId());
					Merchant merchant = member.getCreateMerchant();
					List<SalePloy> salePloys = findCreateMemberTypePloys(merchant);
					for (SalePloy salePloy : salePloys) {
						if (merchant.getMerchantSmsAccount().getRemainCount() == 0)
							return;
						boolean success = smsSendManager.sendSms(member.getUser().getMobile(), salePloy.getTemplate());
						if (success) {
							try {
								merchantSmsAccountManager.reduceSmsCount(merchant, 1,
										SmsAccountDetail.Type.TYPE_TEMP_SEND, "创建会员发送营销短信", operaotr);
							} catch (BusinessException e) {
								LOG.error(e);
								continue;
							}
						}
						salePloy.setSmsCount(salePloy.getSmsCount() + 1);
						salePloy.setSendCount(salePloy.getSendCount() + 1);
						salePloyDao.update(salePloy);
					}
				}
				finally {
					//hibernateLazyResolver.flushSession();
				}

			}
		};
		ExecutorServiceUtil.execute(command);
	}

	private List<SalePloy> findCreateMemberTypePloys(Merchant merchant) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("salePloyType", SalePloyType.CREATE_MEMBER_SEND);
		params.put("status", Status.AUDIT_SUCCESS);
		params.put("merchant", merchant);
		merchant.getId();
		Finder finder = getDynamicFinder(params);
		return salePloyDao.find(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void sendSmsByTrade(final TradeDetail noSessiontradeDetail, final Operator operaotr) {

		Runnable command = new Runnable() {
			@Override
			public void run() {
				try {
					hibernateLazyResolver.openSession();
					TradeDetail tradeDetail = tradeDetailManager.get(noSessiontradeDetail.getId());

					Merchant merchant = tradeDetail.getTradeOrder().getMerchant();

					if (!tradeDetail.isConsumeTrade())
						return;
					BigDecimal tradeMoney = tradeDetail.getAmount();
					List<SalePloy> salePloys = findTradeTypePloys(merchant);
					for (SalePloy salePloy : salePloys) {
						if (merchant.getMerchantSmsAccount().getRemainCount() == 0)
							return;
						if (salePloy.getTradeMinMoney().compareTo(tradeMoney) > 0)
							continue;
						boolean success = smsSendManager.sendSms(tradeDetail.getTradeOrder().getMember().getUser()
								.getMobile(), salePloy.getTemplate());
						if (success) {
							try {
								merchantSmsAccountManager.reduceSmsCount(merchant, 1,
										SmsAccountDetail.Type.TYPE_TEMP_SEND, "单次消费满额发送营销短信", operaotr);
							} catch (BusinessException e) {
								LOG.error(e);
								continue;
							}
						}
						salePloy.setSmsCount(salePloy.getSmsCount() + 1);
						salePloy.setSendCount(salePloy.getSendCount() + 1);
						salePloyDao.update(salePloy);
					}
				} finally {
					//hibernateLazyResolver.flushSession();
				}
			}
		};
		ExecutorServiceUtil.execute(command);
	}

	private List<SalePloy> findTradeTypePloys(Merchant merchant) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("salePloyType", SalePloyType.TRADE_MONEY_SEND);
		params.put("status", Status.AUDIT_SUCCESS);
		params.put("merchant", merchant);
		Finder finder = getDynamicFinder(params);
		return salePloyDao.find(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SalePloy> find() {
		String hql = "from SalePloy sale where sale.status in(1,3,4,6) and sale.merchant.status=1  and sysDate <= sale.validEndDate order by sale.createDate desc";
		Finder finder = new SimpleQueryStringFinder(hql);
		List<SalePloy> result = salePloyDao.find(finder, 5);
		return result;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SalePloy> find(Merchant merchant) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchant", merchant);
		String hql = "from SalePloy sale where sale.status in(1,3,4,6)" + "{ and sale.merchant =:merchant}"
				+ " order by sale.createDate desc";
		Finder finder = new DynamicFinder(hql, map);
		return salePloyDao.find(finder);
	}

	@Override
	public List<SalePloy> getNewlySalePloy(Merchant merchant) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchant", merchant);
		String hql = "from SalePloy sale where sale.status in(1,3,4,6)" + "{ and sale.merchant =:merchant}"
				+ " order by sale.createDate desc";
		Finder finder = new DynamicFinder(hql, map);
		return salePloyDao.find(finder, 1);
	}

	@Override
	public List<SalePloy> getExpiredSalePloy() {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from SalePloy sale where sale.status in(0,1,2) and sale.validEndDate<:today";
		map.put("today", new Date());
		Finder finder = new SimpleParametersFinder(hql, map);
		return salePloyDao.find(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean isExistMerchantPloy(Merchant merchant) {
		String hql = "from SalePloy s where s.status in (1,3,4,6) and s.merchant = ?";
		Finder finder = new PlaceholderFinder(hql, merchant);
		return salePloyDao.isExisted(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updateExpiredStatus(SalePloy salePloy) {
		if (salePloy.getValidEndDate() == null) {
			return;
		}
		if(!(salePloy.getStatus()==Status.WAIT_AUDIT||salePloy.getStatus()==Status.AUDIT_FAIL||salePloy.getStatus()==Status.AUDIT_SUCCESS)){
			return;
		}
		if (new Date().compareTo(salePloy.getValidEndDate())>0) {
			salePloy.setStatus(Status.EXPIREDED);
			int sendCount = (int) salePloyUserManager.getSendCount(salePloy);
			int quantity = salePloy.getSmsCount() - sendCount;
			if (quantity != 0) {				
				try {
					Operator operator = operatorManager.get(Operator.SYS_OPERATOR_ID);
					merchantSmsAccountManager.restore(salePloy.getMerchant(), quantity,
							SmsAccountDetail.Type.TYPE_BACK_REMAIN, "退回未发送完的短信数", operator);
				} catch (BusinessException e) {
				}
			}
			salePloyDao.update(salePloy);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updateSendStatus(SalePloy salePloy) {
		if (salePloy.getStatus() != Status.SEND_SUCCESS) {
			salePloy.setStatus(Status.SEND_SUCCESS);
			salePloyDao.update(salePloy);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object[] findSendFinallyPloy() {
		final String hql = " select a.id from sale_ploy a,(select sp.sale_ploy_id sale_ploy_id, count(sp.sale_ploy_id) "+
		                " quantity from sale_ploy_user sp where sp.success is not null  group by sp.sale_ploy_id ) "+
		                "  b where a.id = b.sale_ploy_id  and a.sms_count=b.quantity   and a.status=3  ";
		return (Object[]) salePloyDao.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Object> list = session.createSQLQuery(hql).list();
				return list.toArray();
			}
		});
	}

}
