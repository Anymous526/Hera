 
package com.vlives.core.dao.generic.findercallback;

import org.hibernate.Query;

import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.QueryStringFinder;

/**
 * 唯一记录查询callback
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class FinderUniqueHibernateCallback extends FinderQueryStringHibernateCallback{
	
	public FinderUniqueHibernateCallback(Finder finder){
		super(finder);
	}
	protected String genQueryString() {
		if (getFinder() instanceof QueryStringFinder)
			return ((QueryStringFinder) getFinder()).genQueryString();
		return null;
	}

	protected Object processQuery(Query query) {
		return query.uniqueResult();
	}

}


