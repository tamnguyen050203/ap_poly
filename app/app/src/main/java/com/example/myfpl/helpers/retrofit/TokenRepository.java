package com.example.myfpl.helpers.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.myfpl.helpers.SharedPreferencesHelper;

public class TokenRepository {
    public static String TOKEN = "USER_TOKEN";
    public static String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final Context context;
    @SuppressLint("StaticFieldLeak")
    private static TokenRepository instance;

    private TokenRepository(Context context) {
        this.context = context;
    }

    public static TokenRepository getInstance(Context context) {
        if (instance == null) instance = new TokenRepository(context);
        return instance;
    }

    public String getToken() {
        return SharedPreferencesHelper.getSharedPreferenceString(context, TOKEN, TOKEN);
    }

    public void setToken(String token) {
        SharedPreferencesHelper.setSharedPreferenceString(context, TOKEN, token);
    }

    public String getRefreshToken() {
        return SharedPreferencesHelper.getSharedPreferenceString(context, REFRESH_TOKEN, REFRESH_TOKEN);
    }

    public void setRefreshToken(String refreshToken) {
        SharedPreferencesHelper.setSharedPreferenceString(context, REFRESH_TOKEN, refreshToken);
    }
}
