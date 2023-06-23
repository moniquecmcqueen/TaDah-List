package com.kenzie.appserver.service.model;

import java.util.*;

public class Parent {
    private String parentId;
    private String username;
    private Map<String, Boolean> childTaskCompletedTask;
    private List<Task> todoList;
    private List<Child> children;

    public Parent(String username, List<Task> todoList) {
        this.parentId = UUID.randomUUID().toString();
        this.username = username;
        this.todoList = todoList;
        this.children = new ArrayList<>();
        this.childTaskCompletedTask = new HashMap<>();
    }

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
