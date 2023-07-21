package com.example.myfpl.models;

import java.io.Serializable;

public class NotificationModel implements Serializable {
    private String id;
    private String title;
    private String author;
    private String type;
    private String content;
    private String created_date;
    private String updated_date;
    public NotificationModel() {
    }

    public NotificationModel(String id, String title, String author, String type, String content, String created_date, String updated_date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.content = content;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }
}
