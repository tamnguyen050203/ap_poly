package com.example.myfpl.models;

import java.io.Serializable;

public class TestScheduleModel extends BaseSchedule implements Serializable {

    private String is_attend;

    public TestScheduleModel(int id, String lesson_name, String lesson_code_name, String room_name, String date, String shift_name, String start_time, String end_time, String amphitheater_name, String is_attend, String lecturer_name, int is_alarm, String reminder_id) {
        super(id, lesson_name, lesson_code_name, room_name, date, shift_name, start_time, end_time, lecturer_name, amphitheater_name, is_alarm, reminder_id);
        this.is_attend = is_attend;
    }

    public String getIs_attend() {
        return is_attend;
    }

    public void setIs_attend(String is_attend) {
        this.is_attend = is_attend;
    }
}
