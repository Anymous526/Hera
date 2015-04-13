 
package com.vlives.core.dao.generic.finder;

/**
 * 按占位符查询器<br/>
 * 不能做分页查询
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class PlaceholderFinder  extends ParametersFinder {

	private String placeholderString;
	/**
	 * 不能做分页查询
	 * @author jianguo.xu
	 * @param placeholderString
	 * @param properties
	 */
	public PlaceholderFinder(String placeholderString, Object[] properties) {
		super(properties);
		this.placeholderString = placeholderString;
	}
	
	/**
	 * 不能做分页查询
	 * @author jianguo.xu
	 * @param placeholderString
	 * @param properties
	 */
	public PlaceholderFinder(String placeholderString, Object property) {
		this(placeholderString,new Object[]{property});
	}

	public String genQueryString() {
		return placeholderString;
	}
}

