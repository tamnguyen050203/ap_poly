package com.example.myfpl.ui.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.myfpl.adapters.MainViewPager;
import com.example.myfpl.databinding.ActivityNavigationBinding;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class NavigationActivity extends FragmentActivity {
    private static final String TAG = NavigationActivity.class.getSimpleName();
    private ActivityNavigationBinding binding;
    private MainViewPager mainViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init() {
        mainViewPagerAdapter = new MainViewPager(NavigationActivity.this);
        binding.viewPager.setAdapter(mainViewPagerAdapter);
        binding.viewPager.setUserInputEnabled(false);

        addEvent();
    }

    public void addEvent() {
        binding.bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NonNull AnimatedBottomBar.Tab newTab) {
                binding.viewPager.setCurrentItem(newIndex, false);
            }

            @Override
            public void onTabReselected(int index, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });
    }
}