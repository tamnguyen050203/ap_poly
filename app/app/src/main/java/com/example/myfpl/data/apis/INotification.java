package com.example.myfpl.data.apis;

import com.example.myfpl.data.DTO.NotificationDTO;
import com.example.myfpl.models.NotificationModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface INotification {
    @GET("/api/student/notifies/0")
    Single<NotificationDTO> getNotificationData();

    @POST("/api/student/readNotify/{notifyId}")
    Single<NotificationDTO.ReadNotificationResponseDTO> readNotification(
           @Path ("notifyId") String notifyId
    );

    @POST("/api/student/readAllNotify")
    Single<NotificationDTO.ReadNotificationResponseDTO> readAllNotification();
}
