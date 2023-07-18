package com.example.myfpl.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myfpl.R;
import com.example.myfpl.adapters.MainViewPager;
import com.example.myfpl.databinding.ActivityNavigationBinding;
import com.example.myfpl.viewmodels.TestViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationActivity extends FragmentActivity {
    private ActivityNavigationBinding binding;
    private TestViewModel testViewModel;
    private MainViewPager mainViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init(){
        mainViewPagerAdapter = new MainViewPager(NavigationActivity.this);

        binding.viewPager.setAdapter(mainViewPagerAdapter);
        addEvent();
    }

    public void addEvent(){
        binding.bottomTab.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeFragment:
                        binding.viewPager.setCurrentItem(0);
                        break;
                    case R.id.scheduleFragment:
                        binding.viewPager.setCurrentItem(1);
                        break;
                    case R.id.studentFragment:
                        binding.viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.bottomTab.getMenu().getItem(position).setChecked(true);
            }
        });
    }
}