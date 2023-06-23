package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {
    private List<Parent> parents = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        parents.add(parent);
        return ResponseEntity.status(HttpStatus.CREATED).body(parent);
    }

    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents() {
        return ResponseEntity.ok(parents);
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<Parent> getParentById(@PathVariable String parentId) {
        for (Parent parent : parents) {
            if (parent.getParentId().equals(parentId)) {
                return ResponseEntity.ok(parent);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{parentId}")
    public ResponseEntity<Parent> updateParent(@PathVariable String parentId, @RequestBody Parent updatedParent) {
        for (Parent parent : parents) {
            if (parent.getParentId().equals(parentId)) {
                parent.setUsername(updatedParent.getUsername());
                parent.setTodoList(updatedParent.getTodoList());
                parent.setChildren(updatedParent.getChildren());
                return ResponseEntity.ok(parent);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{parentId}")
    public ResponseEntity<Void> deleteParent(@PathVariable String parentId) {
        Parent parentToRemove = null;
        for (Parent parent : parents) {
            if (parent.getParentId().equals(parentId)) {
                parentToRemove = parent;
                break;
            }
        }
        if (parentToRemove != null) {
            parents.remove(parentToRemove);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{parentId}/children")
    public ResponseEntity<Child> addChild(@PathVariable String parentId, @RequestBody Child child) {
        Parent parent = getParentById(parentId).getBody();
        if (parent != null) {
            parent.addChild(child);
            return ResponseEntity.status(HttpStatus.CREATED).body(child);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{parentId}/children/{childId}")
    public ResponseEntity<Void> removeChild(@PathVariable String parentId, @PathVariable String childId) {
        Parent parent = getParentById(parentId).getBody();
        if (parent != null) {
            Child childToRemove = null;
            for (Child child : parent.getChildren()) {
                if (child.getChildId().equals(childId)) {
                    childToRemove = child;
                    break;
                }
            }
            if (childToRemove != null) {
                parent.removeChild(childToRemove);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{parentId}/children/{childId}/tasks")
    public ResponseEntity<Task> addTaskToChild(
            @PathVariable String parentId,
            @PathVariable String childId,
            @RequestBody Task task
    ) {
        Parent parent = getParentById(parentId).getBody();
        if (parent != null) {
            Child childToUpdate = null;
            for (Child child : parent.getChildren()) {
                if (child.getChildId().equals(childId)) {
                    childToUpdate = child;
                    break;
                }
            }
            if (childToUpdate != null) {
                childToUpdate.getTaskCompletedTask().put(task.getTaskId(), task.isCompleted());
                return ResponseEntity.status(HttpStatus.CREATED).body(task);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{parentId}/children/{childId}/tasks/{taskId}")
    public ResponseEntity<Void> removeTaskFromChild(
            @PathVariable String parentId,
            @PathVariable String childId,
            @PathVariable String taskId
    ) {
        Parent parent = getParentById(parentId).getBody();
        if (parent != null) {
            Child childToUpdate = null;
            for (Child child : parent.getChildren()) {
                if (child.getChildId().equals(childId)) {
                    childToUpdate = child;
                    break;
                }
            }
            if (childToUpdate != null) {
                childToUpdate.getTaskCompletedTask().remove(taskId);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
