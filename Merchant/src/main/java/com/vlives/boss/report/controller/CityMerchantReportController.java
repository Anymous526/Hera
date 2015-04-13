/**
 * @(#)CityMerchantReportController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
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

import com.vlives.boss.report.domain.CityMerchantReport;
import com.vlives.boss.report.manager.CityMerchantReportManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.vaildator.ParamValidator;
import com.vlives.core.support.spring.vaildator.ParamValidators;
import com.vlives.core.support.spring.vaildator.ValidatorType;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-18
 */
@Controller
public class CityMerchantReportController {
	@Autowired
	private CityMerchantReportManager cityMerchantReportManager;
	
	/**
	 * 商户信息导出/查询
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value="/manager/platform/report/cityMerchantReport.htm",method=RequestMethod.GET)
	public ModelAndView reportFinder(@ObjectConvertAnno Pagination pagination) {
		Date startDate = HttpParameterParser.getDate("startDate");
		Date endDate = HttpParameterParser.getDate("endDate");
		try {
			List<CityMerchantReport> cityMerchantReports = cityMerchantReportManager.findReoprt(startDate, endDate);
			Map<String,Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("cityMerchantReports", cityMerchantReports);
			return new ModelAndView(new JsonView("cityMerchantReports", result));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false, "查询报表时报"));
		}
	}
	
	
}
