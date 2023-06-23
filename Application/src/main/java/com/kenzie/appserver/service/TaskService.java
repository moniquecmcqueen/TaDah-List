package com.kenzie.appserver.service;


import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.TaDahTaskList;

import com.kenzie.appserver.service.model.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    // maybe?
    private Parent parent;
    // maybe?
    private Task task;

    public TaskService(TaskRepository taskRepository, Parent parent, Task task) {
        this.taskRepository = taskRepository;
        this.parent = parent;
        this.task = task;
    }
}
    // TaskService interacts with the model classes



