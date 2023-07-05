package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.controller.model.ParentResponse;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.service.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParentService {
    private ParentRepository parentRepository;

    private CacheStore cache;

    @Autowired
    public ParentService(ParentRepository parentRepository, CacheStore cache) {

        this.parentRepository = parentRepository;
        this.cache = cache;
    }

    public Parent findByParentId(String parentId) {
        Parent cacheParent = cache.get(parentId);
        // Check if parent is cached and return it if true

        if (cacheParent != null) {
            return cacheParent;
        }
        Optional<ParentRecord> parentFromBackendService = parentRepository.findById(parentId);
        Parent cachedParent = cache.get(parentId);
        // Check if parent is cached and return it if true
        if (cachedParent != null) {
            return cachedParent;
        }
        // if not cached, find the parent
        Parent parentFromBackendService1 = parentRepository
                .findById(parentId)
                .map(parent -> new Parent(parent.getParentId(),
                        parent.getParentUsername(),
                        parent.getChildren()))
                .orElse(null);

        // if parent found, cache it
        if (parentFromBackendService1 != null) {
            cache.add(parentFromBackendService1.getParentId(), parentFromBackendService1);
        }
        // return parent
        return parentFromBackendService1;
    }

    public Parent addNewParent(Parent parent) {
        ParentRecord parentRecord = new ParentRecord();
        parentRecord.setParentId(parent.getParentId());
        parentRecord.setChildren(parent.getChildren());
        ;
        parentRecord.setParentUsername(parent.getParentUsername());
        parentRepository.save(parentRecord);
        return parent;
    }


    public void deleteParent(String parentId) {
        parentRepository.deleteById(parentId);

    }


    public Parent findByParentUsername(String parentUsername)  {
        //verify that parent user exists with given username
        //is parentusername is not null return matching username from repo else throw exception
        Parent parent = parentRepository.findByParentUsername(parentUsername);
        if (parent != null) {

        }
        return parent;
    }




}
