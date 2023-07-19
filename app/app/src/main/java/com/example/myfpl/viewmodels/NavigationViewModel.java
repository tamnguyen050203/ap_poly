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
    private LiveData<List<TestModelSchedule>> listTestSchedule = new MediatorLiveData<>();

    public NavigationViewModel(@NonNull Application application) {
        super(application);

        //fake list data
        this.listTestSchedule = (LiveDataReactiveStreams.fromPublisher(getListData().toFlowable(BackpressureStrategy.LATEST)));
    }

    public LiveData<List<TestModelSchedule>> getListTestSchedule() {
        return listTestSchedule;
    }

    public void setListTestSchedule(LiveData<List<TestModelSchedule>> listTestSchedule) {
        this.listTestSchedule = listTestSchedule;
    }

    public Observable<List<TestModelSchedule>> getListData(){
        return Observable.just(TestModelSchedule.getListModel());
    }

}
