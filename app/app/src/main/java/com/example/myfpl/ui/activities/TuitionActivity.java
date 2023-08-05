package com.example.myfpl.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myfpl.R;
import com.example.myfpl.adapters.TuitionListAdapter;
import com.example.myfpl.databinding.ActivityTuitionBinding;
import com.example.myfpl.models.TuitionModel;

import java.util.ArrayList;
import java.util.List;

public class TuitionActivity extends AppCompatActivity {

    private static final String TAG = TuitionActivity.class.getSimpleName();

    private List<TuitionModel> tuitionList;

    private TuitionListAdapter adapter;
    private ActivityTuitionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition);

        init();
        setupTuitionList();
    }

    public void init() {
        binding = ActivityTuitionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tuitionList = new ArrayList<>();
    }

    public void setupTuitionList() {
        tuitionList = TuitionModel.getTuitionList();
        adapter = new TuitionListAdapter(tuitionList, getApplication(), new TuitionListAdapter.HandleEvent() {
            @Override
            public void OnItemClick(TuitionModel tuitionModel, int index) {
                Log.d(TAG, "OnItemClick: click tuition");
            }
        });
        binding.listItemTuition.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));
        binding.listItemTuition.setAdapter(adapter);
    }
}