package com.example.myfpl.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfpl.data.DTO.StudentDTO;
import com.example.myfpl.data.apis.ScheduleService;
import com.example.myfpl.data.apis.StudentService;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;
import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.StudentModel;
import com.example.myfpl.models.TestModelSchedule;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NavigationViewModel extends AndroidViewModel {
    private static final String TAG = NavigationViewModel.class.getSimpleName();
    private ScheduleService scheduleService;
    private StudentService studentService;
    private final MutableLiveData<List<TestModelSchedule>> listTestSchedule = new MutableLiveData<>();
    private final MutableLiveData<List<ScheduleModel>> listSchedule = new MutableLiveData<>();
    private final MutableLiveData<StudentModel> student = new MutableLiveData<>();

    public NavigationViewModel(@NonNull Application application) {
        super(application);

        scheduleService = RetrofitHelper.createService(ScheduleService.class, application);
        studentService = RetrofitHelper.createService(StudentService.class, application);
        //fake list data

        listTestSchedule.setValue(TestModelSchedule.getListModel(5));
        fetchStudent();
    }

    public MutableLiveData<StudentModel> getStudent() {
        return student;
    }

    public MutableLiveData<List<TestModelSchedule>> getListTestSchedule() {
        return listTestSchedule;
    }

    public MutableLiveData<List<ScheduleModel>> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<ScheduleModel> list) {
        this.listSchedule.setValue(list);
    }

    public Observable<List<TestModelSchedule>> getListData(int size) {
        return Observable.just(TestModelSchedule.getListModel(size));
    }

    public void fetchStudent() {
        studentService.getStudentInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<StudentDTO.StudentResponseDTO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(StudentDTO.@io.reactivex.rxjava3.annotations.NonNull StudentResponseDTO studentResponseDTO) {
                        student.setValue(studentResponseDTO.getStudent());
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    public void getListScheduleFromServer(Map<String, String> params) {
        scheduleService.getSchedules(params)
                .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe();
    }
}
