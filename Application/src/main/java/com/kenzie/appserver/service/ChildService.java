package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildService {
    private ChildRepository childRepository;
    private TaskRepository taskRepository;
    @Autowired
    public ChildService(ChildRepository childRepository, TaskRepository taskRepository) {
        this.childRepository = childRepository;
        this.taskRepository = taskRepository;

    }

    public Child findById(String childId) {//does this not work because DynamoDB does not agree with UUIDs?
        Child childFromBackend = childRepository
                .findById(childId)
                .map(child-> new Child(child.getChildUsername(), child.getChildId(), child.getTaskId()))
                        //once records added, need to add code for get username)
                .orElse(null);

        return childFromBackend;
    }
    public List<Child> findAllTasks() {//need to update this method once you get all the data from the Task Service class when meeting with Elise
       List<Child> taDahTaskList= new ArrayList<>();
       childRepository
                .findAll()
                .forEach(child -> taDahTaskList.add(new Child(child.getChildUsername(),
                                child.getChildId(),
                                child.getTaskId())));


        return taDahTaskList;
    }

    public Task markTaskCompleted(Task task) { //need to update this record when it gets addded
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(task.getTaskId());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRepository.save(taskRecord);
        return task;
    }

    public Child findByUsername(String childUsername) {
        ChildRecord childRecord = childRepository.findByChildUsername(childUsername);

        return new Child(childRecord.getChildUsername(),childRecord.getChildId(),childRecord.getListOfChildTasks());
    }
}

// TaskService interacts with the model classes






