/**
 * @(#)OperatorController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.boss.operator.domain.OperatorRole;
import com.vlives.boss.operator.manager.OperatorManager;
import com.vlives.boss.security.domain.Role;
import com.vlives.boss.security.domain.Role.RoleGroup;
import com.vlives.boss.security.manager.RoleManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.AcceptHashMap;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-7
 */
@Controller
public class OperatorController {
	@Autowired
	private OperatorManager operatorManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private MerchantManager merchantManager;
	
	
	
	/**
	 * 当前管理员查看其他管理员
	 * @author jianguo.xu
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/{id}/",method=RequestMethod.GET)
	public ModelAndView view(@PathVariable Long id) {
		Operator operator = operatorManager.get(id);
		return new ModelAndView("/manager/security/operator/view.jsp", "operator", operator);
	}
	/**
	 * 修改显示地址
	 * @author jianguo.xu
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/{id}/update.htm")
	public ModelAndView updateView(@PathVariable Long id) {
		Map<RoleGroup,List<Role>> roles = roleManager.findRoleGroups();
		//TODO 零时处理
		//roles.remove(RoleGroup.SECURITY_MANAGER);
		
		Operator operator = operatorManager.get(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("operator", operator);
		model.put("roles", roles);
		Set<OperatorRole> operatorRoles = operator.getOperatorRoles();
		String roleStr = "";
		for(OperatorRole operatorRole:operatorRoles) {
			roleStr+=operatorRole.getRole().getId()+",";
		}
		model.put("roleStr", roleStr);
		return new ModelAndView("/manager/security/operator/update.jsp", model);
	}
	
	/**
	 * 授权管理员修改其他管理员的密码和用户名
	 * @author jianguo.xu
	 * @param operator
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/{id}/",method=RequestMethod.PUT)
	public ModelAndView modifyPassword(@PathVariable Long id) {
		Operator operator = operatorManager.get(id);
		try {
			modifyPass(operator);
		} catch (BusinessException e) {
			return new ModelAndView("/manager/security/operator/"+id+"/update.htm","errorMsg",e.getMessage());
		}
		return new ModelAndView(new RedirectView("/manager/security/operator/"+id+"/"));
	}
	/**
	 * 查看自己的帐号
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/manager/security/self/",method=RequestMethod.GET)
	public ModelAndView viewSelf(@ObjectConvertAnno Operator operator) {
		return new ModelAndView("/manager/security/self/view.jsp", "operator", operator);
	}
	/**
	 * 修改自己帐号和密码的页面
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/manager/security/self/update.htm")
	public ModelAndView updateViewSelf(@ObjectConvertAnno Operator operator) {
		return new ModelAndView("/manager/security/self/update.jsp", "operator", operator);
	}
	
	/**
	 * 商户修改自己的密码和用户名
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/manager/security/self/",method=RequestMethod.PUT)
	@OperatorLogAnno(OperatorType.TYPE_UPDATE_PASSWORD)
	public ModelAndView modifyPassword(@ObjectConvertAnno Operator operator) {
		try {
			modifyPass(operator);
		} catch (BusinessException e) {
			return new ModelAndView("/manager/security/self/update.htm","errorMsg",e.getMessage());
		}
		return new ModelAndView(new RedirectView("/manager/security/self/"));
	}
	
	private void modifyPass(Operator operator) throws BusinessException {
		String name = HttpParameterParser.getString("name");
		String oldPassword = HttpParameterParser.getString("oldPassword");
		String newPassword = HttpParameterParser.getString("newPassword");
		operator.setName(name);
		operatorManager.updatePassword(operator, oldPassword, newPassword);
		String roleStr = HttpParameterParser.getString("roleStr");
		if(!StringUtils.isNullOrEmpty(roleStr)) {
			String[] roleStrs = roleStr.split(",");
			List<Role> roles = new ArrayList<Role>();
			for(String str : roleStrs) {
				Long roleId = new Long(str);
				roles.add(roleManager.get(roleId));
			}
			if(roles.size()>0)
				operatorManager.bindRole(operator, roles);
		}
	}
	
	/**
	 * 得到操作员的操作菜单
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping("/manager/include/left.htm")
	public ModelAndView getOperatorMenu(@ObjectConvertAnno Operator operator) {
		return new ModelAndView("/manager/include/left.jsp","operator",operator);
	}
	
	
	/**
	 * 动态查询商户管理员
	 * @author jianguo.xu
	 * @param operator
	 * @param pagination
	 * @return
	 */
	 
