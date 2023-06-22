package com.kenzie.appserver.controller;
import com.kenzie.appserver.service.model.Task;

import com.kenzie.appserver.service.model.Child;
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
        for (Child child : children) {
            if (child.getChildId().equals(childId)) {
                children.remove(child);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
