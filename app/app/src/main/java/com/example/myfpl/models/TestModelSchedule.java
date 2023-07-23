package com.example.myfpl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestModelSchedule implements Serializable {
    private String scheduleTitle;
    private String subjectCode;
    private String startTime;
    private String endTime;
    private String shift;
    private String lecturer;
    private String amphitheater;
    private String room;
    private boolean isAlarm;

    private boolean isExpand;
    private boolean isTestSchedule;

    public TestModelSchedule(String scheduleTitle, String subjectCode, String time, String endTime, String shift, String lecturer, String amphitheater, String room, boolean isAlarm, boolean isTestSchedule) {
        this.scheduleTitle = scheduleTitle;
        this.subjectCode = subjectCode;
        this.startTime = time;
        this.endTime = endTime;
        this.shift = shift;
        this.lecturer = lecturer;
        this.amphitheater = amphitheater;
        this.room = room;
        this.isAlarm = isAlarm;
        this.isExpand = false;
        this.isTestSchedule = isTestSchedule;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isTestSchedule() {
        return isTestSchedule;
    }

    public void setTestSchedule(boolean testSchedule) {
        isTestSchedule = testSchedule;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public static List<TestModelSchedule> getListModel(int size) {
        List<TestModelSchedule> list = new ArrayList<>();

        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 09:30:30", "2023-07-13 011:30:30","1", "chann3", "Phan Mem QT", "T301, Tòa T", false, true));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-12 07:30:30", "2023-07-13 09:30:30","1", "chann3", "Phan Mem QT", "T301, Tòa T", false, false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-11 07:30:30", "2023-07-13 09:30:30","1", "chann3", "Phan Mem QT", "T301, Tòa T", false, false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-10 09:30:30","2023-07-13 11:30:30", "1", "chann3", "Phan Mem QT", "T301, Tòa T", false, false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-9 09:30:30", "2023-07-13 11:30:30","1", "chann3", "Phan Mem QT", "T301, Tòa T", false, true));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-8 07:30:30", "2023-07-13 09:30:30","1", "chann3", "Phan Mem QT", "T301, Tòa T", false, false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-7 09:30:30", "2023-07-13 11:30:30","1", "chann3", "Phan Mem QT", "T301, Tòa T", false, true));

        return list.subList(0, size);
    }

    public static List<TestModelSchedule> getListModel() {
        List<TestModelSchedule> list = new ArrayList<>();

        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-13 09:30:30", "2023-07-13 011:30:30","1", "chann3", "Phan Mem Quang Trung", "T301, Tòa T", false, true));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-12 07:30:30", "2023-07-13 09:30:30","1", "chann3", "Phan Mem Quang Trung", "T301, Tòa T", false, false));
        list.add(new TestModelSchedule("Android Networking", "MOB403", "2023-07-11 07:30:30", "2023-07-13 09:30:30","1", "chann3", "Phan Mem Quang Trung", "T301, Tòa T", false, false));

        return list;
    }
}
