package com.example.myfpl.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Test")
public class Test implements Serializable {
    private String title;
    private String description;
    @PrimaryKey
    @NonNull
    private String id;

    public Test(String title, String description, @NonNull String id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
