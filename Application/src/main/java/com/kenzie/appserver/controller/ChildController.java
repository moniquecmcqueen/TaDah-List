package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.TaskCreateRequest;
import com.kenzie.appserver.controller.model.TaskResponse;
import com.kenzie.appserver.service.ChildService;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/children")
public class ChildController {
    private List<Child> children = new ArrayList<>();

    private ChildService childService;



    @GetMapping("/{childId}")
    public ResponseEntity<Child> getChildById(@PathVariable("childId") String childId) {
        Child child1 = childService.findById(childId);
        // If there are no tasks, then return a 204
        if (child1 == null) {

        }
        return ResponseEntity.notFound().build();
    }



}

