/**
 * @(#)FreeMarkerPaginationConvertor.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.pagination;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-3-11
 */
public class FreeMarkerPaginationConvertor implements PaginationConvertor{
	//private static final String PAGINATION_TEMPLATE="pagination.ftl";
	 private static final String PAGINATION_TEMPLATE="pagination_one.ftl";
	private static final String TEMPLATE_ENCODING="UTF-8";
	private static final Log LOG = LogFactory.getLog(FreeMarkerPaginationConvertor.class);
	
	private static Template template;
	static {
		template = getTemplate();
	}
	
	private static Template getTemplate() {
		URL url = FreeMarkerPaginationConvertor.class.getResource("");
		File dir = new File(url.getPath());
		Configuration freemarkerConfiguration = new Configuration();
		freemarkerConfiguration.setDefaultEncoding(TEMPLATE_ENCODING);
		try {
			 freemarkerConfiguration.setDirectoryForTemplateLoading(dir);
			 Template temp = freemarkerConfiguration.getTemplate(PAGINATION_TEMPLATE);
			 return temp;
		} catch (IOException e) {
			throw new RuntimeException("get pagination template error.",e);
		}
	}
	

	public String convert(Pagination pagination) {
		 
		 Map<String,Object> model = new HashMap<String, Object>();
		 model.put("pagination", pagination);
		 try {
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}
	
	
	/*
	public static void main(String[] args) {
	 
		
		String CURRENT_PAGE_TARGET = "pagination.currentPage";

		String PAGE_SIZE_TARGET = "pagination.pageSize";

		String SKIP_SIZE_TARGET = "pagination.skipSize";
		
	 
		FreeMarkerPaginationConvertor c = new FreeMarkerPaginationConvertor();
		Pagination pagination = new Pagination(5,1);
		pagination.setCount(100);
		pagination.setUrl("/user/questionTest.htm");
		pagination.setCurrentPageTarget(CURRENT_PAGE_TARGET);
		pagination.setPageSizeTarget(PAGE_SIZE_TARGET);
		pagination.setSkipSizeTarget(SKIP_SIZE_TARGET);
		pagination.setQueryString("uid=123&form=dd");
		String value = c.convert(pagination);
		System.out.println(value); 
	}*/
}
