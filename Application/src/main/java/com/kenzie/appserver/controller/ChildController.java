package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ChildUserLoginResponse;
import com.kenzie.appserver.controller.model.ParentCreateLoginResponse;
import com.kenzie.appserver.controller.model.ParentUserLoginResponse;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.service.ChildService;
import com.kenzie.appserver.service.TaskService;
import com.kenzie.appserver.service.model.Child;

import com.kenzie.appserver.service.model.Parent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/children")
public class ChildController {
    private List<Child> children = new ArrayList<>();

    private ChildService childService;

    private TaskService taskService;



    @GetMapping("/{childId}")
    public ResponseEntity<ChildUserLoginResponse> getChildByUsername(@PathVariable("childUsername") String childUsername) {
        Child child = childService.findByChildUsername(childUsername);
        // If there are no tasks, then return a 204
        if (child == null) {
            return ResponseEntity.notFound().build();
        }
        // Otherwise, convert it into a prentResponses and return it
        ChildUserLoginResponse childUserLoginResponse = childUserLoginResponse(child);
        return ResponseEntity.ok(childUserLoginResponse);
    }
    @GetMapping("/childUsername/{childUsername}")
    // changed "?" to Child -mo
    public ResponseEntity<Child> checkChildUsername(@PathVariable String childUsername) {
        // Check if child username exists
        Child child = (Child) childService.findByChildUsername(childUsername);
        if (child != null) {
            return ResponseEntity.ok(child); // Return child object
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{parentId}/children/{childId}")
    public ResponseEntity<Void> removeChild(@PathVariable String parentUsername, @PathVariable String childUsername) {

        childService.removeChild(childUsername);

        return ResponseEntity.noContent().build();
    }

    private ChildUserLoginResponse childUserLoginResponse(Child child) {
        ChildUserLoginResponse childUserLoginResponse = new ChildUserLoginResponse();
        childUserLoginResponse.setChildUsername(child.getChildUsername());
        childUserLoginResponse.setParentUsername(child.getParentUsername());

        return childUserLoginResponse;
    }
}

