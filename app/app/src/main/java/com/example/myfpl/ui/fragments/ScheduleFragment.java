package com.example.myfpl.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfpl.R;
import com.example.myfpl.adapters.ScheduleViewPager;
import com.example.myfpl.databinding.FragmentScheduleBinding;
import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.TestScheduleModel;
import com.example.myfpl.viewmodels.ScheduleFragmentViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private FragmentScheduleBinding binding;
    private ScheduleFragmentViewModel scheduleFragmentViewModel;
    private static final String TAG = ScheduleFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public void init() {
        //setup viewmodel
        scheduleFragmentViewModel = new ViewModelProvider(requireActivity()).get(ScheduleFragmentViewModel.class);
        scheduleFragmentViewModel.setCurrentDateSelected(binding.calendarView.getSelectedItem());

        //setup tab layout;
        binding.viewPager.setAdapter(new ScheduleViewPager(requireActivity()));
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) tab.setText("Lịch Học");
                else tab.setText("Lịch Thi");
            }
        }).attach();
        binding.tabLayout.setSmoothScrollingEnabled(true);

        addEvent();
    }

    public void addEvent() {
        scheduleFragmentViewModel.getIsListScrolling().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isScroll) {
                if (isScroll) {
                    collapseCalendar();
                }
            }
        });
        binding.calendarView.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                scheduleFragmentViewModel.setCurrentDateSelected(binding.calendarView.getSelectedItem());
            }

            @Override
            public void onItemClick(@Nullable View v) {

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

        scheduleFragmentViewModel.getListScheduleRes().observe(getViewLifecycleOwner(), new Observer<List<ScheduleModel>>() {
            @Override
            public void onChanged(List<ScheduleModel> listScheduleModel) {
                for(ScheduleModel scheduleModel : listScheduleModel){
                    binding.calendarView.addEventTag(
                            Integer.parseInt(scheduleModel.getDate().substring(0, 4)),
                            Integer.parseInt(scheduleModel.getDate().substring(5, 7)) - 1,
                            Integer.parseInt(scheduleModel.getDate().substring(8)),
                            Color.parseColor("#FFA04B")
                    );
                }
            }
        });

        scheduleFragmentViewModel.getListTestScheduleRes().observe(getViewLifecycleOwner(), new Observer<List<TestScheduleModel>>() {
            @Override
            public void onChanged(List<TestScheduleModel> listTestScheduleModel) {
                for(TestScheduleModel testScheduleModel : listTestScheduleModel){
                    binding.calendarView.addEventTag(
                            Integer.parseInt(testScheduleModel.getDate().substring(0, 4)),
                            Integer.parseInt(testScheduleModel.getDate().substring(5, 7)) - 1,
                            Integer.parseInt(testScheduleModel.getDate().substring(8)),
                            Color.parseColor("#EC4310")
                    );
                }
            }
        });
    }

    public void collapseCalendar() {
        if (binding.calendarView.getExpanded()) {
            binding.calendarView.collapse(400);
        }
    }
}