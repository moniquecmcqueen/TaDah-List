package com.kenzie.appserver.service.model;

import java.util.HashMap;
import java.util.Map;

public class Child {
    private String childId;
    private String username;
    //if we want to use age to help with verification of child??
    //can delete if we do not want to use
    private int age;
    // using Map to be able to retrieve task by Id and mark complete or incomplete
    private Map<String, Boolean> taskCompletedTask;

    public Child(String childId, String username, int age) {
        this.childId = childId;
        this.username = username;
        this.age = age;
        this.taskCompletedTask = new HashMap<>();
    }
    public void markTaskAsCompleted(Task task){
        taskCompletedTask.put(task.getTaskId(),true);
}
    public void markTaskAsIncomplete(Task task) {
        taskCompletedTask.put(task.getTaskId(),false);
}
   // if we want to get the number of completed task
    // we can delete if we want
    public int getTotalNumberOfCompletedTask() {
        int count = 0;
        for(boolean isCompleted : taskCompletedTask.values()) {
            if (isCompleted) {
                count++;
            }
        }
        return count;
    }
    // if we want to get the total number of task

    public int getTotalNumberOfTask() {
        return taskCompletedTask.size();
    }


    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Boolean> getTaskCompletedTask() {
        return taskCompletedTask;
    }

    public void setTaskCompletedTask(Map<String, Boolean> taskCompletedTask) {
        this.taskCompletedTask = taskCompletedTask;
    }
}
