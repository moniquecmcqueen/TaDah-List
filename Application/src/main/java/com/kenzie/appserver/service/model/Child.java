package com.kenzie.appserver.service.model;

import java.util.*;

public class Child {
    private UUID childId;
    private String childUsername;
    //if we want to use age to help with verification of child??
    //can delete if we do not want to use- monique
    //I dont think this is necessary- brandon

    private Map<String, Boolean> taskCompletedTask;

//    public Child(){
//        //would this generate a unique childId
//        this.childId = UUID.randomUUID().toString();
//    } do we need this constructor - rebecca

    public Child(String childUsername, UUID childId) {
        //could we do this for the unique Ids? - monique
        this.childId = childId;
        //might need to check to make sure this id does not exist in our child repository if were doing it this way
        this.childUsername = childUsername;

        this.taskCompletedTask = new HashMap<>();
    }

    public void markTaskAsCompleted(Task task) {
        taskCompletedTask.put(task.getTaskId(), true);
    }

    public void markTaskAsIncomplete(Task task) {
        taskCompletedTask.put(task.getTaskId(), false);
    }

    // if we want to get the number of completed task
    // we can delete if we want- monique
    public int getTotalNumberOfCompletedTask() {
        int count = 0;
        for (boolean isCompleted : taskCompletedTask.values()) {
            if (isCompleted) {
                count++;
            }
        }
        return count;
    }

    // gets tasks
    public List<String> getTasks() {
        return new ArrayList<>(taskCompletedTask.keySet());
    }
    // if we want to get the total number of task- monique

    public int getTotalNumberOfTask() {
        return taskCompletedTask.size();
    }


    public UUID getChildId() {
        return childId;
    }

//    public void setChildId(String childId) {
//        this.childId = UUID.fromString(childId);
//    }

    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }


    public Map<String, Boolean> getTaskCompletedTask() {
        return taskCompletedTask;
    }

    public void setTaskCompletedTask(Map<String, Boolean> taskCompletedTask) {
        this.taskCompletedTask = taskCompletedTask;
    }

    public void deleteTask(String taskId) {
        taskCompletedTask.remove(taskId);
    }
}
