package com.example.myfpl.util;

public class StringUtil {
    public static boolean nullOrEmpty(String text){
        return text != null && text.length() != 0;
    }
    public static boolean isFPLDomain(String email){
        return email.contains("fpt.edu.vn");
    }
}
