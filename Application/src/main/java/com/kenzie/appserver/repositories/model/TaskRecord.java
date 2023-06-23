package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "TaDahTasks")
public class TaskRecord {

    private String taskId;
    private String taskDescription;
    private String taskTitle;
    private String childId;
    private Boolean isCompleted;
    private String parentId;
    @DynamoDBHashKey(attributeName = "taskId")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    @DynamoDBAttribute(attributeName = "taskDescription")
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    @DynamoDBAttribute(attributeName = "taskTitle")
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    @DynamoDBAttribute(attributeName = "childId")
    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
    @DynamoDBAttribute(attributeName = "isCompleted")
    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
    @DynamoDBAttribute(attributeName = "parentId")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskRecord)) return false;
        TaskRecord that = (TaskRecord) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(taskDescription, that.taskDescription) && Objects.equals(taskTitle, that.taskTitle) && Objects.equals(childId, that.childId) && Objects.equals(isCompleted, that.isCompleted) && Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskDescription, taskTitle, childId, isCompleted, parentId);
    }
}
