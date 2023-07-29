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
    private MutableLiveData<List<TestModelSchedule>> listTestSchedule = new MutableLiveData<>();
    private MutableLiveData<List<TestModelSchedule>> listSchedule = new MutableLiveData<>();

    public NavigationViewModel(@NonNull Application application) {
        super(application);

        //fake list data
        listSchedule.setValue(TestModelSchedule.getListModel(5));
        listTestSchedule.setValue(TestModelSchedule.getListModel(5));
    }

    public MutableLiveData<List<TestModelSchedule>> getListTestSchedule() {
        return listTestSchedule;
    }

    public MutableLiveData<List<TestModelSchedule>> getListSchedule(){
        return  listSchedule;
    }

    public void setListSchedule(List<TestModelSchedule> list){
        this.listSchedule.setValue(list);
    }

    public Observable<List<TestModelSchedule>> getListData(int size){
        return Observable.just(TestModelSchedule.getListModel(size));
    }

}
