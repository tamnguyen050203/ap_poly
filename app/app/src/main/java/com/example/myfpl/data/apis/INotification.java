package com.example.myfpl.data.apis;

import com.example.myfpl.data.DTO.NotificationDTO;
import com.example.myfpl.models.NotificationModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface INotification {
    @GET("/api/student/notifies/0")
    Single<NotificationDTO> getNotificationData();

}
