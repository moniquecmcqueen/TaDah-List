package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.model.ChildRecord;

import java.util.*;

public class Parent {
    private final String parentId;
    //updated to final to always store variable with same value
    //moved UUID to final variable declared above instead of within the method
    private String parentUsername;
    private String childId;

    private List<Child> children;


    public Parent (String parentId, String parentUsername, List<Child> children){
        this.parentId = parentId;
        this.parentUsername = parentUsername;
        this.children = children;
    }

    public Parent (String parentId, String childId){
        this.parentId = parentId;
        this.childId = childId;
    }

    public String getParentId(){
        return parentId;
    }

    public String getParentUsername() {
        return parentUsername;
    }


    public String getChildId() {
        return childId;
    }

    public List<Child> getChildren() {
        return children;
    }
}
