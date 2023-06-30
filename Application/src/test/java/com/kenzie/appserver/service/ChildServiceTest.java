package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChildServiceTest {
    private ChildRepository childRepository;
    private ChildService childService;
    private TaskRepository taskRepository;

    @BeforeEach
    void setup() {
        childRepository = mock(ChildRepository.class);
        taskRepository = mock(TaskRepository.class);
        childService = new ChildService(childRepository, taskRepository);
    }

    @Test
    void findChildById() {
        // GIVEN
        String childId = randomUUID().toString();


        ChildRecord childRecord = new ChildRecord();
        childRecord.setChildUsername("trent1234");
        childRecord.setChildId(childId);

        // WHEN
        when(childRepository.findById(childId)).thenReturn(Optional.of(childRecord));
        Child child = childService.findById(childId);

        // THEN
        Assertions.assertNotNull(child, "The object is returned");
        Assertions.assertEquals(childRecord.getChildId(), child.getChildId(), "The child id matches");
        Assertions.assertEquals(childRecord.getChildUsername(), child.getChildUsername(), "The child username matches");
    }

    @Test
    void findById_invalid() {
        // GIVEN
        String childId = randomUUID().toString();

        when(childRepository.findById(childId)).thenReturn(Optional.empty());

        // WHEN
        Child child = childService.findById(childId);

        // THEN
        Assertions.assertNull(child, "The childId is null when not found");
    }

//    @Test
//    void getToDoItemsWithValidChildID_retrievesAllTasks() {
//        //GIVEN
//        ChildRecord childRecord1 = new ChildRecord();
//        childRecord1.setChildId(randomUUID().toString());
//        childRecord1.setChildUsername("Tommy");
//        childRecord1.setTaskId(randomUUID().toString());
//
//
//        ChildRecord childRecord2 = new ChildRecord();
//        childRecord2.setChildId(randomUUID().toString());
//        childRecord2.setChildUsername("Kenny");
//        childRecord2.setTaskId(randomUUID().toString());
//
//        ChildRecord childRecord3 = new ChildRecord();
//        childRecord3.setChildId(randomUUID().toString());
//        childRecord3.setChildUsername("Jason");
//        childRecord3.setTaskId(randomUUID().toString());
//
//        List<ChildRecord> taskList = new ArrayList<>();
//        taskList.add(childRecord1);
//        taskList.add(childRecord2);
//        taskList.add(childRecord3);
//
//        //WHEN
//        List<Child> tasks = childService.findAllByChildId();
//
//        //THEN
//        Assertions.assertNotNull(tasks, "The task list is returned");
//        Assertions.assertEquals(3, tasks.size(), "There are three tasks in the list.");
//
//        for (Task task : tasks) {
//            if (task.getTaskId() == childRecord1.getTaskId()) {
//                Assertions.assertEquals(childRecord1.getTaskId(), task.getTaskId());
//            } else if (task.getTaskId() == childRecord1.getTaskId()) {
//                Assertions.assertEquals(childRecord2.getTaskId(), task.getTaskId());
//            } else {
//                Assertions.assertTrue(false, "Task returned that was not in the records!");
//            }
//
//        }

    @Test
    void getAllTasks(){

    }
}
