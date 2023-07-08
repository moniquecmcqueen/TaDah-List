package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.model.ChildRecord;
import org.springframework.data.annotation.Id;

import java.util.*;

public class Parent {

@Id
    private String parentUsername;


    private String childUsername;


    public Parent ( String parentUsername, String childUsername){

        this.parentUsername = parentUsername;
        this.childUsername = childUsername;
    }


    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }


    public String getChildUsername() {
        return childUsername;
    }
}
