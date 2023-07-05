package com.kenzie.appserver.service.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.util.*;
public class Child {
    //roles authorized user,limited access to account
@Id
    private final String childUsername;
    //if we want to use age to help with verification of child??
    //can delete if we do not want to use- monique
    //I dont think this is necessary- brandon
    private final String parentUsername;

    public Child(String childUsername, String parentUsername) {
        //could we do this for the unique Ids? - monique

        //might need to check to make sure this id does not exist in our child repository if were doing it this way
        this.childUsername = childUsername;
        this.parentUsername = parentUsername;

    }


    public String getChildUsername() {
        return childUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }
}



