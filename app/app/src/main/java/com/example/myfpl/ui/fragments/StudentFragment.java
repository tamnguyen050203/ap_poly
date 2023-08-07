package com.example.myfpl.ui.fragments;

import android.annotation.SuppressLint;
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

import com.bumptech.glide.Glide;
import com.example.myfpl.adapters.InfoListAdapter;
import com.example.myfpl.data.DTO.StudentDTO;
import com.example.myfpl.data.apis.StudentService;
import com.example.myfpl.databinding.FragmentStudentBinding;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;
import com.example.myfpl.models.InfoModel;
import com.example.myfpl.models.StudentModel;
import com.example.myfpl.ui.activities.ExtensionScreen;
import com.example.myfpl.viewmodels.NavigationViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentFragment extends Fragment {
    private static final String TAG = StudentFragment.class.getSimpleName();
    private FragmentStudentBinding binding;
    private NavigationViewModel navigationViewModel;
    private Disposable mDisposable;
    private InfoListAdapter infoListAdapter;
    private List<InfoModel> infoModelList = new ArrayList<>();

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
        navigationViewModel = new ViewModelProvider(requireActivity()).get(NavigationViewModel.class);

        setupInfoList();
        addEvent();
    }

    public void setupInfoList() {
        getInfoData();
        infoListAdapter = new InfoListAdapter(infoModelList, getContext());
        binding.listInfoStudent
                .setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.listInfoStudent.setAdapter(infoListAdapter);
    }

    public void addEvent() {
        binding.buttonExtension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireContext().startActivity(new Intent(requireContext(), ExtensionScreen.class));
            }
        });
    }

    public void getInfoData() {
        navigationViewModel.getStudent().observe(getViewLifecycleOwner(), new Observer<StudentModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(StudentModel studentModel) {
                if (studentModel != null) {
                    infoModelList.add(new InfoModel("Ngày sinh", studentModel.getDob()));
                    infoModelList.add(new InfoModel("Giới tính", "Nam"));
                    infoModelList.add(new InfoModel("Số điện thoại", studentModel.getPhone()));
                    infoModelList.add(new InfoModel("Email", studentModel.getEmail()));
                    // infoModelList.add(new InfoModel("Lớp", studentModel.getClass()));
                    infoModelList
                            .add(new InfoModel("Chuyên ngành", studentModel.getSpecialize()));
                    binding.email.setText(studentModel.getEmail());
                    infoListAdapter.notifyDataSetChanged();
                    binding.nameStudent.setText(studentModel.getName());
                    Glide.with(requireContext()).load(studentModel.getAvatar())
                            .into(binding.avatarStudent);
                    Glide.with(requireContext()).load(studentModel.getAvatar())
                            .into(binding.subAvatarStudent);
                }
            }
        });
    }

    public Disposable getDisposable() {
        return mDisposable;
    }

    public void setDisposable(Disposable disposable) {
        this.mDisposable = disposable;
    }
}
