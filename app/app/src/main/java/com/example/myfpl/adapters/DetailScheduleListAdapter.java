package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ItemScheduleBinding;
import com.example.myfpl.databinding.ItemScheduleDetailBinding;
import com.example.myfpl.models.TestModelSchedule;
import com.example.myfpl.util.DateUtil;
import com.example.myfpl.util.StringUtil;

import java.util.List;
import java.util.Locale;

public class DetailScheduleListAdapter extends RecyclerView.Adapter<DetailScheduleListAdapter.ItemViewHolder> {
    private List<TestModelSchedule> listData;
    HandleEventListItem eventListItem;

    public DetailScheduleListAdapter(HandleEventListItem eventListItem) {
        this.eventListItem = eventListItem;
    }


    public DetailScheduleListAdapter(List<TestModelSchedule> listData) {
        this.listData = listData;
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
    public DetailScheduleListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScheduleDetailBinding binding = ItemScheduleDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailScheduleListAdapter.ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (listData.get(position) != null) {

            holder.binding.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListItem.onItemClick(listData.get(position), position);
                }
            });

            holder.binding.btnAlarm.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    listData.get(position).setAlarm(!listData.get(position).isAlarm());
                    eventListItem.onAlarmClick(listData.get(position), position);
                }
            });

            holder.onBind(listData.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        if (listData == null) return 0;
        return listData.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemScheduleDetailBinding binding;

        public ItemViewHolder(ItemScheduleDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void onBind(TestModelSchedule testModelSchedule, int itemIndex) {
            binding.amphitheater.setText(testModelSchedule.getAmphitheater());
            binding.lecuturer.setText(testModelSchedule.getLecturer());
            binding.roomAShift.setText(
                    testModelSchedule.getRoom() + " - " + testModelSchedule.getShift()
            );

            binding.timeStateEnd.setText(DateUtil.getMeridiemFromString("yyyy-MM-dd hh:mm:ss", testModelSchedule.getEndTime()).toUpperCase(Locale.ROOT));
            binding.timEnd.setText(DateUtil.getTimeFromString("yyyy-MM-dd hh:mm:ss", testModelSchedule.getEndTime()));
            binding.timeStart.setText(DateUtil.getTimeFromString("yyyy-MM-dd hh:mm:ss", testModelSchedule.getStartTime()));
            binding.timeState.setText(DateUtil.getMeridiemFromString("yyyy-MM-dd hh:mm:ss", testModelSchedule.getStartTime()).toUpperCase(Locale.ROOT));

            binding.btnAlarm.setImageResource(
                    testModelSchedule.isAlarm() ? R.drawable.ic_alarm_active : R.drawable.ic_alarm_inactive
            );
        }
    }

    public interface HandleEventListItem {
        void onItemClick(TestModelSchedule testModelSchedule, int itemIndex);

        void onAlarmClick(TestModelSchedule testModelSchedule, int itemIndex);
    }
}
