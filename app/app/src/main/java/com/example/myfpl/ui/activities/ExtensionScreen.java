package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ActivityExtensionScreenBinding;

public class ExtensionScreen extends AppCompatActivity implements View.OnClickListener {

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
        addEvent();
    }

    public void addEvent() {
        binding.btnExtension.setOnClickListener(ExtensionScreen.this);
        binding.btnCommunity.setOnClickListener(ExtensionScreen.this);
        binding.btnFee.setOnClickListener(ExtensionScreen.this);
        binding.btnSurvey.setOnClickListener(ExtensionScreen.this);
    }

    public void onBack(View view) {
        onBackPressed();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_extension:
                startActivity(new Intent(ExtensionScreen.this, ServiceScreen.class));
                break;
            case R.id.btn_community:
                startActivity(new Intent(ExtensionScreen.this, CommunityScreen.class));
                break;
            case R.id.btn_fee:
                startActivity(new Intent(ExtensionScreen.this, TuitionActivity.class));
                break;
            case R.id.btn_survey:
                startActivity(new Intent(ExtensionScreen.this, SurveyActivity.class));
                break;
        }
    }
}