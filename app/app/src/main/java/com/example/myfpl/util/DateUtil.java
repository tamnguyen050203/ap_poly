package com.example.myfpl.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
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
}
