package com.example.myfpl.helpers.retrofit;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myfpl.data.apis.AuthService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Retrofit instance;

    public static Retrofit getInstance(Context context) {

        if (instance == null) {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();

            TokenRepository tokenRepository = TokenRepository.getInstance(context);

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new Interceptor() {
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {

                            Request request = chain.request();
                            Request.Builder builder = request.newBuilder();
                            if (tokenRepository.getToken() != null) {
                                builder.addHeader("Authorization", "Bearer " + tokenRepository.getToken());
                            }
                            request = builder.build();
                            return chain.proceed(request);
                        }
                    })
                    .authenticator(RetrofitAuthenticator.getInstance(tokenRepository, authServiceHolder));


            String bareUrl = "http://10.0.2.2:8000";
//            String bareUrl = "http://192.168.1.5:8000";
            instance = new Retrofit.Builder()
                    .baseUrl(bareUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(okBuilder.build())
                    .build();

            authServiceHolder.set(instance.create(AuthService.class));
        }
        return instance;
    }

    public static <T> T createService(Class<T> service, Context context) {
        return getInstance(context).create(service);
    }

}
