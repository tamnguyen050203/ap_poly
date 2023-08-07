package com.example.myfpl.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.adapters.DetailScheduleListAdapter;
import com.example.myfpl.databinding.FragmentTestScheduleBinding;
import com.example.myfpl.models.TestScheduleModel;
import com.example.myfpl.util.ScheduleUtils;
import com.example.myfpl.util.ToastApp;
import com.example.myfpl.viewmodels.ScheduleFragmentViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.shrikanthravi.collapsiblecalendarview.data.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestScheduleFragment extends Fragment {
    private static final String TAG = TestScheduleFragment.class.getSimpleName();
    private FragmentTestScheduleBinding binding;
    private ScheduleFragmentViewModel scheduleFragmentViewModel;
    private DetailScheduleListAdapter<TestScheduleModel> adapter;
    private com.google.api.services.calendar.Calendar calendarService;

    private ThreadPoolExecutor threadPoolExecutor;
    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int status = msg.arg1;
            if(msg.obj == "create"){
                if(status == 1){
                    ToastApp.show(requireContext(), "Tạo thông báo thành công");
                }else{
                    ToastApp.show(requireContext(), "Tạo thông báo thất bại");
                }
            }else if(msg.obj == "delete"){
                if(status == 1){
                    ToastApp.show(requireContext(), "Xóa thông báo thành công");
                }else{
                    ToastApp.show(requireContext(), "Xóa thông báo thất bại");
                }
            }
        }
    };
    //end thread pool

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTestScheduleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        scheduleFragmentViewModel = new ViewModelProvider(requireActivity()).get(ScheduleFragmentViewModel.class);

        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(requireContext());
        GoogleAccountCredential googleAccountCredential = GoogleAccountCredential.usingOAuth2(
                        requireContext(),
                        Collections.singletonList(CalendarScopes.CALENDAR)
                ).setBackOff(new ExponentialBackOff())
                .setSelectedAccountName(Objects.requireNonNull(googleSignInAccount.getAccount()).name);

        calendarService = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, googleAccountCredential)
                .setApplicationName("MyFPL")
                .build();

        addEvent();
        setupList();
    }

    public void setupList() {
        binding.scheduleList.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new DetailScheduleListAdapter<>(new DetailScheduleListAdapter.HandleEventListItem<TestScheduleModel>() {
            @Override
            public void onItemClick(TestScheduleModel scheduleModel, int itemIndex) {
                Log.d(TAG, "onItemClick: " + scheduleModel.getLesson_name());
            }

            @Override
            public void onAlarmClick(TestScheduleModel scheduleModel, int itemIndex) {
//                if (scheduleModel.isAlarm() == 1) {
//                    threadPoolExecutor.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            Message resultMessage = new Message();
//                            resultMessage.obj = "remove";
//                            try {
//                                calendarService.events().delete("primary", scheduleModel.getReminder_id()).execute();
//
//                                //success
//                                resultMessage.arg1 = 1;
//                            } catch (IOException e) {
//
//                                //failure
//                                resultMessage.arg1 = 0;
//                                Log.e(TAG, "run: " + e.getMessage());
//                            }finally {
//                                handler.sendMessage(resultMessage);
//                            }
//                        }
//                    });
//                } else {
//                    threadPoolExecutor.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            Message resultMessage = new Message();
//                            resultMessage.obj = "create";
//                            try {
//                                Event eventResult = calendarService.events().insert("primary",
//                                                ScheduleUtils.createEvent(scheduleModel))
//                                        .execute();
//                                Log.d(TAG, "onAlarmClick: " + eventResult.getId());
//
//                                //success
//                                resultMessage.arg1 = 1;
//                            } catch (IOException e) {
//                                //failure
//                                resultMessage.arg1 = 0;
//
//                                Log.d(TAG, "run: " + e.getMessage());
//                            }finally {
//                                handler.sendMessage(resultMessage);
//                            }
//                        }
//                    });
//                }
            }
        }, scheduleFragmentViewModel.getCurrentDateSelected().getValue());

        adapter.setListData(new ArrayList<>());
        binding.scheduleList.setAdapter(adapter);

        binding.scheduleList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
//                        System.out.println("The RecyclerView is not scrolling");
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        System.out.println("The RecyclerView is scrolling");
                        scheduleFragmentViewModel.setIsListScrolling(true);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
//                        System.out.println("Scroll Settling");
                        scheduleFragmentViewModel.setIsListScrolling(false);
                        break;
                }
            }
        });
    }

    public void addEvent() {
        scheduleFragmentViewModel.getListTestSchedule().observe(
                getViewLifecycleOwner(),
                scheduleModels -> adapter.setListData(scheduleModels));
    }
}