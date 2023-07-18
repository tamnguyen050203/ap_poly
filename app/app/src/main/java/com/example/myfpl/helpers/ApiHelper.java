package com.example.myfpl.helpers;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor);
    private static Retrofit instance;
    public static Retrofit getInstance(){
        if(instance == null){
            String bareUrl = "https://63e44b8d4474903105e94a86.mockapi.io/";
            instance = new Retrofit.Builder()
                    .baseUrl(bareUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(okBuilder.build())
                    .build();
        }
        return instance;
    }
}
