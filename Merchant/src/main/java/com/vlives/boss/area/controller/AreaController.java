/**
 * @(#)AreaController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.area.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.area.manager.AreaManager;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.web.ResponseUtils;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-3-25
 */
@Controller
public class AreaController {
	@Autowired
	private AreaManager areaManager;
	@RequestMapping(value="/manager/platform/area/area_json.htm",method=RequestMethod.GET)
	public void getArea(HttpServletRequest request, HttpServletResponse response) {
		long id = HttpParameterParser.getLongValue("id", 0);
		Area area = areaManager.get(id);
		ResponseUtils.writeJSON(request,response, area, "area");
	}
}