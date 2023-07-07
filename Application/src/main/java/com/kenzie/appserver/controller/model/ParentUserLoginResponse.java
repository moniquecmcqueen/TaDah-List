package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ParentUserLoginResponse {
    @JsonProperty("parentId")
    private String parentId;
    @JsonProperty("parentUsername")
    private String parentUsername;

    //whats in this list? child usernames or child ids
    //are the tasks tied to each child
    @JsonProperty("children")
    private List<String> children;

    public List<String> getChildren() {
        return children;
    }


    public void setChildren(List<String> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }
}
