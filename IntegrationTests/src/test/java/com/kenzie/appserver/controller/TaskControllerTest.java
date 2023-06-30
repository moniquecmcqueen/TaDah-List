package com.kenzie.appserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.TaskCreateRequest;

import com.kenzie.appserver.controller.model.TaskResponse;
import com.kenzie.appserver.service.TaskService;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.service.model.Task;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.com.google.common.base.Verify.verify;


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

//    @Test
//    public void getById_Exists() throws Exception {
//        String taskId = UUID.randomUUID().toString();
//        String title = UUID.randomUUID().toString();
//        boolean isCompleted = true;
//
//
//        Task task = new Task(taskId, title, isCompleted);
//        Task persistedTask = taskService.addNewTask(task);
//        mvc.perform(get("/task/{id}", persistedTask.getTaskId()
//                //.accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("id")
//                        .value(is(taskId)))
//                .andExpect(jsonPath("name")
//                        .value(is(title)))
//                .andExpect(status().isOk());
//    }

  //  @Test
    public void createTask_CreateSuccessful() throws Exception {
        String createTaskId = mockNeat.strings().valStr();

        TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
        taskCreateRequest.setTaskTitle(createTaskId);

        mapper.registerModule(new JavaTimeModule());

        mvc.perform(post("/task")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskCreateRequest)))
                .andExpect(jsonPath("id")
                        .exists())
                .andExpect(jsonPath("name")
                        .value(is(createTaskId)))
                .andExpect(status().isCreated());
    }

 //   @Test
    public void createATask_IsValid() throws JsonProcessingException {
        String createTaskId = mockNeat.strings().valStr();

        TaskService createTask = mock(TaskService.class);

        TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
        taskCreateRequest.setTaskTitle(createTaskId);

        TaskController taskController = new TaskController(createTask);
        ResponseEntity<Task> response = taskController.addNewTask(taskCreateRequest);


        mapper.registerModule(new JavaTimeModule());

        try {
            mvc.perform(post("/task")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(taskCreateRequest)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

   @Test
    public void addNewTaskTest_addedTask() {
        TaskService myNewTask = mock(TaskService.class);
        String taskId = randomUUID().toString();

        // fake request
        TaskCreateRequest myRequest = new TaskCreateRequest();
        myRequest.setTaskTitle("Am I right or am I right");
        //task object


        Task myTask = new Task(taskId, myRequest.getTaskTitle());
        when(myNewTask.addNewTask(any(Task.class))).thenReturn(myTask);

        TaskController taskController = new TaskController(myNewTask);
        ResponseEntity<Task> response = taskController.addNewTask(myRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // assertEquals(myTask,response.getBody());
    }
  //  @Test
    public void getAllTasksTest() throws Exception {

        Task myResponse = new Task("123","Wash the dishes");
        myResponse.setTaskId("123");
        myResponse.setTaskTitle("Wash the dishes");

        Task taskResponse = new Task("456","Take out the trash");

        List<Task> allTasks = Arrays.asList(myResponse,taskResponse);

        when(taskServices.getAllTasks()).thenReturn(allTasks);


       mvc.perform(MockMvcRequestBuilders.get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].taskId").value("123"))
                .andExpect(jsonPath("$[0].taskTitle").value("Wash the dishes"));
    }

}
