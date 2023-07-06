package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.TaskCreateRequest;
import com.kenzie.appserver.controller.model.TaskResponse;
import com.kenzie.appserver.controller.model.TaskUpdateRequest;
import com.kenzie.appserver.service.TaskService;
import com.kenzie.appserver.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> addNewTask(@RequestBody TaskCreateRequest taskCreateRequest) {
//add UUID
        Task task = new Task(randomUUID().toString(),taskCreateRequest.getParentUsername(), taskCreateRequest.getChildUsername()
                ,taskCreateRequest.getTaskTitle(),false);

        taskService.addNewTask(task);


        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskId(task.getTaskId());
        taskResponse.setParentUsername(task.getParentUsername());
        taskResponse.setChildUsername(task.getChildUsername());
        taskResponse.setTaskTitle(task.getTaskTitle());
        taskResponse.setCompleted(task.getIsCompleted());

        return ResponseEntity.ok(taskResponse);
    }


    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if(tasks ==null) {
            return ResponseEntity.noContent().build();
        }
        List<TaskResponse> taskResponseList = new ArrayList<>();
            for(Task task : tasks)
            taskResponseList.add(this.createTaskResponse(task));

        return ResponseEntity.ok().build();
    }

    //get all task shows us completed and incomplete


    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("taskId") String taskId) {
        Task task = taskService.findById(taskId);
        // If there are no tasks, then return a 204
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        // Otherwise, convert it into a TaskResponses and return it
        TaskResponse taskResponse = createTaskResponse(task);
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping
    public ResponseEntity<TaskResponse> updateTask( @RequestBody TaskUpdateRequest taskUpdateRequest) {
        Task task = new Task(UUID.randomUUID().toString(),taskUpdateRequest.getParentUsername(), taskUpdateRequest.getChildUsername(),
                taskUpdateRequest.getTaskTitle(), false);
       taskService.updateTask(task);

        TaskResponse taskResponse1 = createTaskResponse(task);

        return ResponseEntity.ok(taskResponse1);
    }



    private TaskResponse createTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskId(task.getTaskId());
        taskResponse.setTaskTitle(task.getTaskTitle());
        taskResponse.setCompleted(task.getIsCompleted());

        return taskResponse;
}

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("taskId") String taskId) {
        //Creating this endpoint will require the method from task service
        // and use the taskID that is passed in through @PathVariable("concertId").
        //
        //How do we make a ResponseEntity return type?
        // we want to return no content (204).
        // for the no content (204) status, have the method return ResponseEntity.noContent().build();.

        taskService.deleteTask(taskId);

        return ResponseEntity.noContent().build();
        //return ResponseEntity.status(284).build();
    }

}