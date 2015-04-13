package com.justinmobile.security.dao.hibernate;

 
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.justinmobile.core.dao.EntityPageDaoHibernate;
import com.justinmobile.core.dao.support.Page;
import com.justinmobile.security.dao.SysMenuDao;
import com.justinmobile.security.domain.SysMenu;

@Repository("sysMenuDao")
public class SysMenuDaoHibernate extends EntityPageDaoHibernate<SysMenu> implements SysMenuDao {

	@SuppressWarnings("unchecked")
	public Page<SysMenu> pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		Integer totalCount = findInt(countQueryString, values);
		
		if (totalCount == null || totalCount < 1)
			return new Page<SysMenu>();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		//
		if(hql.indexOf("order by  entity.parent.menuName")!=-1){
			String leftJoinHql = "left join entity.parent as parent where" ;
		    hql = StringUtils.replace(hql, "where", leftJoinHql);
		    hql = StringUtils.replace(hql, "entity.parent.menuName", "parent.menuName");
		    hql = StringUtils.replace(hql, "from", "select entity from");
        
		}
		Query query = createQuery(hql, values);
		//开启缓存
		query.setCacheable(true);
		List<SysMenu> list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();

		return new Page<SysMenu>(startIndex, totalCount, pageSize, list);
	}
	
	private String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
