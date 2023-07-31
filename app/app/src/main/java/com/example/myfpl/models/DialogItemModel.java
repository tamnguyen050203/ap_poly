package com.example.myfpl.models;

import java.io.Serializable;
import java.util.ArrayList;

public class DialogItemModel implements Serializable {
    private String title;
    private boolean isChoose;

    public DialogItemModel(String title, boolean isChoose) {
        this.title = title;
        this.isChoose = isChoose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public static ArrayList<DialogItemModel> getExModel(){
        ArrayList<DialogItemModel> list = new ArrayList<>();
        list.add(new DialogItemModel("FPT Polytechnic HO", false));
        list.add(new DialogItemModel("FPT Polytechnic Hà Nội", false));
        list.add(new DialogItemModel("FPT Polytechnic Hồ Chí Minh", false));
        list.add(new DialogItemModel("FPT Polytechnic Đà Nẵng", false));
        list.add(new DialogItemModel("FPT Polytechnic Cần Thơ", false));
        list.add(new DialogItemModel("FPT Polytechnic Tây Nguyên", false));
        list.add(new DialogItemModel("FPT Polytechnic Hải Phòng", false));
        return list;
    }
}
