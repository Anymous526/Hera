 
package com.vlives.core.dao.generic.finder;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public interface Finder {
	 

 
	/**
	 * 是否采用了查询缓存
	 * @author jianguo.xu
	 * @return
	 */
	public boolean isCacheable();
	/**
	 * 设置是否使用查询缓存
	 * 默认是不是用
	 * @author jianguo.xu
	 * @param cacheable
	 */
	public void setCacheable(boolean cacheable);
	/**
	 * 转化查询结果
	 * 改接口是对查询结果进行一个转换，目前没使用
	 * 实现类知识返回该参数
	 * @author jianguo.xu
	 * @param obj
	 * @return
	 */
	public Object convert(Object obj);
}


