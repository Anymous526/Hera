package com.justinmobile.bmp.pos.mina.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.justinmobile.bmp.pos.mina.request.PosRequestCode;
import com.justinmobile.bmp.pos.mina.request.PosRequestCodeAnnotation;

public class AbstractMessageHandler {

 
	protected Map<PosRequestCode, Method> messageMethodMap = new HashMap<PosRequestCode, Method>();

	protected AbstractMessageHandler() {
		initMessageHandler();
	}
	
	private void initMessageHandler() {
		Class<?> serviceClass = this.getClass();
		Method[] methods = serviceClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if(methods[i].isAnnotationPresent(PosRequestCodeAnnotation.class)) {
				PosRequestCodeAnnotation messageHandler = methods[i].getAnnotation(PosRequestCodeAnnotation.class);
				PosRequestCode messageCode = messageHandler.value();
				this.messageMethodMap.put(messageCode, methods[i]);
			}
		}
	}

	
	
	




}
