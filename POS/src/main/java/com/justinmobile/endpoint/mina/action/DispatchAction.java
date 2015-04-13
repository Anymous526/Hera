/**
 * @(#)DispatchAction.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.action;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.justinmobile.endpoint.mina.action.mt.MtActionTarget;
import com.justinmobile.endpoint.mina.action.mt.MtActionUtils;
import com.justinmobile.endpoint.mina.action.mt.MtRequestAction;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;

/**
 * 此action是个一个起到转发作用的action
 * 
 * @author 
 * @version 1.0,2010-9-28
 */
public final class DispatchAction implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	private static Map<MtRequestCode, MtActionTarget> mtTragetMaps;

	
	
	@SuppressWarnings("unused")
	private void initMap() {
		mtTragetMaps = MtActionUtils.getMtActionMethodTarget();
	}
	

	private DispatchAction() {}
	/**
	 * 根据传入的Hs8583Dto分发到具体的action业务处理方法处理request请求
	 * 
	 * @author 
	 * @param Hs8583Dto
	 * @return 返回响应dto
	 */
	public Hs8583Dto executeMtRequest(Hs8583Dto hs8583Dto) {
		MtRequestCode requestCode = hs8583Dto.getRequestCode();
		MtActionTarget target = mtTragetMaps.get(requestCode);
		if (target == null) {
			throw new RuntimeException(
					"request matching action error. action method is not found. mt request code : "
							+ requestCode.getCode());
		}
		Class<? extends MtRequestAction> clazz = target.getActionClazz();
		MtRequestAction action = null;
		try {
			action = clazz.newInstance();
			action.setRequestDto(hs8583Dto);
			Field[] fields = target.getFields();
			for (Field field : fields) {
				if(!applicationContext.containsBeanDefinition(field.getName())) continue;
				Object obj = applicationContext.getBean(field.getName());
				if (obj != null)
					field.set(action, obj);
			}

		} catch (Exception e) {
			throw new RuntimeException("instance action error : "
					+ clazz.getName(), e);
		}
		try {
			return (Hs8583Dto) target.getMethod().invoke(action);
		} catch (Exception e) {
			throw new RuntimeException("execute action method error.", e);
		}
	}


	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}