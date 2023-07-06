package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.kenzie.appserver.service.model.Child;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;



    @DynamoDBTable(tableName = "TaDahParent")
    public class ParentRecord {
        @Id
        private String parentUsername;
        private List<String> children;


        @DynamoDBHashKey(attributeName = "parentUsername")
        public String getParentUsername() {
            return parentUsername;
        }

        public void setParentUsername(String parentUsername) {
            this.parentUsername = parentUsername;
        }
        @DynamoDBAttribute(attributeName = "listOfChildren")
        public List<String> getChildren() {
            return children;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ParentRecord)) return false;
            ParentRecord that = (ParentRecord) o;
            return Objects.equals(parentUsername, that.parentUsername);
                    //&& Objects.equals(children, that.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(parentUsername);
        }
    }






