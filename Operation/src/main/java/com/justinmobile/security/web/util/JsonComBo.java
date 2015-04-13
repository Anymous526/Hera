package com.justinmobile.security.web.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

/**
 * 为下拉框(ComboBox)提供text->value格式的Json数组
 * 
 * @author DuWei
 * 
 */
public class JsonComBo {

	private List<TextValueItem> jsonItems = null;

	public JsonComBo() {
		jsonItems = new ArrayList<TextValueItem>();
	}

	public void add(String text, Object value) {
		TextValueItem it = new TextValueItem(text, value.toString());
		jsonItems.add(it);
	}

	public String getJsonString(Properties p) {
		Iterator<Entry<Object, Object>> i = p.entrySet().iterator();
		while (i.hasNext()) {
			Entry<Object, Object> e = (Entry<Object, Object>) i.next();
			TextValueItem it = new TextValueItem(e.getKey().toString(), e
					.getValue().toString());
			jsonItems.add(it);
		}
		return getJsonString(true);

	}

	public String getJsonString() {

		return getJsonString(false);
	}

	public String getJsonString(boolean reverse) {

		StringBuffer result = new StringBuffer();

		result.append("[");
		if (reverse) {
			for (int i = jsonItems.size() - 1; i >= 0; i--) {
				TextValueItem it = jsonItems.get(i);
				result.append("['" + it.text + "',");
				result.append("'" + it.value + "'],");
			}

		} else {
			for (int i = 0; i < jsonItems.size(); i++) {
				TextValueItem it = jsonItems.get(i);
				result.append("['" + it.text + "',");
				result.append("'" + it.value + "'],");
			}
		}
		result.deleteCharAt(result.length() - 1);// 删除最后一个逗号
		result.append("]");

		return result.toString();
	}



	private class TextValueItem {

		public String text;

		public String value;

		public TextValueItem(String text, String value) {

			this.text = text;
			this.value = value;
		}

	}

}
