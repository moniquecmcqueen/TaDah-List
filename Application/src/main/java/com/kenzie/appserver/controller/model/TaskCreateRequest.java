package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class TaskCreateRequest {

    @NotEmpty
    @JsonProperty("taskId")
    private String taskId;
    @NotEmpty
    @JsonProperty("taskTitle")
    private String taskTitle;

    @NotEmpty
    @JsonProperty("childUsername")
    private String childUsername;

    @NotEmpty
    @JsonProperty("parentUsername")
    private String parentUsername;

    @JsonProperty("isCompleted")
    private Boolean isCompleted;



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

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }
}

