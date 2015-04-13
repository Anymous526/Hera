/*
 * @(#)XlsView.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.core.support.spring.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-5-17
 */
public class XlsView extends AbstractView{
	
	private String resFileName;
	private String destFileName;
	private Map<String,Object> map ;
	
	private static final String contentType = "application/vnd.ms-excel;charset=UTF-8";
	private final static String resPath = "/template/";
	private final static String suffix = ".xls";
	
	/**
	 * 
	 * @param resFileName 模板文件名
	 * @param map 填充的数据
	 */
	public XlsView(String resFileName,Map<String,Object> map){
		this.resFileName = resFileName;
		this.destFileName = String.valueOf(System.currentTimeMillis()+suffix);
		this.map = map;
	}
	
	/**
	 * 
	 * @param  模板文件名
	 * @param map 填充的数据
	 * @param destFileName 保存的文条件名
	 */
	public XlsView(String resFileName,Map<String,Object> map,String destFileName){
		this.resFileName = resFileName;
		this.destFileName = destFileName + suffix;
		this.map = map;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String urlPath = resPath + resFileName ;
		URL url =  XlsView.class.getResource(urlPath);
		String path = url.getPath();
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(destFileName.getBytes("gbk"),"ISO8859-1") + "\"");
		InputStream in = null;
		OutputStream out = null;
		HSSFWorkbook workbook;
		try {
			in = new FileInputStream(new File(path));
			XLSTransformer transformer = new XLSTransformer();
			workbook = transformer.transformXLS(in, map);
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
			if(out != null)
				out.close();
		}
	}
}

