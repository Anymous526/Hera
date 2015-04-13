/*
 * @(#)MemberManagerImpl.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.manager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vlives.boss.area.domain.Area;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloyType;
import com.vlives.boss.coupon.domain.rulesupport.SendTrigger;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.member.dao.MemberDao;
import com.vlives.boss.member.dao.MemberPointDetailDao;
import com.vlives.boss.member.domain.ActiveRate;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.Member.Status;
import com.vlives.boss.member.domain.MemberGroup;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.member.domain.MemberSource;
import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.member.dto.MemberReport;
import com.vlives.boss.member.manager.exception.MemberException;
import com.vlives.boss.merchant.domain.MemberUpdateRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MemberUpdateRuleManager;
import com.vlives.boss.operator.dao.OperatorDao;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sms.manager.SmsSendManager;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserSource;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.dao.generic.finder.SimpleQueryStringFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.hibernate.HibernateLazyResolver;
import com.vlives.core.support.thread.ExecutorServiceUtil;
import com.vlives.util.AcceptHashMap;
import com.vlives.util.SmsTemplateUtils;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-6-2
 */
@Service("memberManager")
public class MemberManagerImpl implements MemberManager {

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private UserManager userManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MemberPointDetailDao memberPointDetailDao;
	@Resource
	private OperatorDao operatorDao;
	@Autowired
	private MemberUpdateRuleManager memberUpdateRuleManager;
	@Autowired
	private SmsSendManager smsSendManager;
	@Autowired
	private MemberPointDetailManager memberPointDetailManager;
	@Autowired
	private TempMemberManager  tempMemberManager;
	@Autowired
	private CouponPloyManager couponPloyManager;
	@Autowired
	private CouponManager couponManager;

	
	@Autowired
	private HibernateLazyResolver hibernateLazyResolver;
	private static final Log LOG = LogFactory.getLog(MemberManagerImpl.class);
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Member get(Long id) {
		return memberDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(Member member, Operator operator) throws BusinessException {
		if (getBy(member.getUser(), member.getMemberGroup()) != null) {
			throw new BusinessException("会员已存在");
		}
		member.setStatus(Status.STATUS_ACTIVE);
		memberDao.save(member);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> find(Map<String, Object> params, Pagination pagination) {
		DynamicFinder finder = constFinder(params);
		return memberDao.find(finder, pagination);
	}

	private DynamicFinder constFinder(Map<String, Object> params) {
		if (params.get("name") != null) {
			params.put("name", params.get("name") + "%");
		}
		String hql = " select m from Member m where 1=1 " + "{ and m.status = :status}"
				+ "{ and m.status in (:inStatus)}"
				+ "{ and m.memberGroup = :memberGroup }" + "{ and m.createMerchant = :createMerchant}" 
				+ "{ and m.source = :source}"+ "{ and m.level = :level}"+ "{ and m.user.name like :name}" 
				+ "{ and m.user.mobile = :mobile}"+ "{ and lastConsumeDate>=:startLastConsumeDate}" 
				+ "{ and lastConsumeDate<=:endLastConsumeDate}"+ "{ and createDate>=:startCreateDate}" 
				+ "{ and createDate<=:endCreateDate}" + "{ order by ?orderBy}";
		DynamicFinder finder = new DynamicFinder(hql, params);
		return finder;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Member getBy(User user, MemberGroup memberGroup) {
		PropertiesFinder finder = new PropertiesFinder(Member.class);
		finder.add("user", user);
		finder.add("memberGroup", memberGroup);
		return memberDao.getBy(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void freeze(Member member, Operator operator) throws BusinessException {
		if (!member.isEnable())
			throw new BusinessException("会员现在状态不允许冻结");
		Status startStatus = member.getStatus();
		member.addStatusLog(operator, startStatus, Status.STATUS_FREEZE, "冻结会员");
		member.setStatus(Status.STATUS_FREEZE);
		memberDao.update(member);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void unFreeze(Member member, Operator operator) throws BusinessException {
		if (!member.isFreeze())
			throw new BusinessException("会员现在状态不允许解冻");
		Status startStatus = member.getStatus();
		member.addStatusLog(operator, startStatus, Status.STATUS_ACTIVE, "解冻会员");
		member.setStatus(Status.STATUS_ACTIVE);
		memberDao.update(member);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void logOut(Member member, Operator operator) throws BusinessException {
		if (member.isLogOut())
			throw new BusinessException("会员已经被注销,不允许再注销");
		Status startStatus = member.getStatus();
		member.addStatusLog(operator, startStatus, Status.STATUS_LOGOUT, "注销会员");
		member.setStatus(Status.STATUS_LOGOUT);
		memberDao.update(member);
	}

	private static final Finder getSalePloyFinder(SalePloy salePloy, List<Member> excludeMembers) {
		AcceptHashMap<String, Object> params = AcceptHashMap.newInstance();
		
		params.accept("memberGroup", salePloy.getMerchant().getMemberGroup());
		params.accept("status", Status.STATUS_ACTIVE);
		params.acceptIf("levels", salePloy.getMemberLevels(), salePloy.getMemberLevels() != null
				&& salePloy.getMemberLevels().size() > 0);
		params.acceptIf("minPoint", salePloy.getMinPoint(), salePloy.getMinPoint() != null);
		params.acceptIf("maxPoint", salePloy.getMaxPoint(), salePloy.getMaxPoint() != null);
		if (salePloy.getActiveRate() != null) {
			if (salePloy.getActiveRate() == ActiveRate.RECENT_NULL_CONSUME)
				params.accept("nullConsumeDate", "is null");
			else
				params.accept("lastConsumeDate", salePloy.getActiveRate().getStartDate());
		}
		
		params.acceptIf("excludeMembers", excludeMembers, excludeMembers != null && excludeMembers.size() > 0);
		String hql = "from Member m where m.memberGroup=:memberGroup and m.status=:status"
				+ "{ and m.level in (:levels)}" + "{ and m.totalPoint>=:minPoint}" + "{ and m.totalPoint<=:maxPoint}"
				+ "{ and m.lastConsumeDate<=:lastConsumeDate}" + "{ and m.lastConsumeDate  ?nullConsumeDate}"
				+ "{ and m not in (:excludeMembers)}";
		return new DynamicFinder(hql, params);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> findBySalePloy(SalePloy salePloy, List<Member> excludeMembers) {
		return memberDao.find(getSalePloyFinder(salePloy, excludeMembers));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> findBySalePloy(SalePloy salePloy, List<Member> excludeMembers, Pagination pagination) {
		return memberDao.find(getSalePloyFinder(salePloy, excludeMembers), pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long countBySalePloy(SalePloy salePloy, List<Member> excludeMembers) {
		return memberDao.count(getSalePloyFinder(salePloy, excludeMembers));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> findByMerchant(Merchant merchant) {
		PropertiesFinder finder = new PropertiesFinder(Member.class);
		finder.add("createMerchant", merchant);
		return memberDao.find(finder);
	}

	/**
	 * create by datao.wang 添加用户时需要判断 手机号 的唯一性
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public Member regist(Operator operator, User user, Merchant merchant, int point, Level level,MemberSource source)
			throws BusinessException {
		User memberUser = userManager.getByMobile(user.getMobile());
		Member member = null;
		if (memberUser != null) {
			if (getBy(memberUser, merchant.getMemberGroup()) != null)
				throw new BusinessException("手机号为：" + user.getMobile() + "的会员已经存在！");
			member = createMember(operator, memberUser, merchant, point, level,source);
		 
		}else {
			user.setPassword(DigestUtils.shaHex(User.USER_DEFAULT_PASSWORD));
			userManager.save(user);
			member = createMember(operator, user, merchant, point, level,source);
		}
		
		return member;
		
	}
	/**
	 * 下发电子券给会员
	 */
	private void sendCouponToMember(final Merchant merchant,final Member noSessionMember) {
		Runnable command = new Runnable() {
			@Override
			public void run() {
				try {
					hibernateLazyResolver.openSession();
					Member member = get(noSessionMember.getId());
					List<CouponPloy> couponPloys = couponPloyManager.findValidPloyByType(merchant, CouponPloyType.REGISTER_GIVE_GIFTS);
					if(couponPloys.size()==0)return;
					for(CouponPloy couponPloy : couponPloys) {
						try {
							couponManager.receiveCouponBySendRule(member.getUser(), couponPloy,SendTrigger.createRegisterSendTrigger());
						} catch (BusinessException e) {
							LOG.error("注册会员发送电子券失败", e);
						}
					}
				} finally {
					//hibernateLazyResolver.flushSession();
				}
			}
		};
		ExecutorServiceUtil.execute(command);
	}

	private Member createMember(Operator operator, User user, Merchant merchant, int point, Level level,MemberSource source)
			throws BusinessException {
		Member member = constMember(operator, user, merchant, point, level,source);
		save(member, operator);
		member.addLevelLog(operator, null, member.getLevel(), "创建会员");
		member.addStatusLog(operator, null, member.getStatus(), "创建会员");
		MemberPointDetail memberPointDetail = null;
		if (point > 0) {
			memberPointDetail = constMemberPointDetail(member,MemberPointDetail.Type.TYPE_INIT_POINT);
			memberPointDetailDao.save(memberPointDetail);
		}
		return member;
	}
    /**
     * 创建会员积分明细
     * @param member
     * @param type
     * @return
     */
	private MemberPointDetail constMemberPointDetail(Member member ,MemberPointDetail.Type type) {
		MemberPointDetail memberPointDetail = new MemberPointDetail();
		memberPointDetail.setMember(member);
		memberPointDetail.setPoint(member.getPoint());
		memberPointDetail.setTotalPoint(member.getTotalPoint());
		memberPointDetail.setUsablePoint(member.getPoint());
		memberPointDetail.setDescription(type.getDesc());
		memberPointDetail.setType(type);
		memberPointDetail.setMerchant(member.getCreateMerchant());
		memberPointDetail.setCreateDate(new Date());
		return memberPointDetail;
	}

	private Member constMember(Operator operator, User user, Merchant merchant, int point, Level level,MemberSource source) {
		Member member = new Member();
		member.setUser(user);
		member.setCreateMerchant(merchant);
		member.setMemberGroup(merchant.getMemberGroup());
		member.getMemberGroup().addMemberCount();
		member.setCreateDate(new Date());
		member.setPoint(point);
		member.setTotalPoint(point);
		member.setSource(source);
		member.setLevel(level == null ? Level.GENERAL : level);
		return member;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> find(Map<String, Object> params) {
		DynamicFinder finder = constFinder(params);
		return memberDao.find(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long count(Map<String, Object> params) {
		DynamicFinder finder = constFinder(params);
		return memberDao.count(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long countTotalMemberCount() {
		String hql = "from Member m where m.status=1 group by m.user";
		Finder finder = new SimpleQueryStringFinder(hql);
		return memberDao.count(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Map<String, Object>> statByDate(Object args[]) {
		String sql = "select member_level, count(*) count from member where MERCHANT_ID =? " + "and create_date>=? "
				+ "and create_date<=?" + " group by member_level order by member_level";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
		return list;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Map<String, Object>> statAll(Merchant merchant) {
		String sql = "select member_level, count(*) count from member " + " where MERCHANT_ID = ? "
				+ " group by member_level order by member_level";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, merchant.getId());
		return list;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countMembers(Merchant merchant) {
		return memberDao.count("createMerchant", merchant);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countMerchants(User user) {
		return memberDao.count("user", user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> findPoint(User user, Pagination pagination) {
		String hql = " select m from Member m where m.user =:user";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		Finder finder = new SimpleParametersFinder(hql, map);
		return memberDao.find(finder, pagination);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Member createBySms(String mobileNo, Merchant merchant) throws BusinessException, MemberException {
		User user = userManager.create(mobileNo, UserSource.SOURCE_SMS);
		Member oldMember = getBy(user, merchant.getMemberGroup());
		if (oldMember != null) {
			String smsContent = SmsTemplateUtils.getMemberExisted(mobileNo, merchant.getShortName(), oldMember
					.getLevel().getDesc());
			smsSendManager.sendTimelySMSByMxtong(mobileNo, smsContent);
			throw new MemberException("会员已存在");
		}	
		int point = this.givePoint(merchant, Level.GENERAL);
		Operator operator = operatorDao.get(Operator.SYS_OPERATOR_ID);
		Member member = constMember(operator, user, merchant, point, Level.GENERAL,MemberSource.SOURCE_SMS);
		save(member, operator);
		member.addLevelLog(operator, null, member.getLevel(), "SMS创建");
		member.addStatusLog(operator, null, member.getStatus(), "SMS创建");
		MemberPointDetail memberPointDetail = null;
		if (point > 0){
			memberPointDetail = constMemberPointDetail(member,MemberPointDetail.Type.TYPE_ALL_CONSUME);
			memberPointDetailDao.save(memberPointDetail);
		}
		return member;
	}

	private int givePoint(Merchant merchant, Level level) {
		MemberUpdateRule memberUpdateRule = memberUpdateRuleManager.getByMerchantAndLevel(merchant, level);
		int rewardPoint = 0;
		if (memberUpdateRule != null) {
			rewardPoint = memberUpdateRule.getRewardPoint();
		}
		return rewardPoint;
	}

	@Override
	public List<MemberReport> findTotalMember(Map<String,Object> params,Pagination pagination) {
		String queryString="select m.user.mobile,m.createDate,me.code,me.name " +
				"from Member m  ,Merchant me where m.memberGroup=me.memberGroup " +
				"and me.id not in(1019,1020,1040) { and m.createDate>=:createStartDate } { and m.createDate<=:createEndDate }" +
				"order by me.code,m.createDate";
		Finder finder = new DynamicFinder(queryString, params);
		memberDao.find(finder, pagination);

		List<Object> list = memberDao.findToObjectArray(finder,pagination);
		List<MemberReport> result = new ArrayList<MemberReport>();
		for(Object obj : list) {
			Object[] values = (Object[]) obj;
			MemberReport member = new MemberReport();
			member.setMobile((String)values[0]);
			member.setCreateDate((Date)values[1]);
			member.setMerchantCode((String)values[2]);
			member.setMerchantName((String)values[3]);
			result.add(member);
		}
		return result;
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public boolean isValid(User user, Merchant merchant) {
		Member member = this.getBy(user, merchant.getMemberGroup());
		if (member != null && member.getStatus() == Member.Status.STATUS_ACTIVE) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Member createByPos(String mobileNo, Merchant merchant) throws PosBussinessException, MemberException {	
		User user = userManager.create(mobileNo,UserSource.SOURCE_POS);
		if (!user.isValid()) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_VALID);
		}
		Member member = createMember(user, merchant);
		// 检查会员是否赠送积分。
		memberUpdateRuleManager.givePoint(merchant, member);
		memberDao.update(member);
		//发送注册营销电子劵	
		sendCouponToMember(merchant, member);
		return member;
	}

	/**
	 * 创建初始化会员
	 * 
	 * @param user
	 * @param merchant
	 * @return
	 * @throws MemberException
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private Member createMember(User user, Merchant merchant) throws MemberException {
		if (this.isExit(user, merchant)) {
			throw new MemberException(PosReturnCode.MERCHANT_MEMBER_EXIST);
		}
		Member member = new Member();
		member.setUser(user);
		member.setCreateMerchant(merchant);
		member.setMemberGroup(merchant.getMemberGroup());
		member.setStatus(Member.Status.STATUS_ACTIVE);
		member.setLevel(Level.GENERAL);
		member.setSource(MemberSource.SOURCE_POS);
		member.setCreateDate(new Date());

		Operator operator = operatorDao.get(Operator.SYS_POS_ID);
		member.addLevelLog(operator, null, Level.GENERAL, "创建会员");
		member.addStatusLog(operator, null, member.getStatus(), "创建会员");

		merchant.getMemberGroup().addMemberCount();
		memberDao.save(member);
		return member;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Member bindMember(String mobileNo, TempMember tempMember) throws PosBussinessException, MemberException {
		User user = userManager.create(tempMember, mobileNo);
		this.checkUserValid(user);
		Merchant merchant = tempMember.getMerchant();

		Member member = this.getBy(user, merchant.getMemberGroup());

		if (member == null) {
			member = new Member();
			member.setUser(user);
			member.setCreateMerchant(merchant);
			member.setLevel(tempMember.getLevel());
			member.setPoint(tempMember.getPoint());
			member.setTotalPoint(tempMember.getPoint());
			member.setUser(user);
			member.setCreateMerchant(merchant);
			member.setMemberGroup(merchant.getMemberGroup());
			member.setStatus(Member.Status.STATUS_ACTIVE);
			member.setSource(MemberSource.SOURCE_POS);
			member.setCreateDate(new Date());

			Operator operator = operatorDao.get(Operator.SYS_POS_ID);
			member.addLevelLog(operator, null, tempMember.getLevel(), "会员绑定");
			member.addStatusLog(operator, null, member.getStatus(), "会员绑定");
            
			merchant.getMemberGroup().addMemberCount();
			memberDao.save(member);
			if (tempMember.getPoint() != 0) {
				memberPointDetailManager.createDetail(member, merchant, tempMember.getPoint(),
						MemberPointDetail.Type.TYPE_INIT_POINT, MemberPointDetail.Type.TYPE_INIT_POINT.getDesc());
			}
		}
		this.checkMemberValid(member);
		tempMember.setBind(true);
		tempMemberManager.update(tempMember);
		return member;
	}

	
	public boolean isExit(User user, Merchant merchant) {
		if (this.getBy(user, merchant.getMemberGroup()) != null) {
			return true;
		}
		return false;
	}


	private void checkMemberValid(Member member) throws PosBussinessException {
		if (member.getStatus() != Member.Status.STATUS_ACTIVE) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_VALID);
		}
	}

	private void checkUserValid(User user) throws PosBussinessException {
		if (!user.isValid()) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_VALID);
		}
	}
	
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(Member member) {
		memberDao.update(member);
	}
	
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> findByPloy(Merchant merchant,Map<String,Object> param){
		Finder finder = getSearchMemberByPloyFinder(merchant,param);
		return memberDao.find(finder);
		
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Member> findByPloy(Merchant merchant,Map<String,Object> param,Pagination pagination){
		Finder finder = getSearchMemberByPloyFinder(merchant,param);
		return memberDao.find(finder, pagination);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long countByPloy(Merchant merchant,Map<String,Object> param){
		Finder finder = getSearchMemberByPloyFinder(merchant,param);
		return memberDao.count(finder);
	}
	
	private static Finder getSearchMemberByPloyFinder(Merchant merchant,Map<String,Object> parameters) {
		parameters.put("memberGroup", merchant.getMemberGroup());
		parameters.put("status", Member.Status.STATUS_ACTIVE);
		if (parameters.get("activeRate")!=null) {
			ActiveRate activeRate = (ActiveRate) parameters.get("activeRate");
			if (activeRate == ActiveRate.RECENT_NULL_CONSUME)
				parameters.put("nullConsumeDate", "is null");
			else
				parameters.put("lastConsumeDate", activeRate.getStartDate());
		}
		if (parameters.get("likeMobile")!=null) {
			parameters.put("likeMobile", "%"+parameters.get("likeMobile")+"%");
		}
		Area area = (Area) parameters.get("memberArea");
		if(area!=null) {
			parameters.put("memberArea", area.getAreaSearchCode());
		}
		String queryString = "from Member m where m.memberGroup=:memberGroup and m.status=:status"+
			"{ and m.level in (:levels)}" +
			"{ and m.user.mobile like :likeMobile}" +
			"{ and m.user.mobile in (:mobiles)}" +
			"{ and m.user.gender = :gender}" +
			"{ and m.user.birthday >= :startBirthday}" +
			"{ and m.user.birthday <= :endBirthday}" +
			"{ and m.user.area.searchCode like :memberArea}" +
			"{ and m.source = :memberSource}" +
			"{ and m.createDate >= :startCreateDate}" +
			"{ and m.createDate <= :endCreateDate}" +
			"{ and m.totalPoint>=:minPoint}" + 
			"{ and m.totalPoint<=:maxPoint}"+ 
			"{ and m.lastConsumeDate<=:lastConsumeDate}" + 
			"{ and m.lastConsumeDate  ?nullConsumeDate}"+ 
			"{ and m.id not in (:excludeMemberIds)}";
		return new DynamicFinder(queryString, parameters);
	}
}
