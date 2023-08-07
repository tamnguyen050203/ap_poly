package com.example.myfpl.models;

import java.io.Serializable;

public class BaseSchedule implements Serializable {
    private int id;
    private String lesson_name;
    private String lesson_code_name;
    private String room_name;
    private String date;
    private String shift_name;
    private String start_time;
    private String end_time;
    private String lecturer_name;
    private String amphitheater_name;
    private Integer is_alarm;
    private String reminder_id;


    public BaseSchedule(int id, String lesson_name, String lesson_code_name, String room_name, String date, String shift_name, String start_time, String end_time, String lecturer_name, String amphitheater_name, Integer is_alarm, String reminder_id) {
        this.id = id;
        this.lesson_name = lesson_name;
        this.lesson_code_name = lesson_code_name;
        this.room_name = room_name;
        this.date = date;
        this.shift_name = shift_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.lecturer_name = lecturer_name;
        this.amphitheater_name = amphitheater_name;
        this.is_alarm = is_alarm;
        this.reminder_id = reminder_id;
    }

    public Integer isAlarm() {
        return is_alarm;
    }

    public void setAlarm(Integer is_alarm) {
        this.is_alarm = is_alarm;
    }

    public String getReminder_id() {
        return reminder_id;
    }

    public void setReminder_id(String reminder_id) {
        this.reminder_id = reminder_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getLesson_code_name() {
        return lesson_code_name;
    }

    public void setLesson_code_name(String lesson_code_name) {
        this.lesson_code_name = lesson_code_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShift_name() {
        return shift_name;
    }

    public void setShift_name(String shift_name) {
        this.shift_name = shift_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getAmphitheater_name() {
        return amphitheater_name;
    }

    public void setAmphitheater_name(String amphitheater_name) {
        this.amphitheater_name = amphitheater_name;
    }
}
