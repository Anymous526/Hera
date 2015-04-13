package com.vlives.boss.report.controller;



import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.report.domain.CouponConsumReport;
import com.vlives.boss.report.manager.CouponConsumReportManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.XlsView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

 

/**
 *电子券报表 
 * @author sun_xun
 *
 */

@Controller
@RequestMapping("/manager/report")
public class CouponReportController {
	@Autowired
	private CouponConsumReportManager couponConsumReportManager;
	
	/**
	 * 查询报表 
	 * @return
	 */
	@RequestMapping("coupon/list.htm")
	public ModelAndView findToday(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		 
		ModelAndView model = new ModelAndView("/manager/report/coupon/list.jsp");
		Date startDate =getStartDate();
		Date endDate = getEndDate();
		List<CouponConsumReport> list = couponConsumReportManager.findReoprt(operator.getMerchant(), startDate, endDate, pagination);
		model.addObject("startDate",startDate);
		model.addObject("endDate",endDate);
		model.addObject("list",list);
		model.addObject("pagination",pagination);
		return model;
	}
	
	/**
	 * 导出报表 
	 * @param operator
	 * @param pagination
	 * @return
	 */
	@RequestMapping("coupon/exp_excel.htm")
	public ModelAndView exp_excel(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		List<CouponConsumReport> list = couponConsumReportManager.findReoprt(operator.getMerchant(),getStartDate(),getEndDate());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("conponReport.xls", map));
	}
	
	
	private Date getStartDate() {
		Date startDate = HttpParameterParser.getSqlDate("startCreateDate");
		return startDate == null?new  Date(DateUtils.add(new java.util.Date(), TimeUnit.DAYS, -1).getTime()):startDate;
	}
	
	private Date getEndDate() {
		Date endDate = HttpParameterParser.getSqlDate("endCreateDate");
		return endDate == null?new  Date(DateUtils.add(new java.util.Date(), TimeUnit.DAYS, -1).getTime()):endDate;
	}
 
}
