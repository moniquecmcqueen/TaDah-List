package com.kenzie.appserver.service.model;

public class Task {
    private String taskId;
    private String taskDescription;
    private String taskTitle;
    private boolean completed;

    // constructor
    public Task( String taskId, String taskDescription, String taskTitle){
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.taskTitle = taskTitle;
        // tasks are not completed by default because they are being made
        this.completed = false;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

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
                ", taskDescription='" + taskDescription + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", completed=" + completed +
                '}';
    }
}
