package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myfpl.R;
import com.example.myfpl.adapters.ServiceListAdapter;
import com.example.myfpl.databinding.ActivityServiceBinding;
import com.example.myfpl.models.ServiceModel;

public class ServiceScreen extends AppCompatActivity {

    private ActivityServiceBinding binding;
    private ServiceModel model;
    private ServiceListAdapter serviceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        binding.recycler.setLayoutManager(manager);
        serviceListAdapter = new ServiceListAdapter(ServiceScreen.this, ServiceModel.getData());
        binding.recycler.setAdapter(serviceListAdapter);
    }
    public void init(){
        Log.d(">>>>ServiceActivity"," model:"+ ServiceModel.getData());
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}