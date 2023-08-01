package com.example.myfpl.data.apis;

import com.example.myfpl.data.DTO.ScheduleDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ScheduleService {

    @GET("/api/student/testSchedules")
    Single<ScheduleDTO.ScheduleResponseDTO> getTestSchedules();

    @GET("/api/student/schedules")
    Single<ScheduleDTO.ScheduleResponseDTO> getSchedules();
}
