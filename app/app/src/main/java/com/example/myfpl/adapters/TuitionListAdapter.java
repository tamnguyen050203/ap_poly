package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.databinding.ItemTuitionBinding;
import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.models.TuitionModel;

import java.util.ArrayList;
import java.util.List;

public class TuitionListAdapter  extends RecyclerView.Adapter<TuitionListAdapter.TuitionListViewHolder> {

    private List<TuitionModel> tuitionList =  new ArrayList<TuitionModel>();
    private static HandleEvent handleEvent;
    private Context context;

    public TuitionListAdapter(List<TuitionModel> tuitionList, Context context, HandleEvent handleEvent) {
        this.tuitionList = tuitionList;
        this.context = context;
        TuitionListAdapter.handleEvent = handleEvent;
    }

    public List<TuitionModel> getListData() {
        return tuitionList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<TuitionModel> tuitionList) {
        this.tuitionList = tuitionList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TuitionListAdapter.TuitionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTuitionBinding binding = ItemTuitionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TuitionListAdapter.TuitionListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TuitionListAdapter.TuitionListViewHolder viewHolder, int position) {
        TuitionModel tuition = tuitionList.get(position);
        if (tuition == null) return;

        viewHolder.onBind(tuition);
    }

    @Override
    public int getItemCount() {
        if (tuitionList == null) return 0;
        return tuitionList.size();
    }

    public static class TuitionListViewHolder extends RecyclerView.ViewHolder {

        public ItemTuitionBinding binding;

        public TuitionListViewHolder(ItemTuitionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void onBind(TuitionModel tuition) {
            binding.feeNameValue.setText(tuition.getFee_name());
            binding.descFeeValue.setText(tuition.getFee_desc());
            binding.codeStudentFeeValue.setText(tuition.getDesc_student());
            binding.feeValue.setText(tuition.getFee_value());

            binding.btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleEvent.OnItemClick(tuition, getAdapterPosition());
                }
            });
        }
    }

    public interface HandleEvent {
        void OnItemClick(TuitionModel tuitionModel, int index);
    }
}
