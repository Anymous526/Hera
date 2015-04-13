/**
 * @(#)ListFactoryBean.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.vlives.util.ClassUtils;
import com.vlives.util.ClassUtils.ClassFilter;
/**
 * 查询hibernate entity spring FactoryBean<br/>
 * 查询规则为：<br/>
 * 1、sourceList集合全部作为entity加入<br/>
 * 2、查询packageList集中指定包下的hibernate entity<br/>
 * @author jianguo.xu
 * @version 1.0,2011-3-8
 */
@SuppressWarnings("rawtypes")
public class HibernateAnnotatioListFactoryBean extends AbstractFactoryBean {
	
	private List<String> sourceList;
	private List<String> packageList; 
	 
	private Class [] entityTypes = new Class [] {Entity.class,Embeddable.class,MappedSuperclass.class};

	public void setSourceList(List<String> sourceList) {
		this.sourceList = sourceList;
	}
	
	public void setPackageList(List<String> packageList) {
		this.packageList = packageList;
	}

	public Class<?> getObjectType() {
		return List.class;
	}
	
	protected Object createInstance() {
		if (this.sourceList == null&&packageList == null) {
			throw new IllegalArgumentException("'sourceList' or 'pageList' is required");
		}
		List<String> result = new ArrayList<String>();
		if(sourceList!=null) result.addAll(this.sourceList);
		if(packageList!=null) {
			for(String packageName : packageList) {
				result.addAll(searchEntity(packageName));
			}
		}
		return result;
	}
	
	private List<String> searchEntity(String packageName) {
		List<String> entities = new ArrayList<String>();
		List<Class<?>> clazzs = ClassUtils.getClassPathClasses(packageName, true,new ClassFilter() {
			public boolean accept(Class<?> clazz) {
				for (Class<? extends Annotation> entityType : entityTypes) {
					if(clazz.isAnnotationPresent(entityType)) return true;
				}
				return false;
			}
		});
		if(clazzs==null||clazzs.size() == 0)
			return entities;
		 return entities;
	}

}
