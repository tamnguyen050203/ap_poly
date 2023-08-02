package com.example.myfpl.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfpl.adapters.ScheduleViewPager;
import com.example.myfpl.databinding.FragmentScheduleBinding;
import com.example.myfpl.viewmodels.ScheduleFragmentViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScheduleFragment extends Fragment {

    private FragmentScheduleBinding binding;
    private ScheduleFragmentViewModel viewModel;
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
        addEvent();
    }

    public void init() {
        //setup viewmodel
        viewModel = new ViewModelProvider(requireActivity()).get(ScheduleFragmentViewModel.class);

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

        viewModel.getIsListScrolling().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isScroll) {
                if (isScroll) {
                    collapseCalendar();
                }
            }
        });
    }

    public void addEvent() {

    }

    public void collapseCalendar() {
        if (binding.calendarView.getExpanded()) {
            binding.calendarView.collapse(400);
        }
    }
}