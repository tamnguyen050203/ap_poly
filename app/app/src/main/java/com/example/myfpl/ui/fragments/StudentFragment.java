package com.example.myfpl.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.myfpl.util.DateUtil;
import com.example.myfpl.viewmodels.NavigationViewModel;
import com.example.myfpl.viewmodels.TestViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentFragment extends Fragment {
    private static final String TAG = StudentFragment.class.getSimpleName();
    private FragmentStudentBinding binding;
    private Disposable mDisposable;
    private InfoListAdapter infoListAdapter;
    private List<InfoModel> infoModelList = new ArrayList<InfoModel>();

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
        StudentService studentService = RetrofitHelper.createService(StudentService.class, getContext());
        studentService.getStudentInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<StudentDTO.StudentResponseDTO>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(
                            StudentDTO.@io.reactivex.rxjava3.annotations.NonNull StudentResponseDTO studentResponseDTO) {
                        Log.d(TAG, "onSuccess: " + studentResponseDTO.getStudent().getName());
                        infoModelList.add(new InfoModel("Ngày sinh", studentResponseDTO.getStudent().getDob()));
                        infoModelList.add(new InfoModel("Giới tính", "Nam"));
                        infoModelList.add(new InfoModel("Số điện thoại", studentResponseDTO.getStudent().getPhone()));
                        infoModelList.add(new InfoModel("Email", studentResponseDTO.getStudent().getEmail()));
                        // infoModelList.add(new InfoModel("Lớp", studentModel.getClass()));
                        infoModelList
                                .add(new InfoModel("Chuyên ngành", studentResponseDTO.getStudent().getSpecialize()));
                        binding.email.setText(studentResponseDTO.getStudent().getEmail());
                        infoListAdapter.notifyDataSetChanged();
                        binding.nameStudent.setText(studentResponseDTO.getStudent().getName());
                        Glide.with(requireContext()).load(studentResponseDTO.getStudent().getAvatar())
                                .into(binding.avatarStudent);
                        Glide.with(requireContext()).load(studentResponseDTO.getStudent().getAvatar())
                                .into(binding.subAvatarStudent);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

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
