package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.databinding.TestLayoutItemBinding;
import com.example.myfpl.models.Test;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestAdapterHolder> {
    private ArrayList<Test> listData;

    public TestAdapter(ArrayList<Test> lisData) {
        this.listData = lisData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(ArrayList<Test> listData){
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TestLayoutItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.test_layout_item, parent, false);
        return new TestAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapterHolder holder, int position) {
        if(listData.get(position) != null){
            holder.binding.setTestViewModel(listData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(listData == null) return 0;
        return  listData.size();
    }

    static class TestAdapterHolder extends RecyclerView.ViewHolder {
        private final TestLayoutItemBinding binding;

        public TestAdapterHolder(TestLayoutItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
