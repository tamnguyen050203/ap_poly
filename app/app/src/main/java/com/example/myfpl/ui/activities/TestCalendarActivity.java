package com.example.myfpl.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ActivityTestCalendarBinding;
import com.example.myfpl.util.ScheduleUtils;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.lang.reflect.Array;
import java.util.Calendar;

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
        binding.addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });
    }

    private final ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                if (isGranted.containsValue(false)) {
                    Log.d(TAG, "Chans oiw laf chasn am: ");
                } else {
                    addEvent();
                }
            });

    public void requestPermission(){
        if (hasPermissions(this, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)) {
            addEvent();
        } else {
            requestPermissionLauncher.launch(new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR});
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addEvent(){

    }
}