package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myfpl.databinding.ActivityNotifyBinding;

public class NotifyActivity extends AppCompatActivity {
    private ActivityNotifyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}