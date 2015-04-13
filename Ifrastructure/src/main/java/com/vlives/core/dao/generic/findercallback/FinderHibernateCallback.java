 
package com.vlives.core.dao.generic.findercallback;

import org.springframework.orm.hibernate3.HibernateCallback;

import com.vlives.core.dao.generic.finder.Finder;

/**
 * FinderHibernateCallback 继承 HibernateCallback
 * 该抽象类是Finder 回调方法的的基类
 * 所有该类的子类都必须必须得到一个 Finder(查询器)
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
@SuppressWarnings("rawtypes")
public abstract class FinderHibernateCallback implements HibernateCallback {

	private Finder finder;
	public Finder getFinder() {
		return finder;
	}
 
	public void setFinder(Finder finder) {
		this.finder = finder;
	}

	public FinderHibernateCallback(Finder finder) {
		this.finder = finder;
	}
}

