package com.kenzie.appserver.controller;

 branbranch
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/children")
public class ChildController {
    private List<Child> children = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Child> createChild(@RequestBody Child child) {
        children.add(child);
        return ResponseEntity.status(HttpStatus.CREATED).body(child);
    }

    @GetMapping
    public ResponseEntity<List<Child>> getAllChildren() {
        return ResponseEntity.ok(children);
    }

    @GetMapping("/{childId}")
    public ResponseEntity<Child> getChildById(@PathVariable String childId) {
        for (Child child : children) {
            if (child.getChildId().equals(childId)) {
                return ResponseEntity.ok(child);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{childId}")
    public ResponseEntity<Child> updateChild(@PathVariable String childId, @RequestBody Child updatedChild) {
        for (Child child : children) {
            if (child.getChildId().equals(childId)) {
                child.setUsername(updatedChild.getUsername());
                child.setAge(updatedChild.getAge());
                child.setTaskCompletedTask(updatedChild.getTaskCompletedTask());
                return ResponseEntity.ok(child);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{childId}")
    public ResponseEntity<Void> deleteChild(@PathVariable String childId) {
        Child childToRemove = null;
        for (Child child : children) {
            if (child.getChildId().equals(childId)) {
                childToRemove = child;
                break;
            }
        }
        if (childToRemove != null) {
            children.remove(childToRemove);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{childId}/tasks")
    public ResponseEntity<Task> addTaskToChild(
            @PathVariable String childId,
            @RequestBody Task task
    ) {
        Child child = getChildById(childId).getBody();
        if (child != null) {
            child.getTaskCompletedTask().put(task.getTaskId(), task.isCompleted());
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{childId}/tasks/{taskId}")
    public ResponseEntity<Void> removeTaskFromChild(
            @PathVariable String childId,
            @PathVariable String taskId
    ) {
        Child child = getChildById(childId).getBody();
        if (child != null) {
            child.getTaskCompletedTask().remove(taskId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

