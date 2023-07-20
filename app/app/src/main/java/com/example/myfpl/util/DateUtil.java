package com.example.myfpl.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

public class DateUtil {
    public static String getCurrentSession(){
        @SuppressLint("SimpleDateFormat") int hours = Integer.parseInt(new SimpleDateFormat("HH").format(new Date(System.currentTimeMillis())));
        if (1 <= hours && hours < 10) {
            return "Chào buổi sáng";
        } else if (10 <= hours && hours < 18) {
          return "Chào buổi chiều";
        } else {
            return "Chào buổi tối";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static Date fromStringToDate(String format, String value) throws ParseException {
        return new SimpleDateFormat(format).parse(value);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeFromString(String format, String value){
        try {
            return new SimpleDateFormat("hh:mm").format(fromStringToDate(format, value));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateFromString(String format, String value){
        try {
            return new SimpleDateFormat("dd-MM").format(fromStringToDate(format, value));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static YearMonth getCurrentMonth(){
        return YearMonth.now();
    }

    public static YearMonth getStartMonth(int monthAmount){
        return getCurrentMonth().minusMonths(monthAmount);
    }

    public static YearMonth getStartMonth(){
        return getCurrentMonth().minusMonths(100);
    }

    public static YearMonth getEndMonth(int monthAmount){
        return getCurrentMonth().plusMonths(monthAmount);
    }

    public static YearMonth getEndMonth(){
        return getCurrentMonth().plusMonths(100);
    }
}
