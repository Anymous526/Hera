package com.vlives.util.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException; 
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.vlives.util.StringUtils;

public class ReplaceTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 需要输入字符串 */
	private String value;
	/** 字符串后加加其他 */
	private String suffix;
	/** 保留的字符串最大长度 */
	private int length;
	/** 是否出去value中的html标签 */
	private boolean removeHtml;

	/** 是否使用byte计算长度 */
	private boolean byByteLength;

	/** 保留的字符串最大长度 */
	private boolean escape;
	/** 出去回车\n\r等回车符 */
	private boolean removeEnter;

	public ReplaceTag() {
		this.suffix = "";
		this.removeHtml = false;
		this.byByteLength = false;
		this.escape = true;
		this.removeEnter = false;

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRemoveHtml() {
		return removeHtml;
	}

	public void setRemoveHtml(boolean removeHtml) {
		this.removeHtml = removeHtml;
	}

	public boolean isByByteLength() {
		return byByteLength;
	}

	public void setByByteLength(boolean byByteLength) {
		this.byByteLength = byByteLength;
	}

	public boolean isEscape() {
		return escape;
	}

	public void setEscape(boolean escape) {
		this.escape = escape;
	}

	public boolean isRemoveEnter() {
		return removeEnter;
	}

	public void setRemoveEnter(boolean removeEnter) {
		this.removeEnter = removeEnter;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();

		String actualValue = value;
		if (!StringUtils.isNullOrEmpty(actualValue)) {

			actualValue = initValue(actualValue);

			actualValue = this.removeEnter(actualValue);

			StringBuffer sb = new StringBuffer(actualValue);
			if (!byByteLength && length > 0 && length < actualValue.length()) {
				sb.delete(length, actualValue.length());
				if (suffix != null)
					sb.append(suffix);
			}
			if (byByteLength && length > 0 && length < actualValue.getBytes().length) {
				sb = new StringBuffer(StringUtils.deleteByteLength(actualValue, length, actualValue.getBytes().length));
				if (suffix != null)
					sb.append(suffix);
			}
			actualValue = prepare(sb.toString());

		} else {
			actualValue = "";
		}
		try {
			out.print(actualValue);
		} catch (IOException e) {
			// LOG.info("Could not print out value '" + value + "'", e);
		}
		return (SKIP_BODY);
	}

	@Override
	public int doStartTag() throws JspException {
		return (SKIP_BODY);
	}

	/**
	 * 判断是否出去HTML标签，如要需要开始就出去
	 * 
	 * @param actualValue
	 * @return
	 */
	private String initValue(String actualValue) {
		actualValue = actualValue.trim();
		if (removeHtml) {
			return StringUtils.escapeHtmlAndBlank(actualValue);
		}
		return actualValue;
	}

	public String removeEnter(String value) {
		if (removeEnter) {
			return value.replaceAll("\r\n|\n|\r", "");
		} else {
			return value;
		}
	}

	private String prepare(String value) {
		if (escape && !StringUtils.isNullOrEmpty(value)) {
			return StringUtils.escapeSpecialLabel(value);
		} else {
			return value;
		}
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
