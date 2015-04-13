package com.vlives.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;

public class SmsTemplateUtils {

	private static ResourceBundle res = null;

	private static Properties p;

	private static final String PROPERTIES_File = "base/conf/smsTemplate_zh_CN";
	static {
		res = ResourceBundle.getBundle(PROPERTIES_File);
		p = MapUtils.toProperties(MapUtils.toMap(res));
	}

	/**
	 * 简单得到properties文件中key对应的value，没找到key时返回null
	 * 
	 * @param key
	 * @return
	 */
	private static String getStringMessage(String key) {
		if (p == null) {
			return res.getString(key);
		}
		return p.getProperty(key, null);
	}

	/**
	 * 替换Template字符串中{0}
	 * 
	 * @param key
	 *            传到Properties文件key
	 * @param params
	 * @return
	 */
	public static String getStringMessage(String key, Object[] params) {
		String template = getStringMessage(key);
		String message = MessageFormat.format(template, (Object[]) params);
		return message;
	}

	/**
	 * 替换Template字符串中{0}
	 * 
	 * @param desc
	 *            指模板字符串
	 * @param params
	 * @return
	 */
	public static String getDescMessage(String desc, Object[] params) {
		String message = MessageFormat.format(desc, (Object[]) params);
		return message;
	}

	/**
	 * 替换Template字符串中{id} 采用Map传递参数
	 * 
	 * @param key
	 *            传到Properties文件key
	 * @param params
	 * @return
	 */
	public static String getStringMessage(String key, Map<String, Object> params) {
		String template = getStringMessage(key);
		String message = format(template, params);
		return message;
	}

	/**
	 * 替换Template字符串中{id} 采用Map传递参数 (实现原理：利用正则找到{id}出现位置,并把字符串{id}替换{0},
	 * 和把Map的value放到ArrayList的0的index索引位置。所有{}替换完成再利用MessageFormat)
	 * 
	 * @param temp
	 * @param params
	 * @return
	 */
	public static String format(String temp, Map<String, Object> pramaMap) {

		String regex = "''|'.*?'|\\{(.*?)(,.*?)?\\}|[^\\{\\}]+";
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer buff = new StringBuffer();

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(temp);
		String paramName, paramSets;
		Object paramValue;
		int paramIdx = 0;

		while (m.find()) {
			if (m.group(1) == null) {
				buff.append(m.group());
			} else {
				paramName = m.group(1);
				paramValue = pramaMap.get(paramName.trim());
				paramSets = m.group(2) != null ? m.group(2) : "";

				paramList.add(paramValue);
				buff.append("{" + paramIdx + paramSets + "}");
				paramIdx++;
			}
		}

		return MessageFormat.format(buff.toString(), paramList.toArray());
	}
	
	
	/**
	 * 注册发送短信模板
	 */
	public static final String SMS_LOGIN_VERIFY_TEMPLATE = getStringMessage("sms.verify.template");
	
	
	
    /**
     * 把手机号码变成 159****7844
     * @param mobileNo
     * @return
     */
	public static String getPartMobileNo(String mobileNo) {
		return mobileNo.substring(0, 3) + "****" + mobileNo.substring(7);
	}
	
	/**
	 * 商户码错误
	 * @param mobileNo
	 * @return
	 */
	public static  String getMerchantNum(String mobileNo){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("partMobileNo", getPartMobileNo(mobileNo));
		return getStringMessage("sms_mer_num_error",params);
	}
	
	/**
	 * 注册成功
	 * @param mobileNo
	 * @param merchantShort
	 * @param memberLevel
	 * @return
	 */
	public static  String getRegSuccess(String mobileNo,String merchantShort,String memberLevel){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("partMobileNo", getPartMobileNo(mobileNo));
		params.put("MerShortName", merchantShort);
		params.put("memberLevel", memberLevel);
		return getStringMessage("sms_register_success",params);
	}
	
	
	/**
	 * 会员已在
	 * @param mobileNo
	 * @param merchantShort
	 * @param memberLevel
	 * @return
	 */
	public static  String getMemberExisted(String mobileNo,String merchantShort,String memberLevel){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("partMobileNo", getPartMobileNo(mobileNo));
		params.put("MerShortName", merchantShort);
		params.put("memberLevel", memberLevel);
		return getStringMessage("sms_member_exist_error",params);
	}
	
	
	
	/**
	 * 会员已在
	 * @param mobileNo
	 * @param merchantShort
	 * @param memberLevel
	 * @return
	 */
	public static  String getSmsCommand(String mobileNo){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("partMobileNo", getPartMobileNo(mobileNo));
		return getStringMessage("sms_command_error",params);
	}
	
	public static  String getRegFail(String mobileNo,String merchantShort){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("partMobileNo", getPartMobileNo(mobileNo));
		params.put("MerShortName", merchantShort);
		return getStringMessage("sms_command_error",params);
	}
}