	@RequestMapping("/manager/security/operator/list.htm")
	public ModelAndView list(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination) {
		List<Operator> operators = operatorManager.find(getDynamicParam(operator), pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("operators", operators);
		model.put("pagination", pagination);
		return new ModelAndView("/manager/security/operator/list.jsp",model);
	}
	private Map<String,Object> getDynamicParam(Operator operator) {
		AcceptHashMap<String,Object> params = AcceptHashMap.newInstance();
		String mobile = HttpParameterParser.getString("mobile");
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
		params.accept("merchant", operator.getMerchant())
		.acceptIf("mobile", mobile, !StringUtils.isNullOrEmpty(mobile));
		if(startCreateDate!=null) {
			startCreateDate = DateUtils.getEarlyInTheDay(startCreateDate);
			params.put("startCreateDate", startCreateDate);
		}
		if(endCreateDate!=null) {
			endCreateDate =  DateUtils.getLateInTheDay(endCreateDate);
			params.put("endCreateDate", endCreateDate);
		}
		 
		return params;
	}
	
	/**
	 * 冻结管理员
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/freeze.htm",method=RequestMethod.PUT)
	@OperatorLogAnno(OperatorType.TYPE_FREEZE_MEMBER)
	public ModelAndView freeze(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		Operator manager = operatorManager.get(id);
		Map<String,Object> model = new HashMap<String, Object>();
		try {
			operatorManager.freezeOperator(manager, operator);
			model.put("success", true);
			model.put("msg", "冻结成功");
			model.put("status", manager.getStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	
	/**
	 * 解冻管理员
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/unfreeze.htm",method=RequestMethod.PUT)
	@OperatorLogAnno(OperatorType.TYPE_UNFREEZE_MEMBER)
	public ModelAndView unFreeze(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		Operator manager = operatorManager.get(id);
		Map<String,Object> model = new HashMap<String, Object>();
		try {
			operatorManager.unfreezeOperator(manager, operator);
			model.put("success", true);
			model.put("msg", "解冻成功");
			model.put("status", manager.getStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	/**
	 * 添加管理员查看页面
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/add.htm")
	public ModelAndView createView(@ObjectConvertAnno Operator operator) {
		Map<RoleGroup,List<Role>> roles = roleManager.findRoleGroups();
		//TODO 零时处理
		roles.remove(RoleGroup.SECURITY_MANAGER);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("operator", operator);
		model.put("roles", roles);
		return new ModelAndView("/manager/security/operator/add.jsp",model);
		 
	}
	/**
	 * 添加管理员查看页面
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/addcommit.htm",method=RequestMethod.POST)
	public ModelAndView createCommit(@ObjectConvertAnno Operator creator) {
		String mobile = HttpParameterParser.getString("mobile");
		String name = HttpParameterParser.getString("name");
		String password = HttpParameterParser.getString("password");
		String roleStr = HttpParameterParser.getString("roleStr");
		Operator operator = new Operator();
		operator.setMobile(mobile);
		operator.setPassword(password);
		operator.setName(name);
		Long merchantId = HttpParameterParser.getLong("merchantId");
		Merchant merchant = merchantManager.get(merchantId);
		String[] roleStrs = roleStr.split(",");
		List<Role> roles = new ArrayList<Role>();
		for(String str : roleStrs) {
			Long roleId = new Long(str);
			roles.add(roleManager.get(roleId));
		}
		try {
			operatorManager.create(operator, merchant, roles, creator);
		} catch (BusinessException e) {
			return new ModelAndView("/manager/security/operator/add.htm","msg",e.getMessage());
		}
		return new ModelAndView(new RedirectView("/manager/security/operator/"+operator.getId()+"/"));
	}
	
	/**
	 * 验证操作员手机号是否存在
	 * @return
	 */
	@RequestMapping(value="/manager/security/operator/exist.htm",method=RequestMethod.GET)
	public ModelAndView isExistMobile(){
		String mobile = HttpParameterParser.getString("mobile");
		boolean exist = false;
		if(StringUtils.isNullOrEmpty(mobile))
			exist = false;
		exist=operatorManager.isExistMobile(mobile);
		 
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("success", exist);
		return new ModelAndView(new JsonView(model));
	}
}
