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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChildService {
    private final ChildRepository childRepository;
    private final TaskRepository taskRepository;

    private final ParentService parentService;
    @Autowired
    public ChildService(ChildRepository childRepository, TaskRepository taskRepository, ParentService parentService) {
        this.childRepository = childRepository;
        this.taskRepository = taskRepository;
        this.parentService = parentService;
    }

    public Optional<ChildRecord> findByChildId(String childId) throws Exception {//does this not work because DynamoDB does not agree with UUIDs?
        if (childId != null) {
            return childRepository.findById(childId);
        } else {
            throw new Exception();
        }

    }


    public boolean findByChildUsername(String childUsername) throws Exception {
        //is childusername is not null return matching username from repo else throw exception
        if (childUsername != null) {
            return childRepository.existsById(childUsername);
        } else {
            throw new Exception();
        }

    }

    public ChildRecord addChild(Child child) throws Exception {
        //retrieve a parent
        Parent parent = parentService.findByParentId(child.getParentId());
        //add new child
        ChildRecord newChild = new ChildRecord();
        newChild.setChildId(UUID.randomUUID().toString());
        newChild.setParentId(parent.getParentId());
        newChild.setChildUsername(child.getChildUsername());
        //save
        childRepository.save(newChild);

        //return
        return newChild;

    }


    public void removeChild(String childId) {
    //delete stored child from repo by id
        childRepository.deleteById(childId);
    }



}



// TaskService interacts with the model classes






