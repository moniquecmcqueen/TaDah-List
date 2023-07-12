package com.kenzie.appserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.TaskCreateRequest;

import com.kenzie.appserver.controller.model.TaskResponse;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.service.model.Task;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class TaskControllerTest {
    @Autowired
   private MockMvc mvc;
    @Autowired
    TaskService taskService;
    @Mock
    private TaskService taskServices;
    @InjectMocks
    private TaskController taskController;
    private final MockNeat mockNeat = MockNeat.threadLocal();
    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void getAllTasksTest() throws Exception {

        List<Task> allTasks = new ArrayList<>();
        allTasks.add(new Task("Mo", "Ava", "123", "wash hands",
                false));
        allTasks.add(new Task("Mo", "ava", "567", "do dishes",
                false));

        when(taskServices.getAllTasks()).thenReturn(allTasks);

        ResponseEntity<List<TaskResponse>> response = taskController.getAllTasks();

        verify(taskServices, times(1)).getAllTasks();
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getTaskByChildUsernameTest_NoId() {
        // Given
        String childname = "Aaiden";
        String taskId = "123";
        Task task = new Task("Mo",childname,taskId,"Laundry",false);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskServices.getTasksByChildUsername(childname)).thenReturn(tasks);

        ResponseEntity<List<TaskResponse>> response = taskController.getTasksByChildUsername(childname);

        verify(taskServices, Mockito.times(1)).getTasksByChildUsername(childname);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
   @Test
   public void getAllTasksTest_Null() {
        //Given/When
        when(taskServices.getAllTasks()).thenReturn(Collections.emptyList());

        ResponseEntity<List<TaskResponse>> response = taskController.getAllTasks();
        // verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskServices, Mockito.times(1)).getAllTasks();
   }
    @Test
    public void deleteByTaskIdTest() throws Exception {
       //Given
        String taskId = "12345";

        mvc.perform(delete("/tasks/{taskId}", taskId))
                .andExpect(status().isNoContent());
    }

}
