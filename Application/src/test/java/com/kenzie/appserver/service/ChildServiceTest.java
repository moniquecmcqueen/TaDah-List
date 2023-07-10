package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.ChildUserLoginRequest;
import com.kenzie.appserver.controller.model.ParentCreateLoginRequest;
import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class ChildServiceTest {
    private ChildRepository childRepository;
    private ChildService childService;
    private TaskRepository taskRepository;
    private ParentService parentService;
    private ParentRepository parentRepository;


    @BeforeEach
    void setup() {
        childRepository = mock(ChildRepository.class);
        taskRepository = mock(TaskRepository.class);
        childService = new ChildService(childRepository, taskRepository, parentRepository, parentService);

    }
    @Test
    void getAllTasks() {
        ChildRecord childRecord = new ChildRecord();
        childRecord.setChildUsername("andrew1234");
        childRecord.setParentUsername("Mya");

        ChildRecord childRecord1 = new ChildRecord();
        childRecord1.setChildUsername("andrew1234");
        childRecord1.setParentUsername("Carl");


        ChildRecord childRecord2 = new ChildRecord();
        childRecord2.setChildUsername("andrew1234");
        childRecord2.setParentUsername("Mia");

        List<ChildRecord> childRecords = new ArrayList<>();
        childRecords.add(childRecord);
        childRecords.add(childRecord1);
        childRecords.add(childRecord2);

        when(childRepository.findAll()).thenReturn(childRecords);

        //THEN
        Assertions.assertNotNull(childRecords, "The child task list is returned");
        Assertions.assertEquals(3, childRecords.size(), "There are three tasks for a child  in the list.");
       // System.out.println(childTasks);
        for (ChildRecord child : childRecords) {
            if (Objects.equals(child.getChildUsername(), childRecord.getChildUsername())) {
                Assertions.assertEquals(childRecord.getChildUsername(), child.getChildUsername());
               // Assertions.assertEquals(childRecord.getChildId(), child.getChildId());
            } else if (Objects.equals(child.getChildUsername(), childRecord1.getChildUsername())) {
                Assertions.assertEquals(childRecord1.getChildUsername(), child.getChildUsername());
               // Assertions.assertEquals(childRecord1.getChildId(), child.getChildId());
            } else if (Objects.equals(child.getChildUsername(), childRecord2.getChildUsername())) {
                Assertions.assertEquals(childRecord2.getChildUsername(), child.getChildUsername());
             //   Assertions.assertEquals(childRecord2.getChildId(), child.getChildId());
            } else {
                Assertions.fail("Task returned that was not in the records!");
            }
        }
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
    }

    @Test
    void removeChildTest() {
        String childUsername = "Rocky";

        Child child = new Child(childUsername, "Brian");

        childService.removeChild(child.getChildUsername());
        verify(childRepository).deleteById(childUsername);
    }

    @Test
    void addNewChildTest() {
        String childname = "Braxton";
        ChildUserLoginRequest childUserLoginRequest = new ChildUserLoginRequest();
        childUserLoginRequest.setChildUsername(childname);
        childUserLoginRequest.setParentUsername("Mason");

        childService.addNewChild(childUserLoginRequest);

        ArgumentCaptor<ChildRecord> childRecordCaptor = ArgumentCaptor.forClass(ChildRecord.class);
        verify(childRepository, times(1)).save(childRecordCaptor.capture());

        ChildRecord childRecord1 = childRecordCaptor.getValue();
        Assertions.assertEquals(childUserLoginRequest.getParentUsername(), childRecord1.getParentUsername());
        Assertions.assertEquals(childUserLoginRequest.getChildUsername(), childRecord1.getChildUsername());
    }

    @Test
    void findChildByUsernameTest() {

        String name = "Alex";

        ChildRecord childRecord = new ChildRecord();
        childRecord.setChildUsername(name);

        when(childRepository.findByChildUsername(name)).thenReturn(childRecord);

        Child myResult = childService.findByChildUsername(name);
        verify(childRepository, times(1)).findByChildUsername(name);

        Assertions.assertNotNull(myResult);
    }

    @Test
    void findParentByUsername_Null() {

        String name = "no name";

        ChildRecord childRecord = new ChildRecord();
        childRecord.setChildUsername("no name");

        when(childRepository.findByChildUsername("no name")).thenReturn(null);

        Child myResult = childService.findByChildUsername(name);

        verify(childRepository, times(1)).findByChildUsername(name);

        Assertions.assertNull(myResult);

        Child nullName = childService.findByChildUsername("no name");

        verify(childRepository, times(2)).findByChildUsername("no name");

        Assertions.assertNull(nullName);
    }
}



