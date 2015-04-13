/**
 * @(#)AuthenticationEntryUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain.rulesupport;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.vlives.boss.coupon.domain.rulesupport.SendRule.RuleItem;

/**
 * 
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 */
public class SendRuleHandler {
	private static final Log LOG = LogFactory.getLog(SendRuleHandler.class);
	public static String parseXML(SendRule rule) {
		StringBuffer stringBuffer = null;
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("rule");
		if(rule!=null&&rule.getRuleItems()!=null&&rule.getRuleItems().size()>0) {
			for(RuleItem it : rule.getRuleItems()) {
				Element itemElement = root.addElement("item");
				Element nameEl = itemElement.addElement("name");
				nameEl.setText(it.getName());
				Element valueEl = itemElement.addElement("value");
				valueEl.setText(it.getValue());
			}
		}
		stringBuffer = new StringBuffer(document.asXML());
		stringBuffer.delete(0, 39);  //删除<?xml version="1.0" encoding="UTF-8"?>
		return stringBuffer.toString();
	}
	
	public static SendRule parseSendRule(String text) {
		if (text == null || text.trim().equals(""))
			return null;
		Document document = null;
		try {
			document = DocumentHelper.parseText(text);
		} catch (DocumentException e) {
			LOG.error("paraser authentication entry xml error",e);
			return null;
		}
		Element root = document.getRootElement();
		List<RuleItem> ruleItems = new ArrayList<RuleItem>();
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		for(Element el: elements) {
			Element nameEl = el.element("name");
			Element valeEl = el.element("value");
			RuleItem item = new RuleItem(nameEl.getText(), valeEl.getText());
			ruleItems.add(item);
		}
		return  new SendRule(ruleItems);
	}

}
