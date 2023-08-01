package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ActivityExtensionScreenBinding;
import com.example.myfpl.databinding.ActivityServiceBinding;

public class ServiceActivity extends AppCompatActivity {

    private ActivityServiceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }
    public void init(){

    }
}