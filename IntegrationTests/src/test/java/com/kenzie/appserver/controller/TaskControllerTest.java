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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    public void createATask_IsValid() throws JsonProcessingException {
        String createTaskId = mockNeat.strings().valStr();

        TaskService createTask = mock(TaskService.class);

        TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
        taskCreateRequest.setTaskTitle(createTaskId);

        TaskController taskController = new TaskController(createTask);
        ResponseEntity<TaskResponse> response = taskController.addNewTask(taskCreateRequest);

        mapper.registerModule(new JavaTimeModule());
        try {
            mvc.perform(post("/task")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(taskCreateRequest)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void getAllTasksTest() throws Exception {
        //Given
        Task myResponse = new Task("123", "Wash the dishes",
                "18","hands clean",false);
        Task taskResponse = new Task("456", "Take out the trash",
                "21","hands clean",false);

        List<Task> allTasks = Arrays.asList(myResponse, taskResponse);

        when(taskServices.getAllTasks()).thenReturn(allTasks);
        //perform GET endpoint
        String getResponse = mvc.perform(MockMvcRequestBuilders.get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        if (getResponse != null && !getResponse.isEmpty()) {
            // use object mapper
            ObjectMapper objectMapper = new ObjectMapper();
            List<TaskResponse> taskResponses = objectMapper.readValue(getResponse, new TypeReference<List<TaskResponse>>() {
            });
            Assertions.assertEquals("123", taskResponses.get(0).getParentUsername());
            Assertions.assertEquals("456", taskResponses.get(1).getParentUsername());
            Assertions.fail("Empty Response");
        }
    }
    @Test
    public void getTaskByIdTest_NoId() {
        // Given
        String noTaskId = "2";

        when(taskServices.findById(noTaskId)).thenReturn(null);

        ResponseEntity<List<TaskResponse>> nullResponse = taskController.getTasksByChildUsername(noTaskId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, nullResponse.getStatusCode());
        Assertions.assertNull(nullResponse.getBody());
        verify(taskServices, Mockito.times(1)).findById(noTaskId);
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
    // @Test
    public void getTaskByIdTest() throws Exception{
        String parentname = "Monique";
        String childname = "Ava";
        String taskTitle = "Clean your room";
        String taskId = "98789";
        boolean completed = true;
        Task task = new Task(parentname,childname,taskId,taskTitle,completed);

        when(taskServices.findById(taskId)).thenReturn(task);

        MvcResult result=
                (MvcResult) mvc.perform(get("/tasks/{taskId}",taskId))
                        .andExpect(status().isOk())
                        .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Task task1 = mapper.readValue(responseBody,Task.class);

        assertEquals(taskId,task1.getTaskId());
        assertEquals(taskTitle, task1.getTaskTitle());
    }
}