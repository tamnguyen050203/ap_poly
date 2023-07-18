package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myfpl.adapters.TestAdapter;
import com.example.myfpl.databinding.ActivityTestBinding;
import com.example.myfpl.util.ToastApp;
import com.example.myfpl.viewmodels.TestViewModel;

public class Test extends AppCompatActivity {
    private ActivityTestBinding binding;
    private TestViewModel testViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init(){
        testViewModel = new TestViewModel(getApplication(), "ss");
        binding.setMainViewModel(testViewModel);
    }

    @BindingAdapter({"list_data", "is_success"})
    public static void loadData(RecyclerView view, ObservableArrayList<com.example.myfpl.models.Test> list, ObservableBoolean isSuccess){
        if(isSuccess.get()){
            ToastApp.show(view.getContext(), "Success");
        }else{
            ToastApp.show(view.getContext(), "Failure");
        }

        TestAdapter testAdapter = new TestAdapter(list);
        view.setAdapter(testAdapter);
    }
}