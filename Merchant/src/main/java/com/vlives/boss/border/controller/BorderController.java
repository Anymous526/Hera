package com.vlives.boss.border.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.border.domain.Border;
import com.vlives.boss.border.domain.BorderType;
import com.vlives.boss.border.manager.BorderManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

@Controller
@RequestMapping("/manager/platform/border")
public class BorderController {

	@Autowired
	private BorderManager borderManager;
	
	/***
	 * 外围系统接口-create
	 * @param name 公告
	 * @throws BussinessException 
	 */
	
	@RequestMapping(value = "/create.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_CREATE_BORDER)
	public ModelAndView create(@ObjectConvertAnno Operator operator) throws BusinessException {
		String name = HttpParameterParser.getString("name");
		String type = HttpParameterParser.getString("type");
		String content = HttpParameterParser.getString("content");
		Border border = new Border();
		border.setName(name);
		border.setType(BorderType.get(Integer.parseInt(type)));
		border.setContent(content);
		try{
			borderManager.create(border);
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
		return new ModelAndView(new JsonView(true,"设置公告成功"));
	}
	
	/**
	 * 外围系统接口-find
	 * @param pagination
	 * @return
	 * @throws BussinessException
	 */
	@RequestMapping(value = "/find.htm", method = RequestMethod.GET)
	public ModelAndView find(@ObjectConvertAnno Pagination pagination) throws BusinessException {
		Long id = HttpParameterParser.getLong("id");
		String name = HttpParameterParser.getString("name");
		int[] type = HttpParameterParser.getIntArray("type");
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(id!=null)
			map.put("id", id);
		if(!StringUtils.isNullOrEmpty(name))
			map.put("name","%"+name+"%");

		if(type != null){
			List<BorderType> types = new ArrayList<BorderType>();
			for (int typ : type) {
				types.add(BorderType.get(typ));
			}
			map.put("type", types);
		}
		List<Border> borders = borderManager.find(map,pagination);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("borders", borders);
		result.put("pagination", pagination);
		
		return new ModelAndView(new JsonView(result));
	}
	
	/**
	 * 外围系统接口-delete
	 * @param pagination
	 * @return
	 * @throws BussinessException
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.DELETE)
	@OperatorLogAnno(OperatorType.TYPE_DELETE_BORDER)
	public ModelAndView delete(@ObjectConvertAnno Operator operator){
		String[] id = HttpParameterParser.getStringArray("id");

		Border border = null;
		for(String lid : id){
			border = borderManager.getId(Long.parseLong(lid));
			if(border != null){
				borderManager.delete(border);
			}
		}
		return new ModelAndView(new JsonView(true,"删除公告成功"));
	}
	
	/**
	 * 云掌柜 - 查看公告明细
	 * @return
	 */
	@RequestMapping(value = "/borderInfo.htm", method = RequestMethod.GET)
	public ModelAndView borderInfo(){
		Long id = HttpParameterParser.getLong("id");
		Border border = borderManager.getId(id);
		return new ModelAndView("/manager/border/border.jsp","border",border);
	}
}
