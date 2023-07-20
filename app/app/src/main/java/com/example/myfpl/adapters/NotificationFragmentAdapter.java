package com.example.myfpl.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myfpl.ui.fragments.NotificationFragment;

public class NotificationFragmentAdapter extends FragmentStateAdapter {

    public NotificationFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
            switch (position){
                case 1:return NotificationFragment.instance("Học tập");
                case 2:return NotificationFragment.instance("Học phí");
                case 3:return NotificationFragment.instance("Hoạt động");
                case 4:return NotificationFragment.instance("Việc làm");
                default:return NotificationFragment.instance("Tất cả");
            }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
