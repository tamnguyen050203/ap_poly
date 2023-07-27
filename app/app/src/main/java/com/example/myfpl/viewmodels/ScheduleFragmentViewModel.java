package com.example.myfpl.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScheduleFragmentViewModel extends ViewModel {

    private MutableLiveData<Boolean> isListScrolling = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsListScrolling() {
        return isListScrolling;
    }

    public void setIsListScrolling(Boolean isListScrolling) {
        this.isListScrolling.setValue(isListScrolling);
    }

    public ScheduleFragmentViewModel() {
        isListScrolling.setValue(false);
    }

}
