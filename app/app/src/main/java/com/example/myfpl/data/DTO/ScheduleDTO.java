package com.example.myfpl.data.DTO;

import com.example.myfpl.models.ScheduleModel;

import java.util.List;

public class ScheduleDTO {
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
}
