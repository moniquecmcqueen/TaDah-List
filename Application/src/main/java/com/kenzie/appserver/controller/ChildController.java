//package com.kenzie.appserver.controller;
//import com.kenzie.appserver.controller.model.ParentResponse;
//import com.kenzie.appserver.repositories.model.ChildRecord;
//import com.kenzie.appserver.service.ChildService;
//import com.kenzie.appserver.service.TaskService;
//import com.kenzie.appserver.service.model.Child;
//import com.kenzie.appserver.service.model.Parent;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@RestController
//@RequestMapping("/children")
//public class ChildController {
//    private List<Child> children = new ArrayList<>();
//
//    private ChildService childService;
//
//    private TaskService taskService;
//
//
//
//    @GetMapping("/{childId}")
//    public ResponseEntity<Child> getChildById(@PathVariable("childId") String childId,String parentId) throws Exception {
//        Optional<ChildRecord> child1 = childService.findByChildUsername(childId,parentId);
//        // If there are no tasks, then return a 204
//        if (child1 == null) {
//
//        }
//        return ResponseEntity.notFound().build();
//    }
//    @GetMapping("/childUsername/{childUsername}")
//    // changed "?" to Child -mo
//    public ResponseEntity<Child> checkChildUsername(@PathVariable String childUsername) {
//        // Check if child username exists
//        Child child = (Child) childService.findByChildUsername(childUsername);
//        if (child != null) {
//            return ResponseEntity.ok(child); // Return child object
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//    @DeleteMapping("/{parentId}/children/{childId}")
//    public ResponseEntity<Void> removeChild(@PathVariable String parentId, @PathVariable String childId) {
//
//        childService.removeChild(childId);
//
//        return ResponseEntity.noContent().build();
//    }
//
////    @PostMapping("/{parentId}/parent/child")
////    public ResponseEntity<Void> addChild(
////            @PathVariable String parentId,
////            @RequestBody Child child) {
////
////        child.setParentId(parentId);
////        childService.addChild(child);
////
////        return ResponseEntity.status(HttpStatus.OK).build();
////
////    }
//
//
//
//
//}
//
