package com.example.myfpl.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;

import com.example.myfpl.R;
import com.example.myfpl.adapters.CommunityListAdapter;
import com.example.myfpl.databinding.ActivityCommunityScreenBinding;
import com.example.myfpl.databinding.ActivityNotifyBinding;
import com.example.myfpl.models.CommunityModel;

public class CommunityScreen extends AppCompatActivity {
    private ActivityCommunityScreenBinding binding;
    private CommunityListAdapter adapter;
    private CommunityModel communityModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunityScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }
    public void init(){
        Log.d(">>>>CommunityActivity","Community model:"+CommunityModel.getData());
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapter = new CommunityListAdapter(CommunityScreen.this, CommunityModel.getData(), new CommunityListAdapter.HandleEvent() {
            @Override
            public void OnItemClick(String url) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
        binding.gridView.setAdapter(adapter);
    }
}