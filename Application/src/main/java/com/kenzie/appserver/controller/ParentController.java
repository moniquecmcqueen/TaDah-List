package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ParentCreateLoginRequest;
import com.kenzie.appserver.controller.model.ParentCreateLoginResponse;
import com.kenzie.appserver.controller.model.ParentUserLoginResponse;
import com.kenzie.appserver.service.ChildService;
import com.kenzie.appserver.service.ParentService;
import com.kenzie.appserver.service.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<ParentCreateLoginResponse> createParent(@RequestBody ParentCreateLoginRequest parentCreateLoginRequest) {

        if(parentCreateLoginRequest == null|| parentCreateLoginRequest.getParentUsername().length()==0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid user");
        }
        if (parentService.findByParentUsername(parentCreateLoginRequest.getParentUsername()) == null ) {

            parentService.addNewParent(parentCreateLoginRequest);
            return ResponseEntity.ok().build();

        }else{
          //  (can add log features)
            return ResponseEntity.ok().build();

        }


        //return (ResponseEntity<ParentCreateLoginResponse>) ResponseEntity.ok();
    }

    @GetMapping("/{parentUsername}")

        public ResponseEntity<ParentCreateLoginResponse> getParentByUsername(@PathVariable("parentUsername") String parentUsername) {
            Parent parent = parentService.findByParentUsername(parentUsername);
            // If there are no tasks, then return a 204
            if (parent == null) {
                return ResponseEntity.notFound().build();
            }
            // Otherwise, convert it into a prentResponses and return it
        ParentCreateLoginResponse parentCreateLoginResponse = createParentResponse(parent);
          return ResponseEntity.ok(parentCreateLoginResponse);
    }



    @DeleteMapping("/{parentUsername}")
    public ResponseEntity<Void> deleteParent(@PathVariable String parentUsername) {
        parentService.deleteParent(parentUsername);
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


    private ParentCreateLoginResponse createParentResponse(Parent parent) {
        ParentCreateLoginResponse parentCreateLoginResponse = new ParentCreateLoginResponse();
        parentCreateLoginResponse.setChildUsername(parent.getChildUsername());
        parentCreateLoginResponse.setParentUsername(parent.getParentUsername());

        return parentCreateLoginResponse;
    }

    private ParentUserLoginResponse loginParentResponse(Parent parent) {
        ParentUserLoginResponse parentUserLoginResponse = new ParentUserLoginResponse();
        parentUserLoginResponse.setChildUsername(parent.getChildUsername());
        parentUserLoginResponse.setParentUsername(parent.getParentUsername());

        return parentUserLoginResponse;
    }
}
