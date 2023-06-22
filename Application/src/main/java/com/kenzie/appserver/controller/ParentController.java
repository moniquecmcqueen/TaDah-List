package com.kenzie.appserver.controller;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;

import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;
import com.kenzie.appserver.service.model.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParentController {
    private List<Parent> parents = new ArrayList<>();

    public void updateChildTadahTask(Child child, Task task, boolean isCompleted) {
        if (isCompleted) {
            child.markTaskAsCompleted(task);
        } else {
            child.markTaskAsIncomplete(task);
        }
    }


    public boolean didChildCompleteTask(String parentId, String childId, String taskId) {
        Parent parent = getParentById(parentId);
        if (parent != null) {
            Child child = getChildById(parent, childId);
            if (child != null) {
                Map<String, Boolean> taskCompletedTask = child.getTaskCompletedTask();
                return taskCompletedTask.getOrDefault(taskId, false);
            }
        }
        return false;
    }

    public int getChildsCompletedTask(String parentId, String childId) {
        Parent parent = getParentById(parentId);
        if (parent != null) {
            Child child = getChildById(parent, childId);
            if (child != null) {
                Map<String, Boolean> taskCompletedTask = child.getTaskCompletedTask();
                int count = 0;
                for (boolean isCompleted : taskCompletedTask.values()) {
                    if (isCompleted) {
                        count++;
                    }
                }
                return count;
            }
        }
        return 0;
    }

    public void deleteChildTask(String parentId, String childId, String taskId) {
        Parent parent = getParentById(parentId);
        if (parent != null) {
            Child child = getChildById(parent, childId);
            if (child != null) {
                child.deleteTask(taskId);
            }
        }
    }

    public List<String> viewChildTask(String parentId, String childId) {
        Parent parent = getParentById(parentId);
        if (parent != null) {
            Child child = getChildById(parent, childId);
            if (child != null) {
                return child.getTasks();
            }
        }
        return new ArrayList<>();
    }

    public List<String> getCompletedTask(String parentId, String childId) {
        Parent parent = getParentById(parentId);
        if (parent != null) {
            Child child = getChildById(parent, childId);
            if (child != null) {
                List<String> completedTasks = new ArrayList<>();
                Map<String, Boolean> taskCompletedTask = child.getTaskCompletedTask();
                for (Map.Entry<String, Boolean> entry : taskCompletedTask.entrySet()) {
                    if (entry.getValue()) {
                        completedTasks.add(entry.getKey());
                    }
                }
                return completedTasks;
            }
        }
        return new ArrayList<>();
    }

    private Parent getParentById(String parentId) {
        for (Parent parent : parents) {
            if (parent.getParentId().equals(parentId)) {
                return parent;
            }
        }
        return null;
    }

    private Child getChildById(Parent parent, String childId) {
        for (Child child : parent.getChildren()) {
            if (child.getChildId().equals(childId)) {
                return child;
            }
        }
        return null;
    }


}