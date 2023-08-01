package com.example.myfpl.models;

import java.util.ArrayList;
import java.util.List;

public class SurveyModel {
    String lesson_name;
    String lecturer_name;
    String email;

    public SurveyModel(String lesson_name, String lecturer_name, String email) {
        this.lesson_name = lesson_name;
        this.lecturer_name = lecturer_name;
        this.email = email;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<SurveyModel> getSurveyList() {
        List<SurveyModel> surveyList = new ArrayList<SurveyModel>();
        surveyList.add(new SurveyModel("Lập trình Android", "Nguyễn Ngọc Chấn", "channn3@fpt.edu.vn"));
        surveyList.add(new SurveyModel("Khởi sự doanh nghiệp", "Nguyễn Thị Kim Ngân", "nganntk45@fpt.edu.vn"));
        surveyList.add(new SurveyModel("Lập trình Java", "Nguyễn Ngọc Chấn", "channn3@fpt.edu.vn"));
        return surveyList;
    }
}
