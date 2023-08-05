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

import com.example.myfpl.adapters.NotificationAdapter;
import com.example.myfpl.data.DTO.NotificationDTO;
import com.example.myfpl.databinding.FragmentNotificationBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.viewmodels.NotifyViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class NotificationFragment extends Fragment {
    private FragmentNotificationBinding binding;
    private NotifyViewModel viewModel;
    private String type;
    private List<NotificationModel> list;
    private NotificationAdapter adapter;

    public NotificationFragment() {

    }

    public static NotificationFragment instance(String type) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString("type");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity(),
                new NotifyViewModel.NotifyViewModelFactory(requireActivity().getApplication()))
                .get(NotifyViewModel.class);
        AppListener();
    }

    public List<NotificationModel> filteredList(String type, List<NotificationModel> unfilteredList) {
        if (Objects.equals(type.toLowerCase(Locale.ROOT), "Tất Cả".toLowerCase(Locale.ROOT)))
            return unfilteredList;
        List<NotificationModel> filtered = new ArrayList<>();
        for (NotificationModel element : unfilteredList) {
            if (Objects.equals(element.getType().toLowerCase(Locale.ROOT), type.toLowerCase(Locale.ROOT))) {
                filtered.add(element);
            }
        }
        return filtered;
    }

    public void AppListener() {
        viewModel.getLiveDataNotification().observe(getViewLifecycleOwner(), new Observer<NotificationDTO>() {
            @Override
            public void onChanged(NotificationDTO notificationDTO) {
                if (notificationDTO == null) {
                    list = new ArrayList<>();
                } else {
                    list = notificationDTO.getNotify().getData();
                    adapter = new NotificationAdapter(filteredList(type, list), getContext());
                    binding.rvNotification.setAdapter(adapter);
                }
            }
        });
    }


}