package com.example.myfpl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestModelSchedule implements Serializable {
    private String scheduleTitle;
    private String subjectCode;
    private String time;
    private String shift;
    private String lecturer;
    private String amphitheater;
    private String room;
    private boolean isAlarm;


    private boolean isExpand;

    public TestModelSchedule(String scheduleTitle, String subjectCode, String time, String shift, String lecturer, String amphitheater, String room, boolean isAlarm) {
        this.scheduleTitle = scheduleTitle;
        this.subjectCode = subjectCode;
        this.time = time;
        this.shift = shift;
        this.lecturer = lecturer;
        this.amphitheater = amphitheater;
        this.room = room;
        this.isAlarm = isAlarm;
        this.isExpand = false;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isAlarm() {
        return isAlarm;
    }

    public void setAlarm(boolean alarm) {
        isAlarm = alarm;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getAmphitheater() {
        return amphitheater;
    }

    public void setAmphitheater(String amphitheater) {
        this.amphitheater = amphitheater;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public static List<TestModelSchedule> getListModel() {
        List<TestModelSchedule> list = new ArrayList<>();

        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 00:39:30", "1", "chann3", "Phan Mem Quang Trung", "T301 • Tòa T", false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 00:39:30", "1", "chann3", "Phan Mem Quang Trung", "T301 • Tòa T", false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 00:39:30", "1", "chann3", "Phan Mem Quang Trung", "T301 • Tòa T", false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 00:39:30", "1", "chann3", "Phan Mem Quang Trung", "T301 • Tòa T", false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 00:39:30", "1", "chann3", "Phan Mem Quang Trung", "T301 • Tòa T", false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 00:39:30", "1", "chann3", "Phan Mem Quang Trung", "T301 • Tòa T", false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 00:39:30", "1", "chann3", "Phan Mem Quang Trung", "T301 • Tòa T", false));

        return list;
    }
}
