package com.vlives.boss.merchant.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.area.manager.AreaManager;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.MemberGroup;
import com.vlives.boss.merchant.dao.MerchantDao;
import com.vlives.boss.merchant.domain.DiscountRule;
import com.vlives.boss.merchant.domain.MemberUpdateRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.Merchant.Status;
import com.vlives.boss.merchant.domain.MerchantCategory;
import com.vlives.boss.merchant.domain.MerchantExtendInfo;
import com.vlives.boss.merchant.domain.MerchantRecommend;
import com.vlives.boss.merchant.domain.PointRule;
import com.vlives.boss.merchant.domain.UpdateRuleItem;
import com.vlives.boss.merchant.dto.MerchantSearchResult;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.manager.OperatorManager;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.security.domain.Role;
import com.vlives.boss.security.manager.RoleManager;
import com.vlives.boss.sms.domain.SmsAccountDetail.Type;
import com.vlives.boss.sms.manager.MerchantSmsAccountManager;
import com.vlives.boss.user.domain.User;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.dao.generic.finder.SimpleQueryStringFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.util.DateUtils;
import com.vlives.util.RandomCodeUtils;
import com.vlives.util.RandomCodeUtils.RandomType;

@Service("merchantManager")
public class MerchantManagerImpl implements MerchantManager {

	@Autowired
	private MerchantDao merchantDao; 
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private MerchantCategoryManager merchantCategoryManager;
	@Autowired
	private MerchantSmsAccountManager merchantSmsAccountManager;
	
	@Autowired
	private OperatorManager operatorManager;
	
	@Autowired
	private RoleManager roleManager;
	 
	@Autowired
	private MerchantExtendInfoManager merchantExtendInfoManager;
	
	@Autowired
	private MemberUpdateRuleManager memberUpdateRuleManager;
 
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Merchant getByCode(String code) {
		return merchantDao.getByProperty("code", code);
	}
	 

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public String create(Merchant merchant,MerchantExtendInfo merchantExtendInfo,String managerMobile,Operator operater) throws BusinessException {
		assertMerchant(merchant);
		if(!merchant.isExistParent()) {
			merchant.setMemberGroup(new MemberGroup());
		} else {
			merchant.setMemberGroup(merchant.getParent().getMemberGroup());
		}
		Date date  = new Date();
		String code = DateUtils.format(date, "yyyyMMddHHmmss")+RandomCodeUtils.random(1, RandomType.NUMBER);
		merchant.setCode(code);
		merchant.setStatus(Merchant.Status.STATUS_WAIT_AUDIT);
		merchant.setCreateDate(new Date());
		merchantDao.save(merchant);
		merchantExtendInfoManager.create(merchantExtendInfo, merchant);
		Operator merchantOp = new Operator();
		merchantOp.setMobile(managerMobile);
		merchantOp.setPassword(Operator.DEFAULT_PASSWORD);
		List<Role> roles = roleManager.findAll();
		operatorManager.create(merchantOp,merchant,roles,operater);
		merchant.addStatusLog(operater,null, Merchant.Status.STATUS_WAIT_AUDIT, "创建商户");
		createMerchantMemberLevel(merchant);
		createMerchantRecommend(merchant);
		return code;
	}
	/**
	 * 创建商户推荐排序
	 * @author jianguo.xu
	 */
	private void createMerchantRecommend(Merchant merchant){
		MerchantRecommend merchantRecommend = new MerchantRecommend();
		merchantRecommend.setCurDate(new Date());
		merchantRecommend.setMerchant(merchant);
		merchant.setMerchantRecommend(merchantRecommend);
	}
	
	/**
	 * 审核通过后给商户增加赠送的短信
	 * @author jianguo.xu
	 * @param merchantExtendInfo
	 * @param operater
	 * @throws BusinessException
	 */
	private void addSmsCount(MerchantExtendInfo merchantExtendInfo,Operator operater) throws BusinessException{
		if(merchantExtendInfo.getPresentSmsCount()==0) return;
		merchantSmsAccountManager.addSmsCount(merchantExtendInfo.getMerchant(), merchantExtendInfo.getPresentSmsCount(), Type.TYPE_PRESEN, "首次合作赠送短信", operater);
	}
	
	/**
	 * 创建商户等级
	 * @author jianguo.xu
	 * @param merchant
	 */
	private void createMerchantMemberLevel(Merchant merchant) {
		Level[] levels = Level.values(); 
		Set<MemberUpdateRule> memberUpdateRules = new LinkedHashSet<MemberUpdateRule>();
		for(Level level : levels) {
			MemberUpdateRule rule = new MemberUpdateRule();
			rule.setMerchant(merchant);
			rule.setLevel(level);
			memberUpdateRules.add(rule);
		}
		merchant.setMemberUpdateRules(memberUpdateRules);
	}
	
