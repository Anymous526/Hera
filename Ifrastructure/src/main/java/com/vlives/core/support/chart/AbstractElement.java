/**
 * @(#)Element.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.chart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-19
 */
/**
 * @author tiger
 *
 */
public abstract class AbstractElement {
	protected String name;
	protected Set<Attribute> attributies;
	protected List<AbstractElement> childrens;

	public AbstractElement(String name) {
		this(name, null,null);
	}
	
	public AbstractElement(String name, Set<Attribute> attributies) {
		this(name, attributies, null);
	}
	public AbstractElement(String name, List<AbstractElement> childrens) {
		this(name, null, childrens);
	}

	protected AbstractElement(String name, Set<Attribute> attributies,
			List<AbstractElement> childrens) {
		this.name = name;
		this.attributies = attributies;
		this.childrens = childrens;
	}
	public Set<Attribute> getAttributies() {
		return attributies;
	}
	public void setAttributies(Set<Attribute> attributies) {
		this.attributies = attributies;
	}
	
	public List<AbstractElement> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<AbstractElement> childrens) {
		this.childrens = childrens;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 添加 element 属性
	 * @author jianguo.xu
	 * @param name
	 * @param value
	 */
	public void addAttribute(String name,Object value) {
		addAttribute(new Attribute(name, value));
	}
	/**
	 *  添加 element 属性
	 * @author jianguo.xu
	 * @param attribute
	 */
	public void addAttribute(Attribute attribute) {
		if(attributies == null) attributies= new HashSet<Attribute>();
		attributies.add(attribute);
	}
	
	public void addElement(AbstractElement element) {
		if(childrens == null) new ArrayList<AbstractElement>();
		childrens.add(element);
	}
}
