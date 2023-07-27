package com.example.myfpl.data.DTO;

import com.google.gson.annotations.SerializedName;

public class RefreshTokenDTO {
    public static class RefreshTokenResponseDTO {
        @SerializedName("access_token")
        private String accessToken;

//        public RefreshTokenResponseDTO(String accessToken, int status) {
////            super(status);
//            this.accessToken = accessToken;
//        }

        public RefreshTokenResponseDTO(String accessToken) {
//            super(status);
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
