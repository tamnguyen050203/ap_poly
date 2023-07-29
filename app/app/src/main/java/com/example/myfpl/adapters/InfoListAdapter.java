package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.databinding.ItemInfoBinding;
import com.example.myfpl.models.InfoModel;

import java.util.List;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.InfoListViewHolder> {

    private List<InfoModel> listData;

    public InfoListAdapter(List<InfoModel> listData, Context context) {
        this.listData = listData;
    }

    public List<InfoModel> getListData() {
        return listData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<InfoModel> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InfoListAdapter.InfoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInfoBinding binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InfoListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoListAdapter.InfoListViewHolder viewHolder, int position) {
        InfoModel info = listData.get(position);
        if (info == null) return;

        viewHolder.onBind(info);
    }

    @Override
    public int getItemCount() {
        if (listData == null) return 0;
        return listData.size();
    }

    public static class InfoListViewHolder extends RecyclerView.ViewHolder {

        ItemInfoBinding binding;
        public InfoListViewHolder(ItemInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void onBind(InfoModel info) {
            binding.titleInfo.setText(info.getTitle());
            binding.contentInfo.setText(info.getContent());
        }

    }
}
