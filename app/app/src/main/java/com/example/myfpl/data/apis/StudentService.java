package com.example.myfpl.data.apis;

import com.example.myfpl.models.StudentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentService {

    @GET("api/student/info")
    Call<List<StudentModel>> getStudentInfo();
}
