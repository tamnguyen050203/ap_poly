package com.example.myfpl.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myfpl.ui.fragments.NormalScheduleFragment;
import com.example.myfpl.ui.fragments.TestScheduleFragment;

public class ScheduleViewPager extends FragmentStateAdapter {
    public ScheduleViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new NormalScheduleFragment();
        }else {
            return new TestScheduleFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public interface EventHandler{
        void onListScroll();
    }
}
