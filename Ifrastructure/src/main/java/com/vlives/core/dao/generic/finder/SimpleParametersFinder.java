 
package com.vlives.core.dao.generic.finder;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单参数查询器
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class SimpleParametersFinder extends ParametersFinder{
	
	private String queryString;
	
	public SimpleParametersFinder(String queryString,Map<String,Object> parameters){
		super(parameters);
		this.queryString=queryString;
	}
	public SimpleParametersFinder(String queryString,String key,Object value){
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put(key, value);
		this.setParameters(parameters);
		this.queryString = queryString;
		 
	}
	
	public String genQueryString() {
		return queryString;
	}

}
