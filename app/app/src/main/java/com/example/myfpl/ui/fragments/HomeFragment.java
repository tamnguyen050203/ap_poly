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
import com.example.myfpl.adapters.TestScheduleListAdapter;
import com.example.myfpl.data.DTO.NotificationDTO;
import com.example.myfpl.data.DTO.ScheduleDTO;
import com.example.myfpl.databinding.FragmentHomeBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.TestScheduleModel;
import com.example.myfpl.ui.activities.DetailNotificationActivity;
import com.example.myfpl.ui.activities.NotifyActivity;
import com.example.myfpl.util.DateUtil;
import com.example.myfpl.viewmodels.NotifyViewModel;
import com.example.myfpl.viewmodels.ScheduleViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private ScheduleViewModel viewModel;
    private NotifyViewModel notifyViewModel;
    private List<TestScheduleModel> listTestSchedule;
    private List<ScheduleModel> listSchedule;
    private TestScheduleListAdapter testScheduleListAdapter;
    private ScheduleListAdapter scheduleListAdapter;
    private SmallNotificationAdapter smallNotificationAdapter;
    private Map<String, String> map;
    private List<NotificationModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        map = new HashMap<>();
        String date = DateUtil.getCurrentDate();
        map.put("date", date);
        init();
        addListener();
    }

    public void init() {
        if (getActivity() != null) {
            viewModel = new ViewModelProvider(requireActivity()).get(ScheduleViewModel.class);
            notifyViewModel = new ViewModelProvider(requireActivity()).get(NotifyViewModel.class);
        }
        viewModel.getTestScheduleData(map);
        viewModel.getScheduleData(map);
        notifyViewModel.getNotificationData(6);
        binding.textSession.setText(DateUtil.getCurrentSession());

    }

    public void setupSmallNotification(ArrayList<NotificationModel> list) {
        smallNotificationAdapter = new SmallNotificationAdapter(requireContext(), list, new SmallNotificationAdapter.HandleEvent() {
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

    public void setupSlider() {

    }

    public void addListener() {

        notifyViewModel.getLiveDataNotification().observe(getViewLifecycleOwner(), new Observer<NotificationDTO>() {
            @Override
            public void onChanged(NotificationDTO notificationDTO) {
                if (notificationDTO == null) {
                    list = new ArrayList<>();
                } else {
                    list = notificationDTO.getNotify().getData();
                }
                setupSmallNotification((ArrayList<NotificationModel>) list);
            }
        });

        viewModel.getLiveDataSchedule().observe(getViewLifecycleOwner(), new Observer<ScheduleDTO.ScheduleResponseDTO>() {
            @Override
            public void onChanged(ScheduleDTO.ScheduleResponseDTO scheduleResponseDTO) {
                if (scheduleResponseDTO == null) {
                    listSchedule = new ArrayList<>();
                } else {
                    listSchedule = scheduleResponseDTO.getSchedules().getData();
                    Log.d(TAG, "onChanged: " + listSchedule.size());
                    scheduleListAdapter = new ScheduleListAdapter(listSchedule, getContext());
                    binding.listSchedule.setAdapter(scheduleListAdapter);
                }
            }
        });

        viewModel.getliveDataTestSchedule().observe(getViewLifecycleOwner(), new Observer<ScheduleDTO.TestScheduleResponseDTO>() {
            @Override
            public void onChanged(ScheduleDTO.TestScheduleResponseDTO TestScheduleResponseDTO) {
                if (TestScheduleResponseDTO == null) {
                    listTestSchedule = new ArrayList<>();
                } else {
                    listTestSchedule = TestScheduleResponseDTO.getSchedules().getData();
                    testScheduleListAdapter = new TestScheduleListAdapter(listTestSchedule, getContext());
                    binding.listTestSchedule.setAdapter(testScheduleListAdapter);
                }
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
        viewModel.getTestScheduleData(map);
        viewModel.getScheduleData(map);
        notifyViewModel.getNotificationData(6);
    }
}