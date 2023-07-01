package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Objects;

@DynamoDBTable(tableName = "TaDahTasks")
public class TaskRecord {

    @DynamoDBHashKey(attributeName = "task_id")
    private String taskId;

    private String taskTitle;
    private Boolean isCompleted;

    // Add the childId attribute
    private String childId;

    @DynamoDBHashKey(attributeName = "taskId")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @DynamoDBAttribute(attributeName = "taskTitle")
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @DynamoDBAttribute(attributeName = "isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }

    // Add the GSI attribute annotation
    @DynamoDBIndexHashKey(attributeName = "childId", globalSecondaryIndexName = "childIdIndex")
    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
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
