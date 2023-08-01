package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.databinding.ItemScheduleBinding;
import com.example.myfpl.models.TestScheduleModel;
import com.example.myfpl.util.DateUtil;

import java.util.List;
import java.util.Locale;

public class TestScheduleListAdapter extends RecyclerView.Adapter<TestScheduleListAdapter.ScheduleListViewHolder> {
    private List<TestScheduleModel> listData;
    HandleEventListItem eventListItem;
    private Context context;

    public TestScheduleListAdapter(List<TestScheduleModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    public List<TestScheduleModel> getListData() {
        return listData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<TestScheduleModel> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScheduleBinding binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ScheduleListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleListViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        TestScheduleModel testSchedule = listData.get(position);
        if (testSchedule == null) return;

        viewHolder.onBind(testSchedule);

        viewHolder.binding.itemSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListItem.onItemClick(testSchedule, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listData == null) return 0;
        return listData.size();
    }

    public static class ScheduleListViewHolder extends RecyclerView.ViewHolder {
        ItemScheduleBinding binding;

        public ScheduleListViewHolder(ItemScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void onBind(TestScheduleModel schedule) {
            binding.subjectTitle.setText(schedule.getLesson_name());
            binding.lecuturer.setText(schedule.getLecturer_name());
            binding.time.setText(DateUtil.getTimeFromString("hh:mm:ss", schedule.getStart_time()));
            binding.timeState.setText(DateUtil.getMeridiemFromString("hh:mm:ss", schedule.getStart_time()).toUpperCase(Locale.ROOT));
            binding.place.setText(schedule.getRoom_name() + " - " + schedule.getAmphitheater_name());
        }
    }

    public interface HandleEventListItem {
        void onItemClick(TestScheduleModel testScheduleModel, int itemIndex);

        void onAlarmClick(TestScheduleModel testScheduleModel, int itemIndex);
    }
}
