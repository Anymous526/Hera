/**
 * @(#)ObjectConvertInterceptor.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.web.interceptor;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.boss.operator.manager.OperatorLogManager;
import com.vlives.boss.user.domain.User;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.pagination.PaginationParser;
import com.vlives.core.security.IdentityValidator;
import com.vlives.core.security.Principal;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-4-27
 */
public class CustomObjectConvertResolver implements WebArgumentResolver{
	@Autowired
	private OperatorLogManager operatorLogManager;
	private static final Log LOG = LogFactory.getLog(CustomObjectConvertResolver.class);
	@Resource(name="operatorIdentityValidator")
	private IdentityValidator operatorIdentityValidator;
	@Resource(name="userIdentityValidator")
	private IdentityValidator userIdentityValidator;
	
	private static final String CURRENT_PAGE_TARGET = "pagination.currentPage";

	private static final String PAGE_SIZE_TARGET = "pagination.pageSize";

	private static final String SKIP_SIZE_TARGET = "pagination.skipSize";
	
	private static final String BEGIN_TARGET="pagination.begin";
	
	
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		Object[] paramAnnos = methodParameter.getParameterAnnotations();
		if(paramAnnos == null||paramAnnos.length == 0) return UNRESOLVED;
		for(Object anno : paramAnnos) {
			if(!ObjectConvertAnno.class.isInstance(anno))continue;
			ObjectConvertAnno objectConvertAnno = (ObjectConvertAnno) anno;
			Object object = execute( methodParameter, webRequest);
			if(objectConvertAnno.required()&&object == null) {
				throw new IllegalStateException("Missing parameter '" + methodParameter.getParameterName() + "' of type [" + methodParameter.getParameterType().getName()+ "]");
			}
			return object;
		}
		return UNRESOLVED;
	}
	
	public Object execute(MethodParameter methodParameter,NativeWebRequest webRequest) {
		Class<?> paramType = methodParameter.getParameterType();
		if(paramType.equals(User.class)) {
			return paraserUser();
		}
		if(paramType.equals(Operator.class)) {
			return paraserOperator(methodParameter);
		}
		if(paramType.equals(Pagination.class)) {
			return paraserPagination(webRequest);
		}
		return null;
	}
	/**
	 * parser user
	 * @author jianguo.xu
	 * @return
	 */
	private User paraserUser() {
		Principal principal = userIdentityValidator.currentPrincipal();
		User user = (User) principal;
		return user;
	}
	
	/**
	 * create Operator object
	 * @author jianguo.xu
	 * @return
	 */
	private Operator paraserOperator(MethodParameter methodParameter) {
		Principal principal = operatorIdentityValidator.currentPrincipal();
		Operator operaotr = (Operator) principal;
		createOperatorLog(operaotr, methodParameter);
		return operaotr;
	}
	 
	/**
	 * create Pagination object
	 * @author jianguo.xu
	 * @param webRequest
	 * @return
	 */
	private Pagination paraserPagination(NativeWebRequest webRequest) {
		PaginationParser paginationParser = new PaginationParser((HttpServletRequest) webRequest.getNativeRequest(),(HttpServletResponse) webRequest.getNativeResponse());
		paginationParser.setCurrentPageTarget(CURRENT_PAGE_TARGET);
		paginationParser.setPageSizeTarget(PAGE_SIZE_TARGET);
		paginationParser.setSkipSizeTarget(SKIP_SIZE_TARGET);
		paginationParser.setBeginTarget(BEGIN_TARGET);
		try {
			return paginationParser.parse();
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
			return null;
		}
	}
	/**
	 * 创建管理员操作日志
	 * @author jianguo.xu
	 * @param operator
	 * @param methodParameter
	 */
	private void createOperatorLog(Operator operator,MethodParameter methodParameter){
		if(operator == null) return ;
		Method method = methodParameter.getMethod();
		OperatorLogAnno operatorLogAnno = method.getAnnotation(OperatorLogAnno.class);
		if(operatorLogAnno == null) return;
		OperatorType operatorType = operatorLogAnno.value();
		operatorLogManager.createLog(operator, operatorType);
	}
}
