package com.example.myfpl.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;

import com.example.myfpl.data.apis.TestService;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;
import com.example.myfpl.models.Test;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
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

    public void getTestModel(){
        RetrofitHelper.getInstance().create(TestService.class).getTestData()
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
