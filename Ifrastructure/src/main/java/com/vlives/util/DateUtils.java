package com.vlives.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils {
	private static final Log LOG = LogFactory.getLog(DateUtils.class);

	/**
	 * yyy-MM-dd
	 */
	public static final String SHORT_DATE_FORMATE_ONE = "yyyy-MM-dd";
	/**
	 * yyy/MM/dd
	 */
	public static final String SHORT_DATE_FORMATE_TWO = "yyyy/MM/dd";

	/**
	 * 2009-05-19 12:47:28 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd hh:mm:ss";

	public static final String SHORT_DATE_FORMAT_STR = "yyyy-MM-dd";
	public static final String LONG_DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	private static final DateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(SHORT_DATE_FORMAT_STR);
	public static final DateFormat LONG_DATE_FORMAT = new SimpleDateFormat(LONG_DATE_FORMAT_STR);
	private static final String EARLY_TIME = "00:00:00";
	private static final String LATE_TIME = "23:59:59";
	
	private static final String MONTH_EARLY_TIME = "01 00:00:00";

	/**
	 * 使用预设Format格式化Date成字符串
	 * 
	 * @return String
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, DEFAULT_DATE_FORMATE);
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
	 * 字符串解析成 java.sql.Date 日期
	 * 
	 * @author jianguo.xu
	 * @param shortDate
	 * @param format
	 * @return
	 */
	public static java.sql.Date parserShortDate(String shortDate, String format) {
		DateFormat dateFormate = new SimpleDateFormat(format);
		try {
			Date date = dateFormate.parse(shortDate);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			LOG.error("parser java.sql.Date error", e);
			return null;
		}
	}

	/**
	 * 字符串解析成日期
	 * 
	 * @author jianguo.xu
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static java.util.Date parserDate(String dateStr, String format) {
		DateFormat dateFormate = new SimpleDateFormat(format);
		try {
			Date date = dateFormate.parse(dateStr);
			return new java.util.Date(date.getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 增加时间的秒数
	 * 
	 * @param date
	 *            要增加的日期
	 * @param second
	 *            增加的时间（以秒为单位）
	 * @return 增加时间后的日期
	 */

	public static Date addSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 根据TimeUnit增加指定日期的的时间
	 * 
	 * @author jianguo.xu
	 * @param date
	 *            要增加的日期
	 * @param timeUnit
	 *            增加的日历字段（只能取 DAYS 到 MILLISECONDS 之间的枚举，否则报错）
	 * @param value
	 *            增加的值(当值为负数时表示减少)
	 * @return
	 */
	public static Date add(Date date, TimeUnit timeUnit, int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int field = 0;
		 if (timeUnit == TimeUnit.YEAR)
			field = Calendar.YEAR;
		 else if (timeUnit == TimeUnit.DAYS)
			field = Calendar.DAY_OF_YEAR;
		else if (timeUnit == TimeUnit.HOURS)
			field = Calendar.HOUR_OF_DAY;
		else if (timeUnit == TimeUnit.MINUTES)
			field = Calendar.MINUTE;
		else if (timeUnit == TimeUnit.SECONDS)
			field = Calendar.SECOND;
		else if (timeUnit == TimeUnit.MILLISECONDS)
			field = Calendar.MILLISECOND;
		else
			throw new RuntimeException("timeUnit error");
		cal.add(field, value);
		return cal.getTime();
	}

	/**
	 * 根据TimeUnit清除指定的日历字段
	 * 
	 * @author jianguo.xu
	 * @param date
	 *            要清除的日期
	 * @param timeUnit
	 *            清除的日历字段（只能取 DAYS 到 MILLISECONDS 之间的枚举，否则报错）
	 * @return
	 */
	public static Date clear(Date date, TimeUnit timeUnit) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int field = 0;
		if (timeUnit == TimeUnit.YEAR)
			field = Calendar.YEAR;
		else if (timeUnit == TimeUnit.DAYS)
			field = Calendar.DAY_OF_YEAR;
		else if (timeUnit == TimeUnit.HOURS)
			field = Calendar.HOUR_OF_DAY;
		else if (timeUnit == TimeUnit.MINUTES)
			field = Calendar.MINUTE;
		else if (timeUnit == TimeUnit.SECONDS)
			field = Calendar.SECOND;
		else if (timeUnit == TimeUnit.MILLISECONDS)
			field = Calendar.MILLISECOND;
		else
			throw new RuntimeException("timeUnit error");
		cal.clear(field);
		return cal.getTime();
	}

	/**
	 * <br>
	 * 传入的日期减去天数后返回的日期</br>
	 * 
	 * @param date
	 *            被减日期
	 * @param day
	 *            减去的天数
	 * @return 返回减去指定天数后的日期
	 */
	public static Date subtractDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		dayOfMonth -= day;
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		return cal.getTime();
	}

	/**
	 * <br>
	 * 第一个日期减去第二个日期后得到的天数</br> <br>
	 * 如果减去的后的天数有不满足一整天的，则不计入天数内</br>
	 * 
	 * @param date
	 *            被减日期
	 * @param day
	 *            减去的日期
	 * @return 返回减去后的天数
	 */
	public static long subtractDay(Date date, Date other) {
		return subtractSecond(date, other)/ (24 * 60 * 60);
	}
	
	public static long subtractSecond(Date date, Date other) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long dateTimeInMillis = calendar.getTimeInMillis();
		Calendar otherCalendar = Calendar.getInstance();
		otherCalendar.setTime(other);
		long otherTimeInMillis = otherCalendar.getTimeInMillis();
		return (dateTimeInMillis - otherTimeInMillis) / (1000);
	}
	
	

	/**
	 * 根据指定天数获得距离当前日期之前的日期(java.sql.date)
	 * 
	 * @param day
	 *            距离当前日期之前的天数
	 * @return
	 */
	public static java.sql.Date getBeforTodayDate(int day) {
		Date dateTime = new Date();
		dateTime = DateUtils.subtractDay(dateTime, day);
		return new java.sql.Date(dateTime.getTime());
	}

	/**
	 * 字符串解析成 java.sql.Time 时间
	 * 
	 * @author jianguo.xu
	 * @param timeStr
	 * @param timeFormat
	 * @return
	 */
	public static java.sql.Time parserTime(String timeStr, String timeFormat) {
		DateFormat dateFormate = new SimpleDateFormat(timeFormat);
		try {
			Date date = dateFormate.parse(timeStr);
			return new java.sql.Time(date.getTime());
		} catch (ParseException e) {
			LOG.error("parser java.sql.Time error", e);
			return null;
		}
	}

	public static enum TimeUnit {
		YEAR,DAYS, HOURS, MINUTES, SECONDS, MILLISECONDS;
	}

	/**
	 * 得到某个日期在这一天中时间最早的日期对象
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getEarlyInTheDay(Date date){
		String dateString = SHORT_DATE_FORMAT.format(date) + " " + EARLY_TIME;
		try {
			return LONG_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("parser date error.", e);
		}
	}

	/**
	 * 得到某个日期在这一天中时间最晚的日期对象
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getLateInTheDay(Date date){
		String dateString = SHORT_DATE_FORMAT.format(date) + " " + LATE_TIME;
		try {
			return LONG_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("parser date error.", e);
		}
	}
	
	/**
	 * 得到某月中最早的时间
	 * @param date
	 * @return
	 */
	public static Date getEarlyInTheMonth(Date date){
		String dateString = SHORT_DATE_FORMAT.format(date);
		dateString = dateString.substring(0,dateString.length()-2);
		dateString += MONTH_EARLY_TIME;
		try {
			return LONG_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("parser date error.", e);
		}
	}

	/**
	 * 根据传入的两个日期获得日期的时间差<br/>
	 * 如果距离时间小于1分钟则返回的时间为秒<br/>
	 * 如果距离时间小于1小时则返回的时间为分<br/>
	 * 如果距离时间小于1天则返回的时间为小时<br/>
	 * 其它则去天数
	 * 
	 * @author jianguo.xu
	 * @param date
	 * @return
	 */
	public static TimeOutEnum getDistanceTimeOut(Date beforeDate, Date aferDate) {
		long beforeMilliseconds = beforeDate.getTime();
		long aferMilliseconds = aferDate.getTime();
		long distanceTime = aferMilliseconds - beforeMilliseconds;
		distanceTime = Math.abs(distanceTime) / 1000;
		long time = 0;
		TimeOutEnum timeOut = null;
		if (distanceTime - 60 < 0) {
			time = distanceTime;
			timeOut = TimeOutEnum.SECONDS;
		} else if (distanceTime - (60 * 60) < 0) {
			time = distanceTime / 60;
			timeOut = TimeOutEnum.MINUTES;
		} else if (distanceTime - (60 * 60 * 24) < 0) {
			time = distanceTime / (60 * 60);
			timeOut = TimeOutEnum.HOURS;
		} else {
			time = distanceTime / (60 * 60 * 24);
			timeOut = TimeOutEnum.DAYS;
		}
		time = (aferMilliseconds - beforeMilliseconds > 0 ? time : 0 - time);
		timeOut.setDistanceTimeOut(time);
		return timeOut;
	}

	public static enum TimeOutEnum {
		DAYS, HOURS, MINUTES, SECONDS;
		private long distanceTimeOut;

		public long getDistanceTimeOut() {
			return distanceTimeOut;
		}

		private void setDistanceTimeOut(long distanceTimeOut) {
			this.distanceTimeOut = distanceTimeOut;
		}

		public String getTimeOutString() {
			String dateString = "";
			switch (this) {
			case SECONDS:
				dateString = distanceTimeOut + "秒钟前";
				break;
			case MINUTES:
				dateString = distanceTimeOut + "分钟前";
				break;
			case HOURS:
				dateString = distanceTimeOut + "小时前";
				break;
			case DAYS:
				dateString = distanceTimeOut + "天前";
				break;
			default:
				break;
			}
			return dateString;
		}
	}
	
   /**
    * 页面显示调用
    * @param createDate
    * @return
    */
	public static String getTimeOutString(Date createDate) {
		Date beforeDate = new Date(createDate.getTime());
		Date nowDate = new Date();
		if (subtractDay(nowDate, beforeDate) > 3) {
			return DateUtils.format(beforeDate, SHORT_DATE_FORMAT_STR);
		}
		return getDistanceTimeOut(beforeDate, nowDate).getTimeOutString();
	}
	/**
	 * 根据年龄计算出生日
	 * @author jianguo.xu
	 * @param age
	 * @return
	 */
	public static java.sql.Date getBirthday(int age) {
		Date date = new Date();
		date = add(date, TimeUnit.YEAR, -age);
		return new java.sql.Date(date.getTime());
	}

	/*public static void main(String[] args) throws ParseException {
		System.out.println(getEarlyInTheMonth(new Date()));
	}
*/
}
