package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.databinding.SpinnerItemBinding;
import com.example.myfpl.models.DialogItemModel;
import com.example.myfpl.util.UIUtil;

import java.util.List;

public class ListItemDialogAdapter extends RecyclerView.Adapter<ListItemDialogAdapter.ItemViewHolder> {
    public ListItemDialogAdapter(List<DialogItemModel> listData, HandleEvent handleEvent, int chooseOption, Context context) {
        this.listData = listData;
        this.handleEvent = handleEvent;
        this.chooseOption = chooseOption;
        this.context = context;
    }

    private List<DialogItemModel> listData;
    private final HandleEvent handleEvent;
    private int chooseOption = -1;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<DialogItemModel> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setChooseOption(int chooseOption) {
        this.chooseOption = chooseOption;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpinnerItemBinding binding = SpinnerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (listData.get(position) == null) return;

        boolean isVisibleIndicator = position != 0 && position != chooseOption && position != chooseOption + 1;
        holder.binding.indicatorTop.setBackgroundColor(isVisibleIndicator ? UIUtil.getColor(context, R.color.indiactor_color) : UIUtil.getColor(context, R.color.transparent));

        holder.binding.textItem.setText(listData.get(position).getTitle());
        holder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEvent.onItemClick(listData.get(position), position);
            }
        });

        boolean isSelected = position == chooseOption;
        holder.binding.item.setBackgroundResource(isSelected ? R.drawable.drawable_item_selected : R.drawable.drawable_item_unselected);
        holder.binding.textItem.setTextColor(isSelected ? UIUtil.getColor(context, R.color.white): UIUtil.getColor(context, R.color.primary_text_color));
    }

    @Override
    public int getItemCount() {
        if (listData == null) return 0;
        return listData.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public SpinnerItemBinding binding;

        public ItemViewHolder(SpinnerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface HandleEvent {
        void onItemClick(DialogItemModel dialogItemModel, int position);
    }
}
