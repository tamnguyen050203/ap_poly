package com.example.myfpl.util;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static String getCurrentSession() {
        @SuppressLint("SimpleDateFormat") int hours = Integer.parseInt(new SimpleDateFormat("HH").format(new Date(System.currentTimeMillis())));
        if (1 <= hours && hours < 10) {
            return "Chào buổi sáng";
        } else if (10 <= hours && hours < 18) {
            return "Chào buổi chiều";
        } else {
            return "Chào buổi tối";
        }
    }

    public static String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public static Date fromStringToDate(String format, String value) throws ParseException {
        return new SimpleDateFormat(format).parse(value);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeFromString(String format, String value) {
        try {
            return new SimpleDateFormat("hh:mm").format(fromStringToDate(format, value));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String getMeridiemFromString(String format, String value) {
        try {
            return new SimpleDateFormat("aa").format(fromStringToDate(format, value));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateFromString(String format, String value) {
        try {
            return new SimpleDateFormat("dd-MM").format(fromStringToDate(format, value));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static YearMonth getCurrentMonth() {
        return YearMonth.now();
    }

    public static YearMonth getStartMonth(int monthAmount) {
        return getCurrentMonth().minusMonths(monthAmount);
    }

    public static YearMonth getStartMonth() {
        return getCurrentMonth().minusMonths(100);
    }

    public static YearMonth getEndMonth(int monthAmount) {
        return getCurrentMonth().plusMonths(monthAmount);
    }

    public static YearMonth getEndMonth() {
        return getCurrentMonth().plusMonths(100);
    }

    public static String ConvertTimeToString(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(input, formatter);
        Log.d("Date util","input:"+input);
        Log.d("Date util",zonedDateTime.getHour()+":"+zonedDateTime.getMinute());
        long givenDate = zonedDateTime.toEpochSecond();
        long current = System.currentTimeMillis() / 1000;
        long timeDistance = current - givenDate;
        Log.d("date util", timeDistance + "");
        String convertedTime = "";
        if (timeDistance <= 59) {
            convertedTime = timeDistance + " giây trước";
        } else if (timeDistance <= 3600) {
            long convertedToMinute = Math.round(timeDistance / 60);
            convertedTime = convertedToMinute + " phút trước";
        } else if (timeDistance < 86400) {
            long convertedToHour = Math.round(timeDistance / 3600);
            convertedTime = convertedToHour + " giờ trước";
        } else if (timeDistance < 604800) {
            long convertedToDay = Math.round(timeDistance / 86400);
            convertedTime = convertedToDay + " ngày trước";
        } else if (timeDistance < 2629743) {
            long convertedToWeek = Math.round(timeDistance / 604800);
            convertedTime = convertedToWeek + " tuần trước";
        } else if (timeDistance < 31556926) {
            long convertedToMonth = Math.round(timeDistance / 2629743);
            convertedTime = convertedToMonth + " tháng trước";
        } else {
            long convertedToYear = Math.round(timeDistance / 31556926);
            convertedTime = convertedToYear + " năm trước";
        }
        System.out.println(convertedTime);
        return convertedTime;
    }

}
