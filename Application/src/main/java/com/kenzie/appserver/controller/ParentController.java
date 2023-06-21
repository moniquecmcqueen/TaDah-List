package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParentController {

    private Map<String, Boolean> childTaskCompletedTask;

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
