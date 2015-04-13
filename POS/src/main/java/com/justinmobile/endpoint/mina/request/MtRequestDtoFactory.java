/**
 * @(#)RequestDtoFactory.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.Modifier;

import com.justinmobile.endpoint.mina.TransferProtocol;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.service.PosServiceException;
import com.justinmobile.util.ClassUtils;


/**
 * @author ThinkPad7
 *
 */
public class MtRequestDtoFactory {
	private MtRequestDtoFactory(){}
	
	
	private static final Map<MtRequestCode, Class<? extends Hs8583Dto>> map;
	static {
		map = getRequestDtoMap();
	}
	
	private static Map<MtRequestCode, Class<? extends Hs8583Dto>> getRequestDtoMap() {
		 Map<MtRequestCode, Class<? extends Hs8583Dto>> map = new HashMap<MtRequestCode, Class<? extends Hs8583Dto>>();
		 List<Class<? extends Hs8583Dto>> classes = ClassUtils.getClassPathClasses(ClassUtils.JUSTINMOBILE_PACKAGE, true,Hs8583Dto.class);
		 for(Class<? extends Hs8583Dto> clazz : classes) {
			 addRequestDto(map,clazz);
		 }
		 return map;
	}
	private static void addRequestDto(Map<MtRequestCode, Class<? extends Hs8583Dto>> map,Class<? extends Hs8583Dto> clazz) {
		int mod =  clazz.getModifiers();
		if(Modifier.isAbstract(mod)) return;
		MtRequestCodeAnnotation annotation = clazz.getAnnotation(MtRequestCodeAnnotation.class);
		assertTrue(map,clazz,annotation);
		MtRequestCode requestCode = annotation.value();
		map.put(requestCode, clazz);
	}
	
	
	private static void assertTrue(Map<MtRequestCode, Class<? extends Hs8583Dto>> map, Class<? extends Hs8583Dto> clazz,MtRequestCodeAnnotation annotation) {
		if(annotation == null) {
			throw new RuntimeException(
					"Hs8583Dto  MtRequestCodeAnnotation is not definition: "
							+ clazz.getName());
		}
		MtRequestCode requestCode = annotation.value();
		if(map.get(requestCode)!=null) 
			throw new RuntimeException("Hs8583Dto request code definition is not unique : "+requestCode.getCode()+
					" class : "+clazz.getName());
	}
	
	
	/**
	 * 根据 请求结果、传输协议、请求业务编码创建 Hs8583Dto
	 * 同时解析该dto
	 * @author 
	 * @param message
	 * @param protocol
	 * @param requestCode
	 * @return
	 * @throws PosServiceException 
	 */
	public Hs8583Dto createRequestDto(Object message,TransferProtocol protocol) throws PosServiceException {
		Hs8583Dto requestDto =createRequestDto();
		
		requestDto.decode(message,protocol);
		//requestDto.setRequestCode(requestDto.getBm0()+requestDto.getBm3()); 
		return requestDto;
	}
	private Hs8583Dto createRequestDto() {
		Class<? extends Hs8583Dto> clazz =  Hs8583Dto.class;
		try {
			return  clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("instance requetDto error : "+clazz.getName(), e);
		}
	}
	
	private Hs8583Dto createRequestDto(MtRequestCode requestCode) {
		Class<? extends Hs8583Dto> clazz = map.get(requestCode);
		try {
			return  clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("instance requetDto error : "+clazz.getName(), e);
		}
	}
}
