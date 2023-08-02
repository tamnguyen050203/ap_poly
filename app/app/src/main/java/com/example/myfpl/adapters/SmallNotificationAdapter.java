package com.example.myfpl.adapters;

import static com.example.myfpl.util.DateUtil.ConvertTimeToString;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.databinding.ItemMainNotificationBinding;
import com.example.myfpl.databinding.NotificationItemBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.ui.activities.NotifyActivity;
import com.example.myfpl.util.ToastApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SmallNotificationAdapter extends RecyclerView.Adapter<SmallNotificationAdapter.ViewHolder> {
    private ArrayList<NotificationModel> list;
    private HandleEvent handleEvent;
    private Context context;

    public SmallNotificationAdapter(Context context, ArrayList<NotificationModel> list, HandleEvent handleEvent) {
        this.list = list;
        this.handleEvent = handleEvent;
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
        ItemMainNotificationBinding binding = ItemMainNotificationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NotificationModel notificationModel = list.get(position);
        if (notificationModel == null) {
            return;
        }
        holder.binding.notifyTitle.setText(notificationModel.getTitle());
        holder.binding.publishDate.setText(ConvertTimeToString(notificationModel.getCreated_at()));
        holder.binding.namePublisher.setText(notificationModel.getAuthor());
        switch (notificationModel.getType()) {
            case "Học Tập":
                holder.binding.itemNotify.setBackgroundTintList(context.getResources().getColorStateList(R.color.aqua_20, context.getTheme()));
                holder.binding.dotIndicator.setBackgroundTintList(context.getResources().getColorStateList(R.color.aqua, context.getTheme()));
                break;
            case "Việc Làm":
                holder.binding.itemNotify.setBackgroundTintList(context.getResources().getColorStateList(R.color.red_light_20, context.getTheme()));
                holder.binding.dotIndicator.setBackgroundTintList(context.getResources().getColorStateList(R.color.red_light, context.getTheme()));
                break;
            case "Học Phí":
                holder.binding.itemNotify.setBackgroundTintList(context.getResources().getColorStateList(R.color.orange_20, context.getTheme()));
                holder.binding.dotIndicator.setBackgroundTintList(context.getResources().getColorStateList(R.color.orange, context.getTheme()));
                break;
            case "Hoạt Động":
                holder.binding.itemNotify.setBackgroundTintList(context.getResources().getColorStateList(R.color.green_light_20, context.getTheme()));
                holder.binding.dotIndicator.setBackgroundTintList(context.getResources().getColorStateList(R.color.green_light, context.getTheme()));
                break;
        }
        holder.binding.itemNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                // go to detail content and pass data
                handleEvent.OnItemClick(notificationModel, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMainNotificationBinding binding;

        public ViewHolder(ItemMainNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface HandleEvent {
        void OnItemClick(NotificationModel notificationModel, int index);
    }
}
