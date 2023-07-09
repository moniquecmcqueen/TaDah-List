package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.ChildUserLoginRequest;
import com.kenzie.appserver.controller.model.ChildUserLoginResponse;
import com.kenzie.appserver.controller.model.ParentCreateLoginRequest;
import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChildService {
    private final ChildRepository childRepository;
    private final TaskRepository taskRepository;

    private final ParentRepository parentRepository;
    private final ParentService parentService;

    @Autowired
    public ChildService(ChildRepository childRepository, TaskRepository taskRepository,ParentRepository parentRepository, ParentService parentService) {
        this.childRepository = childRepository;
        this.taskRepository = taskRepository;
        this.parentService = parentService;
        this.parentRepository = parentRepository;
    }



    public Child findByChildUsername(String childUsername) {
        ChildRecord childRecord = childRepository.findByChildUsername(childUsername);
        if (childRecord != null) {
            return new Child(childRecord.getParentUsername(), childRecord.getChildUsername());
        }
        return null;
    }

    public void addNewChild(ChildUserLoginRequest child) {
        //taking in a request object will not have to repeat when using method in controller
        ChildRecord childRecord = new ChildRecord();
        childRecord.setParentUsername(child.getParentUsername());
        childRecord.setChildUsername(child.getChildUsername());

        childRepository.save(childRecord);
    }


    public void removeChild(String childUsername) {
    //delete stored child from repo by id
        childRepository.deleteById(childUsername);
    }



}



// TaskService interacts with the model classes






