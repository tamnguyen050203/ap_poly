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
                case 1:return NotificationFragment.instance("Học Tập");
                case 2:return NotificationFragment.instance("Học Phí");
                case 3:return NotificationFragment.instance("Hoạt Động");
                case 4:return NotificationFragment.instance("Việc Làm");
                default:return NotificationFragment.instance("Tất Cả");
            }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
