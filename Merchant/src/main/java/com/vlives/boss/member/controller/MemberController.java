/*
 * @(#)MemberController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.Member.Status;
import com.vlives.boss.member.domain.MemberSource;
import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.member.manager.TempMemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.boss.user.domain.TempUser;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserSource;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.vaildator.ParamValidator;
import com.vlives.core.support.spring.vaildator.ParamValidators;
import com.vlives.core.support.spring.vaildator.ValidatorType;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.FileUploadUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Controller
@RequestMapping("/manager/member")
public class MemberController{
	
	@RequestMapping("info/info.htm")
	public ModelAndView info(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator) throws ParseException{
		ModelAndView  model = new ModelAndView("/manager/member/info.jsp");
		Map<String,Object> params = constParams(operator.getMerchant());
		List<Status> status = new ArrayList<Status>();
		status.add(Status.STATUS_ACTIVE);
		status.add(Status.STATUS_FREEZE);
		params.put("inStatus", status);
		List<Member> list = memberManager.find(params, pagination);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("list", list);
		map.put("success", true);
		model.addAllObjects(map);
		model.addAllObjects(params);
		return model;
	}
	
	@RequestMapping("info/member_json.htm")
	@ParamValidators({
	@ParamValidator(param="level",paramName="会员等级",min=0,max=4),
	@ParamValidator(param="mobile",paramName="手机",vaildatorTypes={ValidatorType.MOBILE}),
	@ParamValidator(param="startCreateDate",paramName="开始注册时间",vaildatorTypes=ValidatorType.DATE),
	@ParamValidator(param="endCreateDate",paramName="结束注册时间",vaildatorTypes=ValidatorType.DATE),
	@ParamValidator(param="startLastConsumeDate",paramName="开始最后交易时间",vaildatorTypes=ValidatorType.DATE),
	@ParamValidator(param="endLastConsumeDate",paramName="结束最后交易时间",vaildatorTypes=ValidatorType.DATE)
	})
	public ModelAndView list(@ObjectConvertAnno Pagination pagination){
		String merchantCode = HttpParameterParser.getString("merchantCode");
		Merchant merchant  = null;
		if(!StringUtils.isNullOrEmpty(merchantCode))
			merchant= merchantManager.getByCode(merchantCode);
		Map<String,Object> params = constParams(merchant);
		List<Member> list = memberManager.find(params, pagination);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("list", list);
		map.put("success", true);
		map.put("desc", "查询成功");
		return new ModelAndView(new JsonView("members",map));
	}
	private Map<String,Object> constParams(Merchant merchant) {
		int source  = HttpParameterParser.getIntValue("source");
		String name = HttpParameterParser.getString("name");
		String mobile = HttpParameterParser.getString("mobile");
		int level = HttpParameterParser.getIntValue("level");
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
		Date startLastConsumeDate = HttpParameterParser.getDate("startLastConsumeDate");
		Date endLastConsumeDate = HttpParameterParser.getDate("endLastConsumeDate");
		Map<String,Object> params = new HashMap<String,Object>();
		if(source != 0){
			params.put("source", MemberSource.get(source));
		}
		if(merchant != null && merchant.getMemberGroup() != null)
			params.put("memberGroup", merchant.getMemberGroup());
		if(!StringUtils.isNullOrEmpty(name)){
			params.put("name", name);
			params.put("myname", name);
		}
		if(!StringUtils.isNullOrEmpty(mobile))
			params.put("mobile", mobile);		
		if(level != 0)
			params.put("level", Level.get(level));
		if(startCreateDate != null)
			params.put("startCreateDate", DateUtils.getEarlyInTheDay(startCreateDate));
		if(endCreateDate != null)
			params.put("endCreateDate", DateUtils.getLateInTheDay(endCreateDate));
		if(startLastConsumeDate != null)
			params.put("startLastConsumeDate", DateUtils.getEarlyInTheDay(startLastConsumeDate));
		if(endLastConsumeDate != null)
			params.put("endLastConsumeDate", DateUtils.getLateInTheDay(endLastConsumeDate));
		 
		params.put("orderBy", "decode(lastConsumeDate,null,m.user.createDate) desc,lastConsumeDate desc");
		
		return params;
	}
	
	@RequestMapping("/info/{id}/logout.htm")
	public ModelAndView lougout(@PathVariable Long id,@ObjectConvertAnno Operator operator){
		Member member = memberManager.get(id);
		Map<String,Object> model = new HashMap<String, Object>();
		try {
			memberManager.logOut(member, operator);
			model.put("success", true);
			model.put("msg", "注销成功");
			model.put("status", member.getStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	@RequestMapping(value="info/freeze.htm",method=RequestMethod.PUT)
	@OperatorLogAnno(OperatorType.TYPE_FREEZE_MEMBER)
	public ModelAndView freeze(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		Member member = memberManager.get(id);
		Map<String,Object> model = new HashMap<String, Object>();
		try {
			memberManager.freeze(member, operator);
			model.put("success", true);
			model.put("msg", "冻结成功");
			model.put("status", member.getStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	@RequestMapping(value="info/unfreeze.htm",method=RequestMethod.PUT)
	@OperatorLogAnno(OperatorType.TYPE_UNFREEZE_MEMBER)
	public ModelAndView unFreeze(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		Member member = memberManager.get(id);
		Map<String,Object> model = new HashMap<String, Object>();
		try {
			memberManager.unFreeze(member, operator);
			model.put("success", true);
			model.put("msg", "解冻成功");
			model.put("status", member.getStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	@RequestMapping(value="info/{id}/modify.htm",method=RequestMethod.GET)
	public ModelAndView modify(@PathVariable Long id,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/member/modify.jsp");
		Member member = memberManager.get(id);
		model.addObject("member",member);
		return model;
	}
	
	@RequestMapping(value="info/{id}/modify_commit.htm",method=RequestMethod.PUT)
	@OperatorLogAnno(OperatorType.TYPE_UPDATE_MEMBER)
	public ModelAndView modifyCommit(@PathVariable Long id,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView(new RedirectView("/manager/member/info/"+id+"/view.htm"));
		User user = memberManager.get(id).getUser();
		String name = HttpParameterParser.getString("name");
		int gender = HttpParameterParser.getIntValue("gender");
		Date birthday = HttpParameterParser.getDate("birthday");
		String address = HttpParameterParser.getString("address");
		String email = HttpParameterParser.getString("email");
		user.setName(name);
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setEmail(email);
		user.setGender(gender);
		userManager.update(user);
		return model;
	}
	
	@RequestMapping(value="info/import_member.htm",method=RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_IMPORT_MEMBER)
	public ModelAndView importMember(@ObjectConvertAnno Operator operator,HttpServletRequest request) throws IOException {
		InputStream ins = FileUploadUtils.getInputStream(request, "importFile");
		if(ins == null){
			return showMessage("1111","未找到文件",false);
		}
		List<TempUser> tempUsers = null;
		try {
			tempUsers = ExcelUtils.readExcel(ins);
		} catch (BusinessException e) {
			e.printStackTrace();
			return showMessage("1111",e.getMessage(),false);
		} finally {
			if(ins != null)
				ins.close();
		}
		String message = "";
		Merchant merchant = null;
		String merchantCode = request.getParameter("merchantCode");
		if(!StringUtils.isNullOrEmpty(merchantCode)){
			merchant = merchantManager.getByCode(merchantCode);
		}
		if(merchant == null){
			return showMessage("1111","未找到对应的商户",false);
		}
		importMember(tempUsers, merchant, operator);
		if(StringUtils.isNullOrEmpty(message)) {
			message = "上传成功";
			return showMessage("0000",message,true);
		} else {
			return showMessage("1111",message,false);
		}
	}
	

	private ModelAndView showMessage(String code,String message,boolean success) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("desc", message);
		map.put("success", success);
		return new ModelAndView(new JsonView("member_info",map));
	}
	
	private void importMember(List<TempUser> tempUsers,Merchant merchant,Operator operator) {
		for (TempUser tempUser : tempUsers) {
			if(StringUtils.isNullOrEmpty(tempUser.getMobile())){
				TempMember tempMember = constTempMember(merchant, tempUser);
				try {
					tempMemberManager.create(tempMember);
				} catch (BusinessException e) {
				}
			} else{
				User user = constUser(tempUser);
				try {
					User memberUser = userManager.getByMobile(user.getMobile());
					if (memberUser != null) {
						if (memberManager.getBy(memberUser, merchant.getMemberGroup()) != null) {
							continue;
						}
					}
					memberManager.regist(operator, user, merchant,tempUser.getPoint(), tempUser.getLevel(),MemberSource.SOURCE_PLATFORM); //添加几分
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private TempMember constTempMember(Merchant merchant, TempUser tempUser) {
		TempMember tempMember = new TempMember();
		tempMember.setAddress(tempUser.getAddress());
		tempMember.setBind(false);
		tempMember.setBirthday(tempUser.getBirthday());
		tempMember.setCardNo(tempUser.getCardNo());
		tempMember.setCardNumber(tempUser.getCardNumber());
		tempMember.setCardType(tempUser.getCardType());
		tempMember.setEmail(tempUser.getEmail());
		tempMember.setGender(tempUser.getGender());
		tempMember.setLevel(tempUser.getLevel()==null?Level.GENERAL:tempUser.getLevel());
		tempMember.setMerchant(merchant);
		tempMember.setMsn(tempUser.getMsn());
		tempMember.setName(tempUser.getName());
		tempMember.setPetName(tempUser.getPetName());
		tempMember.setPoint(tempUser.getPoint());
		tempMember.setPost(tempUser.getPost());
		tempMember.setQq(tempUser.getQq());
		tempMember.setCreateDate(new Date());
		return tempMember;
	}
	
	private User constUser(TempUser tempUser) {
		User user = new User();
		user.setAddress(tempUser.getAddress());
		user.setBirthday(tempUser.getBirthday());
		user.setCardNumber(tempUser.getCardNumber());
		user.setCardType(tempUser.getCardType());
		user.setEmail(tempUser.getEmail());
		user.setGender(tempUser.getGender());
		user.setMobile(tempUser.getMobile());
		user.setMsn(tempUser.getMsn());
		user.setName(tempUser.getName());
		user.setPetName(tempUser.getPetName());
		user.setPost(tempUser.getPost());
		user.setQq(tempUser.getQq());
		user.setValid(true);
		user.setSource(UserSource.SOURCE_PLATFORM);
		user.setCreateDate(new Date());
		return user;
	}
	
	@Autowired
	private MemberManager memberManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private TempMemberManager tempMemberManager;
	
	@Autowired
	private MerchantManager merchantManager;
}

