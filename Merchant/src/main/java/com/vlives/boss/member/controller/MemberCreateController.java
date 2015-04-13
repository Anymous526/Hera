/**
 * @(#)MemberCreateController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberSource;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.user.domain.CardType;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserSource;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.support.spring.view.XlsView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.ExcelParser;
import com.vlives.util.ExcelParser.ColumnType;
import com.vlives.util.ExcelParser.Excel;
import com.vlives.util.FileUploadUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-9
 */
@Controller
@RequestMapping("/manager/member")
public class MemberCreateController {
	@Autowired
	private MemberManager memberManager;
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value="/info/add.htm",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView index(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		String msg = (String) request.getAttribute("msg");
		map.put("msg", msg);
		ModelAndView model = new ModelAndView("/manager/member/add.jsp",map);
		return model;
	}
	/**
	 * 商户创建会员
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/info/create.htm",method=RequestMethod.POST)
	public ModelAndView addMember(@ObjectConvertAnno Operator operator) throws IOException {
		int level = HttpParameterParser.getIntValue("level",1);
		int point = HttpParameterParser.getIntValue("point");
		User user = fromUser();
		 try {
			Member member = memberManager.regist(operator, user, operator.getMerchant(), point, Level.get(level),MemberSource.SOURCE_CLOUDBOSS);
			ModelAndView model = new ModelAndView(new RedirectView("/manager/member/info/"+member.getId()+"/view.htm"));
			return model;
		} catch (BusinessException e) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", "创建会员失败  "+e.getMessage());
			return new ModelAndView("/manager/member/info/add.htm",map);
		}
	}
	
	private User fromUser() {
		String mobile = HttpParameterParser.getString("mobile");
		User user = userManager.getByMobile(mobile);
		if(user==null) {
			user = new User();
			user.setMobile(mobile);
			user.setPassword(DigestUtils.shaHex(User.USER_DEFAULT_PASSWORD));
			user.setValid(true);
			user.setCreateDate(new Date());
			user.setSource(UserSource.SOURCE_CLOUDBOSS);
		}
		String name = HttpParameterParser.getString("name");
		if(user.getName()==null) user.setName(name);
		int gender = HttpParameterParser.getIntValue("gender", 0);
		if(user.getGender()==0)user.setGender(gender);
		Date birthday = HttpParameterParser.getDate("birthday");
		if(user.getBirthday()==null) user.setBirthday(birthday);
		String cardNumber = HttpParameterParser.getString("cardNumber");
		if(user.getCardNumber()==null) {
			user.setCardType(CardType.CARDTYPE_IDENTITY);
			user.setCardNumber(cardNumber);
		}
		String email = HttpParameterParser.getString("email");
		if(user.getEmail() == null) {
			user.setEmail(email);
		}
		String address = HttpParameterParser.getString("address");
		if(user.getAddress() == null) {
			user.setAddress(address);
		}
		userManager.saveOrUpdate(user);
		return user;
	}
	
	
	@RequestMapping(value="info/merchant_import_member.htm",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView importMember(@ObjectConvertAnno Operator operator,HttpServletRequest request) throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("msg", "导入会员成功");
		try {
			InputStream ins = FileUploadUtils.getInputStream(request, "importFile");
			ExcelParser parser = ExcelParser.newInstance();
			Map<Integer, ColumnType> columFormatConfig = new HashMap<Integer, ExcelParser.ColumnType>();
			Excel excel = parser.parse(ins, columFormatConfig, true);
			
			validatexcel(excel.getBody());
			importMember(operator, excel.getBody());
			
			map.put("success", true);
			map.put("msg", "导入会员成功");
			return new ModelAndView("/manager/member/importmembermsg.jsp",map);
		} catch (BusinessException e) {
			map.put("success", false);
			map.put("msg", "导入会员失败  "+e.getMessage());
			return new ModelAndView("/manager/member/importmembermsg.jsp",map);
		}
	}
	
	@RequestMapping(value="info/template/download.htm",method=RequestMethod.GET)
	public ModelAndView downloadTemplate(){
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("member_import.xls", map, "member_import"));
	}

	
	private void importMember(Operator operator,List<List<Serializable>> body) throws BusinessException {
		for(List<Serializable> columns: body) {
			if(columns.size() == 0) continue;
			User user = createUserByColumn(columns);
			if(user.getId() != null && memberManager.getBy(user, operator.getMerchant().getMemberGroup())!=null) {
				continue;
			}
			userManager.saveOrUpdate(user);
			try {
				Level level = null;
				int point=0;
				if(columns.size()==8) {
					level = Level.get((String)columns.get(7));
				}
				if(columns.size()==9){
					String slevel = (String)columns.get(7);
					if(!StringUtils.isNullOrEmpty(slevel)) {
						level = Level.get(slevel);
					}
					String spoint = (String)columns.get(8);
					point = Integer.valueOf(spoint.trim());
				}
				memberManager.regist(operator, user, operator.getMerchant(),point, level,MemberSource.SOURCE_CLOUDBOSS);
			} catch (BusinessException e) {
				
			}
		}
	}
	
	 private void validatexcel(List<List<Serializable>> body) throws BusinessException {
		 if(body == null||body.size()==0) throw new BusinessException("无任何会员数据");
		 for(int i=0;i<body.size();i++) {
				List<Serializable> columns = body.get(i);
				String msg = validaterow(columns);
				if(msg != null) {
					throw new BusinessException("第"+(i+2)+"行 "+msg);
				}
			}
	 }
	
	 private String validaterow(List<Serializable> columns) {
		 for(int i = 0;i<columns.size();i++) {
				String column = (String) columns.get(i);
				if(i ==0) {
					if(StringUtils.isNullOrEmpty(column)) {
						return "手机号码为必填";
					}
					boolean isMobile = Pattern.matches("^1\\d{10}", column);
					if(!isMobile){
						return "手机号码格式不正确";
					}
				}
				if(i == 1&&!StringUtils.isNullOrEmpty(column)) {
					byte[] name;
					try {
						name = column.getBytes("gbk");
						if(name.length>20) {
							return "名称长度超过20个字符";
						}
					} catch (UnsupportedEncodingException e) {
					}
				}
				if(i==2) {
					
				}
				if(i ==3&&!StringUtils.isNullOrEmpty(column)) {
					try {
						DateUtils.parserDate(column, "yyyy/MM/dd");
					}
					catch (Exception e) {
						return "生日格式必须为 年-月-日";
					}
					 
				}
				if(i ==4&&!StringUtils.isNullOrEmpty(column)) {
					boolean isIdentity = Pattern.matches("^\\d{15}|^\\d{18}$|^\\d{17}\\w$", column);
					if(!isIdentity) {
						return "身份证号码为15数字或18位数字和字符组成";
					}
				}
				if(i==5&&!StringUtils.isNullOrEmpty(column)) {
					if(column.getBytes().length<=40){
						boolean isEmail = Pattern.matches("^\\w{3,20}(^\\.)*@\\w{2,10}\\.\\w{2,10}$", column);
						if(!isEmail) {
							return "邮件格式不正确";
						}
					} else {
						return "邮件不能超过40个字符";
					}
					
				}
				if(i==6&&!StringUtils.isNullOrEmpty(column)) {
					 
					try {
						if(column.getBytes("gbk").length>30) {
							return "联系地址长度超过30个字符";
						}
					} catch (UnsupportedEncodingException e) {
						
					}
				}
				if(i==7&&!StringUtils.isNullOrEmpty(column)) {
					try {
						Level.get(column);
					} catch (Exception e) {
						return "会员等级不存在";
					}
				}
				if(i==8&&!StringUtils.isNullOrEmpty(column)) {
					int point = 0;
					try {
						point = Integer.parseInt(column.trim());
					} catch (NumberFormatException e) {
						return "会员积分非数字";
					}
					if(point<0) {
						return "会员积分不能小于0";
					}
				}
			}
		return null;
	 }
	
	private User createUserByColumn(List<Serializable> columns) {
		User user = null;
		for(int i = 0;i<columns.size();i++) {
			String column = (String) columns.get(i);
			column = column.trim();
			if(i ==0) {
				user = userManager.getByMobile(column);
				if(user == null) {
					user = new User();
					user.setMobile(column);
					user.setPassword(User.USER_DEFAULT_PASSWORD);
					user.setCreateDate(new Date());
					user.setSource(UserSource.SOURCE_CLOUDBOSS);
					user.setValid(true);
				}
			}
			if(i == 1) {
				user.setName(column);
			}
			if(i==2) {
				if(column!=null&&column.trim().equals("男")) {
					user.setGender(1);
				}
				if(column!=null&&column.trim().equals("女")) {
					user.setGender(2);
				}
				if(column==null) {
					user.setGender(0);
				}
			}
			if(i ==3) {
				 try {
					 Date birthday = DateUtils.parserDate(column, "yyyy/MM/dd");
					 user.setBirthday(birthday);
				 }
				 catch (Exception e) {
				}
			}
			if(i ==4) {
				user.setCardType(CardType.CARDTYPE_IDENTITY);
				user.setCardNumber(column);
			}
			if(i==5) {
				user.setEmail(column);
			}
			if(i==6) {
				user.setAddress(column);
			}
		}
		return user;
	}
}