	private void assertMerchant(Merchant merchant) throws BusinessException{
		if(isExistNum(merchant.getNum())) {
			throw new BusinessException("商户短信编号已存在！");
		}
		if(merchant.isExistParent()) {
			if(merchant.getParent().isExistParent()) {
				throw new BusinessException("子商户不能再作为父商户！");
			}
			if(!merchant.getParent().isActive()) {
				throw new BusinessException("父商户无效！");
			}
		}
		
		
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean isExistNum(long num) {
		return merchantDao.isExisted("num", num);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Merchant> find(Map<String, Object> map, Pagination pagination) {
		Finder finder = constFinder(map);
		return merchantDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Merchant> find(Map<String,Object> map) {
		Finder finder = constFinder(map);
		return merchantDao.find(finder);
	}
	
	private Finder constFinder(Map<String, Object> map) {
		if(map.get("name")!=null) {
			map.put("name", "%"+map.get("name")+"%");
		}
		if(map.get("sort1") == null && map.get("sort2") == null) {
			map.put("sort", "m.name");
		}
		if(map.get("sort1")!=null) {
			map.put("sort1", "m."+map.get("sort1"));
		}
		if(map.get("sort2")!=null) {
			map.put("sort2", "m."+map.get("sort2"));
		}
		String dynamicString = "from Merchant m where 1=1"+
		   "{ and (m.name like :name or m.shortName like :name)}" +
		   "{ and m.code = :code}"+
		   "{ and m.area in (:areas)}"+
		   "{ and m.category = :category}"+
		   "{ and m.status in (:status)}"+
		   "{ order by ?sort }" +
		   "{ order by ?sort1 asc}" +
		   "{ order by ?sort2 desc}";
		Finder finder = new DynamicFinder(dynamicString,map);
		return finder;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Merchant> findOtherParents(Map<String,Object> map,Pagination pagination){
		map.put("status", Merchant.Status.STATUS_ACTIVE);
		map.put("sort", "m.name");
		String hql = "from Merchant m where 1=1 and m.parent is null " +
				"{ and m.status =:status}" +
				"{ and m.name like :merchantName}" +
				"{ order by ?sort}";
		Finder finder = new DynamicFinder(hql,map);
		return merchantDao.find(finder,pagination);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void freeze(Merchant merchant, String desc,Operator operator) throws BusinessException {
		if(merchant.isFreeze()) {
			throw new BusinessException("商户已经被冻结，不允许重复冻结！");
		}
		if(!merchant.canFreese()) {
			throw new BusinessException("商户不允许冻结，存在未冻结的子商户或存在待审核的子商户，或者存在审核不通过的子商户");
		}
		if(merchant.isWaitAduit()) {
			throw new BusinessException("商户未审核，不允许冻结！");
		}
		Status startStatus = merchant.getStatus();
		merchant.setStatus(Merchant.Status.STATUS_FREEZED);
		merchantDao.update(merchant);
		merchant.addStatusLog(operator,startStatus,merchant.getStatus(), "冻结商户  "+desc);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Merchant get(Long id) {
		return merchantDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void logOut(Merchant merchant, String desc,Operator operator) throws BusinessException {
		assertLogOutTrue(merchant);
		Status startStatus = merchant.getStatus();
		merchant.setStatus(Merchant.Status.STATUS_DISABLE);
		merchantDao.update(merchant);
		merchant.addStatusLog(operator,startStatus,merchant.getStatus(),"注销商户  "+desc);
	}
	
	private void assertLogOutTrue(Merchant merchant) throws BusinessException{
		for(Pos pos :  merchant.getPoses()) {
			if(!pos.isLogOut()) {
				throw new BusinessException("商户有未注销的pos机！");
			}
		}
		if(merchant.isExistChildren()) {
			for(Merchant child :  merchant.getChildrens()) {
				if(child.isActive()) {
					throw new BusinessException("商户不能被注销，存在未注销的子商户！");
				}
				if(child.isWaitAduit()) {
					throw new BusinessException("商户不能被注销，存在待审核的子商户！");
				}
				if(child.isFreeze()) {
					throw new BusinessException("商户不能被注销，存在被冻结的子商户！");
				}
				if(child.isAduitFail()) {
					throw new BusinessException("商户不能被注销，存在审核不通过的子商户！");
				}
			}
		} 
		int memberCount = merchant.getMemberGroup().getMemberCount();
		if(memberCount>0) {
			throw new BusinessException("商户不能被注销，存在"+memberCount+"个会员！");
		}
		if(merchant.isLogOut()){
			throw new BusinessException("商户已注销！");
		}
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void unFreeze(Merchant merchant,String desc, Operator operator) throws BusinessException {
		if(!merchant.isFreeze()) {
			throw new BusinessException("商户不允许解冻！");
		}
		if(merchant.isExistParent()&&merchant.getParent().isFreeze()) {
			throw new BusinessException("商户不允许解冻，其父商户为冻结状态");
		}
		Status startStatus = merchant.getStatus();
		merchant.setStatus(Merchant.Status.STATUS_ACTIVE);
		merchantDao.update(merchant);
		merchant.addStatusLog(operator,startStatus, merchant.getStatus(), "解冻商户  "+desc);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(Merchant merchant, Operator operator) throws BusinessException{
		if(!merchant.isAllowModify())
			throw new BusinessException("商户信息不允许修改");
		
		if(merchant.isAduitFail()) {
			Status startStatus = merchant.getStatus();
			merchant.setStatus(Status.STATUS_WAIT_AUDIT);
			merchant.addStatusLog(operator, startStatus, merchant.getStatus(), "修改商户信息，等待审核");
		}
			
		merchantDao.update(merchant);
		
	}
	

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void aduitMerchant(Merchant merchant, boolean pass, String desc,
			Operator operator) throws BusinessException{
		if(!merchant.isWaitAduit()){
			throw new BusinessException("商户不允许审核");
		}
		Status startStatus = merchant.getStatus();
		if(pass) {
			merchant.setStatus(Status.STATUS_ACTIVE);
			addSmsCount(merchant.getMerchantExtendInfo(), operator);
			merchant.addStatusLog(operator, startStatus, merchant.getStatus(), "商户审核通过  "+desc);
		} else {
			merchant.setStatus(Status.STATUS_AUDIT_FAIL);
			merchant.addStatusLog(operator, startStatus, merchant.getStatus(), "商户审核不通过  "+desc);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void levelManagement(PointRule pointRule, DiscountRule discountRule,
			List<UpdateRuleItem> items, Merchant merchant,MemberUpdateRule memberUpdateRule) {
		merchant.addDiscountRule(discountRule);
		merchant.addPointRule(pointRule);
		if(items != null && items.size()>0){
			for (UpdateRuleItem updateRuleItem : items) {
				memberUpdateRule.addUpdateRuleItem(updateRuleItem);
			}
		}
		merchantDao.update(merchant);
		memberUpdateRuleManager.update(memberUpdateRule);
	}
 
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean isExistCode(String code){
		Merchant merchant = this.getByCode(code);
		if(merchant == null) {
			return false;
		} else {
			return true;
		}
	}


	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Merchant> findBy(Map<String, Object> map,Pagination pagination) {
		if(map.get("name")!=null) {
			map.put("name", "%"+map.get("name")+"%");
		}
		String dynamicString = "from Merchant m where 1=1"+
			"{ and m.name like :name}" +
			"{ and m.code = :merchantCode}" +
			"{ and m.contactTelephone = :contactTelephone}" +
			"{ and m.area.id in (:areas)}"+
			"{ and m.category.id in (:categories)}" +
			"{ order by ?orderBy}";
		Finder finder = new DynamicFinder(dynamicString,map);
		return merchantDao.find(finder,pagination);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Merchant> findMerchantsByMember(User user, Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		String dynamicString = "select m from Merchant m, Member r where  m.memberGroup = r.memberGroup and r.user =:user order by m.createDate desc " ;
		Finder finder = new SimpleParametersFinder(dynamicString, map);
		return merchantDao.find(finder, pagination);
	}


	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long countMerchant() {
		String hql = "from Merchant m where m.status=1";
		Finder finder = new SimpleQueryStringFinder(hql);
		return merchantDao.count(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Merchant> getTopMerchants(Area city, int maxResult) {
		String hql ="from Merchant m"
			+ " where m.status = 1 and (m.area = :city or m.area.parent = :city) order by m.merchantRecommend.sortId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("city", city);
		Finder finder = new SimpleParametersFinder(hql, params);
		return merchantDao.find(finder, maxResult);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MerchantSearchResult search(Map<String, Object> map, Pagination pagination) {
		if(map.get("name")!=null) {
			String value = (String) map.get("name");
			map.put("name", "%"+value+"%");
		}
		
		if(map.get("searchArea")!=null) {
			Area area = (Area) map.get("searchArea");
			map.put("searchArea", area);
		}
		List<Merchant> merchants = merchantDao.find(createSearchFinder(map), pagination);
		map.remove("sort");
		String queryString = getSearchQueryString(map);
		Map<Area, Long> areas = createAreaResult(queryString, map);
		Map<MerchantCategory, Long> categories = createMerchantCategoryResult(queryString, map);
		long preferentialCount = createPreferentialCount(queryString, map);
		return new MerchantSearchResult(merchants, areas, categories,preferentialCount);
		 
	}
	
	private long createPreferentialCount(String queryString,Map<String, Object> params) {
		queryString = queryString.startsWith("select")?"from"+queryString.split("from")[1]:queryString;
		queryString ="select  count(distinct m.id) "+queryString;
		List<Object> results = merchantDao.findToObjectArray(new DynamicFinder(queryString,params));
		
		return  (Long)(results.get(0));
	 
	}
	
	private Map<Area, Long> createAreaResult(String queryString,Map<String, Object> params) {
		Map<Area, Long> areas = new LinkedHashMap<Area, Long>();
		queryString = queryString.startsWith("select")?"from"+queryString.split("from")[1]:queryString;
		queryString="select m.area.id, count(m.area.id) "+queryString+
		" and m.area.id is not null group by m.area.id order by count(m.area.id) desc";
		
		List<Object> results = merchantDao.findToObjectArray(new DynamicFinder(queryString,params));
		if(results.size() == 0) return areas;
		for(Object res : results) {
			Object[] values = (Object[]) res;
			Long areaId = (Long) values[0];
			Long count = (Long) values[1];
			 
			Area area = areaManager.get(areaId);
			areas.put(area, count);
		}
		return areas;
	}
	
	private Map<MerchantCategory, Long> createMerchantCategoryResult(String queryString,Map<String, Object> params) {
		Map<MerchantCategory, Long> categories = new LinkedHashMap<MerchantCategory, Long>();
		queryString = queryString.startsWith("select")?"from"+queryString.split("from")[1]:queryString;
		queryString="select m.category.id, count(m.category.id) "+queryString+" and m.category.id is not null group by m.category.id order by count(m.category.id) desc";
		
		List<Object> results = merchantDao.findToObjectArray(new DynamicFinder(queryString,params));
		if(results.size() == 0) return categories;
		for(Object res : results) {
			Object[] values = (Object[]) res;
			Long categoryId = (Long) values[0];
			Long count = (Long) values[1];
			MerchantCategory  category = merchantCategoryManager.getById(categoryId);
			categories.put(category, count);
		}
		return categories;
	}
	
	
	
	private static final String getSearchQueryString(Map<String, Object> map) {
		String sortValue = (String) map.get("sort");
		if(sortValue!=null&&(sortValue.equals("commentcount")||sortValue.equals("favcount"))) {
			return "select m from Merchant m left join m.merchantReferenceStatistics sta where m.status=1"+
				"{ and (m.shortName like :name or m.businessAddress like:name)}" +
				"{ and m.area.id in (:area)}"+
				"{ and (m.area = :searchArea or m.area.parent = :searchArea)}"+
				"{ and m.category.id in (:category)}" +
				"{ order by ?sort}";
		}
		else {
			return "from Merchant m where m.status=1"+
			"{ and (m.shortName like :name or m.businessAddress like:name)}" +
			"{ and m.area.id in (:area)}"+
			"{ and (m.area = :searchArea or m.area.parent = :searchArea)}"+
			"{ and m.category.id in (:category)}" +
			"{ order by ?sort}";
		}
	}

	private static final Finder createSearchFinder(Map<String, Object> map){
		String queryString = getSearchQueryString(map);
		String sortValue = (String) map.get("sort");
		if(sortValue!=null){
			if(sortValue.equals("commentcount")) {
				map.put("sort", "decode(sta.commentCount,null,0,sta.commentCount) desc");
			}
			else if(sortValue.equals("favcount")) {
				map.put("sort", "decode(sta.favCount,null,0,sta.favCount) desc");
			}
			else if(sortValue.equals("membercount")) {
				map.put("sort", "m.memberGroup.memberCount desc");
			}
			else {
				map.put("sort", "m.merchantRecommend.sortId");
			}
		}
		return new DynamicFinder(queryString,map);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Merchant> findByNear(Merchant merchant,int maxResults) {
		Area area = merchant.getArea();
		String hql = "from Merchant m where m.area = :area";
		List<Merchant> merchants = merchantDao.find(new SimpleParametersFinder(hql, "area", area),maxResults);
		merchants.remove(merchant);
		if(merchants.size() == 0&&area.isDistrict()){
			hql = "from Merchant m where m.area.parent = :area";
			merchants = merchantDao.find(new SimpleParametersFinder(hql, "area", area),maxResults);
			merchants.remove(merchant);
		}
		return merchants;
	}


	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Merchant getByNum(Long num) {
		return merchantDao.getByProperty("num", num);
	}

}
