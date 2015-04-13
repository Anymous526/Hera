package com.vlives.boss.whatsnew.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.vlives.boss.whatsnew.domain.WhatsNew;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;
import com.vlives.core.dao.generic.finder.SimpleQueryStringFinder;

@Repository("whatsNewDao")
public class WhatsNewDao extends BaseDaoHibernateImpl<WhatsNew, Long> {

	public List<WhatsNew> getWhatsNew(long cityId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("call WHATS_NEW_PROC(" + cityId + ")");
		query.executeUpdate();
		
		String hql = "from WhatsNew order by datetime desc";
		SimpleQueryStringFinder finder = new SimpleQueryStringFinder(hql);
		return this.find(finder, 0, 5);
	}
}
