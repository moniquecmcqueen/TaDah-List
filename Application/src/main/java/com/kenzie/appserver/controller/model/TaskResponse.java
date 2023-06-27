package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.TaDahTaskList;
import com.kenzie.appserver.service.model.Task;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("isCompleted")
    private boolean isCompleted;
    @JsonProperty("taskTitle")
    private String taskTitle;

    private TaDahTaskList taDahTaskList;
    //List<Task> taskList = new ArrayList<>();

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }


    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;

    }
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;}
}
