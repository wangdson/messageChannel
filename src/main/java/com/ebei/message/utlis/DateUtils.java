package com.ebei.message.utlis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Description : 日期工具类
 * @time 创建时间 : 2018年5月28日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
public class DateUtils {
	
	public static final String YEAR_FORMAT = "yyyy";
	public static final String SINGLE_MONTH_FORMAT = "MM";
	public static final String SINGLE_DAY_FORMAT = "dd";
	public static final String MONTH_FORMAT = "yyyy-MM";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MILLI_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	
	public static String convertToMonth(Date date) {
		return new SimpleDateFormat(MONTH_FORMAT).format(date);
	}
	
	public static Date parseToMonth(String value) throws ParseException {
		return new SimpleDateFormat(MONTH_FORMAT).parse(value);
	}
	
	public static String convertToDate(Date date) {
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
	
	public static String convertToDate(Date date, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(date);
	}
	
	public static Date parseToDate(String value) throws ParseException {
		return new SimpleDateFormat(DATE_FORMAT).parse(value);
	}
	
	public static Date parseToDate(String value, String dateFormat) throws ParseException {
		return new SimpleDateFormat(dateFormat).parse(value);
	}
	
	public static String convertToTime(Date date) {
		return new SimpleDateFormat(TIME_FORMAT).format(date);
	}
	
	public static Date parseToTime(String value) throws ParseException {
		return new SimpleDateFormat(TIME_FORMAT).parse(value);
	}
	
	public static String convertToMilliSecond(Date date) {
		return new SimpleDateFormat(MILLI_SECOND_FORMAT).format(date);
	}
	
	public static Date parseToMilliSecond(String value) throws ParseException {
		return new SimpleDateFormat(MILLI_SECOND_FORMAT).parse(value);
	}
	
	/**
	 * 
	 * @Description : 时间段内是否包含该时间
	 * @param date 时间
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return true-包含,false-不包含
	 */
	public static boolean containDate(Date date, Date beginDate, Date endDate) {
		if (date == null || beginDate == null || endDate == null || date.before(beginDate) || date.after(endDate)) {
			return false;
		}

		return true;
	}
}
