package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ItemScheduleDetailBinding;
import com.example.myfpl.models.BaseSchedule;
import com.example.myfpl.util.DateUtil;
import com.shrikanthravi.collapsiblecalendarview.data.Day;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DetailScheduleListAdapter<T extends BaseSchedule> extends RecyclerView.Adapter<DetailScheduleListAdapter.ItemViewHolder> {
    private List<T> listData;
    HandleEventListItem<T> eventListItem;


    private ArrayList<Day> currentDate;

    public DetailScheduleListAdapter(HandleEventListItem<T> eventListItem, ArrayList<Day> currentDate) {
        this.eventListItem = eventListItem;
        this.currentDate = currentDate;
    }

    public DetailScheduleListAdapter(List<T> listData) {
        this.listData = listData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCurrentDaySelected(ArrayList<Day> currentDate) {
        this.currentDate = currentDate;

        this.listData = filter(currentDate, (ArrayList<T>) this.listData);
        notifyDataSetChanged();
    }

    private ArrayList<T> filter(ArrayList<Day> daysSelected, ArrayList<T> listData) {
        ArrayList<T> list = new ArrayList<>();

        for (T item : listData) {
//            Log.d("TAG>filter", "filter: " + daysSelected);
//            Log.d("TAG>filter", "filter: " + daysSelected.toString().contains(item.getDate()) + ": " + item.getDate());
            if(daysSelected.toString().contains(item.getDate())){
                list.add(item);
            }
        }
        return list;
    }

    public List<T> getListData() {
        return listData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<T> listData) {

        this.listData = filter(this.currentDate, (ArrayList<T>) listData);
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
                    eventListItem.onAlarmClick(listData.get(position), position);
                    listData.get(position).setAlarm(listData.get(position).isAlarm() == 1 ? 0 : 1);
                    notifyDataSetChanged();
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
        public void onBind(BaseSchedule schedule, int itemIndex) {
            binding.amphitheater.setText(schedule.getAmphitheater_name());
            binding.lecuturer.setText(schedule.getLecturer_name());
            binding.roomAShift.setText(
                    schedule.getRoom_name() + " - " + schedule.getShift_name() + " - " + schedule.getDate()
            );

            binding.subjectTitle.setText(schedule.getLesson_name());
            binding.subjectCode.setText(schedule.getLesson_code_name());
            binding.timeStateEnd.setText(DateUtil.getMeridiemFromString("hh:mm:ss", schedule.getEnd_time()).toUpperCase(Locale.ROOT));
            binding.timeEnd.setText(DateUtil.getTimeFromString("hh:mm:ss", schedule.getEnd_time()));
            binding.timeStart.setText(DateUtil.getTimeFromString("hh:mm:ss", schedule.getStart_time()));
            binding.timeState.setText(DateUtil.getMeridiemFromString("hh:mm:ss", schedule.getStart_time()).toUpperCase(Locale.ROOT));

            if (schedule.isAlarm() != null) {
                binding.btnAlarm.setImageResource(
                        schedule.isAlarm() == 1 ? R.drawable.ic_alarm_active : R.drawable.ic_alarm_inactive
                );
            }
        }
    }

    public interface HandleEventListItem<T extends BaseSchedule> {
        void onItemClick(T scheduleModel, int itemIndex);

        void onAlarmClick(T scheduleModel, int itemIndex);
    }
}
