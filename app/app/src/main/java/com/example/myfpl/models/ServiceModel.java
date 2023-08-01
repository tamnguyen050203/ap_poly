package com.example.myfpl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel implements Serializable {
    private String number;
    private String content;

    public ServiceModel() {
    }

    public ServiceModel(String number, String content) {
        this.number = number;
        this.content = content;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public static List<ServiceModel> getData(){
        List<ServiceModel> list = new ArrayList<>();
        list.add(new ServiceModel( "1.", "Đăng ký cấp bảng điểm"));
        list.add(new ServiceModel( "2.", "Đăng ký cấp lại thẻ"));
        list.add(new ServiceModel( "3.", "Đăng ký khôi phục điểm danh"));
        list.add(new ServiceModel( "4.", "Đăng ký tốt nghiệp sớm"));
        list.add(new ServiceModel( "5.", "Đăng ký thi lại"));
        list.add(new ServiceModel( "6.", "Đăng ký xác nhận sinh viên"));
        return list;
    }
}
