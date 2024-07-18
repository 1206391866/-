package com.wh.bank.utils;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

public class DateTools {
    public static Date getYearTime(int num){
        Calendar calendar=Calendar.getInstance();
        Date date =new Date();
        calendar.setTime(date);

        calendar.add(Calendar.YEAR,num);
        return calendar.getTime();


    }
}
