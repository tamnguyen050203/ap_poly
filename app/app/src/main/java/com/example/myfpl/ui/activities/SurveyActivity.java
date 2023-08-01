package com.example.myfpl.ui.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myfpl.R;
import com.example.myfpl.adapters.SurveyListAdapter;
import com.example.myfpl.databinding.ActivitySurveyBinding;
import com.example.myfpl.databinding.ActivityTuitionBinding;
import com.example.myfpl.models.SurveyModel;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {
    private static final String TAG = SurveyActivity.class.getSimpleName();

    private List<SurveyModel> surveyList;

    private SurveyListAdapter adapter;
    private ActivitySurveyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition);

        init();
        setupTuitionList();
    }

    public void init() {
        binding = ActivitySurveyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        surveyList = new ArrayList<>();
    }

    public void setupTuitionList() {
        surveyList = SurveyModel.getSurveyList();
        adapter = new SurveyListAdapter(surveyList, getApplication(), new SurveyListAdapter.HandleEvent() {
            @Override
            public void OnItemClick(SurveyModel surveyModel, int index) {
                Log.d(TAG, "OnItemClick: click tuition");
            }
        });
        binding.listItemSurvey.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));
        binding.listItemSurvey.setAdapter(adapter);
    }
}