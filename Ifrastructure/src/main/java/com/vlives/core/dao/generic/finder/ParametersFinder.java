 
package com.vlives.core.dao.generic.finder;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public abstract class ParametersFinder extends QueryStringFinder{
	
	private Map<String,Object> parameters;
	
	
	private Object[] properties;
	/**
	 * 按命名参数的方式绑定参数
	 * @param 查询参数  key:·查询语句中的命名参数 value:参数值
	 * 
	 */
	public ParametersFinder(Map<String,Object> parameters){
		this.parameters=parameters;	
	}
	/**
	 * 按站位符的形式绑定参数
	 * @param properties
	 */
	public ParametersFinder(Object[] properties) {
		this.properties = properties;
	}
	
	public Object[] getProperties() {
		return properties;
	}

	public void setProperties(Object[] properties) {
		this.properties = properties;
	}

	public ParametersFinder(){
		
	}
	
	public abstract String genQueryString();
	

	/**
	 * @return the parameters
	 */
	public Map<String,Object> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String,Object> parameters) {
		this.parameters = parameters;
	}
	
	 

	/**
	 * 添加一个参数
	 * @param name	参数名
	 * @param value	参数值
	 */
	public void add(String name,Object value){
		if(parameters==null)
			parameters=new HashMap<String,Object>();
		parameters.put(name, value);
	}
	
	 
	 
}

