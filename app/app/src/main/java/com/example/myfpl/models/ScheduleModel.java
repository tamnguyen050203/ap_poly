package com.example.myfpl.models;

import java.io.Serializable;

public class ScheduleModel extends BaseSchedule implements Serializable {
    private String class_group_link;
    private String class_group_name;
    private int class_group_id;
    private String detail;

    public ScheduleModel(int id, String lesson_name, String lesson_code_name, String class_group_link, String class_group_name, String room_name, String date, String shift_name, String start_time, String end_time, String lecturer_name, String amphitheater_name, String detail, int is_alarm, String reminder_id, int class_group_id) {
        super(id, lesson_name, lesson_code_name, room_name, date, shift_name, start_time, end_time, lecturer_name, amphitheater_name, is_alarm, reminder_id);
        this.class_group_link = class_group_link;
        this.class_group_name = class_group_name;
        this.detail = detail;
        this.class_group_id = class_group_id;
    }

    public int getClass_group_id() {
        return class_group_id;
    }

    public void setClass_group_id(int class_group_id) {
        this.class_group_id = class_group_id;
    }

    public String getClass_group_link() {
        return class_group_link;
    }

    public void setClass_group_link(String class_group_link) {
        this.class_group_link = class_group_link;
    }

    public String getClass_group_name() {
        return class_group_name;
    }

    public void setClass_group_name(String class_group_name) {
        this.class_group_name = class_group_name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
