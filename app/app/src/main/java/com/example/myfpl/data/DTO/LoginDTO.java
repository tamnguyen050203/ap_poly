package com.example.myfpl.data.DTO;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDTO {

    public static class LoginResponseDTO implements Serializable {
        @SerializedName("access_token")
        private String accessToken;

        @SerializedName("refreshToken")
        private String refreshToken;

        public LoginResponseDTO(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        @NonNull
        @Override
        public String toString() {
            return "Access Token: " + accessToken + " Refresh Token: " + refreshToken;
        }
    }

    public static class LoginRequestDTO implements Serializable {
        private String email;
        private String name;

        @SerializedName("provider_id")
        private String providerId;
        private String avatar;

        public LoginRequestDTO(String email, String username, String provideId, String avatar) {
            this.email = email;
            this.name = username;
            this.providerId = provideId;
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProviderId() {
            return providerId;
        }

        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
