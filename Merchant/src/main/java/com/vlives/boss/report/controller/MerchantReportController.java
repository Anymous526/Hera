/**
 * 
 */
package com.vlives.boss.report.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.dto.MemberReport;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.report.domain.MerchantReport;
import com.vlives.boss.report.manager.MerchantReportManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * 商户信息导出----运营管理平台
 * @author unicorn
 * @version 1.0,2011-8-2
 */
@Controller
public class MerchantReportController {
	
	@Autowired
	private MerchantReportManager merchantReportManager;
	
	@Autowired
	private MemberManager memberManager; 
	/**
	 * 商户信息导出/查询
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value="/manager/platform/merchant/export.htm",method=RequestMethod.GET)
	public ModelAndView merchantReportFind(@ObjectConvertAnno Pagination pagination) {
		try {
			Date startDate = HttpParameterParser.getDate("startDate");
			Date endDate = HttpParameterParser.getDate("endDate");
			Map<String,Object> map = new HashMap<String, Object>();
			if(startDate !=null) {
				map.put("startDate", DateUtils.getEarlyInTheDay(startDate));
			}
			if(endDate !=null) {
				map.put("endDate", DateUtils.getLateInTheDay(endDate));
			}
			List<MerchantReport> list = merchantReportManager.findReoprt(map,pagination);
			Map<String,Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("merchantReprot", list);
			result.put("pagination", pagination);
			return new ModelAndView(new JsonView("merchantReprot", result));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false, "查询商户列表失败"));
		}
	}
	/***
	 * 导出商户会员信息
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value="/manager/platform/member/export.htm",method=RequestMethod.GET)
	public ModelAndView memberReoprt(@ObjectConvertAnno Pagination pagination) {
		Date startDate = HttpParameterParser.getDate("startDate");
		Date endDate = HttpParameterParser.getDate("endDate");
		Map<String,Object> map = new HashMap<String, Object>();
		if(startDate !=null) {
			map.put("createStartDate", DateUtils.getEarlyInTheDay(startDate));
		}
		if(endDate !=null) {
			map.put("createEndDate", DateUtils.getLateInTheDay(endDate));
		}
		List<MemberReport> list = memberManager.findTotalMember(map,pagination);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("memberReprot", list);
		result.put("pagination", pagination);
		return new ModelAndView(new JsonView("memberReprot", result));
	}
}
