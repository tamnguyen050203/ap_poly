package com.example.myfpl.helpers.retrofit;

import android.content.Context;

import com.example.myfpl.helpers.SharedPreferencesHelper;

public class TokenRepository {
    public static String TOKEN = "USER_TOKEN";
    public static String REFRESH_TOKEN = "REFRESH_TOKEN";

    public static String getToken(Context context) {
        return SharedPreferencesHelper.getSharedPreferenceString(context, TOKEN, TOKEN);
    }

    public static void setToken(Context context, String token) {
        SharedPreferencesHelper.setSharedPreferenceString(context, TOKEN, token);
    }

    public static String getRefreshToken(Context context) {
        return SharedPreferencesHelper.getSharedPreferenceString(context, REFRESH_TOKEN, REFRESH_TOKEN);
    }

    public static void setRefreshToken(Context context, String refreshToken) {
        SharedPreferencesHelper.setSharedPreferenceString(context, REFRESH_TOKEN, refreshToken);
    }
}
