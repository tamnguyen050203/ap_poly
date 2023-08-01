package com.example.myfpl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.databinding.ItemSurveyBinding;
import com.example.myfpl.models.SurveyModel;

import java.util.ArrayList;
import java.util.List;

public class SurveyListAdapter extends RecyclerView.Adapter<SurveyListAdapter.SurveyListViewHolder>{

    private List<SurveyModel> surveyList =  new ArrayList<SurveyModel>();
    private static SurveyListAdapter.HandleEvent handleEvent;
    private Context context;

    public SurveyListAdapter(List<SurveyModel> surveyList, Context context, SurveyListAdapter.HandleEvent handleEvent) {
        this.surveyList = surveyList;
        this.context = context;
        SurveyListAdapter.handleEvent = handleEvent;
    }

    public List<SurveyModel> getListData() {
        return surveyList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<SurveyModel> surveyList) {
        this.surveyList = surveyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SurveyListAdapter.SurveyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSurveyBinding binding = ItemSurveyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SurveyListAdapter.SurveyListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyListAdapter.SurveyListViewHolder viewHolder, int position) {
        SurveyModel survey = surveyList.get(position);
        if (survey == null) return;

        viewHolder.onBind(survey);
    }

    @Override
    public int getItemCount() {
        if (surveyList == null) return 0;
        return surveyList.size();
    }

    public static class SurveyListViewHolder extends RecyclerView.ViewHolder {

        public ItemSurveyBinding binding;

        public SurveyListViewHolder(ItemSurveyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void onBind(SurveyModel survey) {
            binding.lessonName.setText(survey.getLesson_name());
            binding.lecturerName.setText(survey.getLecturer_name());
            binding.emailLecturer.setText(survey.getEmail());

            binding.btnSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleEvent.OnItemClick(survey, getAdapterPosition());
                }
            });
        }
    }

    public interface HandleEvent {
        void OnItemClick(SurveyModel surveyModel, int index);
    }
}
