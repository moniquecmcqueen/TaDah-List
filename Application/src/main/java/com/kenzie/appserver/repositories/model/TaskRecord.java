package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
import java.util.UUID;

@DynamoDBTable(tableName = "TaDahTasks")
public class TaskRecord {

    private UUID taskId;
    private String taskTitle;
    private UUID childId;
    private Boolean isCompleted;
    private UUID parentId;

    @DynamoDBHashKey(attributeName = "taskId")
    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = UUID.fromString(taskId);
    }

    @DynamoDBAttribute(attributeName = "taskTitle")
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @DynamoDBAttribute(attributeName = "childId")
    public UUID getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = UUID.fromString(childId);
    }

    @DynamoDBAttribute(attributeName = "isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        this.isCompleted = isCompleted;
    }

    @DynamoDBAttribute(attributeName = "parentId")
    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = UUID.fromString(parentId);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskRecord that = (TaskRecord) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(taskTitle, that.taskTitle) && Objects.equals(isCompleted, that.isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskTitle, isCompleted);
    }
}
