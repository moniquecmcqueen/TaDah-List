package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Child;

import java.util.List;
import java.util.Objects;


public class ParentRecord {
    @DynamoDBTable(tableName = "TaDahParent")
    public class ParentRecords {

        private String taskId;
        private String taskTitle;
        private String childId;
        private Boolean isCompleted;
        private String parentId;
        private List<Child> children;



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

        @DynamoDBAttribute(attributeName = "childId")
        public String getChildId() {
            return childId;
        }

        public void setChildId(String childId) {
            this.childId = childId;
        }

        @DynamoDBAttribute(attributeName = "isCompleted")
        public Boolean getIsCompleted() {
            return isCompleted;
        }

        public void setIsCompleted(Boolean completed) {
            this.isCompleted = isCompleted;
        }

        @DynamoDBAttribute(attributeName = "parentId")
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
            if (!(o instanceof ParentRecords)) return false;
            ParentRecords that = (ParentRecords) o;
            return Objects.equals(taskId, that.taskId) && Objects.equals(taskTitle, that.taskTitle) && Objects.equals(childId, that.childId) && Objects.equals(isCompleted, that.isCompleted) && Objects.equals(parentId, that.parentId) && Objects.equals(children, that.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(taskId, taskTitle, childId, isCompleted, parentId, children);
        }
    }
    }

