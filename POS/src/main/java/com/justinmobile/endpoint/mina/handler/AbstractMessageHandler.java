package com.justinmobile.endpoint.mina.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.request.MtRequestCodeAnnotation;


public abstract class AbstractMessageHandler {

	protected Map<MtRequestCode, Method> messageMethodMap = new HashMap<MtRequestCode, Method>();

	protected AbstractMessageHandler() {
		initMessageHandler();
	}
	
	private void initMessageHandler() {
		Class<?> serviceClass = this.getClass();
		Method[] methods = serviceClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if(methods[i].isAnnotationPresent(MtRequestCodeAnnotation.class)) {
				MtRequestCodeAnnotation messageHandler = methods[i].getAnnotation(MtRequestCodeAnnotation.class);
				MtRequestCode messageCode = messageHandler.value();
				this.messageMethodMap.put(messageCode, methods[i]);
			}
		}
	}

	/**
	 * 根据请求DTO返回类型创建Response
	 * 
	 * @param code
	 * @return
	 */
	protected Hs8583Dto createResponseFromReq(Hs8583Dto req) {
		MtRequestCode  resp=req.getRequestCode().getRespEnum();
		String responseCode = resp.getCode();
		req.setBm0MessageType(responseCode.substring(0, 4));
		req.setBm3TradeCode(responseCode.substring(4, 10));
		return req;
	}
	
	



}
