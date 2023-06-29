package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.TaskCreateRequest;

import com.kenzie.appserver.service.TaskService;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.service.model.Task;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    TaskService taskService;

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

    @Test
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
}