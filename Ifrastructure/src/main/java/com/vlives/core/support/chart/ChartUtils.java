/**
 * @(#)ChartUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.chart;

 

import java.util.List;
import java.util.Set;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.dom.DOMElement;


/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-19
 */
public class ChartUtils {
	public static String parseXML(ChartElement chartElement) {
		Document document = DocumentHelper.createDocument();
		addChildrenElement(document, chartElement);
		return document.asXML();
	}
	
	private static void addChildrenElement(Branch parent,AbstractElement element) {
		DOMElement domEl = new DOMElement(element.getName());
		parent.add(domEl);
		Set<Attribute> attributies = element.getAttributies();
		if(attributies!=null&&attributies.size()>0)
			for(Attribute attribute : attributies) {
				domEl.setAttribute(attribute.getName(), attribute.getValue().toString());
			}
		List<AbstractElement> childrens = element.getChildrens();
		if(childrens!=null&&childrens.size()>0)
			for(AbstractElement children : childrens) {
				addChildrenElement(domEl, children);
			}
	}

}
