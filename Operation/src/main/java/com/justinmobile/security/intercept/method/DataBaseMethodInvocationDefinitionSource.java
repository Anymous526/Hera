package com.justinmobile.security.intercept.method;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.GrantedAuthority;

import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.resourcedetails.ResourceDetails;

public class DataBaseMethodInvocationDefinitionSource extends
		AbstractMethodDefinitionSource {

	private SecurityCacheManager securityCacheManager;

	@SuppressWarnings("unchecked")
	public ConfigAttributeDefinition lookupAttributes(Method method, Class targetClass) {
		
		//初始化资源并缓存
		securityCacheManager.initResourceInCache();

		//获取所有方法资源
		List<String> methods  = securityCacheManager.getMethodResources();

		//权限集合
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

		//遍历方法资源，并获取匹配的资源名称，然后从缓存中获取匹配正确
		//的资源对应的权限（ResourcDetail对象的GrantedAuthority[]对象数据）
		for (String resourceName_method : methods) {
			System.out.println(">>>>>>>>>>>>>>>" + resourceName_method);
			if (isMatch(targetClass, method, resourceName_method)) {
				ResourceDetails detail = securityCacheManager.getResourcDetailFromCache(resourceName_method);
				if (detail == null) {
					break;
				}
				GrantedAuthority[] authorities = detail.getAuthorities();
				if (authorities == null || authorities.length == 0) {
					break;
				}
				authSet.addAll(Arrays.asList(authorities));
			}
		}
		if (authSet.size() > 0) {
			String authString = "";
			for (GrantedAuthority grantedAuthority : authSet) {
				authString += grantedAuthority.getAuthority() + ",";
			}
			String authority = authString.substring(0, (authString.length() - 1));
			ConfigAttributeEditor attributeEditor = new ConfigAttributeEditor();
			attributeEditor.setAsText(authority.trim());
			return (ConfigAttributeDefinition)attributeEditor.getValue();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static boolean isMatch(Class clszz, Method method, String resourceMethod) {
		boolean isMatch = true;
		try {
			int lastDotIndex = resourceMethod.lastIndexOf('.');
			String className = resourceMethod.substring(0, lastDotIndex);
			String methodName = resourceMethod.substring(lastDotIndex + 1);

			// 判断类是否相等
			if (!clszz.getName().equals(className))
				isMatch = false;

			// 判断接口是否相等
			Class[] interfaces = clszz.getInterfaces();
			for (int i = 0; i < interfaces.length; i++) {
				Class inf = interfaces[i];
				if (inf.getName().equals(className)) {
					isMatch = true;
				}
			}

			// 判断方法是否相等
			if (isMatch
					&& !(method.getName().equals(methodName)
					|| (methodName.endsWith("*") && method.getName().startsWith(
					methodName.substring(0, methodName.length() - 1))) || (methodName.startsWith("*") && method
					.getName().endsWith(methodName.substring(1, methodName.length())))))
				isMatch = false;

		} catch (Exception e) {
			isMatch = false;
		}
		return isMatch;
	}

	@SuppressWarnings("unchecked")
	public Collection getConfigAttributeDefinitions() {
		return null;
	}

	public SecurityCacheManager getSecurityCacheManager() {
		return securityCacheManager;
	}

	public void setSecurityCacheManager(SecurityCacheManager securityCacheManager) {
		this.securityCacheManager = securityCacheManager;
	}

}
