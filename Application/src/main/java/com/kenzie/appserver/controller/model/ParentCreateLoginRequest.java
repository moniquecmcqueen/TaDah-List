package com.kenzie.appserver.controller.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;

import javax.validation.constraints.NotEmpty;
import java.util.List;


public class ParentCreateLoginRequest {
    //create user
    //what do i want the user to tell me here?
    // I need the user info that im requesting for login to create a new user
    @NotEmpty
    @JsonProperty("parentUsername")
    private String parentUsername;

    @JsonProperty("childUsername")
    private String childUsername;

    @JsonProperty("children")
    private List<String> children;



    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }


    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
}


