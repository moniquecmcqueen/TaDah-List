package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskController {

    private List<Task> tasks;

    public TaskController() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getAllCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    public List<Task> getAllIncompletedTasks() {
        List<Task> incompletedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                incompletedTasks.add(task);
            }
        }
        return incompletedTasks;
    }

    public void markTaskAsCompleted(Task task) {
        task.setCompleted(true);
    }

    public void markTaskAsIncompleted(Task task) {
        task.setCompleted(false);
    }
}
