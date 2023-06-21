package com.kenzie.appserver.service.model;

import java.util.*;

public class Parent {
    // unique id and username to authenticate the parent
    private String parentId;
    private String username;
    // using Map to interact with the child class for completed task
    private Map<String, Boolean> childTaskCompletedTask;

    // parent class to include children?
    private List<Child> children;
    //doesnt like to store nonprimitive data types inside a list
    // list of children ids (strings)


    //public void addMyChild(Child child){children.add(child);}

    public Parent( String username){
        this.parentId = UUID.randomUUID().toString();
        this.username = username;
        //need an id
    }
    // parent should be able to monitor a child's progress... to track and retrieve info about
    // the child's overall progress and completion of tasks
    // - as a parent I want to be able to retrieve a task that has been completed
    //- retrieve a task that has been incompleted
    //- view task and delete task

    public void updateChildTadahTask(Child child, Task task, boolean isCompleted) {
        childTaskCompletedTask.put(child.getChildId() + " " + task.getTaskId(), isCompleted);
    }
    // check to see if task is completed? keep or delete
    public boolean didChildCompleteTask(Child child, Task task) {
        return childTaskCompletedTask.getOrDefault(child.getChildId() + " " +
                task.getTaskId(), false);
    }
    // get number of child's completed task? keep or delete
    public int getChildsCompletedTask(Child child) {
        int count = 0;
        for(boolean isCompleted : childTaskCompletedTask.values()) {
            if(isCompleted){
                count++;
            }
        }
        return count;

    }
    // deleteTasks
    public void deleteChildTask( String childId,String taskId){
        Child child = getChildById(childId);
        if(child != null){
            child.deleteTask(taskId);
        }
    }
    // view child tasks
    public List<String> viewChildtask(String childId){
        Child child = getChildById(childId);
        if(child != null) {
            return child.getTasks();
        }
        //if child not found return an empty list
        return new ArrayList<>();
    }
    //get completed tasks
    public List<String> getCompletedTask(String childId) {
        Child child = getChildById(childId);
        if(child != null) {
            // how to rewrite or change this
            return (List<String>) child.getTaskCompletedTask();
        }
        //if not found return empty
        return new ArrayList<>();
        }

    private Child getChildById(String childId){
        for (Child child : children){
            if(child.getChildId().equals(childId)){
                return child;
            }
        }
        //if no child is found
        return null;
    }
}
