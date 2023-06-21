package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskController {

    private List<Task> tasks;

    public void addTask(Task task){
        tasks.add(task);
    }
    public void removeTask(Task task) {
        tasks.remove(task);
    }
    public List<Task> getAllTasks(){
        return tasks;
    }
    public List<Task> getAllCompletedTasks() {
        List<Task> theCompletedTasks = new ArrayList<>();
        for(Task task : tasks) {
            if(task.isCompleted()) {
                theCompletedTasks.add(task);
            }
        }
        return theCompletedTasks;
    }
    public List<Task> getAllIncompletedTasks() {
        List<Task> theIncompletedTasks = new ArrayList<>();
        for(Task task : tasks) {
            if(!task.isCompleted()) {
                theIncompletedTasks.add(task);
            }
        }
        return theIncompletedTasks;
    }
    public void markTaskAsCompleted(Task task){
        task.setCompleted(true);
    }
    public void markTaskAsIncompleted(Task task) {
        task.setCompleted(false);
    }
}

}
