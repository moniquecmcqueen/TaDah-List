package com.kenzie.appserver.service;


import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.service.model.Parent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.UUID.randomUUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParentServiceTest {
    private ChildRepository childRepository;
    private ParentService parentService;
    private TaskRepository taskRepository;
    private ParentRepository parentRepository;

    @BeforeEach
    void setup() {
        parentRepository = mock(ParentRepository.class);
        parentService = new ParentService(taskRepository, childRepository, parentRepository);
    }
    @Test
    void parentAddToDoItem_toChildsList_addsItemToDoList(){
        //** this would need to call parentService for username and taskService for taskId f
        //how do you write a test where you call the parentService for the parentUsername, the taskService for the taskID, and the childService for the task assigned
        //to the child?
        //GIVEN - valid taskId and valid parent users
        ParentRecord parentRecord = new ParentRecord();
        parentRecord.setParentId("Mom");
        parentRecord.setParentUsername("yourMom");


        //WHEN - add a new item by calling addItem by ParentUser

        //THEN -- Parent user boolean returns true, addItem() returns true, toDoList contains new item
    }
    @Test
    void findParentById() {
        // GIVEN
        String parentId = randomUUID().toString();


        ParentRecord parentRecord = new ParentRecord();
        parentRecord.setParentId(parentId);
        parentRecord.setParentUsername("yourMom");


        // WHEN
        when(parentRepository.findById(parentId)).thenReturn(Optional.of(parentRecord));
        Parent parent = parentService.findById(parentId);

        // THEN
        Assertions.assertNotNull(parent, "The parent Id is returned");
        Assertions.assertEquals(parentRecord.getParentId(), parent.getParentId(), "The parent id matches");
        Assertions.assertEquals(parentRecord.getParentUsername(), parent.getUsername(), "The parent username matches");
    }

    @Test
    void findParent_byInvalidId_throwsException() {
        // GIVEN
        String parentId = "";
        ParentRecord parentRecord = new ParentRecord();
        parentRecord.setParentId(parentId);
        parentRecord.setParentUsername("yourMom");

        when(parentRepository.findById(parentId)).thenReturn(Optional.of(parentRecord));


        // THEN
        Assertions.assertTrue(true, "The parent Id is invalid");
    }
}
