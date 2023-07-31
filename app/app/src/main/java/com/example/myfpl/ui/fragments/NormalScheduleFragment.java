package com.example.myfpl.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfpl.R;
import com.example.myfpl.adapters.DetailScheduleListAdapter;
import com.example.myfpl.adapters.ScheduleViewPager;
import com.example.myfpl.databinding.FragmentNormalScheduleBinding;
import com.example.myfpl.models.TestModelSchedule;
import com.example.myfpl.viewmodels.NavigationViewModel;
import com.example.myfpl.viewmodels.ScheduleFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class NormalScheduleFragment extends Fragment {
    private static final String TAG = NormalScheduleFragment.class.getSimpleName();
    private NavigationViewModel viewModel;
    private FragmentNormalScheduleBinding binding;
    private DetailScheduleListAdapter adapter;
    private ScheduleFragmentViewModel scheduleFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNormalScheduleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public void init() {
        if (getActivity() != null) {
            //setup viewModel
            viewModel = new ViewModelProvider(getActivity()).get(NavigationViewModel.class);
            scheduleFragmentViewModel = new ViewModelProvider(getActivity()).get(ScheduleFragmentViewModel.class);
        }

        addEvent();
        setupList();
    }

    public void setupList() {
        binding.scheduleList.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new DetailScheduleListAdapter(new DetailScheduleListAdapter.HandleEventListItem() {
            @Override
            public void onItemClick(TestModelSchedule testModelSchedule, int itemIndex) {

            }

            @Override
            public void onAlarmClick(TestModelSchedule testModelSchedule, int itemIndex) {
                viewModel.setListSchedule(adapter.getListData());
            }
        });
        adapter.setListData(new ArrayList<>());
        binding.scheduleList.setAdapter(adapter);

        binding.scheduleList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        System.out.println("The RecyclerView is not scrolling");
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        System.out.println("The RecyclerView is scrolling");
                        scheduleFragmentViewModel.setIsListScrolling(true);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        System.out.println("Scroll Settling");
                        scheduleFragmentViewModel.setIsListScrolling(false);
                        break;
                }
            }
        });
    }

    public void addEvent() {
        viewModel.getListSchedule().observe(getViewLifecycleOwner(), new Observer<List<TestModelSchedule>>() {
            @Override
            public void onChanged(List<TestModelSchedule> testModelSchedules) {
                adapter.setListData(testModelSchedules);
            }
        });
    }
}