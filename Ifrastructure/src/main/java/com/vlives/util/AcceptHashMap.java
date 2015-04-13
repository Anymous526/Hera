

package com.vlives.util;

import java.util.HashMap;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-01-10
 */
public class AcceptHashMap<K,V> extends HashMap<K,V>{
	private static final long serialVersionUID = 1L;
	
	private AcceptHashMap (){}
	
	public static<K,V> AcceptHashMap<K,V> newInstance() {
		return new AcceptHashMap<K,V>();
	}
	/**
	 * @author jianguo.xu
	 * @param key
	 * @param value
	 * @param flag
	 * @return
	 */
	public AcceptHashMap<K,V> acceptIf(K key,V value,boolean flag){
		if(flag)
			put(key,value);
		return this;
	}
	/**
	 * 
	 * @author jianguo.xu
	 * @param key
	 * @param value
	 * @return
	 */
	public AcceptHashMap<K,V> accept(K key,V value){
		return acceptIf(key,value,true);
	}
}

