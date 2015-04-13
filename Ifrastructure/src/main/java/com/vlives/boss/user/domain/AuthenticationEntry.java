/**
 * @(#)RelationAttribute.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vlives.util.AuthenticationEntryUtils;

/**
 * 关联账户认证条目
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 */
public class AuthenticationEntry implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static class EntryItem {
		private String name;
		private String value;

		public EntryItem(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	private List<EntryItem> entryItems;
	public AuthenticationEntry() {
		 
	}
	public AuthenticationEntry(List<EntryItem> entryItems) {
		this.entryItems = entryItems;
	}
	
	public void addItem(EntryItem entryItem) {
		if(entryItems == null) entryItems = new ArrayList<AuthenticationEntry.EntryItem>();
		for(EntryItem item : entryItems) {
			if(item.getName().equals(entryItem.getName())) {
				item.setValue(entryItem.getValue());
			}
		}
		entryItems.add(entryItem);
	}
	
	
	public List<EntryItem> getEntryItems() {
		return entryItems;
	}


	public void setEntryItems(List<EntryItem> entryItems) {
		this.entryItems = entryItems;
	}


	public String toXml() {
		return AuthenticationEntryUtils.parseXML(this);
	}
	
	public static AuthenticationEntry createAuthenticationEntry(String text) {
		return AuthenticationEntryUtils.parseAuthenticationEntry(text);
	}
	
	 
}
