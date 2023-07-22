package com.example.myfpl.helpers.retrofit;

import androidx.annotation.Nullable;

import com.example.myfpl.data.apis.AuthService;

public class AuthServiceHolder {
    AuthService authService = null;

    @Nullable
    public AuthService get() {
        return authService;
    }

    public void set(AuthService authService) {
        this.authService = authService;
    }
}
