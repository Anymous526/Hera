 
package com.vlives.core.dao.generic.finder;

/**
 * 简单查询语句查找器。直接通过构造方法传入查询语句。
 * 
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class SimpleQueryStringFinder extends QueryStringFinder{
	private String queryString;
	public SimpleQueryStringFinder(String queryString){
		this.queryString=queryString;
	}
	public String genQueryString() {
		return queryString;
	}
	
}
