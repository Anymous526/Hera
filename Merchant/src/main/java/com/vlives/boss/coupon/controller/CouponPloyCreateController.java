/**
 * @(#)CouponPloyCreateController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.area.manager.AreaManager;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloyApplyMerchant;
import com.vlives.boss.coupon.domain.CouponPloyType;
import com.vlives.boss.coupon.domain.CouponSendRule;
import com.vlives.boss.coupon.domain.CouponSendRule.RuleType;
import com.vlives.boss.coupon.domain.CouponType;
import com.vlives.boss.coupon.domain.TempCouponPloy;
import com.vlives.boss.coupon.domain.rulesupport.SendRule;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.coupon.manager.TempCouponPloyManager;
import com.vlives.boss.member.domain.ActiveRate;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberSource;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.AcceptHashMap;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * 创建有会员活动的controller
 * @author  jianguo.xu
 * @version 1.0,2011-9-8
 */
@Controller
@RequestMapping("/manager/coupon/create")
public class CouponPloyCreateController {
	
	@Autowired
	private TempCouponPloyManager tempCouponPloyManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private CouponPloyManager couponPloyManager;
	@Autowired
	private MerchantManager merchantManager;
	
	@RequestMapping(value="/create_one.htm",method=RequestMethod.GET)
	public ModelAndView createOne() {
		return new ModelAndView("/manager/coupon/create_one.jsp");
	}
	
	@RequestMapping(value="/create_two.htm",method=RequestMethod.GET)
	public ModelAndView createTwo(@ObjectConvertAnno Operator operator) {
		Long tempId = HttpParameterParser.getLong("tempId");
		int ployType = HttpParameterParser.getIntValue("ployType");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("merchant",operator.getMerchant());
		model.put("ployType",ployType);
		if(tempId!=null) {
			TempCouponPloy tempCouponPloy = tempCouponPloyManager.get(tempId);
			model.put("tempCouponPloy",tempCouponPloy);
			model.put("ployType",tempCouponPloy.getCouponPloyType().getValue());
		}			
		return new ModelAndView("/manager/coupon/create_two.jsp",model);
	}
	
	@RequestMapping(value="/create_three.htm",method=RequestMethod.POST)
	public ModelAndView createThree(@ObjectConvertAnno Operator operator) {
		int ployType =  HttpParameterParser.getIntValue("ployType");
		CouponPloyType couponPloyType = CouponPloyType.get(ployType);
		String title = HttpParameterParser.getString("title");
		Date validStartDate = HttpParameterParser.getDate("validStartDate");
		validStartDate = DateUtils.getEarlyInTheDay(validStartDate);
		Date validEndDate = HttpParameterParser.getDate("validEndDate");
		validEndDate = DateUtils.getLateInTheDay(validEndDate);
		String applyMerchantIds = HttpParameterParser.getString("applyMerchantIds");
		int couponTypeId =  HttpParameterParser.getIntValue("couponType");
		CouponType couponType = CouponType.get(couponTypeId);
		String content = HttpParameterParser.getString("content");
		String couponValue = HttpParameterParser.getString("couponValue"); 
		int sendCount = HttpParameterParser.getIntValue("sendCount");
		BigDecimal minConsumMoney = HttpParameterParser.getBigDecimal("minConsumMoney");
		Long maxSendCount = HttpParameterParser.getLong("maxSendCount");
		
		Long tempId = HttpParameterParser.getLong("tempId");
		TempCouponPloy tempCouponPloy = (tempId == null?new TempCouponPloy(): tempCouponPloyManager.get(tempId));
		tempCouponPloy.setApplyMerchantIds(applyMerchantIds);
		tempCouponPloy.setCouponPloyType(couponPloyType);
		tempCouponPloy.setCouponType(couponType);
		tempCouponPloy.setMerchant(operator.getMerchant());
		tempCouponPloy.setTitle(title);
		tempCouponPloy.setValidStartDate(validStartDate);
		tempCouponPloy.setValidEndDate(validEndDate);
		tempCouponPloy.setCouponValue(couponValue);
		tempCouponPloy.setSendCount(sendCount);
		tempCouponPloy.setMinConsumMoney(minConsumMoney);
		tempCouponPloy.setMaxSendCount(maxSendCount);
		setDefaultSendDate(tempCouponPloy);
		
		if(content.indexOf("有效期：")<=0) {
			StringBuilder sb = new StringBuilder(content+" 有效期：");
			sb.append(DateUtils.format(tempCouponPloy.getValidStartDate(), "MM月dd日"));
			sb.append("到");
			sb.append(DateUtils.format(tempCouponPloy.getValidEndDate(), "MM月dd日"));
			tempCouponPloy.setContent(sb.toString());
		}
		tempCouponPloyManager.create(tempCouponPloy);
		return new ModelAndView(new RedirectView("/manager/coupon/create/create_three_view.htm?tempId="+tempCouponPloy.getId()));
	}
	
