package com.example.myfpl.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfpl.data.DTO.ScheduleDTO;
import com.example.myfpl.data.apis.ScheduleService;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ScheduleViewModel extends AndroidViewModel {

    private final String TAG = "ScheduleViewModel";
    private ScheduleService scheduleService;
    private final MutableLiveData<ScheduleDTO.ScheduleResponseDTO> liveDataSchedule = new MutableLiveData<>();

    public MutableLiveData<ScheduleDTO.ScheduleResponseDTO> getLiveDataSchedule() {
        return liveDataSchedule;
    }

    public void setLiveDataSchedule(ScheduleDTO.ScheduleResponseDTO liveDataNotification) {
        this.liveDataSchedule.setValue(liveDataNotification);
    }

    public void getScheduleData(){
        scheduleService.getTestSchedules()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ScheduleDTO.ScheduleResponseDTO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ScheduleDTO.ScheduleResponseDTO scheduleResponseDTO) {
                        Log.d(TAG, scheduleResponseDTO.getSchedules().getData().get(1)+"");
                        setLiveDataSchedule(scheduleResponseDTO);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }

    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        scheduleService = RetrofitHelper.createService(ScheduleService.class, application);
    }

    public static class ScheduleViewModelFactory implements ViewModelProvider.Factory {
        @NonNull
        private final Application application;

        public ScheduleViewModelFactory(@NonNull Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ScheduleViewModel(application);
        }
    }
}
