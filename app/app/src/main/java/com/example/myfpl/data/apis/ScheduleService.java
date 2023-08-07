package com.example.myfpl.data.apis;

import com.example.myfpl.data.DTO.ScheduleDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ScheduleService {

    @GET("/api/student/testSchedules")
    Single<ScheduleDTO.TestScheduleResponseDTO> getTestSchedules(
            @QueryMap Map<String, String> params
    );

    @GET("/api/student/schedules")
    Single<ScheduleDTO.ScheduleResponseDTO> getSchedules(
            @QueryMap Map<String, String> params
    );

    //
    @POST("/api/student/setAlarmSchedule")
    @FormUrlEncoded
    Single<ScheduleDTO.UpdateIsAlarmResponse> updateIsAlarm(
            @Field("class_group_id") int class_group_id,
            @Field("schedule_id") int schedule_id,
            @Field("reminder_id") String reminder_id,
            @Field("is_alarm") int is_alarm
    );
}
