package com.vlives.core.dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.dao.generic.findercallback.FinderCountHibernateCallback;
import com.vlives.core.dao.generic.findercallback.FinderListHibernateCallback;
import com.vlives.core.dao.generic.findercallback.FinderPaginationHibernateCallback;
import com.vlives.core.dao.generic.findercallback.FinderSumHibernateCallback;
import com.vlives.core.dao.generic.findercallback.FinderUniqueHibernateCallback;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2010-9-15
 */
public class BaseDaoHibernateImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements BaseDao<T, Serializable> {
	private Class<T> type;
	private static final int BATCH_SIZE = 2000;

	@SuppressWarnings("unchecked")
	public BaseDaoHibernateImpl() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public BaseDaoHibernateImpl(Class<T> type) {
		this.type = type;
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	@SuppressWarnings("unchecked")
	public long count(Finder finder) {
		return ((Long) getHibernateTemplate().execute(
				new FinderCountHibernateCallback(finder))).longValue();
	}

	public long count(String propertyName, Object propertyValue) {
		PropertiesFinder finder = new PropertiesFinder(type, propertyName,
				propertyValue);
		return count(finder);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Serializable sum(Finder finder, String sumName) {
		return ((Serializable) getHibernateTemplate().execute(
				new FinderSumHibernateCallback(finder,sumName)));
	}

	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}

	public void deleteAll(Collection<T> objs) {
		getHibernateTemplate().deleteAll(objs);
	}

	public void evict(T obj) {
		getHibernateTemplate().evict(obj);

	}

	@SuppressWarnings("unchecked")
	public List<T> find(Finder finder) {
		return getHibernateTemplate().executeFind(
				new FinderListHibernateCallback(finder));
	}

	@SuppressWarnings("unchecked")
	public List<T> find(Finder finder, Pagination pagination) {
		return getHibernateTemplate().executeFind(
				new FinderPaginationHibernateCallback(finder, pagination));
	}

	@SuppressWarnings("unchecked")
	public List<T> find(Finder finder, int firstResult, int maxResults) {
		FinderListHibernateCallback action = new FinderListHibernateCallback(
				finder);
		action.setFirstResult(firstResult);
		action.setMaxResults(maxResults);
		return getHibernateTemplate().executeFind(action);
	}

	public List<T> find(Finder finder, int maxResults) {
		return find(finder, 0, maxResults);
	}

 
	public T get(Serializable id) {
		return (T) getHibernateTemplate().get(type, id);
	}

	@SuppressWarnings("unchecked")
	public T getBy(Finder finder) {
		return (T) getHibernateTemplate().execute(
				new FinderUniqueHibernateCallback(finder));
	}

	public T getByProperty(String propertyName, Object propertyValue) {
		PropertiesFinder finder = new PropertiesFinder(type, propertyName,
				propertyValue);
		return getBy(finder);
	}

	public boolean isExisted(Finder finder) {
		return count(finder) > 0;
	}

	public boolean isExisted(String propertyName, Object propertyValue) {
		return count(propertyName, propertyValue) > 0;
	}

	public void merge(T obj) {
		getHibernateTemplate().merge(obj);

	}

	@SuppressWarnings("unchecked")
	public PK save(T obj) {
		return (PK) getHibernateTemplate().save(obj);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveAll(final List<T> list) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for (int j = 0; j < list.size(); j++) {
					Object object = list.get(j);
					session.save(object);
					if (j % BATCH_SIZE == 0) {
						session.flush();
						session.clear();
					}
				}
				return null;
			}
		});
	}

	public void saveOrUpdate(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);

	}

	public void update(T obj) {
		getHibernateTemplate().update(obj);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object execute(HibernateCallback hibernateCallback) {
		return getHibernateTemplate().execute(hibernateCallback);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Long getMin(final String propertyName) {
		return  (Long) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String queryString = "select min("+propertyName+") from "+type.getName();
				Iterator<?> it = session.createQuery(queryString).iterate();
				if(it==null||!it.hasNext()) {
					return null;
				}
				return it.next();
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Long getMax(final String propertyName) {
		return  (Long) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String queryString = "select max("+propertyName+") from "+type.getName();
				Iterator<?> it = session.createQuery(queryString).iterate();
				if(it==null||!it.hasNext()) {
					return null;
				}
				return it.next();
			}
		});
	}

 
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findToObjectArray(Finder finder) {
		return getHibernateTemplate().executeFind(
				new FinderListHibernateCallback(finder));
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findToObjectArray(Finder finder,Pagination pagination) {
		return getHibernateTemplate().executeFind(
				new FinderPaginationHibernateCallback(finder, pagination));
	}

	

}
