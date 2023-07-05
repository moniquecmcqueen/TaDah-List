package com.kenzie.appserver.service.model;

import java.util.*;

public class Child {
    //roles authorized user,limited access to account
    private final String childId;
    private final String childUsername;
    //if we want to use age to help with verification of child??
    //can delete if we do not want to use- monique
    //I dont think this is necessary- brandon
    private final String parentId;

    public Child(String childUsername, String childId, String parentId) {
        //could we do this for the unique Ids? - monique
        this.childId = childId;
        //might need to check to make sure this id does not exist in our child repository if were doing it this way
        this.childUsername = childUsername;

        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public String getChildUsername() {
        return childUsername;
    }

    public String getParentId() {
        return parentId;
    }
}



