package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.xspec.S;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@DynamoDBTable(tableName = "TaDahParent")
public class ParentRecord {
    @Id
    private String parentUsername;
    private String childUsername;

    @DynamoDBHashKey(attributeName = "parentUsername")
    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    @DynamoDBAttribute(attributeName = "childUsername")
    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }

    // Add annotation for the Global Secondary Index
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "ChildUsernameIndex", attributeName = "childUsernameIndex")
    public String getChildUsernameIndex() {
        return childUsername;
    }

    public void setChildUsernameIndex(String childUsername) {
        this.childUsername = childUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentRecord)) return false;
        ParentRecord that = (ParentRecord) o;
        return Objects.equals(parentUsername, that.parentUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentUsername);
    }
}
