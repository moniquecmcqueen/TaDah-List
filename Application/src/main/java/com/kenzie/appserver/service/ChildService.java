package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
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



    public Child findByChildUsername(String childUsername)  {
        if (childUsername != null && parentRepository.existsById(childUsername)) {
            ParentRecord record =  parentRepository.findById(childUsername).get();
            return new Child(record.getChildUsername(),record.getParentUsername());

        }
        return null;

    }

    public ChildRecord addChild(Child child) throws Exception {
        //retrieve a parent
        Parent parent = parentService.findByParentUsername(child.getParentUsername());
        //add new child
        ChildRecord newChild = new ChildRecord();
        newChild.setParentUsername(parent.getParentUsername());
        newChild.setChildUsername(child.getChildUsername());
        //save
        childRepository.save(newChild);

        //return
        return newChild;

    }


    public void removeChild(String childUsername) {
    //delete stored child from repo by id
        childRepository.deleteById(childUsername);
    }



}



// TaskService interacts with the model classes






