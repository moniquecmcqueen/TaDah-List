package com.kenzie.appserver.service.model;

import java.util.HashMap;
import java.util.Map;

public class Parent {
    // unique id and username to authenticate the parent
    private String parentId;
    private String username;
    // using Map to interact with the child class for completed task
    private Map<String, Boolean> childTaskCompletedTask;

    public Parent(String parentId, String username){
        this.parentId = parentId;
        this.username = username;
        this.childTaskCompletedTask = new HashMap<>();
    }
    // parent should be able to monitor a child's progress... to track and retrieve info about
    // the child's overall progress and completion of tasks
    public void updateChildTadahTask(Child child, Task task, boolean isCompleted) {
        childTaskCompletedTask.put(child.getChildId() + " " + task.getTaskId(), isCompleted);
    }
    // check to see if task is completed
    public boolean didChildCompleteTask(Child child, Task task) {
        return childTaskCompletedTask.getOrDefault(child.getChildId() + " " +
                task.getTaskId(), false);
    }
    // get number of child's completed task
    public int getChildsCompletedTask(Child child) {
        int count = 0;
        for(boolean isCompleted : childTaskCompletedTask.values()) {
            if(isCompleted){
                count++;
            }
        }
        return count;
    }
}
