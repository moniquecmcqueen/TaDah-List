package com.kenzie.appserver.service.model;

import org.springframework.data.annotation.Id;

public class Task {

    private final String parentUsername;
    private final String childUsername;
    @Id
    private final String taskId;

    private final boolean isCompleted ;

    private final String taskTitle;



    // constructor
    public Task(String parentUsername, String childUsername, String taskId, String taskTitle, boolean isCompleted){
        this.parentUsername = parentUsername;
        this.childUsername = childUsername;
        this.taskId = taskId;
        //this.taskDescription = taskDescription;
        this.taskTitle = taskTitle;
        // tasks are not completed by default because they are being made-monique
        // it should be set to false here, correct? instead of this.completed = completed
        // this.completed = false,right?? or no?
        this.isCompleted = isCompleted;
        //updated constructor to take in changed to boolean default - rebecca

    }



    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskId() {
        return taskId;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public String getChildUsername() {

        return childUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }
}
