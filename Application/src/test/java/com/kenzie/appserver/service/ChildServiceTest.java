//package com.kenzie.appserver.service;
//
//
//import com.kenzie.appserver.repositories.ChildRepository;
//import com.kenzie.appserver.repositories.TaskRepository;
//import com.kenzie.appserver.repositories.model.ChildRecord;
//import com.kenzie.appserver.repositories.model.TaskRecord;
//import com.kenzie.appserver.service.model.Child;
//import com.kenzie.appserver.service.model.Task;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static java.util.UUID.randomUUID;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class ChildServiceTest {
//    private ChildRepository childRepository;
//    private ChildService childService;
//    private TaskRepository taskRepository;
//
//
//    @BeforeEach
//    void setup() {
//        childRepository = mock(ChildRepository.class);
//        taskRepository = mock(TaskRepository.class);
//        childService = new ChildService(childRepository, taskRepository);
//    }
//
//    @Test
//    void findChildById() {
//        // GIVEN
//        String childId = randomUUID().toString();
//
//
//        ChildRecord childRecord = new ChildRecord();
//        childRecord.setChildUsername("trent1234");
//        childRecord.setChildId(childId);
//
//        // WHEN
//        when(childRepository.findById(childId)).thenReturn(Optional.of(childRecord));
//        Child child = childService.findById(childId);
//
//        // THEN
//        Assertions.assertNotNull(child, "The object is returned");
//        Assertions.assertEquals(childRecord.getChildId(), child.getChildId(), "The child id matches");
//        Assertions.assertEquals(childRecord.getChildUsername(), child.getChildUsername(), "The child username matches");
//    }
//
//    @Test
//    void findById_invalid() {
//        // GIVEN
//        String childId = randomUUID().toString();
//
//        when(childRepository.findById(childId)).thenReturn(Optional.empty());
//
//        // WHEN
//        Child child = childService.findById(childId);
//
//        // THEN
//        Assertions.assertNull(child, "The childId is null when not found");
//    }
//    @Test
//    void getAllTasks() {
//        ChildRecord childRecord = new ChildRecord();
//        childRecord.setChildUsername("andrew1234");
//        childRecord.setTaskId(randomUUID().toString());
//        childRecord.setChildId(randomUUID().toString());
//
//        ChildRecord childRecord1 = new ChildRecord();
//        childRecord1.setChildUsername("andrew1234");
//        childRecord1.setTaskId(randomUUID().toString());
//        childRecord1.setChildId(randomUUID().toString());
//
//        ChildRecord childRecord2 = new ChildRecord();
//        childRecord2.setChildUsername("andrew1234");
//        childRecord2.setTaskId(randomUUID().toString());
//        childRecord2.setChildId(randomUUID().toString());
//
//
//        List<ChildRecord> childRecords = new ArrayList<>();
//        childRecords.add(childRecord);
//        childRecords.add(childRecord1);
//        childRecords.add(childRecord2);
//
//        when(childRepository.findAll()).thenReturn(childRecords);
//
//        //WHEN
//        List<Child> childTasks = childService.findAllTasks();
//
//        //THEN
//        Assertions.assertNotNull(childTasks, "The child task list is returned");
//        Assertions.assertEquals(3, childTasks.size(), "There are three tasks for a child  in the list.");
//        System.out.println(childTasks);
//
//        for (Child child : childTasks) {
//            if (child.getTaskId() == childRecord.getTaskId()) {
//                Assertions.assertEquals(childRecord.getChildUsername(), child.getChildUsername());
//                Assertions.assertEquals(childRecord.getChildId(), child.getChildId());
//            } else if (child.getTaskId() == childRecord1.getTaskId()) {
//                Assertions.assertEquals(childRecord1.getChildUsername(), child.getChildUsername());
//                Assertions.assertEquals(childRecord1.getChildId(), child.getChildId());
//            } else if (child.getTaskId() == childRecord2.getTaskId()) {
//                Assertions.assertEquals(childRecord2.getChildUsername(), child.getChildUsername());
//                Assertions.assertEquals(childRecord2.getChildId(), child.getChildId());
//            } else {
//                Assertions.assertTrue(false, "Task returned that was not in the records!");
//            }
//        }
//
//
//    }
////    @Test
////    void findChildByUserName(){
////        String childUsername = randomUUID().toString();
////
////
////
////        ChildRecord childRecord = new ChildRecord();
////        childRecord.setChildUsername(childUsername);
////        childRecord.setChildId(randomUUID().toString());
////        childRecord.getListOfChildTasks();
////
////        //when(childRepository.findById(childId)).thenReturn(Optional.empty());
////        when(childRepository.findByChildUsername(childUsername)).thenReturn(childRecord);
////
////        // WHEN
////       Child child = childService.findByUsername(childUsername);
////
////        // THEN
////        Assertions.assertNull(child, "The childUsername is found");
////    }
//
//    }

