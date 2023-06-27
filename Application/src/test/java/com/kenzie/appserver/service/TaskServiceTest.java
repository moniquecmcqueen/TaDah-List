package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskServiceTest {
    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setup() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }
    /** ------------------------------------------------------------------------
     *  exampleService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void findById() {
        // GIVEN
        String id = randomUUID().toString();

        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(id);
        taskRecord.setTaskDescription("take out the trash");


        // WHEN
        when(taskRepository.findById(id)).thenReturn(Optional.of(taskRecord));
        Task task = taskService.findById(id);

        // THEN
        Assertions.assertNotNull(task, "The object is returned");
        Assertions.assertEquals(taskRecord.getTaskId(), task.getTaskId(), "The task id matches");
        Assertions.assertEquals(taskRecord.getTaskTitle(), task.getTaskTitle(), "The task title matches");
    }

    @Test
    void findById_invalid() {
        // GIVEN
        String id = randomUUID().toString();

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Task task = taskService.findById(id);

        // THEN
        Assertions.assertNull(task, "The task is null when not found");
    }

}
