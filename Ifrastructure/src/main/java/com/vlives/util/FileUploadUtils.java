/**
 * @(#)FileUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-3-17
 */
public class FileUploadUtils {
	private static final Log LOG = LogFactory.getLog(FileUploadUtils.class);
	private static final String DEFALUT_SUFFIX = "temp";
	private static final String FILE_SEPARATOR = "/";
	/**
	 * 上传文件的最大文件字节数(单位k)
	 */
	private static final long DEFAULT_MAX_SIZE=2048;
	
	/**
	 * 上传文件,为保证文件的上传成功，用时间戳作为文件名<br/>
	 * 如果文件名不存在，则返回null
	 * @author jianguo.xu
	 * @param request http request 请求
	 * @param fileParamter 上传的文件请求参数
	 * @param 上传文件允许的最大字节数(单位k)
	 * @param 上传文件允许的后缀名
	 * @return 返回相对于web工程的绝对路径+文件名
	 * @throws IOException 
	 */
	public static String uploadImage(HttpServletRequest request,String fileParamter,long maxSize,String[] includesuffixs) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);		
		String filename = multipartFile.getOriginalFilename();
		if(StringUtils.isNullOrEmpty(filename)) return null;		
		String[] split = filename.split("\\.");
		String suffix = split.length == 2?split[1]:DEFALUT_SUFFIX;
		return uploadImage(request,fileParamter,ConfigUtils.UPLOAD_IMAGE_PATH,suffix,maxSize,includesuffixs);
	}
	
	/**
	 * 上传文件,为保证文件的上传成功，用时间戳作为文件名
	 * @author jianguo.xu
	 * @param request http request 请求
	 * @param fileParamter 上传的文件请求参数
	 * @return 返回相对于web工程的绝对路径+文件名
	 * @throws IOException 
	 */
	public static String uploadImage(HttpServletRequest request,String fileParamter) throws IOException {
		return uploadImage(request,fileParamter,DEFAULT_MAX_SIZE,null);
	}
	
	private static String getUploadPath(String uploadPath) {
		 String webRoot = ConfigUtils.WEB_ROOT.endsWith(FILE_SEPARATOR)?ConfigUtils.WEB_ROOT:ConfigUtils.WEB_ROOT+"/";
		 uploadPath = uploadPath.startsWith(FILE_SEPARATOR)?uploadPath.substring(1):uploadPath;
		 String path = webRoot+uploadPath;
		String today = DateUtils.format(new Date(), "yyyyMMdd");
		path = path.endsWith(FILE_SEPARATOR)?path+today:path+FILE_SEPARATOR+today+"/";
		initDir(path);
		return path;
	}
	
	private static void initDir(String uploadPath) {
		File directory = new File(uploadPath);
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}
	}
	/**
	 * 上传文件,为保证文件的上传成功，用时间戳作为文件名
	 * @author jianguo.xu
	 * @param request http request 请求
	 * @param fileParamter 上传的文件请求参数
	 * @param uploadPath   相对对web工程的上传路径
	 * @param suffix		文件后缀名
	 * @return 返回相对于web工程的绝对路径+文件名
	 * @throws IOException 
	 */
	public static String uploadImage(HttpServletRequest request,String fileParamter,String uploadPath,String suffix,long maxSize,String[] includesuffixs) throws IOException{
		uploadPath = getUploadPath(uploadPath);
		String filePrefix = new Long(System.currentTimeMillis()).toString();
		String fileName = uploadPath.endsWith(FILE_SEPARATOR)?uploadPath+filePrefix:uploadPath+FILE_SEPARATOR+filePrefix;
		fileName = fileName+"."+suffix;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);
		assertCanUpload(multipartFile,suffix,maxSize,includesuffixs);
		File destFile = new File(fileName.toString());
		try {
			multipartFile.transferTo(destFile);
		} catch (Exception e) {
			LOG.error("upload image error", e);
			return null;
		}
		String returnFileName = fileName.substring(ConfigUtils.WEB_ROOT.length());
		return returnFileName.startsWith(FILE_SEPARATOR)?returnFileName:"/"+returnFileName;
	}
	
	private static void assertCanUpload(MultipartFile multipartFile,String suffix,long maxSize,String[] includesuffixs) throws IOException {
		if(multipartFile == null||multipartFile.getSize() == 0) {
			throw new IOException("上传文件不存在");
		}
		if(multipartFile.getSize()>maxSize*1024){
			throw new IOException("上传文件不能超过  "+maxSize+" K");
		}
		if(includesuffixs ==null||includesuffixs.length == 0) return;
		boolean eixstSuffix = false;
		for(String includesuffix : includesuffixs) {
			if(includesuffix.toLowerCase().equals(suffix.toLowerCase())) {
				eixstSuffix = true;
				break;
			}
		}
		if(!eixstSuffix) {
			throw new IOException("上传的文件格式不正确");
		}
	}
	
	/**
	 * 获得输入流
	 * @param request
	 * @param fileParamter
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(HttpServletRequest request,String fileParamter) throws IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);
		if(multipartFile != null)
			return multipartFile.getInputStream();
		return null;
	}
	
}
