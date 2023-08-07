package com.example.myfpl.ui.fragments;

import android.annotation.SuppressLint;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.adapters.DetailScheduleListAdapter;
import com.example.myfpl.databinding.FragmentNormalScheduleBinding;
import com.example.myfpl.models.ScheduleModel;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NormalScheduleFragment extends Fragment {
    private static final String TAG = NormalScheduleFragment.class.getSimpleName();
    private FragmentNormalScheduleBinding binding;
    private DetailScheduleListAdapter<ScheduleModel> adapter;
    private ScheduleFragmentViewModel scheduleFragmentViewModel;
    private com.google.api.services.calendar.Calendar calendarService;

    private ThreadPoolExecutor threadPoolExecutor;
    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int status = msg.arg1;
            Log.d(TAG, "handleMessage status: " + status);
            Log.d(TAG, "handleMessage: " + msg.obj.toString());

            if (Objects.equals(msg.obj, "create")) {
                if (status == 1) {
                    ToastApp.show(requireContext(), "Tạo thông báo thành công");
                } else {
                    ToastApp.show(requireContext(), "Tạo thông báo thất bại");
                }
            } else if (msg.obj == "remove") {
                if (status == 1) {
                    ToastApp.show(requireContext(), "Xóa thông báo thành công");
                } else {
                    ToastApp.show(requireContext(), "Xóa thông báo thất bại");
                }
            }
        }
    };

    //end thread pool
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
            scheduleFragmentViewModel = new ViewModelProvider(getActivity()).get(ScheduleFragmentViewModel.class);
        }

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
        adapter = new DetailScheduleListAdapter<>(new DetailScheduleListAdapter.HandleEventListItem<ScheduleModel>() {
            @Override
            public void onItemClick(ScheduleModel scheduleModel, int itemIndex) {
                Log.d(TAG, "onItemClick: " + scheduleModel.getLesson_name());
            }

            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onAlarmClick(ScheduleModel scheduleModel, int itemIndex) {
                if (scheduleModel.isAlarm() == 1) {
                    threadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            Message resultMessage = new Message();
                            resultMessage.obj = "remove";
                            try {
                                calendarService.events().delete("primary", scheduleModel.getReminder_id()).execute();
                                scheduleFragmentViewModel.updateIsAlarm(
                                        scheduleModel.getClass_group_id(),
                                        0,
                                        scheduleModel.getReminder_id(),
                                        scheduleModel.getId(),
                                        new ScheduleFragmentViewModel.CallBackHandle() {
                                            @Override
                                            public void onSuccess() {
                                                //success
                                                resultMessage.arg1 = 1;
                                                handler.sendMessage(resultMessage);
                                                Log.d(TAG, "run: sucess");
                                            }

                                            @Override
                                            public void onError() {
                                                //failure
                                                resultMessage.arg1 = 0;
                                                handler.sendMessage(resultMessage);
                                                Log.d(TAG, "run: errrr");
                                            }
                                        }
                                );
                            } catch (IOException e) {
                                //failure
                                resultMessage.arg1 = 0;
                                handler.sendMessage(resultMessage);
//                                Log.e(TAG, "run: " + e.getMessage());
                                Log.d(TAG, "run: catch");
                            }
                        }
                    });
                } else {
                    threadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            Message resultMessage = new Message();
                            resultMessage.obj = "create";
                            try {
                                Event eventResult = calendarService.events().insert("primary",
                                                ScheduleUtils.createEvent(scheduleModel))
                                        .execute();

                                scheduleFragmentViewModel.updateIsAlarm(
                                        scheduleModel.getClass_group_id(),
                                        1,
                                        eventResult.getId(),
                                        scheduleModel.getId(),
                                        new ScheduleFragmentViewModel.CallBackHandle() {
                                            @Override
                                            public void onSuccess() {
                                                //success
                                                resultMessage.arg1 = 1;
                                                handler.sendMessage(resultMessage);
                                                Log.d(TAG, "run: success");
                                            }

                                            @Override
                                            public void onError() {
                                                //failure
                                                resultMessage.arg1 = 0;
                                                handler.sendMessage(resultMessage);
                                                Log.d(TAG, "run: errr");
                                            }
                                        }
                                );
                            } catch (IOException e) {
                                //failure
                                resultMessage.arg1 = 0;
                                handler.sendMessage(resultMessage);
                                Log.d(TAG, "run: catch");
//                                Log.d(TAG, "run: " + e.getMessage());
                            }
                        }
                    });
                }
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
        scheduleFragmentViewModel.getListSchedule().observe(getViewLifecycleOwner(), scheduleModels -> {
            Log.d(TAG, "onChanged: list data");
            adapter.setListData(scheduleModels);
        });
    }
}