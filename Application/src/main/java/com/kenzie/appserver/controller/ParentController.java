package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ParentCreateRequest;
import com.kenzie.appserver.controller.model.ParentResponse;
import com.kenzie.appserver.service.ChildService;
import com.kenzie.appserver.service.ParentService;
import com.kenzie.appserver.service.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/parents")
public class ParentController {
    @Autowired
    private ParentService parentService;
    @Autowired
    private ChildService childService;



    @PostMapping
    public ResponseEntity<ParentResponse> createParent(@RequestBody ParentCreateRequest parentCreateRequest) {

        Parent parent = new Parent(randomUUID().toString(), parentCreateRequest.getParentUsername());
        parentService.addNewParent(parent);

        ParentResponse parentResponse = createParentResponse(parent);

        return ResponseEntity.created(URI.create("/parent/" + parentResponse.getParentId())).body(parentResponse);
    }

    @GetMapping("/{parentId}")

        public ResponseEntity<ParentResponse> getParentById(@PathVariable("parentId") String parentId) {
            Parent parent = parentService.findByParentId(parentId);
            // If there are no tasks, then return a 204
            if (parent == null) {
                return ResponseEntity.notFound().build();
            }
            // Otherwise, convert it into a TaskResponses and return it
            ParentResponse parentResponse = createParentResponse(parent);
          return null;
    }



    @DeleteMapping("/{parentId}")
    public ResponseEntity<Void> deleteParent(@PathVariable String parentId) {
        parentService.deleteParent(parentId);
            return ResponseEntity.noContent().build();
        }






//    @RequestMapping("/{parentUsername}")
//    public ResponseEntity<Object> checkParentUsername(@PathVariable("parentUsername")String parentUsername) {
//        // Check if parent username exists
//        Parent parent = parentService.findByParentUsername(parentUsername);
//        if (parent == null) {
//            return ResponseEntity.notFound().build(); // Return parent object
//        }
//
//        ParentResponse parentResponse = createParentResponse(parent);
//        return parentResponse;
//    }


    private ParentResponse createParentResponse(Parent parent) {
        ParentResponse parentResponse = new ParentResponse();
        parentResponse.setParentId(parent.getParentId());
        parentResponse.setParentUsername(parent.getParentUsername());

        return parentResponse;
    }
}
