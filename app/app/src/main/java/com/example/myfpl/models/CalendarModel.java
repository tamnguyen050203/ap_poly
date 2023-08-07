package com.example.myfpl.models;

import androidx.annotation.NonNull;

public class CalendarModel {
    private long id;
    private String displayName;
    private String accountName;

    public CalendarModel(long id, String displayName, String accountName) {
        this.id = id;
        this.displayName = displayName;
        this.accountName = accountName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "";
    }
}
