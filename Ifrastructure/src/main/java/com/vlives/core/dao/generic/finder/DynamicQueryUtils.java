 
package com.vlives.core.dao.generic.finder;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class DynamicQueryUtils {

	private static final Pattern FIELD_PATTERN = Pattern.compile(":\\w+");
	private static final Pattern REPLACE_PATTERN = Pattern.compile("\\?\\w+");
	/**
	 * 根据 parameters 的参数与dynamicString中{}内的查询条件进行匹配
	 * 对于匹配不到的就删除该{}以及{}内的数据 
	 * 最终结果为parameters中的参数与{}内的命名参数一一对应
	 * eg. dynamicString = eg: from Customer c where 1=1 {and c.name=:name} {and c.age >:age} {and c.address =:address};
	 *   parameters.put("name",name);parameters.put("age",age);
	 *   return : from Customer c where 1=1 and c.name=:name and c.age >:age;
	 * @author jianguo.xu
	 * @param dynamicString 
	 * @param parameters 
	 * @return 
	 */
	public static String parse(String dynamicString, Map<String,Object> parameters) {
		if(parameters == null) parameters = new HashMap<String, Object>();
		if (dynamicString == null||dynamicString.length()==0)
			return null;
		dynamicString = dynamicString.trim();
		StringBuffer main = new StringBuffer();
		StringBuffer condition = new StringBuffer();
		boolean isCondition = false;
		for (int i = 0; i < dynamicString.length(); i++) {
			char c = dynamicString.charAt(i);
			if (c != '{' && c != '}' && !isCondition) {
				main.append(c);
			} else if (c == '{') {
				isCondition = true;
			} else if (c != '{' && c != '}' && isCondition) {
				condition.append(c);
			} else if (c == '}') {
				 
				isCondition = false;
				String conditionString = condition.toString();
				condition.delete(0, condition.length());
				Matcher filedMatcher = FIELD_PATTERN.matcher(conditionString);
				boolean macthed = true;
				while (filedMatcher.find()) {
					String filedGroup = filedMatcher.group();
					String filed = filedGroup.substring(1);
					if (parameters.get(filed) == null) {
						macthed = false;
						break;
					}
				}
				if (macthed) {
					Matcher replaceMatcher = REPLACE_PATTERN
							.matcher(conditionString);
					while (replaceMatcher.find()) {
						String replaceGroup = replaceMatcher.group();
						String replace = replaceGroup.substring(1);
						Object parameter=parameters.get(replace);
						if (parameter == null) {
							macthed = false;
							break;
						}else
							conditionString=conditionString.replaceAll("\\?"+replace, parameter.toString());
					}
				}
				if (macthed)
					main.append(conditionString);
			}
		}	 
		return main.toString().trim().replaceAll("\\s+", " ");
	}
}

