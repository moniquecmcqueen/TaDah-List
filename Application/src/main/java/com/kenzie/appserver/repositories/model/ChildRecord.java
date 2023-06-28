package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

    @DynamoDBTable(tableName = "TaDahChild")

    public class ChildRecord {

        private String childId;

        private String childUsername;

        @DynamoDBAttribute(attributeName = "childId")
        public String getChildId() {
            return childId;
        }

        public void setChildId(String childId) {
            this.childId = childId;
        }

        @DynamoDBAttribute(attributeName = "childUsername")
        public String getChildUsername() {
            return childUsername;
        }

        public void setChildUsername(String childUsername) {
            this.childUsername = childUsername;
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