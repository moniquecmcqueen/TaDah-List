package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.repositories.ExampleRepository;
import com.kenzie.appserver.repositories.model.TaskRepository;
import com.kenzie.appserver.service.model.Example;

import com.kenzie.appserver.service.model.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(String id) {
        Task taskFromBackend = taskRepository
                .findById(id)
                .map(example -> new Task(task., example.getName()))
                .orElse(null);

        return exampleFromBackend;
    }

    public Example addNewExample(Example example) {
        ExampleRecord exampleRecord = new ExampleRecord();
        exampleRecord.setId(example.getId());
        exampleRecord.setName(example.getName());
        exampleRepository.save(exampleRecord);
        return example;
    }
}
