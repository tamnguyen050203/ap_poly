package com.example.myfpl.data.apis;

import com.example.myfpl.models.NotificationModel;
import com.example.myfpl.models.Test;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface INotification {
    @GET("/api/student/notifies/0")
    Observable<List<NotificationModel>> getNotificationData();

}
