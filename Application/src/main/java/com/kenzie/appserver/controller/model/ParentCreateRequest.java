package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;

import javax.validation.constraints.NotEmpty;
import java.util.*;

public class ParentCreateRequest {

    @NotEmpty
    @JsonProperty("parentId")
    private String parentId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("childTaskCompletedTask")
    private Map<String, Boolean> childTaskCompletedTask;
    @JsonProperty("todoList")
    private List<Task> todoList;
    @JsonProperty("children")
    private List<Child> children;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Boolean> getChildTaskCompletedTask() {
        return childTaskCompletedTask;
    }

    public void setChildTaskCompletedTask(Map<String, Boolean> childTaskCompletedTask) {
        this.childTaskCompletedTask = childTaskCompletedTask;
    }

    public List<Task> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Task> todoList) {
        this.todoList = todoList;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public void removeChild(Child child) {
        children.remove(child);
    }
}


