/**
 * @(#)JsonView.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.view.AbstractView;

import com.vlives.util.web.ResponseUtils;

/**
 * 自定义 JSON View
 * @author  jianguo.xu
 * @version 1.0,2011-4-27
 */
public class JsonView extends AbstractView{
	
	private static final String DEFAULT_JSON_KEY="default_json";
	
	protected String jsonKey;
	protected Object object;
	 
	public JsonView(String jsonKey, Model model) {
		this(jsonKey, model.asMap());
	}
	/**
	 * 
	 * @author jianguo.xu
	 * @param jsonKey  对应json配置文件中item节点的name属性
	 * @param object	需要转换的json的对象
	 */
	public JsonView(String jsonKey, Object object) {
		this.jsonKey = jsonKey;
		this.object = object;
	}
	 
	/**
	 * 
	 * @author jianguo.xu
	 * @param object 需要转换的json的对象
	 */
	public JsonView(Object object) {
		 this(DEFAULT_JSON_KEY, object);
	}
	/**
	 * 
	 * @author jianguo.xu
	 * @param success 是否成功
	 * @param msg	消息 可为null
	 */
	public JsonView(boolean success,String msg) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", success);
		model.put("msg", msg);
		this.jsonKey = DEFAULT_JSON_KEY;
		this.object = model;
	}

	/**
	 * 
	 * @author jianguo.xu
	 * @param model spring model对象
	 */
	public JsonView(Model model) {
		this(model.asMap());
	}
 
	@Override
	protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResponseUtils.writeJSON(request,response, object, jsonKey);
	}

}
