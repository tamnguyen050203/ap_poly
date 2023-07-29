package com.example.myfpl.data.DTO;

import com.example.myfpl.models.StudentModel;

public class StudentDTO {

    public static class StudentResponseDTO {
        private String status;
        private StudentModel student;

        public StudentResponseDTO(String status, StudentModel student) {
            this.status = status;
            this.student = student;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public StudentModel getStudent() {
            return student;
        }

        public void setStudent(StudentModel student) {
            this.student = student;
        }
    }

}
