package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class TaskCreateRequest {

    @NotEmpty
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("taskTitle")
    private String taskTitle;



    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskId(String createTaskId) {
    }
}
