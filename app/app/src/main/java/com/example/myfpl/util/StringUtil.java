package com.example.myfpl.util;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;

public class StringUtil {
    public static boolean nullOrEmpty(String text){
        return text != null && text.length() != 0;
    }
    public static boolean isFPLDomain(String email){
        return email.contains("fpt.edu.vn");
    }

    public static Spanned builderTitle(String section, String content){
        Log.d("TextValue", "builderTitle: " + "<font color=\"#808A9A\">"+section+":</font> " + "<font color=\"#011635\">"+content+"</font>");
         return Html.fromHtml("<font color=\"#808A9A\">"+section+":</font> " + "<font color=\"#011635\">"+content+"</font>");
    }
}
