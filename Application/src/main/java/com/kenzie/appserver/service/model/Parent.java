package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.model.ChildRecord;
import org.springframework.data.annotation.Id;

import java.util.*;

public class Parent {

@Id
    private String parentUsername;


    private List<Child> children;


    public Parent ( String parentUsername, List<Child> children){

        this.parentUsername = parentUsername;
        this.children = children;
    }


    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }


    public List<Child> getChildren() {
        return children;
    }
}
