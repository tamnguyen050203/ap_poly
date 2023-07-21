package com.example.myfpl.helpers.retrofit;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class RetrofitAuthenticator implements Authenticator {
    private Context context;

    public RetrofitAuthenticator(Context context) {
        this.context = context;
    }



    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NonNull Response response) throws IOException {
        return null;
    }
}
