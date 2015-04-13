/**
 * @(#)AuthenticationEntryUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.AuthenticationEntry.EntryItem;

/**
 * 关联帐号工具类
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 */
public class AuthenticationEntryUtils {
	private static final Log LOG = LogFactory.getLog(AuthenticationEntryUtils.class);
	public static String parseXML(AuthenticationEntry entry) {
		StringBuffer stringBuffer = null;
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("entry");
		if(entry!=null&&entry.getEntryItems()!=null&&entry.getEntryItems().size()>0) {
			for(EntryItem it : entry.getEntryItems()) {
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
	
	public static AuthenticationEntry parseAuthenticationEntry(String text) {
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
		List<EntryItem> entryItems = new ArrayList<AuthenticationEntry.EntryItem>();
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		for(Element el: elements) {
			Element nameEl = el.element("name");
			Element valeEl = el.element("value");
			EntryItem item = new EntryItem(nameEl.getText(), valeEl.getText());
			entryItems.add(item);
		}
		return  new AuthenticationEntry(entryItems);
	}

}
