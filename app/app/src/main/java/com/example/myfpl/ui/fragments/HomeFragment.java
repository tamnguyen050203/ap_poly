package com.example.myfpl.ui.fragments;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.myfpl.R;
import com.example.myfpl.adapters.ScheduleListAdapter;
import com.example.myfpl.databinding.FragmentHomeBinding;
import com.example.myfpl.models.TestModelSchedule;
import com.example.myfpl.ui.activities.NavigationActivity;
import com.example.myfpl.util.ToastApp;
import com.example.myfpl.viewmodels.NavigationViewModel;

import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private NavigationViewModel viewModel;
    private ScheduleListAdapter scheduleListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        addListener();
    }

    public void init() {
        binding.motionLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        binding.listTestSchedule.setNestedScrollingEnabled(false);

        if (getActivity() != null) {
            viewModel = new NavigationViewModel(getActivity().getApplication());
        }

        scheduleListAdapter = new ScheduleListAdapter(new ScheduleListAdapter.HandleEventListItem() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemClick(TestModelSchedule testModelSchedule, int itemIndex) {
                TransitionManager.beginDelayedTransition(binding.listTestSchedule, new AutoTransition());
                ToastApp.show(requireContext(), testModelSchedule.getScheduleTitle());
            }

            @Override
            public void onAlarmClick(TestModelSchedule testModelSchedule, int itemIndex) {
                TransitionManager.beginDelayedTransition(binding.listTestSchedule, new AutoTransition());
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.listTestSchedule.setLayoutManager(layoutManager);
        binding.listTestSchedule.setAdapter(scheduleListAdapter);
    }

    public void addListener() {
        if (viewModel != null) {
            viewModel.getListTestSchedule().observe(getViewLifecycleOwner(), new Observer<List<TestModelSchedule>>() {
                @Override
                public void onChanged(List<TestModelSchedule> testModelSchedules) {
                    scheduleListAdapter.setListData(testModelSchedules);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}