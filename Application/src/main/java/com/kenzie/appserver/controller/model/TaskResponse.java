package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;


import javax.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("taskTitle")
    private String taskTitle;
    @JsonProperty("parentUsername")
    private String parentUsername;
    @JsonProperty("childUsername")
    private String childUsername;




    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;

    }
    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;}

    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }
    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }
}
