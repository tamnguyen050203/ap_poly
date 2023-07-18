package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myfpl.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init(){

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}