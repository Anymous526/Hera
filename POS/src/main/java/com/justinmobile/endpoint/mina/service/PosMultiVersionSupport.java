/**
 * @(#)MultiVersionSupport.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.justinmobile.util.HttpClientUtil;
import com.justinmobile.util.HttpClientUtil.MethodType;
import com.justinmobile.util.PlatformCaller;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-11
 */
@Service
@Lazy(false)
public class PosMultiVersionSupport implements BeanFactoryAware{
	private ConfigurableListableBeanFactory beanFactory;
	public static final Map<String, PosProcesserA1> POS_PROCESSER_BEAN_MAP = new HashMap<String, PosProcesserA1>();
	/**
	 * 多版本支持spring bean name前缀
	 */
	private static final String MultiVersionBeanPrefix = "posProcesser";
    @PostConstruct
	public void initPosVersionSupport() throws ClientProtocolException, IOException {
		 
		JSONObject jsonObject = HttpClientUtil.getResponseByJson(PlatformCaller.getManagerPlatformUrl()+"/externalInterface.do?method=getValidVersions", null, null, MethodType.GET);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		if(jsonArray == null||jsonArray.size() == 0)
			throw new RuntimeException("valid pos version not exist");
		List<PosVersion> posVersions = new ArrayList<PosMultiVersionSupport.PosVersion>();
		for(int i =0;i<jsonArray.size();i++) {
			JSONObject itemObj = jsonArray.getJSONObject(i);
			posVersions.add(new PosVersion(itemObj.getInt("code"), itemObj.getString("version")));
		}
		setPosPointProcesserBean(posVersions);
		assertExistVersion();
	}
	
	private void assertExistVersion() {
		if(POS_PROCESSER_BEAN_MAP.size() == 0) {
			throw new RuntimeException("system must exist vaild pos version");
		}
	}
	
	private void setPosPointProcesserBean(List<PosVersion> posVersions) {
		for(PosVersion posVersion : posVersions){
			PosProcesserA1 posPointProcesser = (PosProcesserA1) beanFactory.getBean(MultiVersionBeanPrefix+posVersion.toBeanString());
			if(posPointProcesser == null) 
				throw new RuntimeException("pos business server not support version: "+ posVersion.getVersion()+" in application: "+posVersion.getAppCode());
			POS_PROCESSER_BEAN_MAP.put(posVersion.toString(), posPointProcesser);
		}
	}

	public static class PosVersion{
		private final  int appCode;
		private  final String version;
		public PosVersion(int appCode, String version) {
			this.appCode = appCode;
			this.version = version;
		}
		public int getAppCode() {
			return appCode;
		}
		public String getVersion() {
			return version;
		}
		@Override
		public String toString() {
			return appCode+"_"+version;
		}
		public String toBeanString() {
			return "A"+appCode+"V"+version.replaceAll("\\.", "_");
		}
		
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
			throw new IllegalArgumentException(
					"AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory");
		}

		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
	}
	 
}
