package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.ParentCreateLoginRequest;
import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ParentRecord;

import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;


import static org.mockito.Mockito.*;

public class ParentServiceTest {
    private ChildRepository childRepository;
    private ParentService parentService;
    private TaskRepository taskRepository;
    private ParentRepository parentRepository;

    @BeforeEach
    void setup() {
        parentRepository = mock(ParentRepository.class);
        parentService = new ParentService(parentRepository);
    }
    @Test
    void deleteParent() {

        String parentUsername = "Rocky";

        Parent parent = new Parent(parentUsername,"ava");

        parentService.deleteParent(parent.getParentUsername());
        verify(parentRepository).deleteById(parentUsername);
    }
    @Test
    void addNewParentTest() {
        String parentname = "Brandon";
        ParentCreateLoginRequest parentRecord = new ParentCreateLoginRequest();
        parentRecord.setParentUsername(parentname);
        parentRecord.setChildUsername("Unique");

        parentService.addNewParent(parentRecord);

        ArgumentCaptor<ParentRecord> parentRecordArgumentCaptor = ArgumentCaptor.forClass(ParentRecord.class);
        verify(parentRepository, times(1)).save(parentRecordArgumentCaptor.capture());

        ParentRecord parentRecord1 = parentRecordArgumentCaptor.getValue();
        Assertions.assertEquals(parentRecord.getParentUsername(), parentRecord1.getParentUsername());
        Assertions.assertEquals(parentRecord.getChildUsername(), parentRecord1.getChildUsername());
    }
    @Test
    void findParentByUsernameTest() {

        String parentname = "Alexis";

        ParentRecord parentRecord = new ParentRecord();
        parentRecord.setParentUsername(parentname);

        when(parentRepository.findByParentUsername(parentname)).thenReturn(parentRecord);

        Parent myResult = parentService.findByParentUsername(parentname);

        verify(parentRepository,times(1)).findByParentUsername(parentname);

        Assertions.assertNotNull(myResult);
    }
    @Test
    void findParentByUsername_Null() {

        String parentname = "no parent name";

        ParentRecord parentRecord = new ParentRecord();
        parentRecord.setParentUsername(parentname);

        when(parentRepository.findByParentUsername("no parent name")).thenReturn(null);

        Parent myResult = parentService.findByParentUsername(parentname);

        verify(parentRepository,times(1)).findByParentUsername(parentname);

        Assertions.assertNull(myResult);

        Parent nullName = parentService.findByParentUsername("no parent name");

        verify(parentRepository,times(2)).findByParentUsername("no parent name");

          Assertions.assertNull(nullName);
    }
@Test
void findChildByUsernameTest() {

    String name = "Alex";

    ParentRecord childRecord = new ParentRecord();
    childRecord.setChildUsername(name);

    when(parentRepository.findByChildUsername(name)).thenReturn(childRecord);

    Child myResult = parentService.findByChildUsername(name);

    verify(parentRepository,times(1)).findByChildUsername(name);

    Assertions.assertNotNull(myResult);
}
    @Test
    void findChildByUsername_Null() {

        String childname = "Leah";
        
        when(parentRepository.findByChildUsername(childname)).thenReturn(null);

        Child noChild = parentService.findByChildUsername(childname);

        Assertions.assertNull(noChild);
    }
}