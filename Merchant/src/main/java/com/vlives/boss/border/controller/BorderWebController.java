package com.vlives.boss.border.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.border.domain.Border;
import com.vlives.boss.border.manager.BorderManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;

@Controller
public class BorderWebController {

	@Autowired
	private BorderManager borderManager;
		
	/**
	 * 会生活系统接口-find
	 * @return
	 * @throws BussinessException
	 */
	@RequestMapping(value = "/border.htm", method = RequestMethod.GET)
	public ModelAndView findWeb() throws BusinessException {
		int count = HttpParameterParser.getIntValue("count");
		List<Border> borders = borderManager.find(count);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("borders", borders);
		return new ModelAndView(new JsonView(result));
	}

}
