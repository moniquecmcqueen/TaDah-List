package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

public class ChildRecord {


    @DynamoDBTable(tableName = "TaDahChild")

    public class ChildRecords {

        private String taskId;
        private String taskTitle;
        private String childId;
        private Boolean isCompleted;


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

        public void setIsCompleted(Boolean isCompleted) {
            this.isCompleted = isCompleted;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ChildRecords)) return false;
            ChildRecords that = (ChildRecords) o;
            return Objects.equals(taskId, that.taskId) && Objects.equals(taskTitle, that.taskTitle) && Objects.equals(childId, that.childId) && Objects.equals(isCompleted, that.isCompleted);
        }

        @Override
        public int hashCode() {
            return Objects.hash(taskId, taskTitle, childId, isCompleted);
        }
    }
}
