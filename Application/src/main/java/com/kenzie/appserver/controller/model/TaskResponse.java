package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("isCompleted")
    private boolean isCompleted;
    @JsonProperty("taskTitle")
    private String taskTitle;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

 brandonsbranch
    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
  main
    }

    public String getTaskTitle() {
        return taskTitle;
    }
}
