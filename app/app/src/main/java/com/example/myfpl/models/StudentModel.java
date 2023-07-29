package com.example.myfpl.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class StudentModel implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("phone")
    String phone;
    @SerializedName("dob")
    String dob;
    @SerializedName("specialize")
    String specialize;

    public StudentModel(String id, String name, String email, String avatar, String phone, String dob, String specialize) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.phone = phone;
        this.dob = dob;
        this.specialize = specialize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student: " + name + " Email: " + email + " Phone: " + phone + " Dob: " + dob + " Specialize: " + specialize;
    }
}
