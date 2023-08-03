package com.example.myfpl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfpl.R;
import com.example.myfpl.databinding.CommunityItemBinding;
import com.example.myfpl.models.CommunityModel;

import java.util.List;

public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListAdapter.ViewHolder> {
    private List<CommunityModel> list;
    private Context context;
    private HandleEvent handleEvent;
    public CommunityListAdapter(Context context, List<CommunityModel> list, HandleEvent handleEvent) {
        this.list = list;
        this.context = context;
        this.handleEvent = handleEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommunityItemBinding binding = CommunityItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommunityModel community = list.get(position);
        if(community==null) return;
        holder.binding.communityName.setText(community.getName());
        Glide.with(context).load(community.getImage()).into(holder.binding.communityImage);
        holder.binding.communityItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEvent.OnItemClick(community.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list==null) return 0;
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        private final CommunityItemBinding binding;
         public ViewHolder(CommunityItemBinding binding) {
             super(binding.getRoot());
             this.binding = binding;
         }
     }
     public interface HandleEvent{
        void OnItemClick(String url);
     }
}
