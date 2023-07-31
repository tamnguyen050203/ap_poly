package com.example.myfpl.data.apis;

import com.example.myfpl.data.DTO.StudentDTO;
import com.example.myfpl.models.StudentModel;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentService {

    @GET("api/student/info")
    Single<StudentDTO.StudentResponseDTO> getStudentInfo();
}
