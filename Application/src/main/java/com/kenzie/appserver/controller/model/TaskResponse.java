package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("taskTitle")
    private String taskTitle;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getTaskTitle() {
        return taskTitle;
    }
}
