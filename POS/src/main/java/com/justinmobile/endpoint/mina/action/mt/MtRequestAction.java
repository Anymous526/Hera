/**
 * @(#)MtBaseActionAction.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.action.mt;

import com.justinmobile.endpoint.mina.action.BaseAction;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;


/**
 * <br>该类为处理request的基类</br>
 * <br>所有处理request结果的Action必须实现该类</br>
 * <br>实现类中要处理request的业务方法上必须配置 MtRequestCodeAnnotation,一个类中可以有多个配置业务处理方法</br> 
 * <br>业务处理方法不能带有参数,且必须返回MtResponse</br>
 * <br>所有处理request的业务方法中MtRequestCodeAnnotation配置不能一样，否则会抛异常</br>
 * <br>子类中的MtRequestDto已经在基类中注入，如果要得到MtRequestDto的具体子类，需通过强制转换获得(需改进)</br>
 * <br>子类中需要的spring bean 可以直接获得不需要再关心bean的注入问题</br>
 * <br>业务处理方法必须返回MtResponseDto,如果实际业务逻辑没有返回结果，就返回null</br>
 * @author ThinkPad7
 *
 */
public abstract class MtRequestAction implements BaseAction{
	protected Hs8583Dto requestDto;
	 
	public Hs8583Dto getRequestDto() {
		return requestDto;
	}

	public void setRequestDto(Hs8583Dto requestDto) {
		this.requestDto = requestDto;
	}
}