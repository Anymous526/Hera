/**
 * @(#)CategoryElement.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.chart.element;

import java.util.List;
import java.util.Set;

import com.vlives.core.support.chart.AbstractElement;
import com.vlives.core.support.chart.Attribute;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-19
 */
public class DefinitionElement extends AbstractElement{
	private static final String elementName ="definition";
	public DefinitionElement(List<AbstractElement> childrens) {
		super(elementName, childrens);
	}
	public DefinitionElement(Set<Attribute> attributies,
			List<AbstractElement> childrens) {
		super(elementName, attributies, childrens);
	}

	public DefinitionElement(Set<Attribute> attributies) {
		super(elementName, attributies);
	}
	
}
