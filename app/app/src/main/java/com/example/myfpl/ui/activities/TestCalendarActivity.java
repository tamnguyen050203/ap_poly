package com.example.myfpl.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfpl.databinding.ActivityTestCalendarBinding;

public class TestCalendarActivity extends AppCompatActivity {
    private static final String TAG = TestCalendarActivity.class.getSimpleName();
    private ActivityTestCalendarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init() {

    }
}