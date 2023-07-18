package com.example.myfpl.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myfpl.ui.fragments.HomeFragment;
import com.example.myfpl.ui.fragments.ScheduleFragment;
import com.example.myfpl.ui.fragments.StudentFragment;

public class MainViewPager extends FragmentStateAdapter {

    public MainViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new ScheduleFragment();
            case 2: return new StudentFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
