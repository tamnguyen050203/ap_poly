package com.example.myfpl.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfpl.R;
import com.example.myfpl.adapters.NotificationAdapter;
import com.example.myfpl.adapters.TuitionListAdapter;
import com.example.myfpl.databinding.FragmentHomeBinding;
import com.example.myfpl.databinding.FragmentTuitionBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.models.TuitionModel;

import java.util.List;

public class TuitionFragment extends Fragment {

    private List<TuitionModel> tuitionList;

    private TuitionListAdapter adapter;
    private FragmentTuitionBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTuitionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTuitionList();
    }

    public void setupTuitionList() {
        adapter = new TuitionListAdapter(tuitionList, getContext(), new TuitionListAdapter.HandleEvent() {
            @Override
            public void OnItemClick(TuitionModel tuitionModel, int index) {

            }
        });
        binding.listTuition.setAdapter(adapter);
    }
}