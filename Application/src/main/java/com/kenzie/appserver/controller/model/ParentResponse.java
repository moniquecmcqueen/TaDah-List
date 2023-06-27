package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

public class ParentResponse {
    @NotEmpty
    @JsonProperty("parentId")
    private String parentId;
    @JsonProperty("parentUsername")
    private String parentUsername;
    @JsonProperty("childUsername")
    private String childUsername;
    @JsonProperty("childId")
    private String childId;
    //    @JsonProperty("todoList")
//    private List<Task> todoList;
    @JsonProperty("children")
    private List<Child> children;


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



