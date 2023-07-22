package com.example.myfpl.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;

import com.example.myfpl.data.DTO.LoginDTO;
import com.example.myfpl.data.apis.AuthService;
import com.example.myfpl.data.apis.INotification;
import com.example.myfpl.data.apis.TestService;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;
import com.example.myfpl.helpers.retrofit.TokenRepository;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.models.Test;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TestViewModel extends AndroidViewModel {
    private static final String TAG = TestViewModel.class.getSimpleName();

    public TestViewModel(@NonNull Application application, String args) {
        super(application);
    }
    private Disposable mDisposable;
    private final ObservableArrayList<Test> listData = new ObservableArrayList<>();
    private final ObservableBoolean isSuccess = new ObservableBoolean();

    public void getTestModel() {
        RetrofitHelper.getInstance(getApplication().getApplicationContext()).create(TestService.class).getTestData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Test>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Test> tests) {
                        listData.addAll(tests);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void refreshToken() {
        RetrofitHelper.createService(INotification.class, getApplication().getApplicationContext()).getNotificationData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NotificationModel>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<NotificationModel> notificationModels) {
                        Log.d(TAG, "onNext: " + notificationModels);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void tryToLogin() {
        LoginDTO.LoginRequestDTO loginRequestDTO = new LoginDTO.LoginRequestDTO("tamnguyen231173@gmail.com", "Nguyễn Hữu Tâm", "102767964507181310037", "https://i.pinimg.com/736x/c3/9b/76/c39b7637c1c2069e44775383768c7d1f.jpg");

        RetrofitHelper.createService(AuthService.class, getApplication().getApplicationContext()).login(loginRequestDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoginDTO.LoginResponseDTO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(LoginDTO.@io.reactivex.rxjava3.annotations.NonNull LoginResponseDTO loginResponseDTO) {
                        TokenRepository.getInstance(getApplication().getApplicationContext()).setToken(loginResponseDTO.getAccessToken());
                        TokenRepository.getInstance(getApplication().getApplicationContext()).setRefreshToken(loginResponseDTO.getRefreshToken());

                        Log.d(TAG, "onSuccess: " + loginResponseDTO);
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

    public ObservableArrayList<Test> getListData() {
        return listData;
    }

    public ObservableBoolean getIsSuccess() {
        return isSuccess;
    }
}
