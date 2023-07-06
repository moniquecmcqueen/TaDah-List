package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.repositories.model.TaskRecord;
//import com.kenzie.appserver.service.model.TaDahTaskList;
import com.kenzie.appserver.service.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class TaskServiceTest {
   private TaskRepository taskRepository;
   private ParentService parentService;
    private TaskService taskService;

    @BeforeEach
    void setup() {
        taskRepository = mock(TaskRepository.class);
        parentService = mock(ParentService.class);
        taskService = new TaskService(taskRepository, parentService);
    }

//    /**
//     * ------------------------------------------------------------------------
//     * exampleService.findById
//     * ------------------------------------------------------------------------
//     **/
//
    @Test
    void findById() {
        // GIVEN
        String id = randomUUID().toString();

        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(id);
        taskRecord.setTaskTitle("take out the trash");
        taskRecord.setIsCompleted(true);

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

    @Test
    void getAllTasks() {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(randomUUID().toString());
        taskRecord.setTaskTitle("take out the trash");
        taskRecord.setIsCompleted(true);

        TaskRecord taskRecord1 = new TaskRecord();
        taskRecord1.setTaskId(randomUUID().toString());
        taskRecord1.setTaskTitle("clean your room");
        taskRecord1.setIsCompleted(true);

        TaskRecord taskRecord2 = new TaskRecord();
        taskRecord2.setTaskId(randomUUID().toString());
        taskRecord2.setTaskTitle("load the dishwasher");
        taskRecord2.setIsCompleted(true);

        List<TaskRecord> taskRecords = new ArrayList<>();
        taskRecords.add(taskRecord);
        taskRecords.add(taskRecord1);
        taskRecords.add(taskRecord2);

        when(taskRepository.findAll()).thenReturn(taskRecords);

        //WHEN
        List<Task> tasks = taskService.getAllTasks();

        //THEN
        Assertions.assertNotNull(tasks, "The task list is returned");
        Assertions.assertEquals(3, tasks.size(), "There are three tasks in the list.");

        for (Task task : tasks) {
            if (task.getTaskId() == taskRecord.getTaskId()) {
                Assertions.assertEquals(taskRecord.getTaskId(), task.getTaskId());
                Assertions.assertEquals(taskRecord.getTaskTitle(), task.getTaskTitle());
                Assertions.assertEquals(taskRecord.getIsCompleted(), task.getIsCompleted());
            } else if (task.getTaskId() == taskRecord1.getTaskId()) {
                Assertions.assertEquals(taskRecord1.getTaskId(), task.getTaskId());
                Assertions.assertEquals(taskRecord1.getTaskTitle(), task.getTaskTitle());
                Assertions.assertEquals(taskRecord1.getIsCompleted(), task.getIsCompleted());
            } else if (task.getTaskId() == taskRecord2.getTaskId())    {
                Assertions.assertEquals(taskRecord2.getTaskId(), task.getTaskId());
                Assertions.assertEquals(taskRecord2.getTaskTitle(), task.getTaskTitle());
                Assertions.assertEquals(taskRecord2.getIsCompleted(), task.getIsCompleted());
            } else {
                Assertions.assertTrue(false, "Task returned that was not in the records!");
            }
        }

    }
//    @Test
//    void addNewTask(){
//        String taskId = randomUUID().toString();
//        String parentId = randomUUID().toString();
//
//        Task task = new Task(taskId, "clean the bathroom", false, parentId);
//
//        ArgumentCaptor<TaskRecord> taskRecordArgumentCaptor = ArgumentCaptor.forClass(TaskRecord.class);
//
//        //WHEN
//        Task returnedTask = taskService.addNewTask(task);
//
//        //THEN
//        Assertions.assertNotNull(returnedTask);
//
//        verify(taskRepository).save(taskRecordArgumentCaptor.capture());
//
//        TaskRecord taskRecord = taskRecordArgumentCaptor.getValue();
//
//        Assertions.assertNotNull(taskRecord, "The task is returned");
//        Assertions.assertEquals(taskRecord.getTaskId(), task.getTaskId(), "The taskId matches");
//        Assertions.assertEquals(taskRecord.getTaskTitle(), task.getTaskTitle(), "The task title matches");
//        Assertions.assertEquals(taskRecord.getIsCompleted(), task.getIsCompleted(), "The task is completed matches");
//        Assertions.assertEquals(taskRecord.getParentId(), task.getParentId(), "The parent Id matches.");
//    }
//
    @Test
   void deleteTask(){
        String deleteTaskId = randomUUID().toString();

        TaskRecord task = new TaskRecord();
        task.setTaskId(deleteTaskId);

        when(taskRepository.findById(deleteTaskId)).thenReturn(Optional.of(task));

       taskService.deleteTask(deleteTaskId);

       verify(taskRepository).deleteById(deleteTaskId);
    }
}
