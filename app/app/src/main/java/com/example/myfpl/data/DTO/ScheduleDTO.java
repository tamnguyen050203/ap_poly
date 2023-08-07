package com.example.myfpl.data.DTO;

import com.example.myfpl.models.ScheduleModel;
import com.example.myfpl.models.TestScheduleModel;

import java.util.List;

public class ScheduleDTO {
    public static class TestScheduleResponseDTO {
        private final String status;
        private final Schedules schedules;

        public static class Schedules {
            private final int current_page;
            private final List<TestScheduleModel> data;

            public Schedules(int current_page, List<TestScheduleModel> data) {
                this.current_page = current_page;
                this.data = data;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public List<TestScheduleModel> getData() {
                return data;
            }
        }

        public TestScheduleResponseDTO(String status, Schedules schedules) {
            this.status = status;
            this.schedules = schedules;
        }

        public String getStatus() {
            return status;
        }

        public Schedules getSchedules() {
            return schedules;
        }
    }

    public static class ScheduleResponseDTO {
        private final String status;
        private final Schedules schedules;

        public static class Schedules {
            private final int current_page;
            private final List<ScheduleModel> data;

            public Schedules(int current_page, List<ScheduleModel> data) {
                this.current_page = current_page;
                this.data = data;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public List<ScheduleModel> getData() {
                return data;
            }
        }

        public ScheduleResponseDTO(String status, Schedules schedules) {
            this.status = status;
            this.schedules = schedules;
        }

        public String getStatus() {
            return status;
        }

        public Schedules getSchedules() {
            return schedules;
        }
    }

    public static class UpdateIsAlarmResponse{
        private String message;
        private int status;

        public UpdateIsAlarmResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
