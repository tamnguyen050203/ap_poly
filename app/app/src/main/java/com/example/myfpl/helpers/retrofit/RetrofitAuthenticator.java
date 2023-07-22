package com.example.myfpl.helpers.retrofit;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfpl.data.DTO.RefreshTokenDTO;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class RetrofitAuthenticator implements Authenticator {
    private static final String TAG = RetrofitAuthenticator.class.getSimpleName();
    private final TokenRepository tokenRepository;
    private final AuthServiceHolder authServiceHolder;
    private static RetrofitAuthenticator instance;

    private RetrofitAuthenticator(TokenRepository tokenRepository, AuthServiceHolder authServiceHolder) {
        this.tokenRepository = tokenRepository;
        this.authServiceHolder = authServiceHolder;
    }

    public static RetrofitAuthenticator getInstance(TokenRepository tokenRepository, AuthServiceHolder authServiceHolder) {
        if (instance == null)
            instance = new RetrofitAuthenticator(tokenRepository, authServiceHolder);
        return instance;
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NonNull Response response) throws IOException {
        if(responseCount(response) >= 3){
            return null;
        }

        if (authServiceHolder == null) {
            Log.e(TAG, "authenticate: AuthServiceHolder is null");
            return null;
        }

        Log.d(TAG, "authenticate: " + tokenRepository.getRefreshToken());

        Call<RefreshTokenDTO.RefreshTokenResponseDTO> call = authServiceHolder.authService.refreshToken(
                tokenRepository.getRefreshToken()
        );
        retrofit2.Response<RefreshTokenDTO.RefreshTokenResponseDTO> res = call.execute();

        if (res.code() == 200) {
            if (res.body() != null) {
                tokenRepository.setToken(res.body().getAccessToken());
//                Log.d(TAG, "authenticate: " + res.body().getAccessToken());
                return response.request().newBuilder().header("Authorization", "Bearer " + res.body().getAccessToken()).build();
//                return null;
            } else {
                Log.e(TAG, "onSuccess: error no response body");
                return null;
            }
        } else if (res.code() == 401) {
            Log.e(TAG, "onSuccess: refresh token is expires -> auto logout");
            return null;
        }

        return null;
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
