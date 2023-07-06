package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Task;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;


@DynamoDBTable(tableName = "TaDahChild")

    public class ChildRecord {

    private  String parentUsername;
@Id
    private  String childUsername;



    @DynamoDBHashKey(attributeName = "childUsername")
    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }

    @DynamoDBAttribute(attributeName = "parentUsername")

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildRecord)) return false;
        ChildRecord that = (ChildRecord) o;
        return Objects.equals(parentUsername, that.parentUsername) && Objects.equals(childUsername, that.childUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentUsername, childUsername);
    }
}
