package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "TaDahChild")
public class ChildRecord {

    @DynamoDBHashKey(attributeName = "childId")
    private String childId;

    @DynamoDBAttribute(attributeName = "childUsername")
    private String childUsername;

    @DynamoDBAttribute(attributeName = "taskId")
    private String taskId;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildRecord)) return false;
        ChildRecord that = (ChildRecord) o;
        return Objects.equals(childId, that.childId) && Objects.equals(childUsername, that.childUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childId, childUsername);
    }
}
