 
package com.vlives.core.dao.generic.finder;

import java.util.Map;

/**
 * 动态查询器
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class DynamicFinder extends ParametersFinder {

	private String dynamicString;

	public DynamicFinder(String dynamicString, Map<String,Object> parameters) {
		super(parameters);
		this.dynamicString = dynamicString;
	}

	public String genQueryString() {
		return DynamicQueryUtils.parse(dynamicString, getParameters());
	}

}

