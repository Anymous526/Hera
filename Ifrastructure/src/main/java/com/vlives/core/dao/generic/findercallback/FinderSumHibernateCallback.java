 
package com.vlives.core.dao.generic.findercallback;

import org.hibernate.Query;

import com.vlives.core.dao.generic.HibernateUtils;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.QueryStringFinder;



/**
 * 求和的 Callback
 * 如果还查询语句带有having子句，那么返回结果就是该结果集的数量
 * 
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class FinderSumHibernateCallback extends
		FinderQueryStringHibernateCallback {
	private String sumName;
	public FinderSumHibernateCallback(Finder finder,String sumName) {
		super(finder);
		this.sumName = sumName;
	}
	/**
	 * 执行sum查询语句 
	 */
	protected Object processQuery(Query query) {
		 return query.iterate().next();
	}

	/**
	 * 根据QueryStringFinder的查询语句 得到 hql的 count查询语句
	 */

	protected String genQueryString() {
		QueryStringFinder queryStringFinder = (QueryStringFinder) getFinder();
		return HibernateUtils.sumHql(queryStringFinder.genQueryString(),sumName);
	}
}
