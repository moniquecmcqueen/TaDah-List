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
    @JsonProperty("children")
    private List<String> children;




    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }
    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }


    }






