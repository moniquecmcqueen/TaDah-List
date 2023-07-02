package com.kenzie.appserver.service.model;

import java.util.*;

public class Child {
    private String childId;
    private String childUsername;
    //if we want to use age to help with verification of child??
    //can delete if we do not want to use- monique
    //I dont think this is necessary- brandon

    private String taskId;
    private List<Task> childTaskList;
//    public Child(){
//        //would this generate a unique childId
//        this.childId = UUID.randomUUID().toString();
//    } do we need this constructor - rebecca

    public Child(String childUsername, String childId) {
        //could we do this for the unique Ids? - monique
        this.childId = childId;
        //might need to check to make sure this id does not exist in our child repository if were doing it this way
        this.childUsername = childUsername;

    }
    public Child(String childUsername, String childId, String taskId) {
        this.taskId = taskId;
        this.childUsername = childUsername;
        this.childId = childId;
    }

    public Child(String childUsername, String childId, List<Task> childTaskList){
        this.childId = childId;
        this.childUsername = childUsername;
        this.childTaskList = childTaskList;
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

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }

    public String getTaskId() {return taskId; }

    public void setTaskId (String taskId){
        this.taskId = taskId;
    }

    public List<Task> getChildTaskList() {
        return childTaskList;
    }

    public void setChildTaskList(List<Task> childTaskList) {
        this.childTaskList = childTaskList;
    }
}




