package com.justinmobile.endpoint.mina.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad7
 *
 */
public class Hs8583CouponSketchList {

	private Map<String,String> couponSketchList=new HashMap<String,String>();

	public Map<String, String> getCouponSketchList() {
		return couponSketchList;
	}

	public void setCouponSketchList(Map<String, String> couponSketchList) {
		this.couponSketchList = couponSketchList;
	}
	
	
	/**
	 * @param couponTypeNo    券号
	 * @param couponSketch    券信息描述
	 */
	public void addCouponType(Long couponTypeNo ,String couponSketch){
		couponSketchList.put(String.format("%8d", couponTypeNo), couponSketch);
	}
	
	/**
	 * @param couponTypeNo    券号
	 * @param couponSketch    券信息描述
	 */
	public void addCouponType(String couponTypeNo ,String couponSketch){
		couponSketchList.put(String.format("%8s", couponTypeNo).replace(" ", "0"), couponSketch);
	}

	@Override
	public String toString() {
		String s="";
		if(!couponSketchList.isEmpty()){
			for(String key:couponSketchList.keySet()){
				s+=key+"={"+couponSketchList.get(key)+"} \n";
			}
		}
		return s;
	}
	
	
}
