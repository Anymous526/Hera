 
package com.vlives.core.dao.generic.findercallback;

import org.hibernate.Query;

import com.vlives.core.dao.generic.HibernateUtils;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.QueryStringFinder;



/**
 * 查询数量的 Callback
 * 如果还查询语句带有having子句，那么返回结果就是该结果集的数量
 * 
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class FinderCountHibernateCallback extends
		FinderQueryStringHibernateCallback {

	public FinderCountHibernateCallback(Finder finder) {
		super(finder);
	}
	/**
	 * 执行count查询语句
	 * 根据hql语句中是否有 having子句来判断是否是一条结果还是多条结果
	 * 
	 */
	protected Object processQuery(Query query) {
		if (HibernateUtils.isExistHavingHql(((QueryStringFinder) getFinder())
				.genQueryString()))
			return new Long(query.list().size());
		else
			return (Long) query.iterate().next();
	}

	/**
	 * 根据QueryStringFinder的查询语句 得到 hql的 count查询语句
	 */

	protected String genQueryString() {
		if (getFinder() instanceof QueryStringFinder) {
			QueryStringFinder queryStringFinder = (QueryStringFinder) getFinder();
			return HibernateUtils.countHql(queryStringFinder.genQueryString());
		}
		return null;
	}
}
