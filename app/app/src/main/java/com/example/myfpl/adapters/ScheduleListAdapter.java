package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.databinding.ItemScheduleBinding;
import com.example.myfpl.models.TestModelSchedule;
import com.example.myfpl.util.DateUtil;

import java.util.List;
import java.util.Locale;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder> {
    private List<TestModelSchedule> listData;
    HandleEventListItem eventListItem;

    public ScheduleListAdapter(HandleEventListItem eventListItem) {
        this.eventListItem = eventListItem;
    }

    public List<TestModelSchedule> getListData() {
        return listData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<TestModelSchedule> listData) {
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
        TestModelSchedule schedule = listData.get(position);
        if (schedule == null) return;

        viewHolder.onBind(schedule);

        viewHolder.binding.itemSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListItem.onItemClick(schedule, position);
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
        public void onBind(TestModelSchedule schedule) {
            binding.subjectTitle.setText(schedule.getScheduleTitle());
            binding.lecuturer.setText(schedule.getLecturer());
            binding.time.setText(DateUtil.getTimeFromString("yyyy-MM-dd hh:mm:ss", schedule.getStartTime()));
            binding.timeState.setText(DateUtil.getMeridiemFromString("yyyy-MM-dd hh:mm:ss", schedule.getStartTime()).toUpperCase(Locale.ROOT));
            binding.place.setText(schedule.getRoom() + " - " + schedule.getAmphitheater());
        }
    }

    public interface HandleEventListItem {
        void onItemClick(TestModelSchedule testModelSchedule, int itemIndex);

        void onAlarmClick(TestModelSchedule testModelSchedule, int itemIndex);
    }
}
