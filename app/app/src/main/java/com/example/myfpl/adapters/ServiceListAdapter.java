package com.example.myfpl.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfpl.R;
import com.example.myfpl.databinding.ServiceItemBinding;
import com.example.myfpl.models.ServiceModel;
import java.util.List;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

    private List<ServiceModel> list;
    private Context context;

    public ServiceListAdapter( Context context,List<ServiceModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử sinh viên
        View view = inflater.inflate(R.layout.service_item, parent, false);
        ServiceItemBinding binding  = ServiceItemBinding.bind(view.getRootView());
        ServiceListAdapter.ViewHolder holder = new ServiceListAdapter.ViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ServiceModel serviceModel = list.get(i);

        holder.binding.serviceNumber.setText(serviceModel.getNumber());
        holder.binding.serviceContent.setText(serviceModel.getContent());

        holder.binding.serviceItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final ServiceItemBinding binding;
        public ViewHolder(ServiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
