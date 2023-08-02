package com.example.myfpl.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myfpl.R;
import com.example.myfpl.component.HeaderApp;
import com.example.myfpl.databinding.ActivityDetailNotificationBinding;
import com.example.myfpl.models.NotificationModel;

public class DetailNotificationActivity extends AppCompatActivity {
    private ActivityDetailNotificationBinding binding;
    NotificationModel notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i = getIntent();
        notification = (NotificationModel) i.getSerializableExtra("detail");
        String createdAt = i.getStringExtra("createdAt");
        binding.tvAuthorDetail.setText(notification.getAuthor());
        binding.tvContentDetail.setText(notification.getContent());
        binding.tvNotificationNameDetail.setText(notification.getTitle());
        binding.tvCreatedAtDetail.setText(createdAt);
        binding.tvTypeDetail.setText(notification.getType());
        binding.headerApp1.setHeaderClickListener(new HeaderApp.HeaderHandleClickListener() {
            @Override
            public void onLeftButtonClick() {
                finish();
            }

            @Override
            public void onUserContainerClick() {

            }

            @Override
            public void onRightButtonClick() {

            }
        });
        switch (notification.getType()) {
            case "Học tập":
                binding.tvTypeDetail.setBackgroundResource(R.drawable.notification_type_blue);
                binding.tvTypeDetail.setTextColor(ContextCompat.getColor(DetailNotificationActivity.this, R.color.aqua));
                break;
            case "Học phí":
                binding.tvTypeDetail.setBackgroundResource(R.drawable.notification_type_orange);
                binding.tvTypeDetail.setTextColor(ContextCompat.getColor(DetailNotificationActivity.this, R.color.orange));
                break;
            case "Hoạt động":
                binding.tvTypeDetail.setBackgroundResource(R.drawable.notification_type_green);
                binding.tvTypeDetail.setTextColor(ContextCompat.getColor(DetailNotificationActivity.this, R.color.green_light));
                break;
            case "Việc làm":
                binding.tvTypeDetail.setBackgroundResource(R.drawable.notification_type_red);
                binding.tvTypeDetail.setTextColor(ContextCompat.getColor(DetailNotificationActivity.this, R.color.red_light));
                break;
        }
    }
}