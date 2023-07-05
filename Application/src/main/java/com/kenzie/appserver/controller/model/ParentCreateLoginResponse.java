package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

public class ParentCreateLoginResponse {
    //whats being created on the backend based on the request

    @JsonProperty("parentUsername")
    private String parentUsername;
    @JsonProperty("childUsername")
    private String childUsername;
    @JsonProperty("children")
    private List<Child> children;




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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }


    }






