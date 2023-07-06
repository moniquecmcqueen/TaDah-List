package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;


import javax.validation.constraints.NotEmpty;

import java.util.List;

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




    @JsonProperty("children")
    private List<Child> children;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;

    }
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;}



    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
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

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }




}
