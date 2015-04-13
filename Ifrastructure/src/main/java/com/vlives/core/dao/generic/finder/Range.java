 
package com.vlives.core.dao.generic.finder;

/**
 * ��Χ����
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class Range {
	private Object min;
	private Object max;
	public Object getMin() {
		return min;
	}
	public void setMin(Object min) {
		this.min = min;
	}
	public Object getMax() {
		return max;
	}
	public void setMax(Object max) {
		this.max = max;
	}
	public Range(){
		
	}
	public Range(Object min,Object max){
		this.min=min;
		this.max=max;
	}
}

