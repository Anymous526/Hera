 
package com.vlives.core.dao.generic.findercallback;

import org.hibernate.Query;

import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.QueryStringFinder;

/**
 * 集合查询Callback
 * 
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class FinderListHibernateCallback extends
		FinderQueryStringHibernateCallback {

	public FinderListHibernateCallback(Finder finder) {
		super(finder);
	}

	private int firstResult = -1;

	private int maxResults = -1;

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	protected Object processQuery(Query query) {
		if (firstResult != -1 && maxResults != -1)
			query.setFirstResult(firstResult).setMaxResults(maxResults);
		return query.list();
	}

	/**
	 * 根据QueryStringFinder的查询语句 得到 hql的 count查询语句
	 */
	protected String genQueryString() {
		if (getFinder() instanceof QueryStringFinder)
			return ((QueryStringFinder) getFinder()).genQueryString();
		return null;
	}
}
