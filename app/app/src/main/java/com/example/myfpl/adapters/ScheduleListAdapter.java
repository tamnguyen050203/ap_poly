package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.databinding.ItemScheduleBinding;
import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.TestScheduleModel;
import com.example.myfpl.util.DateUtil;

import java.util.List;
import java.util.Locale;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder>{

    private List<ScheduleModel> listData;
    ScheduleListAdapter.HandleEventListItem eventListItem;
    private Context context;

    public ScheduleListAdapter(List<ScheduleModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    public List<ScheduleModel> getListData() {
        return listData;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<ScheduleModel> listData) {
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
    public void onBindViewHolder(@NonNull ScheduleListViewHolder viewHolder,@SuppressLint("RecyclerView") int position) {
        ScheduleModel schedule = listData.get(position);
        if (schedule == null) return;

        viewHolder.onBind(schedule);

        viewHolder.binding.itemSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                eventListItem.onItemClick(schedule, position);
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
        public void onBind(ScheduleModel schedule) {
            binding.subjectTitle.setText(schedule.getLesson_name());
            binding.lecuturer.setText(schedule.getLecturer_name());
            binding.time.setText(DateUtil.getTimeFromString("hh:mm:ss", schedule.getStart_time()));
            binding.timeState.setText(DateUtil.getMeridiemFromString("hh:mm:ss", schedule.getStart_time()).toUpperCase(Locale.ROOT));
            binding.place.setText(schedule.getRoom_name() + " - " + schedule.getAmphitheater_name());
        }
    }

    public interface HandleEventListItem {
        void onItemClick(ScheduleModel scheduleModel, int itemIndex);

        void onAlarmClick(ScheduleModel scheduleModel, int itemIndex);
    }
}
