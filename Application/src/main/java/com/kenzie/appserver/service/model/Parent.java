package com.kenzie.appserver.service.model;

import java.util.*;

public class Parent {
    private String parentId;
    //updated to final to always store variable with same value
    //moved UUID to final variable declared above instead of within the method
    private String parentUsername;

    private List<Child> children;

    public Parent(String parentId, String parentUsername, List<Child> children) {
        this.parentId = parentId;
        this.parentUsername = parentUsername;
        this.children = new ArrayList<>();

    }
    public Parent (String parentId, String parentUsername){
        this.parentId = parentId;
        this.parentUsername = parentUsername;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId(){
        return parentId;
    }
    public String getUsername() {
        return parentUsername;
    }

    public void setUsername(String username) {
        this.parentUsername = username;
    }



    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void addChild(Child child) {
        children.add(child);
    }


    public void removeChild(Child child) {
        children.remove(child);
    }
}
