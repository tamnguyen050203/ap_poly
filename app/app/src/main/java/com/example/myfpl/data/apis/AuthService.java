package com.example.myfpl.data.apis;

import com.example.myfpl.data.DTO.LoginDTO;
import com.example.myfpl.data.DTO.RefreshTokenDTO;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {
    @POST("api/auth/login")
    Single<LoginDTO.LoginResponseDTO> login(@Body LoginDTO.LoginRequestDTO loginRequestDTO);

    @FormUrlEncoded
    @POST("api/auth/refresh-token")
    Call<RefreshTokenDTO.RefreshTokenResponseDTO> refreshToken(@Field("refresh_token") String refresh_token);
}
