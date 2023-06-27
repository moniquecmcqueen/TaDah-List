package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.TaDahTaskList;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class ChildResponse {
    @NotEmpty
    @JsonProperty("childId")
    private String childId;
    @JsonProperty("childUserName")
    private String childUsername;
    @JsonProperty("getTasks")
    private TaDahTaskList getTasks;
    @JsonProperty("isCompleted")
    private Map<String, Boolean> taskCompletedTask;

    public TaDahTaskList getTasks() {
        return getTasks;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildUsername() {
        return childUsername;
    }

    public void setUsername(String childUsername) {
        this.childUsername = childUsername;
    }

    public Map<String, Boolean> getTaskCompletedTask() {
        return taskCompletedTask;
    }

    public void setTaskCompletedTask(Map<String, Boolean> taskCompletedTask) {
        this.taskCompletedTask = taskCompletedTask;
    }
}
