package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.myfpl.adapters.NotificationFragmentAdapter;
import com.example.myfpl.databinding.ActivityNotifyBinding;
import com.example.myfpl.models.NotificationModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class NotifyActivity extends AppCompatActivity {
    private ActivityNotifyBinding binding;
    private NotificationFragmentAdapter adapter;
    private ArrayList<NotificationModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        init();
    }
    public void init(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new NotificationFragmentAdapter(fragmentManager, getLifecycle());
        binding.viewPager.setAdapter(adapter);
        new TabLayoutMediator(binding.topTab, binding.viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Tất cả");
                    break;
                case 1:
                    tab.setText("Học tập");
                    break;
                case 2:
                    tab.setText("Học phí");
                    break;
                case 3:
                    tab.setText("Hoạt động");
                    break;
                case 4:
                    tab.setText("Việc làm");
                    break;
            }
        }).attach();


    }
        public void goToDetail(NotificationModel object, String date){
        Intent i = new Intent(NotifyActivity.this, DetailNotificationActivity.class);
        i.putExtra("detail",object);
        i.putExtra("createdAt",date);
        this.startActivity(i);
    }
}