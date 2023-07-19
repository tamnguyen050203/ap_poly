package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ItemScheduleBinding;
import com.example.myfpl.models.TestModelSchedule;
import com.example.myfpl.util.AnimationUtil;
import com.example.myfpl.util.DateUtil;
import com.example.myfpl.util.StringUtil;
import com.google.firebase.auth.PhoneAuthOptions;

import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ItemViewHolder> {
    private List<TestModelSchedule> listData;
    HandleEventListItem eventListItem;

    public ScheduleListAdapter(HandleEventListItem eventListItem) {
        this.eventListItem = eventListItem;
    }


    public ScheduleListAdapter(List<TestModelSchedule> listData) {
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
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScheduleBinding binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (listData.get(position) != null) {
            holder.binding.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listData.get(position).setExpand(!listData.get(position).isExpand());
                    holder.binding.hiddenPart.setVisibility(listData.get(position).isExpand() ? View.VISIBLE : View.GONE);
                    eventListItem.onItemClick(listData.get(position), position);
                }
            });

            holder.binding.buttonAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListItem.onAlarmClick(listData.get(position), position);
                    listData.get(position).setAlarm(!listData.get(position).isAlarm());
                    holder.binding.item.setBackgroundResource(
                            listData.get(position).isAlarm() ? R.drawable.drawable_alarm_border_schedule_item : R.drawable.drawable_border_schedule_item
                    );
                    holder.binding.verticalIndicator.setBackgroundResource(
                            listData.get(position).isAlarm() ? R.drawable.indicator_rounded_active : R.drawable.indicator_rounded
                    );
                    holder.binding.buttonAlarm.setImageResource(
                            listData.get(position).isAlarm() ? R.drawable.ic_alarm_active : R.drawable.ic_alarm_inactive
                    );
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
        private ItemScheduleBinding binding;

        public ItemViewHolder(ItemScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(TestModelSchedule testModelSchedule, int itemIndex) {
            binding.titleSchedule.setText(testModelSchedule.getScheduleTitle());
            binding.subjectCode.setText(testModelSchedule.getSubjectCode());
            binding.room.setText(testModelSchedule.getRoom());
            binding.shift.setText(StringUtil.builderTitle("Ca", testModelSchedule.getShift()));
            binding.lecturer.setText(StringUtil.builderTitle("Giảng viên", testModelSchedule.getLecturer()));
            binding.amphitheater.setText(StringUtil.builderTitle("Giảng đường", testModelSchedule.getAmphitheater()));
            binding.scheduleTime.setText(
                    DateUtil.getDateFromString("yyyy-MM-dd hh:MM:ss", testModelSchedule.getTime())
                            .concat(" • ")
                            .concat(DateUtil.getTimeFromString("yyyy-MM-dd hh:MM:ss", testModelSchedule.getTime()))
            );
        }
    }

    public interface HandleEventListItem {
        void onItemClick(TestModelSchedule testModelSchedule, int itemIndex);

        void onAlarmClick(TestModelSchedule testModelSchedule, int itemIndex);
    }
}
