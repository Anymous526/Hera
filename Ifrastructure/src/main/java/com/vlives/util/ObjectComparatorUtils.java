/**
 * 根据对象的属性排序
 * 如：根据 Order的id,processStatus,advancePayment,createDate,排序
 * String[][] strss = new String[][] {
				new String[] { "id", ObjectComparator.DESC },
				new String[] { "processStatus", ObjectComparator.ASCE },
				new String[] { "advancePayment", ObjectComparator.ASCE },
				new String[] { "createDate", ObjectComparator.DESC }};

		Collections.sort(list, new ObjectComparator(Order.class, strss));
 * @(#)ObjectComparator.java
 * @author jianguo.xu
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.vlives.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * description
 * 参考 ： 对order对象集合排序
 * String[][] rules = new String[][] {new String[] {"id",ObjectComparatorUtils.DESC},
 *				new String[] {"deliveryTime",ObjectComparatorUtils.ASCE},
 *				new String[] {"advancePayment",ObjectComparatorUtils.ASCE}
 *		}
 *	Collections.sort(list, new ObjectComparatorUtils(Order.class,rules));
 * @author xujianguo
 * @version 1.0,2008-10-10
 */
@SuppressWarnings({"all"})
public class ObjectComparatorUtils implements Comparator {
	private static final Log LOG = LogFactory.getLog(ObjectComparatorUtils.class);;
	private Class clazz;
	private String[][] properties;
	/**
	 * 升序
	 */
	public static final String ASCE = "0";
	/**
	 * 降序
	 */
	public static final String DESC = "1";

	public ObjectComparatorUtils(Class clazz, String[][] properties) {
		this.clazz = clazz;
		this.properties = properties;
	}
	
	private List parse() {
		List parseValues = null;
		for (int j = 0; j < properties.length; j++) {
			String param = properties[j][0];
			String getMethodName = "get"
					+ param.substring(0, 1).toUpperCase()
					+ param.substring(1);
			try {
				Method method = clazz.getMethod(getMethodName, null);
				ParseValue ParseValue = new ParseValue(method,
						properties[j][1]);
				if (parseValues == null) {
					parseValues = new ArrayList();
				}
				parseValues.add(ParseValue);
			} catch (Exception e) {
				continue;
			}
		}
		return parseValues;
		/**
		List parseValues = null;
		
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < properties.length; j++) {
				String param = properties[j][0];
				String fieldName = fields[i].getName();
				if (fieldName.equals(param)) {
					String getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					try {
						Method method = clazz.getMethod(getMethodName, null);
						ParseValue ParseValue = new ParseValue(method,
								properties[j][1]);
						if (parseValues == null) {
							parseValues = new ArrayList();
						}
						parseValues.add(ParseValue);
					} catch (Exception e) {
						continue;
					}
				}
			}
		}
		return parseValues;
		*/
	}

	private static int sort(String rule) {
		if (rule.compareTo(DESC) == 0) {
			return -1;
		} else {
			return 1;
		}
	}

	public int compare(Object o1, Object o2) {
		List parseValues = parse();
		if (parseValues == null) {
			return 0;
		}
		for (int i = 0; i < parseValues.size(); i++) {
			ParseValue parseValue = (ParseValue) parseValues.get(i);
			Method method = parseValue.getMethod();
			String rule = parseValue.getRule();
			try {
				String type = method.getReturnType().getName();
				Object return1 = method.invoke(o1, null);
				Object return2 = method.invoke(o2, null);
				if (type.equals("java.lang.String")) {
					if (((String) return1).compareTo((String) return2) > 0) {
						return sort(rule);
					}
					if (((String) return1).compareTo((String) return2) < 0) {
						return -sort(rule);
					}
				} else if (type.equals("java.lang.Short")
						|| type.equals("short")) {
					if (((Short) return1).compareTo((Short) return2) > 0) {
						return sort(rule);
					}
					if (((Short) return1).compareTo((Short) return2) < 0) {
						return -sort(rule);
					}
				} else if (type.equals("java.lang.Integer")
						|| type.equals("int")) {
					if (((Integer) return1).compareTo((Integer) return2) > 0) {
						return sort(rule);
					}
					if (((Integer) return1).compareTo((Integer) return2) < 0) {
						return -sort(rule);
					}
				} else if (type.equals("java.lang.Integer")
						|| type.equals("int")) {
					if (((Integer) return1).compareTo((Integer) return2) > 0) {
						return sort(rule);
					}
					if (((Integer) return1).compareTo((Integer) return2) < 0) {
						return -sort(rule);
					}
				} else if (type.equals("java.lang.Long") || type.equals("long")) {
					if (((Long) return1).compareTo((Long) return2) > 0) {
						return sort(rule);
					}
					if (((Long) return1).compareTo((Long) return2) < 0) {
						return -sort(rule);
					}
				} else if (type.equals("java.math.BigDecimal")) {
					if (((BigDecimal) return1).compareTo((BigDecimal) return2) > 0) {
						return sort(rule);
					}
					if (((BigDecimal) return1).compareTo((BigDecimal) return2) < 0) {
						return -sort(rule);
					}
				} else if (type.equals("java.util.Date")) {
					if (((Date) return1).compareTo((Date) return2) > 0) {
						return sort(rule);
					}
					if (((Date) return1).compareTo((Date) return2) < 0) {
						return -sort(rule);
					}
				} else if (type.equals("java.sql.Date")) {
					if (((java.sql.Date) return1)
							.compareTo((java.sql.Date) return2) > 0) {
						return sort(rule);
					}
					if (((java.sql.Date) return1)
							.compareTo((java.sql.Date) return2) < 0) {
						return -sort(rule);
					}
				}
			} catch (Exception e) {
				LOG.warn("method return value error!!");
			}

		}
		return 0;
	}

	private class ParseValue {
		private Method method;
		private String rule;

		public ParseValue(Method method, String rule) {
			this.method = method;
			this.rule = rule;
		}

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		public String getRule() {
			return rule;
		}

		public void setRule(String rule) {
			this.rule = rule;
		}
	}
}
