package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myfpl.databinding.ItemCurrentSubjectBinding;
import com.example.myfpl.models.TestModelSchedule;

import java.util.ArrayList;

public class SliderViewPager extends PagerAdapter {
    private final Context context;

    public ArrayList<TestModelSchedule> getListData() {
        return listData;
    }

    public void setListData(ArrayList<TestModelSchedule> listData) {
        this.listData = listData;
    }

    private ArrayList<TestModelSchedule> listData;
    private EventHandler eventHandler;

    public SliderViewPager(Context context, ArrayList<TestModelSchedule> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public int getCount() {
        if (listData == null) return 0;
        return listData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TestModelSchedule schedule = listData.get(position);
        if (schedule == null) return null;
        ItemCurrentSubjectBinding binding = ItemCurrentSubjectBinding.inflate(
                LayoutInflater.from(container.getContext()),
                container,
                false
        );

        binding.subjectCode.setText(schedule.getSubjectCode());
        binding.subjectTitle.setText(schedule.getScheduleTitle());
        binding.lecturer.setText(schedule.getLecturer());
        binding.place.setText(schedule.getRoom() + " - " + schedule.getAmphitheater());

        Log.d("ViewPager", "instantiateItem: " + schedule.getScheduleTitle());

        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface EventHandler {
        void onItemClick(TestModelSchedule schedule, int index);
    }
}