	private void setDefaultSendDate(TempCouponPloy tempCouponPloy) {
		if(tempCouponPloy.getSendStartDate() == null) {
			Date sendStartDate = DateUtils.getEarlyInTheDay(new Date());
			tempCouponPloy.setSendStartDate(sendStartDate);
		}
		if(tempCouponPloy.getSendEndDate() == null) {
			Date sendEndDate = DateUtils.getLateInTheDay(new Date());
			sendEndDate = DateUtils.add(sendEndDate, TimeUnit.DAYS, 7);
			sendEndDate = (sendEndDate.compareTo(tempCouponPloy.getValidEndDate())<=0)?sendEndDate:tempCouponPloy.getValidEndDate();
			tempCouponPloy.setSendEndDate(sendEndDate);
		}
	}
	
	@RequestMapping(value="/create_three_view.htm",method=RequestMethod.GET)
	public ModelAndView createThreeView(@ObjectConvertAnno Operator operator) {
		Long tempId = HttpParameterParser.getLong("tempId");
		TempCouponPloy tempCouponPloy = tempCouponPloyManager.get(tempId);
		return new ModelAndView("/manager/coupon/create_three.jsp","tempCouponPloy",tempCouponPloy);
	}
	
	@RequestMapping(value="/create_four.htm",method=RequestMethod.POST)
	public ModelAndView createFour(@ObjectConvertAnno Operator operator) {
		Long tempId = HttpParameterParser.getLong("tempId");
		TempCouponPloy tempCouponPloy = tempCouponPloyManager.get(tempId);
	  
		Date sendStartDate = HttpParameterParser.getDate("sendStartDate");
		sendStartDate = DateUtils.getEarlyInTheDay(sendStartDate);
		tempCouponPloy.setSendStartDate(sendStartDate);
		
		Date sendEndDate = HttpParameterParser.getDate("sendEndDate");
		sendEndDate = DateUtils.getLateInTheDay(sendEndDate);
		tempCouponPloy.setSendEndDate(sendEndDate);
		
		String introduction = HttpParameterParser.getString("introduction");
		tempCouponPloy.setIntroduction(introduction);
		
		boolean displayInWeb = 	HttpParameterParser.getBooleanValue("displayInWeb");
		tempCouponPloy.setDisplayInWeb(displayInWeb);
		if(displayInWeb) {
			boolean agreeDownload = HttpParameterParser.getBooleanValue("agreeDownload");
			if(agreeDownload) {
				long maxReceiveCountByEveryUser = HttpParameterParser.getLongValue("maxReceiveCountByEveryUser");
				tempCouponPloy.setMaxReceiveCountByEveryUser(maxReceiveCountByEveryUser);
			}
		}
		
		String content = HttpParameterParser.getString("content");
		tempCouponPloy.setContent(content);
		tempCouponPloyManager.update(tempCouponPloy);
		
		if(tempCouponPloy.getCouponPloyType().isGroupSendType()) {
			return new ModelAndView(new RedirectView("/manager/coupon/create/select_ploy_user.htm?tempId="+tempCouponPloy.getId()));
		}
		else {
			return new ModelAndView("/manager/coupon/create/create_commit.htm");
			
		}
		
	}
	
	@RequestMapping(value="/select_ploy_user.htm",method=RequestMethod.GET)
	public ModelAndView searchPloyUser(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator) {
		Map<String, Object> model = new HashMap<String, Object>();
		Long tempId = HttpParameterParser.getLong("tempId");
		TempCouponPloy tempCouponPloy = tempCouponPloyManager.get(tempId);
		List<Member> members = memberManager.findByPloy(operator.getMerchant(),createSearchPloyUserParam(tempCouponPloy),pagination);
		
		model.put("operator", operator);
		model.put("members", members);
		model.put("pagination", pagination);
		model.put("tempCouponPloy", tempCouponPloy);
		if(members==null||members.size() == 0||pagination.getCount()>operator.getMerchant().getMerchantSmsAccount().getRemainCount()) {
			model.put("canSubmit", false);
		}
		else {
			model.put("canSubmit", true);
		}
		return new ModelAndView("/manager/coupon/create_four.jsp",model);
	}
	
