package com.example.myfpl.models;

import java.util.ArrayList;
import java.util.List;

public class TuitionModel {
    String fee_name;
    String fee_desc;
    String desc_student;
    String fee_value;

    public TuitionModel(String fee_name, String fee_desc, String desc_student, String fee_value) {
        this.fee_name = fee_name;
        this.fee_desc = fee_desc;
        this.desc_student = desc_student;
        this.fee_value = fee_value;
    }

    public String getFee_name() {
        return fee_name;
    }

    public void setFee_name(String fee_name) {
        this.fee_name = fee_name;
    }

    public String getFee_desc() {
        return fee_desc;
    }

    public void setFee_desc(String fee_desc) {
        this.fee_desc = fee_desc;
    }

    public String getDesc_student() {
        return desc_student;
    }

    public void setDesc_student(String desc_student) {
        this.desc_student = desc_student;
    }

    public String getFee_value() {
        return fee_value;
    }

    public void setFee_value(String fee_value) {
        this.fee_value = fee_value;
    }

    public static List<TuitionModel> getTuitionList() {
        List<TuitionModel> tuitionList = new ArrayList<TuitionModel>();
        tuitionList.add(new TuitionModel("Học phí", "Học phí kì 1", "ps24559", "6.000.000"));
        tuitionList.add(new TuitionModel("Học phí", "Học phí kì 2", "ps24559", "5.500.000"));
        tuitionList.add(new TuitionModel("Học phí", "Học phí kì 3", "ps24559", "5.500.000"));
        tuitionList.add(new TuitionModel("BHYT", "Bảo hiểm y tế", "ps24559", "1.500.000"));
        return tuitionList;
    }
}
