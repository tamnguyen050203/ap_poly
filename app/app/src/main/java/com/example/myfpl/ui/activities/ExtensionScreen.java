package com.example.myfpl.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ActivityExtensionScreenBinding;

public class ExtensionScreen extends AppCompatActivity {

    private ActivityExtensionScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension_screen);
        binding = ActivityExtensionScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    public void init() {

    }

    public void onBack(View view) {
        Intent intent = new Intent(ExtensionScreen.this, SplashScreen.class);
        startActivity(intent);
        finish();
    }
}