package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.service.model.Task;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

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
    public List<Task> findAll() {
        List<Task> taskList= new ArrayList<>();
        taskRepository
                .findAll()
                .forEach(task -> taskList.add(new Task(task.getTaskId(), task.getTaskTitle(), task.isCompleted())));
        return taskList;
    }

    public Task addNewTask(Task task) {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(task.getTaskId());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRecord.setTaskDescription(taskRecord.getTaskDescription());
        taskRepository.save(taskRecord);
        return task;
    }
}

    // TaskService interacts with the model classes




