package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ChildRetrieveTaskResponse {


    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("childUsername")
    private String childUsername;

    @JsonProperty("parentUsername")
    private String parentUsername;
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("taskTitle")
    private String taskTitle;




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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
}
