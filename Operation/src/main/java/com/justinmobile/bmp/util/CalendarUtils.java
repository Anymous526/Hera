package com.justinmobile.bmp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 常用日期格式
 */
public class CalendarUtils {

	/** 完整日期时间无间隔格式 */
	public static final String LONG_FORMAT = "yyyyMMddHHmmss";
	/** 日期无间隔格式 */
	public static final String SHORT_FORMAT = "yyyyMMdd";
	/** 无间隔时间格式 */
	public static final String SHORT_FORMAT_TIME = "HHmmss";
	/** 完整日期时间横线与冒号分隔格式 */
	public static final String LONG_FORMAT_LINE = "yyyy-MM-dd HH:mm:ss";
	/** 横线分隔日期格式 */
	public static final String SHORT_FORMAT_LINE = "yyyy-MM-dd";
	/** 冒号分隔时间格式 */
	public static final String SHORT_FORMAT_TIME_COLON = "HH:mm:ss";
	/** 中文日期格式 */
	public static final String SHORT_FORMAT_CHINESE = "yyyy年M月d日";

	public static Calendar parseCalendar(String str, String... parsePatterns) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (ArrayUtils.isEmpty(parsePatterns)) {
			cal.setTime(DateUtils.parseDate(str, new String[] { LONG_FORMAT_LINE }));
		} else {
			cal.setTime(DateUtils.parseDate(str, parsePatterns));
		}
		return cal;
	}

	public static String fomatCalendar(Calendar cal, String parsePattern) {
		String str = "";
		if (StringUtils.isEmpty(parsePattern)) {
			str = DateFormatUtils.format(cal, LONG_FORMAT_LINE);
		} else {
			str = DateFormatUtils.format(cal, parsePattern);
		}
		return str;
	}

	/**
	 * 当月最后一天23时59分59秒
	 * 
	 * @return
	 */
	public static Calendar getMonthLastDay() {
		Calendar c = Calendar.getInstance();
		int i = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, i);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c;
	}

	/**
	 * 某月第一天0时0分0秒
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar getMonthFirstDay(Calendar cal) {
		Calendar c = (Calendar) cal.clone();
		int i = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, i);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c;
	}

	/**
	 * 当月第一天0时0分0秒
	 * 
	 * @return
	 */
	public static Calendar getMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		int i = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, i);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c;
	}

	public static Calendar parseCalendar(Calendar cal, String parsePattern) throws ParseException {
		String strCal = fomatCalendar(cal, parsePattern);
		return parseCalendar(strCal, parsePattern);
	}

	public static String getMonthFirstDay(String year, String month) {
		StringBuffer sb = new StringBuffer();
		sb.append(year);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append("1");
		return sb.toString();
	}

	public static String getMonthLastDay(String year, String month) {
		String endday = "31";
		boolean leap;
		if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7") || month.equals("8")
				|| month.equals("10") || month.equals("12")) {
			endday = "31";
		}
		if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
			endday = "30";
		}
		if (month.equals("2")) {
			leap = leapYear(year);
			if (leap) {
				endday = "29";
			} else {
				endday = "28";
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append(year);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(endday);
		return sb.toString();
	}

	private static boolean leapYear(String y) {
		boolean leap;
		int year = Integer.valueOf(y);
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					leap = true;
				else
					leap = false;
			} else
				leap = true;
		} else
			leap = false;
		return leap;
	}

	/**
	 * 获取上月开始日期
	 * 
	 * @param 年
	 *            , 本月
	 * @return 上月开始日期字符串
	 * @exception
	 */
	public static String getPreMonthFirstDay(String y, String m) {
		int month = Integer.valueOf(m);
		int preMonth = 0;
		int tempYear = 0;
		if (month != 1) {
			preMonth = month - 1;
			tempYear = Integer.valueOf(y);
		} else {
			preMonth = 12;
			tempYear = Integer.valueOf(y) - 1;
		}
		return CalendarUtils.getMonthFirstDay(String.valueOf(tempYear), String.valueOf(preMonth));
	}

	/**
	 * 获取上月结束日期
	 * 
	 * @param 年
	 *            , 本月
	 * @return 上月结束日期字符串
	 * @exception
	 */
	public static String getPreMonthLastDay(String y, String m) {
		int month = Integer.valueOf(m);
		int preMonth = 0;
		int tempYear = 0;
		if (month != 1) {
			preMonth = month - 1;
			tempYear = Integer.valueOf(y);
		} else {
			preMonth = 12;
			tempYear = Integer.valueOf(y) - 1;
		}
		return CalendarUtils.getMonthLastDay(String.valueOf(tempYear), String.valueOf(preMonth));
	}

	public static String getYearMonth(String date) {
		String[] dates = date.split("-");
		StringBuffer sb = new StringBuffer();
		sb.append(dates[0]);
		sb.append(StringUtils.leftPad(dates[1], 2, "0"));
		return sb.toString();
	}

	/**
	 * 判断是否月份
	 * @param s
	 * @return
	 */
	public static boolean isMonth(String s) {
		if (StringUtils.isEmpty(s)) {
			return false;
		} else {
			int month = 0;
			try {
				month = Integer.valueOf(s);
			} catch (Exception e) {
				return false;
			}
			if (month >= 1 && month <= 12) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * 使用预设Format格式化Date成字符串
	 * 
	 * @return String
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, SHORT_FORMAT_LINE);
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 * 
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	public static void main(String[] args) {
		Calendar c = getMonthLastDay();
		System.out.println(fomatCalendar(c, LONG_FORMAT));
	}

}
