package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.TaDahTaskList;
import com.kenzie.appserver.service.model.Task;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ChildService {
    private TaskRepository taskRepository;
    private ChildRepository childRepository;
    @Autowired
    public ChildService(ChildRepository childRepository, TaskRepository taskRepository) {
        this.childRepository = childRepository;
        this.taskRepository = taskRepository;
    }

    public Child findById(String childId) {//does this not work because DynamoDB does not agree with UUIDs?
        Child childFromBackend = childRepository
                .findById(childId)
                .map(child-> new Child(child.getChildUsername(), child.getChildId()))
                        //once records added, need to add code for get username)
                .orElse(null);

        return childFromBackend;
    }
    public List<Child> findAllByChildId() {//need to update this method once you get all the data from the Task Service class when meeting with Elise
       List<Child> taDahTaskList= new ArrayList<>();
       childRepository
                .findAll()
                .forEach(child -> taDahTaskList.add(new Child(child.getChildUsername(), child.getChildId(), child.getTaskId())));
        return taDahTaskList;
    }

    public Task markTaskCompleted(Task task) { //need to update this record when it gets addded
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(task.getTaskId());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRepository.save(taskRecord);
        return task;
    }
}

// TaskService interacts with the model classes






