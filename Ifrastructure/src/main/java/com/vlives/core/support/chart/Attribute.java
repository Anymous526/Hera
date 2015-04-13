/**
 * @(#)Attribute.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.chart;
/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-19
 */
public class Attribute {
	private String name;
	private Object value;
	
	public Attribute(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
