package com.example.myfpl.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfpl.data.apis.INotification;
import com.example.myfpl.data.apis.ScheduleService;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;
import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.TestModelSchedule;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;

public class NavigationViewModel extends AndroidViewModel {
    private ScheduleService scheduleService;
    private MutableLiveData<List<TestModelSchedule>> listTestSchedule = new MutableLiveData<>();
    private MutableLiveData<List<ScheduleModel>> listSchedule = new MutableLiveData<>();

    public NavigationViewModel(@NonNull Application application) {
        super(application);

        scheduleService = RetrofitHelper.createService(ScheduleService.class, application);
        //fake list data

        listTestSchedule.setValue(TestModelSchedule.getListModel(5));
    }

    public MutableLiveData<List<TestModelSchedule>> getListTestSchedule() {
        return listTestSchedule;
    }

    public MutableLiveData<List<ScheduleModel>> getListSchedule(){
        return  listSchedule;
    }

    public void setListSchedule(List<ScheduleModel> list){
        this.listSchedule.setValue(list);
    }

    public Observable<List<TestModelSchedule>> getListData(int size){
        return Observable.just(TestModelSchedule.getListModel(size));
    }

    public void getListScheduleFromServer(Map<String, String> params){
        scheduleService.getSchedules(params)
                .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe();
    }
}
