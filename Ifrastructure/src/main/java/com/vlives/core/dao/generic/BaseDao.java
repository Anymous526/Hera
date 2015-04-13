
package com.vlives.core.dao.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;

import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public interface BaseDao<T,PK extends Serializable> {
	/**
	 * 通过对象标识获得对象
	 * @param cls	对象类型
	 * @param id	标识
	 * @return
	 */
	public T get(PK id);
	/**
	 * 更新对象
	 * @param obj
	 */
	public void update(T obj);
	
	/**
	 * 保存或更新对象
	 * @param obj
	 */
	public void saveOrUpdate(T obj);
	
	/**
	 * 保存对象
	 * @param obj
	 * @return
	 */
	public PK save(T obj);
	
	/**
	 * 删除对象
	 * @param obj
	 */
	public void delete(T obj);
	
	/**
	 * 删除对象集合
	 * @param objs
	 */
	public void deleteAll(Collection<T> objs);
	
	/**
	 * 根据对象属性获得对象
	 * @param cls	对象类型
	 * @param propertyName	属性名
	 * @param propertyValue	属性值
	 * @return
	 */
	public T getByProperty(String propertyName,Object propertyValue);
	
	/**
	 * 通过查找器查找单个对象
	 */
	public T getBy(Finder finder);
	
	/**
	 * 通过查找器查找对象集合
	 * @param finder
	 * @return
	 */
	public List<T> find(Finder finder);
	
	/**
	 * 通过查找器查找对象集合，带分页
	 */
	public List<T> find(Finder finder,Pagination pagination);
	
	/**
	 * 通过查找器查找对象集合，指定数量
	 * @param finder
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<T> find(Finder finder,int firstResult,int maxResults);
	
	/**
	 * 通过查找器查找对象集合，指定数量
	 * @param finder
	 * @param limit
	 * @return
	 */
	public List<T> find(Finder finder,int maxResults);
	
	/**
	 * 通过查找器查询对象数量
	 */
	public long count(Finder finder);
	
	/**
	 * 通过查找器和指定的属性求和<br/>
	 * 如果没有数据就返回null
	 * @author jianguo.xu
	 * @param finder
	 * @param sumName 需要求和的属性
	 * @return
	 */
	public Serializable sum(Finder finder,String sumName);
	
	/**
	 * 通过对象属性查询对象数量
	 */
	public long count(String propertyName,Object propertyValue);
	
	/**
	 * 通过查找器查询是否存在
	 */
	public boolean isExisted(Finder finder);
	
	/**
	 * 通过对象属性查询对象是否存在
	 */
	public boolean isExisted(String propertyName,Object propertyValue);
	
	/**
	 * 清除对象缓存
	 * @param obj
	 */
	public void evict(T obj);
	
	/**
	 * 清除缓存
	 */
	public void clear();
	
	/**
	 * 合并对象
	 * @param obj
	 */
	public void merge(T obj);
	/**
	 * 批量保存
	 * @param list
	 */
	public void saveAll(List<T> list);
	
	@SuppressWarnings("rawtypes")
	public Object execute(HibernateCallback hibernateCallback);
	
	/**
	 * 得到指定属性的最小值min(propertyName),如果不存在最小值则返回 null
	 * @author jianguo.xu
	 * @param propertyName
	 * @return
	 */
	public Long getMin(final String propertyName);
	/**
	 * 得到指定属性的最大值max(propertyName),如果不存在最大值则返回 null
	 * @author jianguo.xu
	 * @param propertyName
	 * @return
	 */
	public Long getMax(final String propertyName);
	/**
	 * 根据finder中的select字段返回查询结果
	 * 如果是单个字段将返回Object
	 * 如果是多个字段将返回Object[]
	 * @author jianguo.xu
	 * @param finder
	 * @return
	 */
	public List<Object> findToObjectArray(Finder finder);
	
	/**
	 * 根据finder中的select字段返回查询结果
	 * 如果是单个字段将返回Object
	 * 如果是多个字段将返回Object[]
	 * @author jianguo.xu
	 * @param finder
	 * @return
	 */
	public List<Object> findToObjectArray(Finder finder,Pagination pagination);
	
}


