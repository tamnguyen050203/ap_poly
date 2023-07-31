package com.example.myfpl.ui.fragments;

import static com.example.myfpl.util.DateUtil.ConvertTimeToString;

import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myfpl.adapters.ScheduleListAdapter;
import com.example.myfpl.adapters.SmallNotificationAdapter;
import com.example.myfpl.databinding.FragmentHomeBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.models.TestModelSchedule;
import com.example.myfpl.ui.activities.DetailNotificationActivity;
import com.example.myfpl.ui.activities.NotifyActivity;
import com.example.myfpl.util.DateUtil;
import com.example.myfpl.viewmodels.NavigationViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private NavigationViewModel viewModel;
    private ScheduleListAdapter scheduleListAdapter;
    private ScheduleListAdapter scheduleTestListAdapter;
    private SmallNotificationAdapter smallNotificationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        binding.getRoot().setAnimatePa
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        addListener();
    }

    public void init() {
        if (getActivity() != null) {
            viewModel = new ViewModelProvider(requireActivity()).get(NavigationViewModel.class);
        }
        binding.textSession.setText(DateUtil.getCurrentSession());

        setupScheduleList();
        setupScheduleTestList();
        setupSmallNotification();
    }

    public void setupSmallNotification() {
        smallNotificationAdapter = new SmallNotificationAdapter(requireContext(), NotificationModel.getData(), new SmallNotificationAdapter.HandleEvent() {
            @Override
            public void OnItemClick(NotificationModel notificationModel, int index) {
                Intent i = new Intent(requireActivity(), DetailNotificationActivity.class);
                i.putExtra("detail", notificationModel);
                i.putExtra("createdAt", ConvertTimeToString(notificationModel.getCreated_at()));
                requireActivity().startActivity(i);
            }
        });
        binding.listNotify.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        binding.listNotify.setAdapter(smallNotificationAdapter);
    }

    public void setupScheduleList() {
        scheduleListAdapter = new ScheduleListAdapter(new ScheduleListAdapter.HandleEventListItem() {
            @Override
            public void onItemClick(TestModelSchedule testModelSchedule, int itemIndex) {
                Log.d(TAG, "onItemClick: " + testModelSchedule.getScheduleTitle());
            }

            @Override
            public void onAlarmClick(TestModelSchedule testModelSchedule, int itemIndex) {

            }
        });

        binding.listSchedule.setAdapter(scheduleListAdapter);
    }

    public void setupScheduleTestList() {
        scheduleTestListAdapter = new ScheduleListAdapter(new ScheduleListAdapter.HandleEventListItem() {
            @Override
            public void onItemClick(TestModelSchedule testModelSchedule, int itemIndex) {
                Log.d(TAG, "onItemClick: " + testModelSchedule.getScheduleTitle());
            }

            @Override
            public void onAlarmClick(TestModelSchedule testModelSchedule, int itemIndex) {

            }
        });

        binding.listTestSchedule.setAdapter(scheduleTestListAdapter);
    }

    public void setupSlider() {

    }

    public void addListener() {
        viewModel.getListSchedule().observe(getViewLifecycleOwner(), new Observer<List<TestModelSchedule>>() {
            @Override
            public void onChanged(List<TestModelSchedule> testModelSchedules) {
                scheduleListAdapter.setListData(testModelSchedules);
            }
        });

        viewModel.getListTestSchedule().observe(getViewLifecycleOwner(), new Observer<List<TestModelSchedule>>() {
            @Override
            public void onChanged(List<TestModelSchedule> testModelSchedules) {
                scheduleTestListAdapter.setListData(testModelSchedules);
            }
        });

        binding.btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().startActivity(new Intent(requireContext(), NotifyActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}