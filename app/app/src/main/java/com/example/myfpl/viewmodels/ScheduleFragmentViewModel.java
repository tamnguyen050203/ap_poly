package com.example.myfpl.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfpl.data.DTO.ScheduleDTO;
import com.example.myfpl.data.apis.ScheduleService;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;
import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.TestModelSchedule;
import com.example.myfpl.models.TestScheduleModel;
import com.example.myfpl.util.DateUtil;
import com.shrikanthravi.collapsiblecalendarview.data.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ScheduleFragmentViewModel extends AndroidViewModel {
    private static final String TAG = ScheduleFragmentViewModel.class.getSimpleName();
    private final MutableLiveData<Boolean> isListScrolling = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Day>> currentDateSelected = new MutableLiveData<>();
    private final MutableLiveData<List<TestScheduleModel>> listTestScheduleRes = new MutableLiveData<>();
    private final MutableLiveData<List<ScheduleModel>> listScheduleRes = new MutableLiveData<>();
    private final MutableLiveData<List<TestScheduleModel>> listTestSchedule = new MutableLiveData<>();
    private final MutableLiveData<List<ScheduleModel>> listSchedule = new MutableLiveData<>();
    private final ScheduleService scheduleService = RetrofitHelper.createService(ScheduleService.class, getApplication().getApplicationContext());

    public ScheduleFragmentViewModel(@NonNull Application application) {
        super(application);
        isListScrolling.setValue(false);
        listScheduleRes.setValue(new ArrayList<>());
        listTestScheduleRes.setValue(new ArrayList<>());

        fetchSchedule();
        fetchTestSchedule();
    }
    public MutableLiveData<ArrayList<Day>> getCurrentDateSelected() {
        return currentDateSelected;
    }

    public void setCurrentDateSelected(ArrayList<Day> currentDateSelected) {
        this.currentDateSelected.setValue(currentDateSelected);

        this.listSchedule.setValue(filterSchedule(this.currentDateSelected.getValue(), (ArrayList<ScheduleModel>) Objects.requireNonNull(this.listScheduleRes.getValue())));
        this.listTestSchedule.setValue(filterTestSchedule(this.currentDateSelected.getValue(), (ArrayList<TestScheduleModel>) Objects.requireNonNull(this.listTestScheduleRes.getValue())));
    }

    public MutableLiveData<Boolean> getIsListScrolling() {
        return isListScrolling;
    }

    public void setIsListScrolling(Boolean isListScrolling) {
        this.isListScrolling.setValue(isListScrolling);
    }

    public MutableLiveData<List<TestScheduleModel>> getListTestSchedule() {
        return listTestSchedule;
    }

    public MutableLiveData<List<ScheduleModel>> getListSchedule() {
        return listSchedule;
    }

    public MutableLiveData<List<TestScheduleModel>> getListTestScheduleRes() {
        return listTestScheduleRes;
    }

    public MutableLiveData<List<ScheduleModel>> getListScheduleRes() {
        return listScheduleRes;
    }

    public void setListSchedule(List<ScheduleModel> list) {
        this.listSchedule.setValue(list);
    }

    public Observable<List<TestModelSchedule>> getListData(int size) {
        return Observable.just(TestModelSchedule.getListModel(size));
    }

    public void updateIsAlarm(int classGroupId, int isAlarm, String reminderId, int scheduleId, CallBackHandle callBackHandle){
        scheduleService.updateIsAlarm(classGroupId, scheduleId, reminderId, isAlarm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ScheduleDTO.UpdateIsAlarmResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(ScheduleDTO.@io.reactivex.rxjava3.annotations.NonNull UpdateIsAlarmResponse updateIsAlarmResponse) {
                        Log.d(TAG, "onSuccess: " + updateIsAlarmResponse.getMessage());
                        Log.d(TAG, "onSuccess: " + updateIsAlarmResponse.getStatus());
                        if(updateIsAlarmResponse.getStatus() == 200){
                            fetchSchedule();
                            if(callBackHandle != null){
                                callBackHandle.onSuccess();
                            }
                        }else{
                            Log.e(TAG, "Lỗi");
                            if(callBackHandle != null) {
                                callBackHandle.onError();
                            }
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        if(callBackHandle != null) {
                            callBackHandle.onError();
                        }
                    }
                });
    }

    public void fetchSchedule() {
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("dateStart", DateUtil.getFirstDayOfCurrentMonth());
        mapQuery.put("dateEnd", DateUtil.getLastDayOfCurrentMonth());

        scheduleService.getSchedules(mapQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<ScheduleDTO.ScheduleResponseDTO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(ScheduleDTO.@io.reactivex.rxjava3.annotations.NonNull ScheduleResponseDTO scheduleResponseDTO) {
                        listScheduleRes.setValue(scheduleResponseDTO.getSchedules().getData());
                        listSchedule.setValue(listScheduleRes.getValue());
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e(TAG, "fetchSchedule onError: " + e.getMessage());
                    }
                });
    }

    public void fetchTestSchedule() {
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("dateStart", DateUtil.getFirstDayOfCurrentMonth());
        mapQuery.put("dateEnd", DateUtil.getLastDayOfCurrentMonth());

        scheduleService.getTestSchedules(mapQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new SingleObserver<ScheduleDTO.TestScheduleResponseDTO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(ScheduleDTO.@io.reactivex.rxjava3.annotations.NonNull TestScheduleResponseDTO testScheduleResponseDTO) {
                        listTestScheduleRes.setValue(testScheduleResponseDTO.getSchedules().getData());
                        listTestSchedule.setValue(listTestScheduleRes.getValue());
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e(TAG, "fetchTestSchedule onError: " + e.getMessage());
                    }
                });
    }

    private ArrayList<ScheduleModel> filterSchedule(ArrayList<Day> lisDateSelected, ArrayList<ScheduleModel> listSchedule) {
        ArrayList<ScheduleModel> list = new ArrayList<>();

        for (ScheduleModel item : listSchedule) {
            if (lisDateSelected.toString().contains(item.getDate())) {
                list.add(item);
            }
        }
        return list;
    }


    private ArrayList<TestScheduleModel> filterTestSchedule(ArrayList<Day> lisDateSelected, ArrayList<TestScheduleModel> listSchedule) {
        ArrayList<TestScheduleModel> list = new ArrayList<>();

        for (TestScheduleModel item : listSchedule) {
            if (lisDateSelected.toString().contains(item.getDate())) {
                list.add(item);
            }
        }
        return list;
    }

    public interface CallBackHandle{
        void onSuccess();
        void onError();
    }
}
