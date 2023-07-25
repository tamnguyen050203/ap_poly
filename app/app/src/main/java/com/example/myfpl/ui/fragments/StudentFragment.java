package com.example.myfpl.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfpl.R;
import com.example.myfpl.adapters.InfoListAdapter;
import com.example.myfpl.databinding.FragmentStudentBinding;
import com.example.myfpl.models.InfoModel;
import com.example.myfpl.util.DateUtil;
import com.example.myfpl.viewmodels.NavigationViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment {
    private FragmentStudentBinding binding;
    private InfoListAdapter infoListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public void init() {
        setupInfoList();
    }

    public void setupInfoList() {
        List<InfoModel> infoModelList = new ArrayList<InfoModel>();
        infoModelList.add(new InfoModel("Mã số sinh viên", "PS24559"));
        infoModelList.add(new InfoModel("Giới tính", "nam"));
        infoModelList.add(new InfoModel("Ngày sinh", "05/09/2004"));
        infoModelList.add(new InfoModel("Chuyên ngành", "Lập trình mobile"));
        System.out.println("infoModelList: " + infoModelList);
        infoListAdapter = new InfoListAdapter(infoModelList, getContext());
        binding.listInfoStudent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.listInfoStudent.setAdapter(infoListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}