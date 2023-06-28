package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.service.model.TaDahTaskList;
import com.kenzie.appserver.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(String id) {
        Task taskFromBackend = taskRepository
                .findById(id)
                .map(task -> new Task(task.getTaskId(), task.getTaskTitle(), task.getIsCompleted()))
                .orElse(null);

        return taskFromBackend;
    }

    public TaDahTaskList getAllTasks() {
        TaDahTaskList taDahTaskList = new TaDahTaskList();
        taskRepository
                .findAll()
                .forEach(task -> taDahTaskList.add(new Task(task.getTaskId(), task.getTaskTitle(), task.getIsCompleted())));
        return taskList;
    }

    public Task addNewTask(Task task) {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(task.getTaskId());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRepository.save(taskRecord);
        return task;
    }

    public void updateTask(Task task) {
        if (taskRepository.existsById(task.getTaskId())) {
            TaskRecord taskRecord = new TaskRecord();
            taskRecord.setTaskId(task.getTaskId());
            taskRecord.setTaskTitle(task.getTaskTitle());
            taskRepository.save(taskRecord);

        }
    }
}

    // TaskService interacts with the model classes




