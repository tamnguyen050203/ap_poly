package com.example.myfpl.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfpl.data.DTO.NotificationDTO;
import com.example.myfpl.data.apis.INotification;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NotifyViewModel extends AndroidViewModel {
    private INotification iNotification;
    private MutableLiveData<NotificationDTO> liveDataNotification = new MutableLiveData<>();

    public MutableLiveData<NotificationDTO> getLiveDataNotification() {
        return liveDataNotification;
    }

    public void setLiveDataNotification(MutableLiveData<NotificationDTO> liveDataNotification) {
        this.liveDataNotification = liveDataNotification;
    }

    public void getNotificationData(){
        iNotification.getNotificationData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NotificationDTO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull NotificationDTO notificationDTO) {
                        Log.d(">>>>", notificationDTO+"");
                        liveDataNotification.setValue(notificationDTO);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }
    public NotifyViewModel(@NonNull Application application) {
        super(application);
        iNotification = RetrofitHelper.createService(INotification.class, application);
    }
    public static class NotifyViewModelFactory implements ViewModelProvider.Factory {
        private Application application;

        public NotifyViewModelFactory(Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new NotifyViewModel(application);
        }
    }
}