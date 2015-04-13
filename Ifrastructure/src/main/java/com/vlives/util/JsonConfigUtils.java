package com.vlives.util;


import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.vlives.core.support.spring.anno.InitMethod;

public class JsonConfigUtils {
	private static final Log LOG = LogFactory.getLog(JsonConfigUtils.class);
	private static final String FILE_PATH = "/json/jsonConfig.xml";
	/**
	 * JSON 配置文件map映射
	 * key:json资源文件
	 * value:最后修改时间
	 */
	private static Map<File, Long> resourcesMap;
	private static Map<String, JsonConfig> jsonMap ;
	/**
	 * 初始化json配置
	 * @author jianguo.xu
	 */
	@InitMethod(isStatic=true)
	public static void initJsonConfig() {
		jsonMap = new HashMap<String, JsonConfig>();
		Set<File> resources = getResources();
		for(File resource : resources) {
			parserJsonItem(resource,jsonMap,true);
		}
		createResourcesMap(resources);
		LOG.info("init json config success.");
	}
	
	private static void createResourcesMap(Set<File> resources) {
		resourcesMap = new HashMap<File, Long>();
		for(File resource : resources) {
			resourcesMap.put(resource, resource.lastModified());
		}
	}
	
	/**
	 * 解析json xml
	 * @author jianguo.xu
	 * @param resource json 文件
	 * @param jsonMap
	 * @param checkUnique  是否检查json配置项的明细
	 */
	private static void parserJsonItem(File resource,Map<String, JsonConfig> jsonMap,boolean checkUnique) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(resource);
		} catch (DocumentException e) {
			LOG.error("not find the xml file,the path is error.",e);
			return;
		}
		Element root = doc.getRootElement();
		if (root != null) {
			for (Iterator<?> i = root.elementIterator("item"); i.hasNext();) {
				Element ItemNote = (Element) i.next();
				String name = ItemNote.attributeValue("name").trim();
				JsonConfig json = getParamNote(ItemNote);
				putJson(name,json,jsonMap,checkUnique);
			}
		}
	}
	
	private static void putJson(String name,JsonConfig json,Map<String, JsonConfig> jsonMap,boolean checkUnique) {
		if (StringUtils.isBlank(name)) return;
		if(checkUnique&&jsonMap.get(name)!=null)
			throw new RuntimeException("json config name is not unique : "+name);
		jsonMap.put(name, json);
		 
	}
	
	private static File loadRootJsonConfig() {
		try {
			String path = JsonConfigUtils.class.getResource(FILE_PATH).getPath();
			return new File(path);
		}
		catch (NullPointerException e) {
			LOG.warn(FILE_PATH+" not found.");
			return null;
		}
		
		
	}
	
	private static Set<File> getResources() {
		Set<File> resources = new HashSet<File>();
		File rootFile = loadRootJsonConfig();
		if(rootFile == null) return resources;
		resources.add(rootFile);
		SAXReader reader = new SAXReader();		 
		Document doc = null;
		try {
			doc = reader.read(rootFile);
		} catch (DocumentException e) {
			LOG.error("not find the xml file,the path is error.",e);
		}
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> importElements = root.selectNodes("/json/import");
		if(importElements == null||importElements.size() == 0) return resources;
		for(Element element : importElements) {
			String resource = element.attributeValue("resource");
			String itemAbsPath = JsonConfigUtils.class.getResource(resource).getPath();
			resources.add(new File(itemAbsPath));
		}
		return resources;
	}

	private static JsonConfig getParamNote(Element ItemNote) {
		Map<String, String> ParamMap = new HashMap<String, String>();
		for (Iterator<?> i = ItemNote.elementIterator("param"); i.hasNext();) {
			Element ParamNote = (Element) i.next();
			String name = ParamNote.attributeValue("name").trim();
			if (StringUtils.isBlank(name)) {
				continue;
			}
			String value = ParamNote.getTextTrim().trim();
			ParamMap.put(name, value);
		}
		JsonConfig json = new JsonConfig();
		if(ParamMap.get("includeProperties")!=null) {
			json.setIncludeProperties(ParamMap.get("includeProperties").trim());
		}
		if(ParamMap.get("excludeProperties")!=null) {
			json.setExcludeProperties(ParamMap.get("excludeProperties").trim());
		}
		if(ParamMap.get("ignoreHierarchy")!=null) {
			if (StringUtils.equals(ParamMap.get("ignoreHierarchy").trim(), "false")) {
				json.setIgnoreHierarchy(false);
			}
		}
		if(ParamMap.get("enumAsBean")!=null) {
			if (StringUtils.equals(ParamMap.get("enumAsBean").trim(), "false")) {
				json.setEnumAsBean(false);
			}
		}
		if(ParamMap.get("excludeNullProperties")!=null) {
			if (StringUtils.equals(ParamMap.get("excludeNullProperties").trim(), "true")) {
				json.setIgnoreHierarchy(true);
			}
		}
		return json;

	}
    /**
     * 根据key取得对象
     * @param key
     * @return
     */
	public static JsonConfig getConfig(String key) {
		reloadJsonConfig();
		return jsonMap.get(key.trim());
	}
	/**
	 * 如果是开发模式则重新加载
	 * @author jianguo.xu
	 */
	private static void reloadJsonConfig() {
		if(!ConfigUtils.IS_DEVELOPMENT_MODE)
			return;
		for(File file : resourcesMap.keySet()) {
			long oldModifiedTime = resourcesMap.get(file);
			long currentModifiedTime =  file.lastModified();
			if(oldModifiedTime==currentModifiedTime)
				continue;
			LOG.info("reload json config : "+file.getName());
			parserJsonItem(file, jsonMap, false);
			resourcesMap.put(file, currentModifiedTime);
		}
	}
	public static class JsonConfig {
		/** 包括属性 */
		private String includeProperties;
		/** 排除属性 */
		private String excludeProperties;
		/** 排除属性 */
		private boolean ignoreHierarchy;
		/** 排除属性 */
		private boolean enumAsBean;
		/** 排除null属性 */
		private boolean excludeNullProperties;

		public JsonConfig() {
			this.ignoreHierarchy = true;
			this.enumAsBean = true;
			this.excludeNullProperties = false;

		}

		public String getIncludeProperties() {
			return includeProperties;
		}

		@Override
		public String toString() {
			return "JsonConfig [includeProperties=" + includeProperties + ", excludeProperties=" + excludeProperties
					+ ", ignoreHierarchy=" + ignoreHierarchy + ", enumAsBean=" + enumAsBean + ", excludeNullProperties="
					+ excludeNullProperties + "]";
		}

		public boolean isExcludeNullProperties() {
			return excludeNullProperties;
		}

		public void setExcludeNullProperties(boolean excludeNullProperties) {
			this.excludeNullProperties = excludeNullProperties;
		}

		public void setIncludeProperties(String includeProperties) {
			this.includeProperties = includeProperties;
		}

		public String getExcludeProperties() {
			return excludeProperties;
		}

		public void setExcludeProperties(String excludeProperties) {
			this.excludeProperties = excludeProperties;
		}

		public boolean isIgnoreHierarchy() {
			return ignoreHierarchy;
		}

		public void setIgnoreHierarchy(boolean ignoreHierarchy) {
			this.ignoreHierarchy = ignoreHierarchy;
		}

		public boolean isEnumAsBean() {
			return enumAsBean;
		}

		public void setEnumAsBean(boolean enumAsBean) {
			this.enumAsBean = enumAsBean;
		}
	}
}
