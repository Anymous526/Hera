/**
 * @(#)XmlView.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
  
import org.springframework.ui.Model; 

import com.opensymphony.xwork2.util.TextUtils;
import com.vlives.util.JSONUtils;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2008-8-17
 */
public class XmlView extends JsonView{
	private boolean escape = false;
	public XmlView(boolean success, String msg) {
		super(success, msg);
		 
	}

	public XmlView(Model model) {
		super(model);
		 
	}

	public XmlView(Object object) {
		super(object);
		 
	}

	public XmlView(String jsonKey, Model model) {
		super(jsonKey, model);
	 
	}

	public XmlView(String jsonKey, Object object) {
		super(jsonKey, object);
		 
	}

	@Override
	protected void renderMergedOutputModel(Map model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jsonString = JSONUtils.serialize(object, jsonKey);
		jsonString = jsonString == null ? "{}" : jsonString;
		String xml = createXmlString(jsonString);
		response.setContentType("text/xml;charset=utf-8");
		response.getWriter().write(xml);
	}
	
	public String createXmlString(String jsonString) {
		JSON jsonObj = createJSONObj(jsonString);
		XMLSerializer xmlSerializer = createXMLSerializer();
		return escape ? TextUtils.htmlEncode(xmlSerializer.write(jsonObj))
				: xmlSerializer.write(jsonObj);
	}
	
	private JSON createJSONObj(String jsonString) {
		if (jsonString.trim().startsWith("["))
			return JSONArray.fromObject(jsonString);
		return JSONObject.fromObject(jsonString);
	}
	
	private XMLSerializer createXMLSerializer() {
		XMLSerializer xml = new XMLSerializer();
		xml.setObjectName(jsonKey);
		xml.setArrayName(jsonKey);
		xml.setElementName("item");
		xml.setTypeHintsEnabled(false);
		xml.setTypeHintsCompatibility(true);
		xml.setNamespaceLenient(true);
		xml.setSkipNamespaces(true);
		xml.setRemoveNamespacePrefixFromElements(true);
		xml.setTrimSpaces(false);
		return xml;
	}
	
	
}
