package com.justinmobile.bmp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期Util类
 * 
 * @author calvin
 */
public class DateUtils {
	public static final String defaultDatePattern = "yyyy-MM-dd";

	public static final String FullDatePattern = "yyyy-MM-dd HH:mm:ss";

	public static final String HFDatePattern = "yyyy-MM-dd HH:mm";

	public static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";

	private static SimpleDateFormat lenientDateFormat = new SimpleDateFormat(
			defaultDatePattern);

	 

	/**
	 * 获得默认的 date pattern
	 * 
	 * @return String
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 * 
	 * @return String
	 */
	public static String getToday() {
		return format(now());
	}

	/**
	 * 返回当前时间
	 * 
	 * @return Date实例
	 */
	public static Date now() {
		return nowCal().getTime();
	}

	/**
	 * 当前时间
	 * 
	 * @return Calendar实例
	 */
	public static Calendar nowCal() {
		return Calendar.getInstance();
	}

	/**
	 * Date型转化到Calendar型
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar date2Cal(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 当前时间的下一天
	 * 
	 * @return Calendar
	 */
	public static Calendar nextDay() {
		return nextDay(nowCal());
	}

	/**
	 * 当前时间的下一月
	 * 
	 * @return Calendar
	 */
	public static Calendar nextMonth() {
		return nextMonth(nowCal());
	}

	/**
	 * 当前时间的下一年
	 * 
	 * @return Calendar
	 */
	public static Calendar nextYear() {
		return nextMonth(nowCal());
	}

	/**
	 * 下一天
	 * 
	 * @param cal
	 * @return Calendar
	 */
	public static Calendar nextDay(Calendar cal) {
		if (cal == null) {
			return null;
		}
		return afterDays(cal, 1);
	}

	/**
	 * 下一月
	 * 
	 * @param cal
	 * @return Calendar
	 */
	public static Calendar nextMonth(Calendar cal) {
		if (cal == null) {
			return null;
		}
		return afterMonths(cal, 1);
	}

	/**
	 * 下一年
	 * 
	 * @param cal
	 * @return Calendar
	 */
	public static Calendar nextYear(Calendar cal) {
		if (cal == null) {
			return null;
		}
		return afterYesrs(cal, 1);
	}

	/**
	 * 后n天
	 * 
	 * @param cal
	 * @param n
	 * @return Calendar
	 */
	public static Calendar afterDays(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + n);
		return c;
	}

	/**
	 * 下N秒
	 * 
	 * @param cal
	 * @param n
	 * @return
	 */
	public static Calendar afterSecond(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.SECOND, cal.get(Calendar.SECOND) + n);
		return c;
	}

	/**
	 * 后n月
	 * 
	 * @param cal
	 * @param n
	 * @return Calendar
	 */
	public static Calendar afterMonths(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.MONTH, cal.get(Calendar.MONTH) + n);
		return c;
	}

	/**
	 * 后n年
	 * 
	 * @param cal
	 * @param n
	 * @return Calendar
	 */
	public static Calendar afterYesrs(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.YEAR, cal.get(Calendar.YEAR) + n);
		return c;
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 * 
	 * @return String
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 * 
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 试用参数Format格式化Calendar成字符串
	 * 
	 * @param cal
	 * @param pattern
	 * @return String
	 */
	public static String format(Calendar cal, String pattern) {
		return cal == null ? "" : new SimpleDateFormat(pattern).format(cal
				.getTime());
	}

	/**
	 * 使用预设格式将字符串转为Date
	 * 
	 * @return Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate,
				getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 * 
	 * @return Date
	 */
	public static Date parse(String strDate, String pattern)
			throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
				pattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 * 
	 * @return Date
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * get String value(MM/dd/yyyy) of time
	 * 
	 * @param d
	 * @return String
	 */
	public static String dateToString(Date d) {
		if (d == null) {
			return null;
		}
		return lenientDateFormat.format(d);
	}
	/**
	 * 根据java.util.Date 得到java.sql.Date
	/**
	 * @param date
	 * @return
	 */
	public static java.sql.Date getDay(Date date) {
		String dateStr = format(date, "yyyyMMddHHmmss");
		String dayStr = dateStr.substring(0, 8);
		try {
			return new java.sql.Date(parse(dayStr, "yyyyMMdd").getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	

	public static void main(String[] args) {
		Date date  = new Date();
		String str = format(date, FullDatePattern);
		str = format(date, FullDatePattern);
		System.out.println(str);
		
		str = "2010-01-01";
	 
		
	}

}