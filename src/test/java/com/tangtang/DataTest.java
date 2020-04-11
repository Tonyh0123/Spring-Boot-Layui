package com.tangtang;

import com.tangtang.manager.common.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DataTest {
    public static void main(String[] args) throws Throwable{
        String time = "DC"+DateUtils.getNowDateString().replace("-","");
        Random random = new Random();
        for(int i=0;i<4;i++){
            int ran = random.nextInt(10);
            time += ran;
        }
        System.out.println(time);
    }
}
