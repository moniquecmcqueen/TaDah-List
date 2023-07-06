package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


    public class TaskUpdateRequest {

        @NotEmpty
        @JsonProperty("parentUsername")
        private String parentUsername;

        @NotEmpty
        @JsonProperty("childUsername")
        private String childUsername;

        @JsonProperty("isCompleted")
        private Boolean isCompleted;

        @NotEmpty
        @JsonProperty("taskTitle")
        private String taskTitle;

        public String getTaskTitle() {
            return taskTitle;
        }

        public void setTaskTitle(String taskTitle) {
            this.taskTitle = taskTitle;
        }

        public String getChildUsername() {
            return childUsername;
        }

        public void setChildUsername(String childUsername) {
            this.childUsername = childUsername;
        }

        public Boolean getIsCompleted() {
            return isCompleted;
        }

        public void setIsCompleted(Boolean completed) {
            isCompleted = completed;
        }

        public String getParentUsername() {
            return parentUsername;
        }

        public void setParentUsername(String parentUsername) {
            this.parentUsername = parentUsername;
        }
    }