	private Map<String, Object> createSearchPloyUserParam(TempCouponPloy tempCouponPloy) {
		AcceptHashMap<String, Object> param = AcceptHashMap.newInstance();
		String likeMobile= HttpParameterParser.getString("likeMobile");
		if(likeMobile !=null) {
			param.put("likeMobile", likeMobile);
		}
		int[] memberLevelInts = HttpParameterParser.getIntArray("memberLevel");
		if(memberLevelInts!=null&&memberLevelInts.length>0) {
			List<Level> memberLevels = new ArrayList<Level>();
			for(int levelInt : memberLevelInts) {
				Level level = Level.get(levelInt);
				memberLevels.add(level);
			}
			param.put("levels", memberLevels);
		}
		int gender = HttpParameterParser.getIntValue("gender");
		param.acceptIf("gender", gender, gender!=0);
		
		Date startBirthday = HttpParameterParser.getDate("startBirthday");
		param.acceptIf("startBirthday", startBirthday, startBirthday!=null);
		
		Date endBirthday = HttpParameterParser.getDate("endBirthday");
		param.acceptIf("endBirthday", endBirthday, endBirthday!=null);
		
		putMemberArea(param);
		
		int memberSource = HttpParameterParser.getIntValue("memberSource");
		if(memberSource !=0) {
			param.put("memberSource", MemberSource.get(memberSource));
		}
		
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		param.acceptIf("startCreateDate", startCreateDate, startCreateDate!=null);
		
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
		param.acceptIf("endCreateDate", endCreateDate, endCreateDate!=null);
		
		
		Integer minPoint = HttpParameterParser.getInteger("minPoint");
		param.acceptIf("minPoint", minPoint,minPoint!=null);
		
		Integer maxPoint = HttpParameterParser.getInteger("maxPoint");
		param.acceptIf("maxPoint", maxPoint,maxPoint!=null);
		
		int activeRateInt = HttpParameterParser.getIntValue("activeRate");
		if(activeRateInt!=0) {
			param.put("activeRate", ActiveRate.get(activeRateInt));
		}
		
		Set<Long> excludeMembers = getExcludeMembers(tempCouponPloy);
		if(excludeMembers.size()>0) {
			param.put("excludeMemberIds", excludeMembers);
		}
		return param;
	}
	
	private void putMemberArea(AcceptHashMap<String, Object> param){
		long areaId = HttpParameterParser.getLongValue("district",0);
		if(areaId==0){
			areaId = HttpParameterParser.getLongValue("city",0);
		}
		if(areaId == 0) {
			areaId = HttpParameterParser.getLongValue("province",0);
		}
		if(areaId!=0) {
			param.accept("memberArea",areaManager.get(new Long(areaId)));
		}
	}
	
	private Set<Long> getExcludeMembers(TempCouponPloy tempCouponPloy) {
		String excludeMemberIds = tempCouponPloy.getExcludeMemberIds();
		StringBuilder sb = new StringBuilder();
		if(!StringUtils.isNullOrEmpty(excludeMemberIds)) {
			sb.append(excludeMemberIds);
			if(!excludeMemberIds.endsWith(","))
				sb.append(",");
		}
		String excludeMemberIdStr = HttpParameterParser.getString("excludeMemberId");
		if(!StringUtils.isNullOrEmpty(excludeMemberIdStr)) {
			sb.append(excludeMemberIdStr);
		}
		Set<Long> excludeMembers = new HashSet<Long>();
		if(!StringUtils.isNullOrEmpty(sb.toString())) {
			
			String[] idsStr = sb.toString().split(",");
			for(String id : idsStr) {
				excludeMembers.add(new Long(id));	 
			}
		}
		StringBuilder idStr = new StringBuilder();
		for(Long id : excludeMembers) {
			idStr.append(id).append(",");
		}
		tempCouponPloy.setExcludeMemberIds(idStr.toString());
		tempCouponPloyManager.update(tempCouponPloy);
		return excludeMembers;
	}
	
	
	
	
	@RequestMapping(value="/create_commit.htm",method=RequestMethod.POST)
	public ModelAndView createCommit(@ObjectConvertAnno Operator operator) {
		Long tempId = HttpParameterParser.getLong("tempId");
		TempCouponPloy tempCouponPloy = tempCouponPloyManager.get(tempId);
		List<Member> members = memberManager.findByPloy(operator.getMerchant(),createSearchPloyUserParam(tempCouponPloy));
		CouponPloy couponPloy = createCouponPloy(tempCouponPloy, operator);
		if(couponPloy.getCouponPloyType().isGroupSendType()) {
			try {
				couponPloyManager.create(couponPloy, createPloyUsers(members), operator);
			} catch (BusinessException e) {
				return new ModelAndView("/manager/coupon/create/select_ploy_user.htm","errorMsg",e.getMessage());
			}
		}
		else {
			couponPloyManager.create(couponPloy, operator);
		}
		return new ModelAndView(new RedirectView("/manager/coupon/create/create_success.htm?id="+couponPloy.getId()));
	}
	
