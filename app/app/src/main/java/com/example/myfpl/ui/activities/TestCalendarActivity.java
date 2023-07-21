package com.example.myfpl.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ActivityTestCalendarBinding;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

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
        binding.calendarView.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Log.d(TAG, "onDaySelect: " + binding.calendarView.getSelectedItem());
            }

            @Override
            public void onItemClick(@NonNull View v) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int position) {

            }

            @Override
            public void onClickListener() {

            }

            @Override
            public void onDayChanged() {

            }
        });
        binding.calendarView.plusDay(7);
        binding.calendarView.minusDay(7);
        Calendar c = Calendar.getInstance();
        binding.calendarView.addEventTag(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), getColor(R.color.primary_color));
    }
}