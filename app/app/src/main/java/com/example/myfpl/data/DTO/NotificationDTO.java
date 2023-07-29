package com.example.myfpl.data.DTO;

import com.example.myfpl.models.NotificationModel;

import java.util.List;

public class NotificationDTO {
    private int status;
    private Notify notify;
    public class Notify {
        private int current_page;
        private List<NotificationModel> data;

        public Notify(int current_page, List<NotificationModel> data) {
            this.current_page = current_page;
            this.data = data;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public List<NotificationModel> getData() {
            return data;
        }

        public void setData(List<NotificationModel> data) {
            this.data = data;
        }
    }

    public NotificationDTO() {
    }

    public NotificationDTO(int status, Notify notify) {
        this.status = status;
        this.notify = notify;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }
}
