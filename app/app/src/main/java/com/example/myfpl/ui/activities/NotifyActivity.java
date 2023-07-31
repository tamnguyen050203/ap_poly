package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myfpl.adapters.NotificationFragmentAdapter;
import com.example.myfpl.component.HeaderApp;
import com.example.myfpl.data.DTO.NotificationDTO;
import com.example.myfpl.databinding.ActivityNotifyBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.viewmodels.NotifyViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class NotifyActivity extends AppCompatActivity {
    private static final String TAG = "NotifyActivity";
    private ActivityNotifyBinding binding;
    private NotificationFragmentAdapter adapter;
    private ArrayList<NotificationModel> list;
    private NotifyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        init();

        binding.headerApp.setHeaderClickListener(new HeaderApp.HeaderHandleClickListener() {
            @Override
            public void onLeftButtonClick() {

            }

            @Override
            public void onUserContainerClick() {

            }

            @Override
            public void onRightButtonClick() {
                readAllNotification();
            }
        });
    }

    public void init() {
        viewModel = new ViewModelProvider(this,
                new NotifyViewModel.NotifyViewModelFactory(getApplication())).get(NotifyViewModel.class);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new NotificationFragmentAdapter(fragmentManager, getLifecycle());
        binding.viewPager.setAdapter(adapter);
        new TabLayoutMediator(binding.topTab, binding.viewPager, (tab, position) -> {
            switch (position) {
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

        viewModel.getNotificationData();
    }

    public void goToDetail(NotificationModel object, String date) {
        Intent i = new Intent(NotifyActivity.this, DetailNotificationActivity.class);
        i.putExtra("detail", object);
        i.putExtra("createdAt", date);
        this.startActivity(i);
    }

    public void readNotification(String requestDTO) {
        NotificationDTO.ReadNotificationRequestDTO request = new NotificationDTO.ReadNotificationRequestDTO(requestDTO);
        viewModel.readNotification(request);
        viewModel.getNotificationData();
    }

    public void readAllNotification() {
        viewModel.readAllNotification();
        viewModel.getNotificationData();
    }
}