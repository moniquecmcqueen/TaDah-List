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

    public Child findById(UUID childId) {
        String childIdString = childId.toString(); // Convert UUID to string

        Child childFromBackend = childRepository
                .findById(childIdString)
                .map(child -> new Child(child.getChildUsername(), child.getChildId(), getChildTaskList(child.getChildId())))
                .orElse(null);

        return childFromBackend;
    }

    public List<Task> findAll() {//need to update this method once you get all the data from the Task Service class when meeting with Elise
        List<Task> taDahTaskList = new ArrayList<>();
        taskRepository
                .findAll()
                .forEach(task -> taDahTaskList.add(new Task(task.getTaskId(), task.getTaskTitle(), task.getIsCompleted())));
        return taDahTaskList;
    }

    public Task markTaskCompleted(Task task) { //need to update this record when it gets addded
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(task.getTaskId());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRepository.save(taskRecord);
        return task;
    }

    public boolean checkChildUsername(String username) {
        ChildRecord childRecord = childRepository.findByChildUsername(username);
        return childRecord != null;
    }

    public Child getChildByUsername(String username) {
        ChildRecord childRecord = childRepository.findByChildUsername(username);
        if (childRecord != null) {
            String childId = childRecord.getChildId();
            String childName = childRecord.getChildUsername();
            List<Task> childTaskList = getChildTaskList(childId);
            return new Child(childId, childName, childTaskList);
        }
        return null;
    }

    private List<Task> getChildTaskList(String childId) {
        List<Task> childTaskList = new ArrayList<>();
        taskRepository.findAll().forEach(task -> {
            if (task.getChildId().equals(childId)) {
                childTaskList.add(new Task(
                        task.getTaskId(),
                        task.getTaskTitle(),
                        task.getIsCompleted()
                ));
            }
        });
        return childTaskList;
    }
}



// TaskService interacts with the model classes






