package com.vlives.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-22 上午03:02:59 类说明 用于麦讯通短信信息管理
 */

public class WebServiceUtils {

	private static final String USERID = ConfigUtils.getString("MXT.USERID");// 主账户

	private static final String SUBACCOUNT = ConfigUtils.getString("MXT.SUBACCOUNT");// 子账户

	private static final String PASSWD = ConfigUtils.getString("MXT.PASSWD");// 用户密码
	/** webservice url */
	private static final String URL = ConfigUtils.getString("MXT.URL");
	/** soap action */
	private static final String soapaction = ConfigUtils.getString("MXT.SOAPACTION");
	private static final Log LOG = LogFactory.getLog(WebServiceUtils.class);

	public static void getUpLoadInfo() {

	}
 
	public static List<String> directSend(String mobiles, String content) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("UserID", USERID);
		paramMap.put("Password", PASSWD);
		paramMap.put("Account", SUBACCOUNT);
		paramMap.put("Phones", mobiles);
		paramMap.put("Content", content);
		paramMap.put("SendTime", "");
		paramMap.put("SendType", "1");
		paramMap.put("PostFixNumber", "");
		List<String> rtnV = (List<String>) accessWSDL("DirectSend", paramMap, URL);
		return rtnV;
	}

	/**
	 * WebService 的一个Base调用方法
	 * 
	 * @param methodName
	 * @param paramKeys
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> accessWSDL(String methodName, Map<String, String> paramKeys, String url) {
		Service service = new Service();
		List<String> result = new ArrayList<String>();// 返回结果
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(soapaction, methodName)); // 调用对象的方法名
			// 设置传入的参数值
			if (paramKeys != null) {
				Set<String> set = paramKeys.keySet();
				for (String param : set) {
					call.addParameter(new QName(soapaction, param), // 设置要传递的参数
							org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
				}
			}
			call.setReturnType(new QName(soapaction, methodName), Vector.class);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapaction + methodName);
			@SuppressWarnings("rawtypes")
			Vector rs = (Vector) call.invoke(paramKeys.values().toArray());// 调用方法并传递参数
			for (int i = 0; i < rs.size(); i++) {
				LOG.info("接受参数："+i+":"+rs.get(i));
				result.add((String) rs.get(i));
			}
		} catch (Exception ex) {
			LOG.error(ex);
		}
		return result;
	}
}
