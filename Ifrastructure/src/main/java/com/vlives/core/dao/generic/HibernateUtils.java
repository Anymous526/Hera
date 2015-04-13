 
package com.vlives.core.dao.generic;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class HibernateUtils {

	private static final Pattern FROM_PATTERN = Pattern
			.compile("(^|\\s)from\\s");
	private static final Pattern GROUPBY_PATTERN = Pattern
			.compile("\\sgroup\\s+by\\s");
	private static final Pattern ORDERBY_PATTERN = Pattern
			.compile("\\sorder\\s+by\\s");
	private static final Pattern HAVING_PATTERN = Pattern
			.compile("\\shaving(\\s|\\()");
	 
	/**
	 * 判断hql中是否存在 having 子句
	 * @author xujianguo
	 * @return
	 */
	public static final boolean isExistHavingHql(String hql) {
		return HAVING_PATTERN.matcher(hql).find();
	}
	
	public static final String countHql(String hql) {
		
		hql = hql.trim();
		String tempHql = hql.toLowerCase();
		int hqlLength = tempHql.length();
		Matcher fromMatcher = FROM_PATTERN.matcher(tempHql);
		if (fromMatcher.find()) {
			int fromIndex = fromMatcher.end();
			Matcher groupByMathcer = GROUPBY_PATTERN.matcher(tempHql);
			int groupByIndex = -1;
			int groupByEnd = -1;
			if (groupByMathcer.find()) {
				groupByIndex = groupByMathcer.start();
				groupByEnd = groupByMathcer.end();
			}
			Matcher orderByMathcer = ORDERBY_PATTERN.matcher(tempHql);
			int orderByIndex = orderByMathcer.find() ? orderByMathcer.start()
					: -1;
			Matcher havingMathcer = HAVING_PATTERN.matcher(tempHql);
			int havingIndex = havingMathcer.find() ? havingMathcer.start() : -1;
			 
			int endIndex = groupByIndex;
			if (orderByIndex != -1) {
				if (endIndex != -1)
					endIndex = endIndex < orderByIndex ? endIndex
							: orderByIndex;
				else
					endIndex = orderByIndex;
			}
			if (havingIndex != -1) {
				if (endIndex != -1)
					endIndex = endIndex < havingIndex ? endIndex : havingIndex;
				else
					endIndex = havingIndex;
			}
			endIndex = endIndex == -1 ? hqlLength : endIndex;
			int groupByFieldIndex = -1;
			if (groupByIndex != -1) {
				if (orderByIndex > groupByIndex) {
					groupByFieldIndex = orderByIndex;
				}
				if (havingIndex > groupByIndex) {
					if (groupByFieldIndex != -1)
						groupByFieldIndex = groupByFieldIndex < havingIndex ? groupByFieldIndex
								: havingIndex;
					else
						groupByFieldIndex = havingIndex;
				}
				groupByFieldIndex = groupByFieldIndex == -1 ? hqlLength
						: groupByFieldIndex;
			}
			String selectHql = groupByIndex == -1 ? "select count(*)"
					: "select count(distinct "
							+ hql.substring(groupByEnd, groupByFieldIndex)
							+ ")";
			if(havingIndex==-1)
				return selectHql + " from "
						+ hql.substring(fromIndex, endIndex);
			else {
				return selectHql + " from "
				+ hql.substring(fromIndex, orderByIndex==-1?hqlLength:orderByIndex);
			}
		}
		return null;
	}
	/**
	 * 求和hql
	 * @author jianguo.xu
	 * @param hql
	 * @param sumName
	 * @return
	 */
	public static final String sumHql(String hql,String sumName) {
		String countHql = countHql(hql);
		return countHql.replaceAll("count\\([^\\)]*\\)", "sum("+sumName+")");
	}
	

	/**
	 * 根据查询参数 设置query对象的查询参数
	 * @author jianguo.xu
	 * @param query
	 * @param paramMap
	 */
	public static final void paramQuery(Query query, Map<String,Object> paramMap) {
		if (paramMap != null) {
			String[] params = query.getNamedParameters();
			for (int i = 0; i < params.length; i++) {
				String param = params[i];
				Object value = paramMap.get(param);
				if(value instanceof Collection)
					query.setParameterList(param, (Collection<?>)value);
				else if(value instanceof Object[])
					query.setParameterList(param, (Object[])value);
				else
					query.setParameter(param, value);
			}
		}
	}
	
	/**
	 * 根据查询参数 设置query对象的查询参数
	 * @author jianguo.xu
	 * @param query
	 * @param paramMap
	 */
	public static final void paramQuery(Query query, Object[] properties) {
		if (properties != null) {
			for(int i = 0;i<properties.length;i++) {
				query.setParameter(i, properties[i]);
			}
		}
	}
/*	
	public static void main(String[] args) {
		String countHql = "select count(dd,ww) from Merhcant where level in(wc,wd)";
		String sumName="smsCount";
		countHql = countHql.replaceAll("count\\([^\\)]*\\)", "sum("+sumName+")");
		System.out.println(countHql);
	}*/

}