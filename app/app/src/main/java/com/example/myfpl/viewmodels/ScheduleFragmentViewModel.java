package com.example.myfpl.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScheduleFragmentViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isListScrolling = new MutableLiveData<>();
    private final MutableLiveData<String> currentDateSelected = new MutableLiveData<>();

    public MutableLiveData<String> getCurrentDateSelected() {
        return currentDateSelected;
    }

    public void setCurrentDateSelected(String currentDateSelected){
        this.currentDateSelected.setValue(currentDateSelected);
    }

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
