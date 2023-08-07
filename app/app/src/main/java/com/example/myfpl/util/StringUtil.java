package com.example.myfpl.util;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import java.util.Locale;

public class StringUtil {
    public static boolean nullOrEmpty(String text){
        return text != null && text.length() != 0;
    }
    public static boolean isFPLDomain(String email){
        return email.contains("fpt.edu.vn");
    }

    public static Spanned builderTitle(String section, String content){
//        Log.d("TextValue", "builderTitle: " + "<font color=\"#808A9A\">"+section+":</font> " + "<font color=\"#011635\">"+content+"</font>");
         return Html.fromHtml("<font color=\"#808A9A\">"+section+":</font> " + "<font color=\"#011635\">"+content+"</font>");
    }

    public static String capitalize(String value){
        return value.substring(0, 1).toUpperCase(new Locale("vi", "VN")) + value.substring(1, value.length());
    }

    public static String makeupNumber(int number){
        if(number <= 9){
            return "0" + number;
        }
        return number + "";
    }
}
