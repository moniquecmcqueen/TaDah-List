package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.ChildService;
import com.kenzie.appserver.service.TaskService;
import com.kenzie.appserver.service.model.Child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/children")
public class ChildController {
    private List<Child> children = new ArrayList<>();
    @Autowired
    private ChildService childService;
    @Autowired
    private TaskService taskService;



    private ChildUserLoginResponse createChildResponse(Child child) {
        ChildUserLoginResponse childUserLoginResponse = new ChildUserLoginResponse();
        childUserLoginResponse.setChildUsername(child.getChildUsername());
        childUserLoginResponse.setParentUsername(child.getParentUsername());

        return childUserLoginResponse;
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

    private ChildUserLoginResponse childUserLoginResponse(ChildUserLoginRequest childUserLoginRequest) {
        ChildUserLoginResponse childUserLoginResponse = new ChildUserLoginResponse();
        childUserLoginResponse.setChildUsername(childUserLoginRequest.getChildUsername());

        return childUserLoginResponse;
    }

    @PostMapping
    public ResponseEntity<ChildUserLoginResponse> createChild(@RequestBody ChildUserLoginRequest childUserLoginRequest) {
        if (childUserLoginRequest == null || childUserLoginRequest.getChildUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid child create request");
        }

        String childUsername = childUserLoginRequest.getChildUsername();
        Child existingChild = childService.findByChildUsername(childUsername);

        if (existingChild != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Child already exists");
        }

        childService.addNewChild(childUserLoginRequest);

        ChildUserLoginResponse response = new ChildUserLoginResponse();
        response.setChildUsername(childUsername);

        return ResponseEntity.ok(response);
    }



}

