package com.example.myfpl.util;

import com.example.myfpl.models.BaseSchedule;
import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.TestScheduleModel;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

import java.util.Arrays;

public class ScheduleUtils {
    public static <T extends BaseSchedule> Event createEvent(T schedule) {
        Event event = new Event()
                .setSummary(schedule.getLesson_name())
                .setLocation(schedule.getAmphitheater_name() + "-" + schedule.getShift_name() + "-" + schedule.getRoom_name());

        if (schedule instanceof ScheduleModel) {
            event.setDescription(((ScheduleModel) schedule).getDetail());
        } else if (schedule instanceof TestScheduleModel) {
            event.setDescription(schedule.getLesson_code_name() + "-" + schedule.getAmphitheater_name() + "-" + schedule.getShift_name() + "-" + schedule.getRoom_name());
        }

        DateTime startDateTime = new DateTime(DateUtil.fromStringToDate("yyyy-MM-dd HH:mm:ss", schedule.getDate().concat(" ").concat(schedule.getStart_time())));
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Asia/Ho_Chi_Minh");
        event.setStart(start);

        DateTime endDateTime = new DateTime(DateUtil.fromStringToDate("yyyy-MM-dd HH:mm:ss", schedule.getDate().concat(" ").concat(schedule.getEnd_time())));
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Asia/Ho_Chi_Minh");
        event.setEnd(end);

        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(20),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        return event;
    }
}
