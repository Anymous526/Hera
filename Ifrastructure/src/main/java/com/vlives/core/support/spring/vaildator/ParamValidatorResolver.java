/**
 * @(#)ObjectConvertInterceptor.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring.vaildator;

import java.lang.reflect.Method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.StringUtils;

/**
 * 对基于ParamValidator注解的参数验证
 * @author  jianguo.xu
 * @version 1.0,2011-4-27
 */
public class ParamValidatorResolver implements WebArgumentResolver{
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		Method method = methodParameter.getMethod();
		ParamValidators paramVaildators = method.getAnnotation(ParamValidators.class);
		if(paramVaildators == null) return UNRESOLVED;
		validator(paramVaildators.value(),webRequest);
		return UNRESOLVED;
	}
	
	private void validator(ParamValidator[] paramVaildators,NativeWebRequest webRequest) {
		for(ParamValidator paramVaildator : paramVaildators) {
			String value = HttpParameterParser.getString(paramVaildator.param());
			String paramName = paramVaildator.paramName();
			if(existRequiredValidor(paramVaildator.vaildatorTypes())&&StringUtils.isNullOrEmpty(value))  
				throw new ValidatorException(ValidatorType.REQUIRED.getErrorMsg(paramName));
			if(StringUtils.isNullOrEmpty(value)) continue;
			for(int i = 0;i<paramVaildator.vaildatorTypes().length;i++) {
				ValidatorType vType = paramVaildator.vaildatorTypes()[i];
				if(!vType.validator(value)) {
					throw new ValidatorException(vType.getErrorMsg(paramName));
				}
			}
			validatorLength(value,paramName,paramVaildator.length());
			validatorMinAndMax(value, paramName, paramVaildator.min(), paramVaildator.max());
		}	
	}
	/**
	 * 判断是否有 required 验证类型
	 * @author jianguo.xu
	 * @param validatorTypes
	 * @return
	 */
	private boolean existRequiredValidor(ValidatorType[] validatorTypes) {
		for(ValidatorType validatorType : validatorTypes) {
			if(validatorType == ValidatorType.REQUIRED) return true;
		}
		return false;
	}
	/**
	 * 对长度进行验证
	 * @author jianguo.xu
	 * @param value
	 * @param paramName
	 * @param dataLength
	 */
	private void validatorLength(String value,String paramName,int[] dataLength) {
		if(dataLength.length == 0) return;
		if(dataLength.length == 1)  {
			if(value.length()!=dataLength[0])
				throw new ValidatorException(paramName+" 的长度必须为 "+dataLength[0]);
		}
		else {
			if(value.length()<dataLength[0]||value.length()>dataLength[1]) {
				throw new ValidatorException(paramName+" 的长度必须在  "+dataLength[0]+" 和 "+dataLength[1]+" 之间");
			}
		}
	}
	/**
	 * 对数字进行验证
	 * @author jianguo.xu
	 * @param value
	 * @param paramName
	 * @param minValue
	 * @param maxValue
	 */
	private void validatorMinAndMax(String value,String paramName,int minValue,int maxValue) {
		if(minValue==Integer.MIN_VALUE&&maxValue==Integer.MAX_VALUE) return;
		
		double intValue = 0;
		try {
			intValue = Integer.parseInt(value);
		} catch (Exception e) {
			throw new ValidatorException(paramName+" 请输入一个正确的数字");
		}
		if(minValue!=Double.MIN_NORMAL) {
			if(intValue<minValue) {
				throw new ValidatorException(paramName+" 不能小于 "+minValue);
			}
		}
		if(maxValue!=Double.MAX_EXPONENT) {
			if(intValue>maxValue) {
				throw new ValidatorException(paramName+" 不能大于 "+maxValue);
			}
		}
	}
}
