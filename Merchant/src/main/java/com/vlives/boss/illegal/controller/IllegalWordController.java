package com.vlives.boss.illegal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.illegal.domain.IllegalWord;
import com.vlives.boss.illegal.manager.IllegalWordManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

@Controller
@RequestMapping("/manager/illegal")
public class IllegalWordController {

	@Autowired
	private IllegalWordManager illegalWordManager;
	
	
	/***
	 * 外围系统接口-create
	 * @param content 非法字符
	 * @throws BussinessException 
	 * @throws DocumentException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	
	@RequestMapping(value = "/create.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_CREATE_ILLEGALWORD)
	public ModelAndView create(@ObjectConvertAnno Operator operator) throws BusinessException, NumberFormatException, IOException, DocumentException {
		String content = HttpParameterParser.getString("content");
		IllegalWord illegalWord = new IllegalWord();
		illegalWord.setContent(content);
		try{
			illegalWordManager.create(illegalWord);
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
		return new ModelAndView(new JsonView(true,"设置违禁字成功"));
	}
	
	/**
	 * 外围系统接口-find
	 * @param pagination
	 * @return
	 * @throws BussinessException
	 */
	@RequestMapping(value = "/find.htm", method = RequestMethod.GET)
	public ModelAndView find(@ObjectConvertAnno Pagination pagination) throws BusinessException {
		String content = HttpParameterParser.getString("content");
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(content!=null)
			map.put("content","%"+content+"%");
		List<IllegalWord> illegalWords = illegalWordManager.find(map,pagination);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("illegalWords", illegalWords);
		result.put("pagination", pagination);
		
		return new ModelAndView(new JsonView("illegalWords",result));
	}
	
	/**
	 * 外围系统接口-delete
	 * @param pagination
	 * @return
	 * @throws DocumentException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * @throws BussinessException
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.DELETE)
	@OperatorLogAnno(OperatorType.TYPE_DELETE_ILLEGALWORD)
	public ModelAndView delete(@ObjectConvertAnno Operator operator) throws NumberFormatException, IOException, DocumentException{
		String[] id = HttpParameterParser.getStringArray("id");

		IllegalWord illegalWord = null;
		for(String lid : id){
			illegalWord = illegalWordManager.getId(Long.parseLong(lid));
			if(illegalWord != null){
				illegalWordManager.delete(illegalWord);
			}
		}
		return new ModelAndView(new JsonView(true,"删除违禁字成功"));
	}

	@RequestMapping(value = "/findAll.htm", method = RequestMethod.GET)
	public ModelAndView findAll() throws BusinessException {
		 
		List<IllegalWord> illegalWords = illegalWordManager.findAll();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("illegalWords", illegalWords);
		return new ModelAndView(new JsonView("illegalWords",result));
	}	

}
