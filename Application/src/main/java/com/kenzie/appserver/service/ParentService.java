package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ParentService {
    private ParentRepository parentRepository;
    private ChildRepository childRepository;
    private TaskRepository taskRepository;

    @Autowired
    public ParentService(TaskRepository taskRepository, ChildRepository childRepository, ParentRepository parentRepository) {
        this.taskRepository = taskRepository;
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
    }

    public Parent findById(String parentId) {
        Parent parentFromBackend = parentRepository
                .findById(parentId)
                .map(parent -> new Parent(parent.getParentId(), parent.getParentUsername(), parent.getChildren()))
                .orElse(null);

        return parentFromBackend;
    }


    public Task addNewTask(Task task) {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId((task.getTaskId()));
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRepository.save(taskRecord);
        return task;
    }

    public void deleteParent(String parentId) {
        taskRepository.deleteById(parentId);

    }

    public Child addChild(Child child) {
        ChildRecord childRecord = new ChildRecord();
        childRecord.setChildId(child.getChildId());
        childRecord.setChildUsername(child.getChildUsername());
        childRepository.save(childRecord);
        return child;

    }

    public void removeChild(String childId) {
        childRepository.deleteById(childId);
    }
//    public Task addNewTaskToChild(Task task) {
//        TaskRecord taskRecord = new TaskRecord();
//        taskRecord.setTaskId(String.valueOf(task.getTaskId()));
//        taskRecord.setTaskTitle(task.getTaskTitle());
//        taskRepository.save(taskRecord);
//        return task;
//    }
}


