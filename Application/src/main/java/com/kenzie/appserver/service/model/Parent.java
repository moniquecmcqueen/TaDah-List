package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.model.ChildRecord;
import org.springframework.data.annotation.Id;

import java.util.*;

public class Parent {

@Id
    private String parentUsername;


    private List<String> children;


    public Parent ( String parentUsername, List<String> children){

        this.parentUsername = parentUsername;
        this.children = children;
    }


    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }


    public List<String> getChildren() {
        return children;
    }
}
