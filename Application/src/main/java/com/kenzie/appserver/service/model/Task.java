package com.kenzie.appserver.service.model;

public class Task {

    private final String parentId;
    private final String childId;
    private final String taskId;

    private final boolean isCompleted ;

    private final String taskTitle;



    // constructor
    public Task(String parentId, String childId, String taskId, String taskTitle, boolean isCompleted){
        this.parentId = parentId;
        this.childId = childId;
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

    public String getParentId() {
        return parentId;
    }

    public String getChildId() {
        return childId;
    }
}
