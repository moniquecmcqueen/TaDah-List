package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "TaDah")
public class TaskTableRecord {
    private String taskId;
    private String taskDescription;
    private String taskTitle;
    private String childid;
    private Boolean isCompleted;
    private Boolean isParent;
    private Boolean isChild;
@DynamoDBHashKey(attributeName = "TaskId")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
@DynamoDBAttribute(attributeName = "TaskDescription")
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
@DynamoDBAttribute(attributeName = "TaskTitle")
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
@DynamoDBAttribute(attributeName = "ChildId")
    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid;
    }
@DynamoDBAttribute(attributeName = "Completed")
    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
@DynamoDBAttribute(attributeName = "Parent")
    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }
@DynamoDBAttribute(attributeName = "Child")
    public Boolean getChild() {
        return isChild;
    }

    public void setChild(Boolean child) {
        isChild = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTableRecord that = (TaskTableRecord) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(taskDescription, that.taskDescription) && Objects.equals(taskTitle, that.taskTitle) && Objects.equals(childid, that.childid) && Objects.equals(isCompleted, that.isCompleted) && Objects.equals(isParent, that.isParent) && Objects.equals(isChild, that.isChild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskDescription, taskTitle, childid, isCompleted, isParent, isChild);
    }
}
