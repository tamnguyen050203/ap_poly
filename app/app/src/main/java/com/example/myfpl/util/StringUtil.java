package com.example.myfpl.util;

import android.text.Html;
import android.text.Spanned;

public class StringUtil {
    public static boolean nullOrEmpty(String text){
        return text != null && text.length() != 0;
    }
    public static boolean isFPLDomain(String email){
        return email.contains("fpt.edu.vn");
    }

    public static Spanned builderTitle(String section, String content){
         return Html.fromHtml("<font color=\"#80011635\">"+section+":</font> " + "<font color=\"#011635\">"+content+"</font>");
    }
}
