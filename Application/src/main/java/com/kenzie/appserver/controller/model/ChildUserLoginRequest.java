package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class ChildUserLoginRequest {
    @NotEmpty
    @JsonProperty("childUsername")
    private String childUsername;

    @NotEmpty
    @JsonProperty("parentUsername")
    private String parentUsername;

    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }

    public String getParentUsername(){return parentUsername;}

    public void setParentUsername(String parentUsername){ this.parentUsername=parentUsername;}
}
