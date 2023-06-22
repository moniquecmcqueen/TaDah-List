package com.kenzie.appserver.service.model;

import java.util.*;

public class Parent {
    // unique id and username to authenticate the parent-monique
    private String parentId;
    //do we want a password? private String password; -rebecca
    private String username;
    // using Map to interact with the child class for completed task-monique
    private Map<String, Boolean> childTaskCompletedTask;
    //should we be interacting with this map or creating a new list
    private List<Task> todoList;

    private List<Child> children;

// not sure if this (private List<Child> children;) is needed yet-rebecca h
    // parent class to include children?-monique
    //private List<Child> children;-monique
    //d-tables don't like to store non-primitive data types inside list-rebecca
    // list of children ids (strings)-rebecca


    //public void addMyChild(Child child){children.add(child);}-monique
    //not sure if this is needed yet-rebecca
    public Parent(String username,List<Task>todoList) {
        this.parentId = UUID.randomUUID().toString();
        this.username = username;
        this.todoList = todoList;
        //do we want a password? this.password = password; .rebecca h
        //removed one of the parent constructors and combined the two into this one constructor-rebecca h
    }
    // parent should be able to monitor a child's progress... to track and retrieve info about
    // the child's overall progress and completion of tasks
    // - as a parent I want to be able to retrieve a task that has been completed
    //- retrieve a task that has been incompleted
    //- view task and delete task-monique

    //removed 7 methods and placed them in the controller classes-rebecca h
    //added getters and setters-rebecca h

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