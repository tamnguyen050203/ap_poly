package com.example.myfpl.adapters;

import static com.example.myfpl.util.DateUtil.ConvertTimeToString;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.databinding.NotificationItemBinding;
import com.example.myfpl.databinding.TestLayoutItemBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.models.Test;
import com.example.myfpl.ui.activities.DetailNotificationActivity;
import com.example.myfpl.ui.activities.NotifyActivity;
import com.example.myfpl.util.ToastApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<NotificationModel> list;
    private Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(ArrayList<NotificationModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotificationItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notification_item, parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationModel notificationModel = list.get(position);
        if (notificationModel == null) {
            return;
        }
        holder.binding.tvNotificationName.setText(notificationModel.getTitle());
        holder.binding.tvCreatedAt.setText(ConvertTimeToString(notificationModel.getCreated_date()));
        holder.binding.tvAuthor.setText(notificationModel.getAuthor());
        holder.binding.tvType.setText(notificationModel.getType());
        switch (holder.binding.tvType.getText().toString()) {
            case "Học tập":
                holder.binding.tvType.setBackgroundResource(R.drawable.notification_type_blue);
                holder.binding.tvType.setTextColor(ContextCompat.getColor(context, R.color.aqua));
                break;
            case "Học phí":
                holder.binding.tvType.setBackgroundResource(R.drawable.notification_type_orange);
                holder.binding.tvType.setTextColor(ContextCompat.getColor(context, R.color.orange));
                break;
            case "Hoạt động":
                holder.binding.tvType.setBackgroundResource(R.drawable.notification_type_green);
                holder.binding.tvType.setTextColor(ContextCompat.getColor(context, R.color.green_light));
                break;
            case "Việc làm":
                holder.binding.tvType.setBackgroundResource(R.drawable.notification_type_red);
                holder.binding.tvType.setTextColor(ContextCompat.getColor(context, R.color.red_light));
                break;
        }
        holder.binding.notificationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to detail content and pass data
                ToastApp.show(context, "Item" + notificationModel.getId());
                ((NotifyActivity) context).goToDetail(notificationModel, ConvertTimeToString(notificationModel.getCreated_date()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final NotificationItemBinding binding;

        public ViewHolder(NotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
