/**
 * @(#)compositor.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.dao.generic.finder;
/**
 * 排序器
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class Compositor {
	/**
	 * 要排序的属性
	 */
	private  String property;
	/**
	 * 要排序规则
	 */
	private Role role;
	
	
	
	
	public Compositor(String property) {
		this.property = property;
		this.role = Role.ASC;
	}

	public Compositor(String property, Role role) {
		this.property = property;
		this.role = role;
	}

	public String getProperty() {
		return property;
	}
	public Role getRole() {
		return role;
	}
	public static enum Role {
		/**
		 * 升序
		 */
		ASC((short)1,"ASC"),
		/**
		 * 升序
		 */
		DESC((short)2,"DESC");
		private final short role;
		private final String value;
		private Role(short role,String value) {
			this.role = role;
			this.value = value;
		}
		public short getRole() {
			return role;
		}
		public String getValue() {
			return value;
		}
		
	}
		
}
