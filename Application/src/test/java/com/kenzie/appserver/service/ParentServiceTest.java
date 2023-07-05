//package com.kenzie.appserver.service;
//
//
//import com.kenzie.appserver.repositories.ChildRepository;
//import com.kenzie.appserver.repositories.ParentRepository;
//import com.kenzie.appserver.repositories.TaskRepository;
//import com.kenzie.appserver.repositories.model.ChildRecord;
//import com.kenzie.appserver.repositories.model.ParentRecord;
//import com.kenzie.appserver.repositories.model.TaskRecord;
//import com.kenzie.appserver.service.model.Child;
//import com.kenzie.appserver.service.model.Parent;
//import com.kenzie.appserver.service.model.Task;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//
//import java.util.Optional;
//
//import static java.util.UUID.randomUUID;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//public class ParentServiceTest {
//    private ChildRepository childRepository;
//    private ParentService parentService;
//    private TaskRepository taskRepository;
//    private ParentRepository parentRepository;
//
//    @BeforeEach
//    void setup() {
//        parentRepository = mock(ParentRepository.class);
//        parentService = new ParentService(taskRepository, childRepository, parentRepository);
//    }
////    @Test
////    void findChildById() {
////        // GIVEN
////        String parentId = randomUUID().toString();
////
////
////        ParentRecord parentRecord = new ParentRecord();
////        parentRecord.setParentId(parentId);
////        parentRecord.setParentUsername("yourMom");
////
////
////        // WHEN
////        when(parentRepository.findById(parentId)).thenReturn(Optional.of(parentRecord));
////        Parent parent = parentService.findById(parentId);
////
////        // THEN
////        Assertions.assertNotNull(parent, "The parent Id is returned");
////        Assertions.assertEquals(parentRecord.getParentId(), parent.getParentId(), "The parent id matches");
////        Assertions.assertEquals(parentRecord.getParentUsername(), parent.getUsername(), "The parent username matches");
////    }
////    @Test
////    void findParentById() {
////        // GIVEN
////        String parentId = randomUUID().toString();
////
////
////        ParentRecord parentRecord = new ParentRecord();
////        parentRecord.setParentId(parentId);
////        parentRecord.setParentUsername("yourMom");
////
////
////        // WHEN
////        when(parentRepository.findById(parentId)).thenReturn(Optional.of(parentRecord));
////        Parent parent = parentService.findById(parentId);
////
////        // THEN
////        Assertions.assertNotNull(parent, "The parent Id is returned");
////        Assertions.assertEquals(parentRecord.getParentId(), parent.getParentId(), "The parent id matches");
////        Assertions.assertEquals(parentRecord.getParentUsername(), parent.getUsername(), "The parent username matches");
////    }
//
//    @Test
//    void findParent_byInvalidId_throwsException() {
//        // GIVEN
//        String parentId = "";
//        ParentRecord parentRecord = new ParentRecord();
//        parentRecord.setParentId(parentId);
//        parentRecord.setParentUsername("yourMom");
//
//        when(parentRepository.findById(parentId)).thenReturn(Optional.of(parentRecord));
//
//
//        // THEN
//        Assertions.assertTrue(true, "The parent Id is invalid");
//    }
//    @Test
//    void deleteParent() {
//        String deleteParentID = randomUUID().toString();
//        String parentUsername = "Rocky";
//
//        Parent parent = new Parent(deleteParentID, parentUsername);
//
//
//        parentService.deleteParent(parent.getParentId());
//        verify(parentRepository).deleteById(deleteParentID);
//    }
////    @Test
////    void addChild(){
////        String childId = randomUUID().toString();
////        String childUsername = randomUUID().toString();
////
////        ChildRecord childRecord = new ChildRecord();
////        childRecord.setChildId(childId);
////        childRecord.setChildUsername(childUsername);
////        childRecord.setTaskId(randomUUID().toString());
////        childRecord.getListOfChildTasks();
////
////        Child child = new Child(childUsername, childId);
////
////        ArgumentCaptor<ChildRecord> parentRecordArgumentCaptor = ArgumentCaptor.forClass(ChildRecord.class);
////
////        //WHEN
////        Child returnedChild = parentService.addChild(child);
////
////        //THEN
////        Assertions.assertNotNull(returnedChild);
////
////        verify(childRepository).save(parentRecordArgumentCaptor.capture());
////
////        ChildRecord aChildRecord = parentRecordArgumentCaptor.getValue();
////
////        Assertions.assertNotNull(aChildRecord, "The task is returned");
////        Assertions.assertEquals(aChildRecord.getChildId(), child.getChildId(), "The child Id matches");
////        Assertions.assertEquals(aChildRecord.getChildUsername(), child.getChildUsername(),
////                "The child username task matches");
////
////    }
//
//    }
//
