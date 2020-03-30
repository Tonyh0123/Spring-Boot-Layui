package com.tangtang;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTest {
    public static void main(String[] args) throws Throwable{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse("2018-03-21 14:11:11");
        Date date = format.parse("2018-03-21 15:18:59");
        long between = date.getTime() - parse.getTime();
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        System.out.println( hour + ":" + min + ":" + s);
    }
}
