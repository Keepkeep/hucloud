/**
 * @Copyright (C)2017, pcitech
 */
package com.expand.hucloud.common.utils;

import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author hdq
 * @time 2023/12/28 22:40
 */

@Slf4j
public class DateTimeUtil {

	public static final String SIMPLEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SIMPLEFORMAT_ZH = "yyyy年MM月dd日 HH:mm:ss";
	public static final String YEAR_MONTH = "yyyy-MM";
	public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String TS_FORMAT = "yyyyMMddHHmmss";

    public static final String YEAR_MONTH_FULL = "yyyyMM";
	
	public static final Long SECONDS_OF_ONE_DAY = 86400L;
	
    /**
     * @return 返回今天“yyyy-MM-dd HH:mm:ss”格式的时间字符串
     * @author ChenJq
     */
    public static String getTodayBySimpleFormat() {
        return getTodayByFormat(SIMPLEFORMAT);
    }


    /**
     * @param timeFormat 日期格式
     * @return 返回今天指定格式的时间字符串
     */
    public static String getTodayByFormat(String timeFormat) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(timeFormat));
    }

    /**
     * @return 返回今天指定格式的时间字符串
     */
    public static String getStartTodayByFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_DAY))  + " 00:00:00";
    }

    /**
     * 格式化
     * @param d
     * @param format
     * @return
     */
    public static String formatDate(Date d,String format){
        Locale locale = Locale.CHINA;
        SimpleDateFormat sdf = new SimpleDateFormat(format,locale);
        return sdf.format(d);
    }

    /**
     * 格式化 当前时间
     * @param format
     * @return
     */
    public static String getCurrentDate(String format){
        return formatDate(new Date(),format);
    }

    public static Date getDayAgo(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -1 * n);
        return c.getTime();
    }


    /**
     * 把一个日期格式的字符串转为另外一种日期格式字符串
     *
     * @param srcTime       源日期字符串
     * @param srcFormat     源日期格式
     * @param dstTimeFormat 目标日期格式
     * @return 目标日期字符串
     */
    public static String transTimeFormat(String srcTime, String srcFormat, String dstTimeFormat) {
        LocalDateTime srcDataTime = LocalDateTime.parse(srcTime, DateTimeFormatter.ofPattern(srcFormat));
        return srcDataTime.format(DateTimeFormatter.ofPattern(dstTimeFormat));
    }


    /**
     * 获取指定日期时间的最后一秒
     *
     * @param srcTime 日期时间 格式 yyyy-MM-dd或者 yyyy-MM-dd HH:mm:ss
     * @return 日期时间的最后一秒, 格式yyyy-MM-dd HH:mm:ss  例如 2018-02-28 23:59:59
     */
    public static String getDateEndTime(String srcTime) {
        if (StringUtil.isEmpty(srcTime)) {
            return null;
        }
        int dateTimeLength = 10;
        if (srcTime.length() > dateTimeLength) {
            srcTime = srcTime.substring(0, 10);
        }

        return srcTime + " 23:59:59";
    }

    /**
     * 获取指定日期时间的最开始一秒
     *
     * @param srcTime 日期时间 格式 yyyy-MM-dd或者 yyyy-MM-dd HH:mm:ss
     * @return 日期时间的最开始一秒, 格式yyyy-MM-dd HH:mm:ss  例如 2018-02-28 00:00:00
     */
    public static String getDateBeginTime(String srcTime) {
        if (StringUtil.isEmpty(srcTime)) {
            return null;
        }
        int dateTimeLength = 10;
        if (srcTime.length() > dateTimeLength) {
            srcTime = srcTime.substring(0, 10);
        }

        return srcTime + " 00:00:00";
    }


    /**
     * 获取当前时间距离到第二天0点0分的间隔
     *
     * @return 时间间隔，单位秒
     */
    public static long getInterval2NextData() {
        // 时间加减的demo
        LocalDateTime currentTime = LocalDateTime.now();

        // 第二天零点时间
        LocalDateTime nextDateTime = currentTime.plusDays(1).minusHours(currentTime.getHour())
                .minusMinutes(currentTime.getMinute()).minusSeconds(currentTime.getSecond());

        Duration duration = Duration.between(currentTime, nextDateTime);
        // 加上60秒，延迟一分钟
        return duration.toMinutes() * 60 + 60;

    }
    
    /**
     * 获取当前时间距离下一个指定时间 <i>hour:minute:second </i>的时间间隔，单位：秒
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 时间间隔，单位秒
     */
    public static long getInterval2NextTime(int hour, int minute, int second) {
    	// 当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 第二天的指定时间
        LocalDateTime nextDateTime = currentTime.plusDays(1)
        		.minusHours(currentTime.getHour())
                .minusMinutes(currentTime.getMinute())
                .minusSeconds(currentTime.getSecond())//第二天零点时间
                .plusHours(hour) 
                .plusMinutes(minute)
                .plusSeconds(second);//第二天的指定时间

        Duration duration = Duration.between(currentTime, nextDateTime);

        long interval = duration.getSeconds();
    	
        //获取距离下一个指定时间点的时间间隔（单位秒，下一个时间点不一定是第二天）
        return interval - SECONDS_OF_ONE_DAY > 0 ? interval - SECONDS_OF_ONE_DAY : interval;
    }
    
    /**
     * 获取当前时间距离下一个指定时间的时间间隔，单位：秒
     * @param timeStr 指定任务执行的时间，格式：HH:mm:ss
     * @return 时间间隔，单位秒
     */
    public static long getInterval2NextTime(String timeStr) {
    	//转换时间
    	String[] time = timeStr.split(":");
    	int hour = Integer.valueOf(time[0]);
    	int minute = Integer.valueOf(time[1]);
    	int second = Integer.valueOf(time[2]);
    	
    	// 当前时间
    	LocalDateTime currentTime = LocalDateTime.now();
    	
    	// 第二天的指定时间
    	LocalDateTime nextDateTime = currentTime.plusDays(1)
    			.minusHours(currentTime.getHour())
    			.minusMinutes(currentTime.getMinute())
    			.minusSeconds(currentTime.getSecond())//第二天零点时间
    			.plusHours(hour) 
    			.plusMinutes(minute)
    			.plusSeconds(second);//第二天的指定时间
    	
    	Duration duration = Duration.between(currentTime, nextDateTime);
    	
    	long interval = duration.getSeconds();
    	
    	//获取距离下一个指定时间点的时间间隔（单位秒，下一个时间点不一定是第二天）
    	return interval - SECONDS_OF_ONE_DAY > 0 ? interval - SECONDS_OF_ONE_DAY : interval;
    }

    /**
     * 获取时间字符串的毫秒数
     * @param timeStr
     * @return 毫秒
     * @throws ParseException 
     */
    public static long getMsecOfTimeStr(String timeStr) throws ParseException {
    	DateFormat format = new SimpleDateFormat(SIMPLEFORMAT);
    	return format.parse(timeStr).getTime();
    }

    /**
     * 时间格式化
     * @param datetime 要格式的时间，默认数据是yyyy-MM-dd HH:mm:ss格式
     * @param format 格式
     * @return
     */
    public static String format(String datetime, String format){
    	return format(datetime, SIMPLEFORMAT, format);
    }
    
    /**
     * 
     * @param datetime 时间
     * @param sourceFormat 时间源格式
     * @param targetFormat 目标格式
     * @return
     */
    public static String format(String datetime, String sourceFormat, String targetFormat){
        try {
            SimpleDateFormat sourceFmt = new SimpleDateFormat(sourceFormat);
            SimpleDateFormat targetFmt = new SimpleDateFormat(targetFormat);
            return targetFmt.format(sourceFmt.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return "";
    }
    
    public static String format(Date date, String format){
    	try {
			SimpleDateFormat targetFmt = new SimpleDateFormat(format);
			return targetFmt.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return "";
    }

    public static Date getDate(String str,String format){
        Locale locale = Locale.CHINA;
        if(StringUtils.length(str)<StringUtils.length(format)){
            format = StringUtils.substring(format, 0, StringUtils.length(str));
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format,locale);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            return new Date();
        }
    }
    
    public static String getDateTime(String format, int yearOffset, int monthOffset, int dayOffset){
    	Calendar now = Calendar.getInstance();
    	if(yearOffset != 0){
    		now.add(Calendar.YEAR, yearOffset);
    	}
    	if(monthOffset != 0){
    		now.add(Calendar.MONTH, monthOffset);
    	}
    	if(dayOffset != 0){
    		now.add(Calendar.DAY_OF_MONTH, dayOffset);
    	}
    	
    	try {
			SimpleDateFormat targetFmt = new SimpleDateFormat(format);
			return targetFmt.format(now.getTime());
		} catch (Exception e) {
		}
    	
    	return "";
    }
    
    public static String getDateTime(Date date, String format, int yearOffset, int monthOffset, int dayOffset){
    	Calendar now = Calendar.getInstance();
    	if(date != null){//可以传日期进来
    		now.setTime(date);
    	}
    	if(yearOffset != 0){
    		now.add(Calendar.YEAR, yearOffset);
    	}
    	if(monthOffset != 0){
    		now.add(Calendar.MONTH, monthOffset);
    	}
    	if(dayOffset != 0){
    		now.add(Calendar.DAY_OF_MONTH, dayOffset);
    	}
    	
    	try {
			SimpleDateFormat targetFmt = new SimpleDateFormat(format);
			return targetFmt.format(now.getTime());
		} catch (Exception e) {
		}
    	
    	return "";
    }

    /**
     * 获取该时间所在月份的每个相同星期的日期
     * @param dateStr 日期字符串
     * @param formatter 格式化
     * @param dayOfWeek 星期
     * @return
     */
    public static List<Object> getWeeksOfMonth(String dateStr, String formatter, DayOfWeek dayOfWeek) {
    	LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(formatter));
        LocalDate mondayOfMonth = date.withDayOfMonth(1);

        while (mondayOfMonth.getDayOfWeek() != dayOfWeek) {
            mondayOfMonth = mondayOfMonth.plusDays(1);
        }

        List<Object> days = new ArrayList<>();
        int monthValue = date.getMonthValue();
        while (mondayOfMonth.getMonthValue() == monthValue) {
            String dayOfMonday = mondayOfMonth.format(DateTimeFormatter.ISO_LOCAL_DATE);
            days.add(dayOfMonday);
            mondayOfMonth = mondayOfMonth.plusDays(7);
        }
        
        return days;
    }
    
    /**
     * 比较两个日期字符串的大小，dateStr1-dateStr2，返回两者的时间差（单位：毫秒）
     * @param dateStr1 第一个日期字符串
     * @param dateStr2 第二个日期字符串
     * @param formatter 日期字符串的转化格式
     * @return 返回值 >0，表示dateStr1较大；返回值<0，表示dateStr1较小；返回值=0，表示两者相等
     */
    public static Long compareDataTimeByStr(String dateStr1,String dateStr2,String formatter){
    	try{
			SimpleDateFormat sdf=new SimpleDateFormat(formatter);
			//将字符串形式的时间转化为Date类型的时间
			Date a=sdf.parse(dateStr1);
			Date b=sdf.parse(dateStr2);
			return a.getTime()-b.getTime();
    	} catch (ParseException e) {
			log.error("compareDataTimeByStr," + "参数：" 
					+ dateStr1 + "," + dateStr2 + "," + formatter + "," + " error", e);
			return null;
		}
    }






}


