package com.vlives.boss.feedback.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.feedback.domain.IdeaFeedback;
import com.vlives.boss.feedback.manager.IdeaFeedbackManager;
import com.vlives.boss.user.domain.User;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.vaildator.ParamValidator;
import com.vlives.core.support.spring.vaildator.ParamValidators;
import com.vlives.core.support.spring.vaildator.ValidatorType;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-6-28 上午05:54:43 类说明
 */

@Controller
public class IdeaFeedbackController {

	@Autowired
	private IdeaFeedbackManager ideaFeedbackManager;

	/**
	 * 将意见反馈存入数据库中
	 * 
	 * @param operator
	 * @return
	 */
	@RequestMapping("/ideafeedback/add.htm")
	public ModelAndView addIdeaFeedback(@ObjectConvertAnno(required=false) User user) {
		String feed_content = HttpParameterParser.getString("template");// 反馈意见内容
		IdeaFeedback objFeedback = new IdeaFeedback();
		objFeedback.setFeedContent(feed_content);
		objFeedback.setUser(user);
		objFeedback.setCreateDate(new java.util.Date());
		ideaFeedbackManager.create(objFeedback);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "反馈意见已经递交成功！");
		return new ModelAndView(new JsonView(model));
	}

	@ParamValidators({
		@ParamValidator(param="cur_date",paramName="大于投递时间",vaildatorTypes=ValidatorType.DATE)
	})
	@RequestMapping(value="/ideafeedback/info.htm",method=RequestMethod.GET)
	public ModelAndView getIdeaFeedback(@ObjectConvertAnno Pagination pagination){
		String user_id = HttpParameterParser.getString("user_id");
		Date cur_date = HttpParameterParser.getDate("cur_date"); 
		
		Map<String,Object> params = new HashMap<String,Object>();
		if(cur_date!=null){
			java.util.Date _cur_date = DateUtils.getLateInTheDay(cur_date);
			params.put("cur_date", _cur_date);
		}
		if(!StringUtils.isNullOrEmpty(user_id)){
			params.put("create_user",user_id);
		}
		//定义List 存储 反馈意见信息
		List<IdeaFeedback> list = ideaFeedbackManager.find(params,pagination);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("desc", "查询成功");
		map.put("pagination", pagination);
		map.put("list", list);
		return new ModelAndView(new JsonView("listFeedBackPagination",map));
	}
	
}
