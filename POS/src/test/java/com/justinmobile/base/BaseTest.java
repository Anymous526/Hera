package com.justinmobile.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = {"classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = false)
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	

	@Before
	public void before() {
	//	AbstractTransactionalJUnit4SpringContextTests
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("applicationContext-test.xml");
		// PostConstructBean postConstructBean = (PostConstructBean)
		// ctx.getBean("smsSendManager");
		try {

			
			// postConstructBean.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void executeSql(String sql) {
		if (StringUtils.isNotEmpty(sql)) {
			simpleJdbcTemplate.getJdbcOperations().execute(sql);
		}
	}

	protected void setSimpleProperties(Object o) {
		// Set<Field> fields = BeanUtils.getAllDeclareFields(o);
		Field[] fields = o.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			String methodName = "set" + StringUtils.capitalize(field.getName());
			try {
				Method method = o.getClass().getMethod(methodName, field.getType());
				Random r = new Random();
				int value = r.nextInt(10);
				String typeName = field.getType().getSimpleName();
				if ("String".equals(typeName)) {
					char[] charStr = "中股价饿哦if恶魔离开呢开发娟哦ieabcdqwertyuiop123456789".toCharArray();
					String str = "";
					for (int i = 1; i < 4; i++) {
						int j = r.nextInt(charStr.length);
						str += charStr[j];
					}
					method.invoke(o, String.valueOf(str));
				} else if ("LONG".equals(typeName.toUpperCase()) && !("id".equals(field.getName()))) {
					// method.invoke(o, Long.valueOf(value));
				} else if (("Integer".equals(typeName) || "int".equals(typeName)) && !("id".equals(field.getName()))) {
					method.invoke(o, Integer.valueOf(value));
				} else if ("Calendar".equals(typeName)) {
					method.invoke(o, Calendar.getInstance());
				} else if ("Date".equals(typeName)) {
					method.invoke(o, new Date());
				} else if ("Boolean".equals(typeName) || "boolean".equals(typeName)) {
					method.invoke(o, true);
				} else if ("BigDecimal".equals(typeName)) {
					method.invoke(o, new BigDecimal("0"));
				} else if ("String".equals(typeName)) {
					method.invoke(o, "中国ejlk");
				} else {
					method.invoke(o, null);
				}

			} catch (SecurityException e) {
				logger.warn("SecurityException: " + methodName + " method");
			} catch (NoSuchMethodException e) {
				logger.warn("NoSuchMethodException: " + methodName + " method");
			} catch (IllegalArgumentException e) {
				logger.warn("IllegalArgumentException: invoke method " + methodName + " ");
			} catch (IllegalAccessException e) {
				logger.warn("IllegalAccessException: invoke method " + methodName + " ");
			} catch (InvocationTargetException e) {
				logger.warn("InvocationTargetException: invoke method " + methodName + " ");
			}
		}
	}

}
