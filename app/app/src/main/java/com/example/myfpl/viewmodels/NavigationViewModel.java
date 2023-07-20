package com.example.myfpl.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfpl.models.TestModelSchedule;

import java.util.List;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;

public class NavigationViewModel extends AndroidViewModel {
    private LiveData<List<TestModelSchedule>> listTestSchedule;
    private LiveData<List<TestModelSchedule>> listSchedule = new MediatorLiveData<>();

    public NavigationViewModel(@NonNull Application application) {
        super(application);

        //fake list data
        this.listTestSchedule = (LiveDataReactiveStreams.fromPublisher(getListData(2).toFlowable(BackpressureStrategy.LATEST)));
        this.listSchedule = LiveDataReactiveStreams.fromPublisher(getListData(2).toFlowable(BackpressureStrategy.LATEST));
    }

    public LiveData<List<TestModelSchedule>> getListTestSchedule() {
        return listTestSchedule;
    }

    public LiveData<List<TestModelSchedule>> getListSchedule(){
        return  listSchedule;
    }

    public void setListSchedule(List<TestModelSchedule> list){
        this.listSchedule = (LiveDataReactiveStreams.fromPublisher(Observable.just(list).toFlowable(BackpressureStrategy.LATEST)));
    }

    public Observable<List<TestModelSchedule>> getListData(int size){
        return Observable.just(TestModelSchedule.getListModel(size));
    }

}
