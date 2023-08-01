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

public class CommunityListAdapter extends BaseAdapter {
    private List<CommunityModel> list;
    private Context context;
    private HandleEvent handleEvent;
    public CommunityListAdapter(Context context, List<CommunityModel> list, HandleEvent handleEvent) {
        this.list = list;
        this.context = context;
        this.handleEvent = handleEvent;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.community_item, viewGroup, false);
            CommunityItemBinding binding  = CommunityItemBinding.bind(view.getRootView());
            ViewHolder holder = new ViewHolder(binding);
            view.setTag(holder);
        }
        CommunityModel communityModel = list.get(i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.binding.communityName.setText(communityModel.getName());
        holder.binding.communityImage.setImageResource(communityModel.getImage());
        holder.binding.communityItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEvent.OnItemClick(communityModel.getUrl());
            }
        });
        return view;
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
