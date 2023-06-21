package com.kenzie.appserver.service.model;

public class Task {
    private String taskId;

   // private String taskDescription; is a description section apart of the program design - rebecca
    private String taskTitle;
    private Boolean completed = Boolean.FALSE;
    //set boolean to equal false by default - rebecca

    // constructor
    public Task( String taskId, String taskTitle, Boolean completed){
        this.taskId = taskId;
        //this.taskDescription = taskDescription;
        this.taskTitle = taskTitle;
        // tasks are not completed by default because they are being made-monique
        this.completed = completed;
        //updated constructor to take in changed to boolean default - rebecca
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

//    public String getTaskDescription() {
//        return taskDescription;
//    } dont know if needed- rebecca

//    public void setTaskDescription(String taskDescription) {
//        this.taskDescription = taskDescription;
//    } dont know if needed- rebecca

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    @Override
    public String toString() {
        return "TaDahTask{" +
                "taskId='" + taskId + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", completed=" + completed +
                '}';

        //", taskDescription='" + taskDescription + '\'' ",
    }
}
