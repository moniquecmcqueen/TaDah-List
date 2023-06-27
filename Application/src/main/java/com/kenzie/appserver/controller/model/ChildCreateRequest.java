package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.TaDahTaskList;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class ChildCreateRequest {
    @NotEmpty
    @JsonProperty("getTasks")
    private TaDahTaskList getTasks;

    private Map<String, Boolean> taskCompletedTask;

    public TaDahTaskList getTasks() {
        return getTasks;
    }


    public Map<String, Boolean> getTaskCompletedTask() {
        return taskCompletedTask;
    }

    public void setTaskCompletedTask(Map<String, Boolean> taskCompletedTask) {
        this.taskCompletedTask = taskCompletedTask;
    }

}
