package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;




public class ChildCreateRequest {
    //retrieve task
//    @JsonProperty("getTasks")
//    private TaDahTaskList getTasks;
//    old code requesting empty list not tied to any task Ids

    @JsonProperty("taskId")
    private String taskId;

//    private Map<String, Boolean> taskCompletedTask;
//old code replaced and moved to new request class ,no need for map
// when we have ids and just need a true or false


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
