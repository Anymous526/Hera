 
package com.vlives.core.dao.generic.finder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 按对象的属性作为查询器
 * @author  jianguo.xu
 * @version 1.0,2010-9-15
 */
public class PropertiesFinder extends ParametersFinder {

	private Class<?> type;

	private List<Compositor> compositors;
	private Map<String,Comparator> comparators;
	private String appendString;

	public PropertiesFinder(Class<?> type) {
		this(type, null);
	}

	public PropertiesFinder(Class<?> type, Map<String,Object> parameters) {
		super(parameters);
		this.type = type;
	}

	public PropertiesFinder(Class<?> type, String propertyName,
			Object propertyValue) {
		this(type, null);
		add(propertyName, propertyValue);
	}
	
	/**
	 * 添加一个参数
	 * @param name	参数名
	 * @param value	参数值
	 * @param comparator	比较器
	 */
	public void add(String name,Object value,Comparator comparator){
		if(comparators==null)
			comparators=new HashMap<String,Comparator>();
		comparators.put(name, comparator);
		add(name,value);
	}
	

	/**
	 * 移除一个参数
	 * @param name	参数名
	 */
	public void remove(String name){
		if(getParameters()!=null)
			getParameters().remove(name);
		if(comparators!=null)
			comparators.remove(name);
	}
	
	/**
	 * 设置排序器
	 * @author jianguo.xu
	 * @param compositors
	 */
	public void setCompositors(List<Compositor> compositors) {
		this.compositors = compositors;
	}
	/**
	 * 添加排序器
	 * @author jianguo.xu
	 * @param compositor
	 */
	public void addCompositor(Compositor compositor) {
		if(compositors ==null) compositors = new ArrayList<Compositor>();
		compositors.add(compositor);
	}

	/**
	 * 设置追加到查询字符串最后的字符串
	 * 
	 * @param appendString
	 */
	public void setAppendString(String appendString) {
		this.appendString = appendString;
	}

	 

 
	public String genQueryString() {
		StringBuffer buffer = new StringBuffer("from ").append(type.getName())
				.append(" as model where 1=1");
		Map<String,Object> parameters = getParameters();
	 
		if (parameters != null) {
			/*Map<String,Object> newParameters = null;
			Iterator<String> it = parameters.keySet().iterator();
			while (it.hasNext()) {
				String name = it.next();
				Object value = parameters.get(name);
				boolean isRange = value instanceof Range;
				short comparator = isRange ? Finder.GTEQ_LSEQ : Finder.EQ;
				boolean isCollection = value instanceof Collection
						|| value instanceof Object[];
				if (isCollection)
					comparator = Finder.IN;
				if (comparators != null) {
					Short comparator0 = (Short) comparators.get(name);
					if (comparator0 != null)
						comparator = comparator0.shortValue();
				}
				if (isRange) {
					Range range = (Range) value;
					Object min = range.getMin();
					Object max = range.getMax();
					if (min == null && max == null) {
						continue;
					}
					if (min != null) {
						String key = "__min"
								+ name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						String symbol = (comparator == Finder.GTEQ_LSEQ || comparator == Finder.GTEQ_LS) ? ">="
								: ">";
						buffer.append(" and model.").append(name).append(" ")
								.append(symbol).append(" ").append(":").append(
										key);
						if (newParameters == null)
							newParameters = new HashMap<String,Object>();
						newParameters.put(key, min);
					}
					if (max != null) {
						String key = "__max"
								+ name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						String symbol = (comparator == Finder.GTEQ_LSEQ || comparator == Finder.GT_LSEQ) ? "<="
								: "<";
						buffer.append(" and model.").append(name).append(" ")
								.append(symbol).append(" ").append(":").append(
										key);
						if (newParameters == null)
							newParameters = new HashMap<String,Object>();
						newParameters.put(key, max);
					}
				} else if (isCollection) {
					buffer.append(" and model.").append(name).append(" ")
							.append(ComparatorUtils.symbol(comparator)).append(
									" (").append(":").append(name).append(")");
				} else if (value != null) {
					buffer.append(" and model.").append(name).append(" ")
							.append(ComparatorUtils.symbol(comparator)).append(
									" ").append(":").append(name);
				}
			}
			if (newParameters != null) {
				parameters.putAll(newParameters);
			}*/
			
			parserParameter(parameters,buffer);
		}
		addCompositorRole(buffer);
		if (appendString!=null&&appendString.length()>0)
			buffer.append(" ").append(appendString);
		return buffer.toString();
	}
	
	private void parserParameter(Map<String,Object> parameters,StringBuffer buffer) {
		Map<String,Object> newParameters = new HashMap<String, Object>();
 
		for(String name : parameters.keySet()) {
			Object value = parameters.get(name);
			
			 
			if (value instanceof Range) {
				 
				processRang(name,(Range)value,buffer,newParameters);
				continue;
			}
			 
			if (value instanceof Collection || value instanceof Object[]) {
				Comparator comparator = Comparator.IN;
				buffer.append(" and model.").append(name).append(" ")
						.append(comparator.getValue()).append(
								" (").append(":").append(name).append(")");
				continue;
			}
			if (value != null) {
				Comparator comparator = (comparators == null ||comparators.get(name) == null)?Comparator.EQ:comparators.get(name);
				comparator = (comparator.getValue() == null||comparator==Comparator.IN||comparator ==Comparator.LK)?Comparator.EQ:comparator;
				buffer.append(" and model.").append(name).append(" ")
						.append(comparator.getValue()).append(
								" ").append(":").append(name);
			}
		 
			 
		}
		 
		parameters.putAll(newParameters);
		 
	}
	
	private void processRang(String param,Range range,StringBuffer buffer,Map<String,Object> newParameters) {
		 
		Object min = range.getMin();
		Object max = range.getMax();
		if (min == null && max == null) {
			return;
		}
		Comparator comparator = (comparators == null||comparators.get(param) ==null)?Comparator.GTEQ_LSEQ:comparators.get(param);
		comparator = (comparator.getMin()==null||comparator.getMax() == null)?Comparator.GTEQ_LSEQ:comparator;
		if (min != null) {
			String key = "__min"
					+ param.substring(0, 1).toUpperCase()
					+ param.substring(1);
			
			String symbol = comparator.getMin().getValue();
			buffer.append(" and model.").append(param).append(" ")
					.append(symbol).append(" ").append(":").append(key);
			 
			newParameters.put(key, min);
		}
		if (max != null) {
			String key = "__max"
					+ param.substring(0, 1).toUpperCase()
					+ param.substring(1);
			String symbol = comparator.getMax().getValue();
			buffer.append(" and model.").append(param).append(" ")
					.append(symbol).append(" ").append(":").append(key);
			 
			newParameters.put(key, max);
		}
	}
	
	
	/**
	 * 添加排序规则
	 * @author jianguo.xu
	 * @param buffer
	 */
	private void addCompositorRole(StringBuffer buffer) {
		if(compositors == null||compositors.size()==0) return;
		buffer.append(" order by");
		for(int i = 0;i<compositors.size();i++) {
			Compositor compositor = compositors.get(i);
			 
			if(i>0)  buffer.append(",");
			buffer.append(" ").append(compositor.getProperty()).append(" ").
			append(compositor.getRole().getValue());
		}
		 
	}

}