	@RequestMapping(value="/create_success.htm",method=RequestMethod.GET)
	public ModelAndView createSuccess(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		CouponPloy couponPloy = couponPloyManager.get(id);
		return new ModelAndView("/manager/coupon/create_success.jsp","couponPloy",couponPloy);
	}
	
	private List<User> createPloyUsers(List<Member> members) {
		List<User> users = new ArrayList<User>();
		for(Member member : members) {
			users.add(member.getUser());
		}
		return users;
	}
	
	private CouponPloy createCouponPloy(TempCouponPloy tempCouponPloy,Operator operator) {
		CouponPloy couponPloy = new CouponPloy();
		couponPloy.setContent(tempCouponPloy.getContent());
		setApplyMerchants(tempCouponPloy, couponPloy, operator);
		couponPloy.setCouponPloyType(tempCouponPloy.getCouponPloyType());
		couponPloy.setCouponType(tempCouponPloy.getCouponType());
		couponPloy.setCouponValue(tempCouponPloy.getCouponValue());
		couponPloy.setDisplayInWeb(tempCouponPloy.isDisplayInWeb());
		couponPloy.setIntroduction(tempCouponPloy.getIntroduction()==null?tempCouponPloy.getContent():tempCouponPloy.getIntroduction());
		couponPloy.setMaxReceiveCountByEveryUser(tempCouponPloy.getMaxReceiveCountByEveryUser());
		couponPloy.setMaxSendCount(tempCouponPloy.getMaxSendCount());
		couponPloy.setMerchant(operator.getMerchant());
		couponPloy.setSendStartDate(DateUtils.getEarlyInTheDay(tempCouponPloy.getSendStartDate()));
		couponPloy.setSendEndDate(DateUtils.getLateInTheDay(tempCouponPloy.getSendEndDate()));
		couponPloy.setTitle(tempCouponPloy.getTitle());
		couponPloy.setValidStartDate(DateUtils.getEarlyInTheDay(tempCouponPloy.getValidStartDate()));
		couponPloy.setValidEndDate(DateUtils.getLateInTheDay(tempCouponPloy.getValidEndDate()));
		setCouponSendRule(tempCouponPloy, couponPloy);
		return couponPloy;
	}
	
	private void setCouponSendRule(TempCouponPloy tempCouponPloy,CouponPloy couponPloy) {
		if(!couponPloy.getCouponPloyType().isGroupSendType()) {
			CouponSendRule couponSendRule = new CouponSendRule();
			if(couponPloy.getCouponPloyType() == CouponPloyType.REGISTER_GIVE_GIFTS) {
				couponSendRule.setRuleType(RuleType.REGISTER_SEND);
				SendRule sendRule = SendRule.createRegisterSendRule(tempCouponPloy.getSendCount());
				couponSendRule.setSendRule(sendRule);
			}
			else  {
				couponSendRule.setRuleType(RuleType.QUOTA_SEND);
				SendRule sendRule = SendRule.createQuotaSendRule(tempCouponPloy.getSendCount(), tempCouponPloy.getMinConsumMoney());
				couponSendRule.setSendRule(sendRule);
			}
			couponPloy.setCouponSendRule(couponSendRule);
			
		}
	}
	
	private void setApplyMerchants(TempCouponPloy tempCouponPloy,CouponPloy couponPloy,Operator operator) {
		Merchant merchant = operator.getMerchant();
		String applyMerchantIds = tempCouponPloy.getApplyMerchantIds();
		Set<CouponPloyApplyMerchant> couponPloyApplyMerchants = new LinkedHashSet<CouponPloyApplyMerchant>();
		if(StringUtils.isNullOrEmpty(applyMerchantIds)) {
			 
			couponPloyApplyMerchants.add(new CouponPloyApplyMerchant(operator.getMerchant(),couponPloy));
			if(merchant.isExistChildren()) {
				for(Merchant children : merchant.getChildrens()) {
					couponPloyApplyMerchants.add(new CouponPloyApplyMerchant(children,couponPloy));
				}
			}
		}
		else {
			for(String idStr : applyMerchantIds.split(",")) {
				Merchant applyMerchant = merchantManager.get(new Long(idStr));
				couponPloyApplyMerchants.add(new CouponPloyApplyMerchant(applyMerchant,couponPloy));
			}
		}
		couponPloy.setCouponPloyApplyMerchants(couponPloyApplyMerchants);
	}
}