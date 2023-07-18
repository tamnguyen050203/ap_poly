package com.example.myfpl.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfpl.R;
import com.example.myfpl.databinding.FragmentStudentBinding;

public class StudentFragment extends Fragment {
    private FragmentStudentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}