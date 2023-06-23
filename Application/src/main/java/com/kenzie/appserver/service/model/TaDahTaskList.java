package com.kenzie.appserver.service.model;



import java.util.List;
import java.util.ArrayList;

public class TaDahTaskList {
private List<Task> tasks;

public TaDahTaskList() {
    tasks = new ArrayList<>();
}
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
