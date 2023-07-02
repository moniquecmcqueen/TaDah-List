package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ParentCreateRequest;
import com.kenzie.appserver.controller.model.ParentResponse;
import com.kenzie.appserver.controller.model.TaskResponse;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.service.ChildService;
import com.kenzie.appserver.service.ParentService;
import com.kenzie.appserver.service.model.Parent;
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
@RequestMapping("/parents")
public class ParentController {
    private List<Parent> parents = new ArrayList<>();
    private ParentService parentService;
    private ChildService childService;



    @PostMapping
    public ResponseEntity<Parent> createParent(@RequestBody ParentCreateRequest parentCreateRequest) {
        Parent parent = new Parent(randomUUID().toString(), parentCreateRequest.getParentUsername());
        parents.add(parent);
        return ResponseEntity.status(HttpStatus.CREATED).body(parent);
    }

    @GetMapping("/{parentId}")

        public ResponseEntity<ParentResponse> getParentById(@PathVariable("parentId") String parentId) {
            Parent parent = parentService.findById(parentId);
            // If there are no tasks, then return a 204
            if (parent == null) {
                return ResponseEntity.notFound().build();
            }
            // Otherwise, convert it into a TaskResponses and return it
            ParentResponse parentResponse = createParentResponse(parent);
            return ResponseEntity.ok(parentResponse);
    }



    @DeleteMapping("/{parentId}")
    public ResponseEntity<Void> deleteParent(@PathVariable String parentId) {
        parentService.deleteParent(parentId);
            return ResponseEntity.noContent().build();
        }


//    @PostMapping("/{parentId}/children")
//    public ResponseEntity<Parent> addChild(@PathVariable String parentId, @RequestBody Child child) {
//        Child addAChild = new Child(randomUUID().toString(),parentCreateRequest.getChildUsername());
//        parentService.addChild(addAChild);
//        ParentResponse parentResponse = createParentResponse();
//
//        //return ResponseEntity.created(URI.create("/tasks" + parentResponse.())).body(task);
//        }
//        return ResponseEntity.notFound().build();
//    }

    @DeleteMapping("/{parentId}/children/{childId}")
    public ResponseEntity<Void> removeChild(@PathVariable String parentId, @PathVariable String childId) {

        parentService.removeChild(childId);

//        Parent parent = getParentById(parentId);
//        if (parent != null) {
//            Child childToRemove = null;
//            for (Child child : parent.getChildren()) {
//                if (child.getChildId().equals(childId)) {
//                    childToRemove = child;
//                    break;
//                }
//            }
//            if (childToRemove != null) {
//                parent.removeChild(childToRemove);
//                return ResponseEntity.noContent().build();
//            }
//        }
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/{parentId}/children/{childId}/tasks")
//    public ResponseEntity<Task> addTaskToChild(
//            @PathVariable String parentId,
//            @PathVariable String childId,
//            @RequestBody Task task
//    ) {
//        Parent parent = getParentById(parentId).getBody();
//        if (parent != null) {
//            Child childToUpdate = null;
//            for (Child child : parent.getChildren()) {
//                if (child.getChildId().equals(childId)) {
//                    childToUpdate = child;
//                    break;
//                }
//            }
//            if (childToUpdate != null) {
//                childToUpdate.getTaskCompletedTask().put(task.getTaskId(), task.getCompleted());
//                return ResponseEntity.status(HttpStatus.CREATED).body(task);
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }

//    @DeleteMapping("/{parentId}/children/{childId}/tasks/{taskId}")
//    public ResponseEntity<Void> removeTaskFromChild(
//            @PathVariable String parentId,
//            @PathVariable String childId,
//            @PathVariable String taskId
//    ) {
//        Parent parent = getParentById(parentId).getBody();
//        if (parent != null) {
//            Child childToUpdate = null;
//            for (Child child : parent.getChildren()) {
//                if (child.getChildId().equals(childId)) {
//                    childToUpdate = child;
//                    break;
//                }
//            }
//            if (childToUpdate != null) {
//                childToUpdate.getTaskCompletedTask().remove(taskId);
//                return ResponseEntity.noContent().build();
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
    private ParentResponse createParentResponse(Parent parent) {
        ParentResponse parentResponse = new ParentResponse();
        parentResponse.setParentId(parent.getParentId());
        parentResponse.setParentUsername(parent.getUsername());

        return parentResponse;
}

    @GetMapping("/parentUsername/{parentUsername}")
    public ResponseEntity<?> checkParentUsername(@PathVariable String parentUsername) {
        // Check if parent username exists
        Parent parent = parentService.findByUsername(parentUsername);
        if (parent != null) {
            return ResponseEntity.ok(parent); // Return parent object
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
