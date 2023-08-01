package com.example.myfpl.models;

import java.io.Serializable;

public class ScheduleModel implements Serializable {

    String id;
    String lesson_name;
    String lesson_code_name;
    String room_name;
    String date;
    String shift_name;
    String start_time;
    String end_time;
    String amphitheater_name;
    String is_attend;
    String lecturer_name;

    public ScheduleModel(String id, String lesson_name, String lesson_code_name, String room_name, String date, String shift_name, String start_time, String end_time, String amphitheater_name, String is_attend, String lecturer_name) {
        this.id = id;
        this.lesson_name = lesson_name;
        this.lesson_code_name = lesson_code_name;
        this.room_name = room_name;
        this.date = date;
        this.shift_name = shift_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.amphitheater_name = amphitheater_name;
        this.is_attend = is_attend;
        this.lecturer_name = lecturer_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAmphitheater_name() {
        return amphitheater_name;
    }

    public void setAmphitheater_name(String amphitheater_name) {
        this.amphitheater_name = amphitheater_name;
    }

    public String getIs_attend() {
        return is_attend;
    }

    public void setIs_attend(String is_attend) {
        this.is_attend = is_attend;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }
}
