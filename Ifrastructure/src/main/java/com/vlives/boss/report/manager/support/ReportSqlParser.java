/**
 * @(#)AuthenticationEntryUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager.support;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 */
public class ReportSqlParser {
	 
	private static String xmlFile = "report_sql.xml";
	private static Map<String, String> sqlMap;
	static {
		init();
	}
	private static void init() {
		InputStream ins = ReportSqlParser.class.getResourceAsStream(xmlFile);
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(ins);
		} catch (DocumentException e) {
			 
			throw new RuntimeException("paraser authentication entry xml error",e);
		}
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		sqlMap = new HashMap<String, String>();
		for(Element el: elements) {
			sqlMap.put(el.element("name").getText().trim(), el.element("value").getText().trim());
		}
	}
	
	public static String getQueryString(String queryName) {
		return sqlMap.get(queryName);
	}
	 
}
