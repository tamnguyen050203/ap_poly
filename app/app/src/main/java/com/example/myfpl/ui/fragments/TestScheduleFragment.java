package com.example.myfpl.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfpl.databinding.FragmentNormalScheduleBinding;
import com.example.myfpl.viewmodels.ScheduleFragmentViewModel;

public class TestScheduleFragment extends Fragment {
    private FragmentNormalScheduleBinding binding;
    private ScheduleFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNormalScheduleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(requireActivity()).get(ScheduleFragmentViewModel.class);
    }
}