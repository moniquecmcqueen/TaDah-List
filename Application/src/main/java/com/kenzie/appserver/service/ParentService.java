package com.kenzie.appserver.service;


import com.kenzie.appserver.controller.model.ParentCreateLoginRequest;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.service.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParentService {
    private ParentRepository parentRepository;

    @Autowired
    public ParentService(ParentRepository parentRepository) {

        this.parentRepository = parentRepository;

    }

    public Parent findByParentUsername(String parentUsername) {
        ParentRecord record = parentRepository.findByParentUsername(parentUsername);
        if (record != null) {
            return new Parent(record.getParentUsername(), record.getChildUsername());
        }
        return null;
    }

    public void addNewParent(ParentCreateLoginRequest parent) {
        //taking in a request object will not have to repeat when using method in controller
        ParentRecord parentRecord = new ParentRecord();
        parentRecord.setParentUsername(parent.getParentUsername());
        parentRecord.setChildUsername(parent.getChildUsername());

        parentRepository.save(parentRecord);
    }


    public void deleteParent(String parentUsername) {
        parentRepository.deleteById(parentUsername);

    }


    //    public Parent findByParentUsername(String parentUsername)  {
//        //verify that parent user exists with given username
//        //is parentusername is not null return matching username from repo else throw exception
//        Parent parent = parentRepository.findByParentUsername(parentUsername);
//        if (parent != null) {
//
//        }
//        return parent;
//    }
//    public Parent parentLogin( String parentUsername) {
//        Parent parent = parentRepository.findByParentUsername(String.valueOf(cache.get(parentUsername)));
//        if (parent != null) {
//            return findByParentUsername(parentUsername);
//
//        }
//        return null;
//    }
}


