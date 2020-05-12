package com.tangtang.manager.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: DateUtils
 * @Description:
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/09 9:58
 */
public class DateUtils {

    /**
     *
     * 功能描述:
     *
     * @param: 获取当前系统时间 yyyy-MM-dd HH:mm:ss
     * @return:
     * @auther: tangtang
     * @date: 2020/03/09 9:59
     */
    public static String getCurrentDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(System.currentTimeMillis());
        return date;
    }


    /**
     *
     * 功能描述: 
     *
     * @param: date类 获取当前系统时间 yyyy-MM-dd HH:mm:ss
     * @return: 
     * @auther: tangtang
     * @date: 2020/03/09 10:39
     */
    public static Date getCurrentDateToDate () {
        DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String date = df.format(System.currentTimeMillis());
        Date d = null;
        try {
            d = df.parse( date.toString( ) );
        } catch ( ParseException e ) {
            e.printStackTrace( );
        }
        return d;
    }

    /**
     * 增加时间单位：天
     * @param day
     * @return
     */
    public static String getCurrentAddDay(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }

    /**
     * 增加时间单位：分钟
     * @param minute
     * @return
     */
    public static String getCurrentAddMin(int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, minute);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowDateString (  ) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
        return sdf.format( d );
    }

    /**
     * 获取当前时间
     * @return
     */
    public static List<String> getNearly30days (  ) {
        List<String> nearly30days = new ArrayList<>();
        String today = getNowDateString();
        nearly30days.add(today);
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
        for(int i=1; i<29; i++ ){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(calendar.DATE, 0-i);
            String dates = sdf.format(calendar.getTime());
            nearly30days.add(dates);
        }
        return nearly30days;
    }

    /**
     * 把Date转为String
     * @param date
     * @param format
     * @return
     */
    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 增加时间单位：天
     * @param day
     * @return
     */
    public static Date addDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 增加时间单位：天
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 减去多少天
     * @param date
     * @param day
     * @return
     */
    public static Date minusDay(Date date, int day) {
        return addDay(date, -day);
    }

    public static void main(String[] args) {
        System.out.println(getCurrentAddDay(2));
    }

    /**
     * 获取时间差
     */
    public static String getLeftTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = format.parse(startTime);
        Date end = format.parse(endTime);
        long between = start.getTime() - end.getTime();
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if(hour>0){
            return "0:0:0";
        }else if(min>40){
            return "0:0:0";
        }else {
            String leftTime = hour + ":" + (39-min) + ":" + (60-s);
            return leftTime;
        }

    }
}
