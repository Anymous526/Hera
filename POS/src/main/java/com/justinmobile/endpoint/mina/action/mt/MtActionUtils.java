/**
 * @(#)ActionUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.action.mt;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.request.MtRequestCodeAnnotation;
import com.justinmobile.util.ClassUtils;


/**
 * @author ThinkPad7
 *
 */
public class MtActionUtils {

	/**
	 * 得到MtRequestAction下的所有处理request业务方法，也就是带有 MoRequestCodeAnnotation注解的方法
	 * @author 
	 * @return
	 */
	public static Map<MtRequestCode,MtActionTarget> getMtActionMethodTarget() {
		Map<MtRequestCode,MtActionTarget> tragetMaps = new HashMap<MtRequestCode, MtActionTarget>();
		Class<? extends MtRequestAction> actionClass = MtRequestAction.class;
		List<Class<? extends MtRequestAction>> classes = ClassUtils.getClassPathClasses(ClassUtils.JUSTINMOBILE_PACKAGE, true,actionClass);
		for(Class<? extends MtRequestAction> clazz : classes) {
			addMtActionMethod(tragetMaps,clazz);
		}
		return tragetMaps;
	}
	
	private static void addMtActionMethod(Map<MtRequestCode,MtActionTarget> tragetMaps,Class<? extends MtRequestAction> actionClass) {
		Method[] methods = actionClass.getMethods();
		for(Method method : methods) {
			MtRequestCodeAnnotation annotation = method.getAnnotation(MtRequestCodeAnnotation.class);
			if(annotation==null) continue;
			MtRequestCode requestCode = annotation.value();
			MtActionTarget target = new MtActionTarget(actionClass,method,requestCode);
			addMtTragetMaps(tragetMaps,requestCode,target);
		}
		 
	}
	
	private static void addMtTragetMaps(Map<MtRequestCode,MtActionTarget> tragetMaps,MtRequestCode requestCode,MtActionTarget target) {
		if(tragetMaps.get(requestCode)!=null) 
			throw new RuntimeException("add action method error.action request code definition is not unique : "+requestCode.getCode()+
					" class : "+target.getActionClazz().getName()+" method : "+target.getMethod().getName());
		tragetMaps.put(requestCode, target);
	}
	
	public static void main(String[] args) {
		MtActionUtils.getMtActionMethodTarget();
	}
	
}
