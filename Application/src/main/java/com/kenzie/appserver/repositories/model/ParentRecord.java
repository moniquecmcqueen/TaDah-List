package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Child;

import java.util.List;
import java.util.Objects;



    @DynamoDBTable(tableName = "TaDahParent")
    public class ParentRecord {

        private String parentUsername;
        private String parentId;
        private List<Child> children;
        @DynamoDBAttribute(attributeName = "parentUsername")
        public String getParentUsername() {
            return parentUsername;
        }

        public void setParentUsername(String parentUsername) {
            this.parentUsername = parentUsername;
        }
        @DynamoDBHashKey(attributeName = "parentId")
        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
        @DynamoDBAttribute(attributeName = "listOfChildren")
        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ParentRecord)) return false;
            ParentRecord that = (ParentRecord) o;
            return Objects.equals(parentUsername, that.parentUsername) && Objects.equals(parentId, that.parentId) && Objects.equals(children, that.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(parentUsername, parentId, children);
        }
    }






