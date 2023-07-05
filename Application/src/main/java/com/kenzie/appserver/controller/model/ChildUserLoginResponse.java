package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;

import java.util.List;

public class ChildUserLoginResponse {

    @JsonProperty("parentUsername")
    private String parentUsername;
    @JsonProperty("childUsername")
    private String childUsername;


//    @JsonProperty("personalizedtasks")



    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }


    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }
}
