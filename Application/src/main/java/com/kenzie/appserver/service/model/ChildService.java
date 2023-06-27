package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChildService {
    private ChildRepository childRepository;
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child findById(String childId) {
        Child childFromBackend = childRepository
                .findById(childId)
                .map(child-> new Child(UUID.fromString(childId.toString())))
                        //once records added, need to add code for get username)
                .orElse(null);

        return childFromBackend;
    }
    public List<Task> findAll() {//need to update this method once you get all the data from the Task Service class when meeting with Elise
        List<Task> taskList= new ArrayList<>();
       childRepository
                .findAll()
                .forEach(task -> taskList.add(new Task(task.getTaskId(), task.getTaskTitle(), task.getIsCompleted())));
        return taskList;
    }

    public Task markTaskCompleted(Task task) { //need to update this record when it gets addded
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(task.getTaskId());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRecord.setTaskDescription(taskRecord.getTaskDescription());
        taskRepository.save(taskRecord);
        return task;
    }
}

// TaskService interacts with the model classes





}
