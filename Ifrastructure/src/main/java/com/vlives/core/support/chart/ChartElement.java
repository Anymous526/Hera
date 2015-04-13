/**
 * @(#)Chart.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.chart;

import java.util.List;
import java.util.Set;



/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-19
 */
public class ChartElement extends AbstractElement{
	private static final String chartName ="chart";
	public ChartElement(List<AbstractElement> childrens) {
		super(chartName, childrens);
	}

	public ChartElement(Set<Attribute> attributies, List<AbstractElement> childrens) {
		super(chartName, attributies, childrens);
	}

}
