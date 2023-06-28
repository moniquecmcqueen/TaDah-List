package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.TaskCreateRequest;
import com.kenzie.appserver.controller.model.TaskResponse;
import com.kenzie.appserver.service.TaskService;
import com.kenzie.appserver.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> addNewTask(@RequestBody TaskCreateRequest taskCreateRequest) {
        Task task = new Task(randomUUID().toString(),taskCreateRequest.getTaskTitle();
        taskService.addNewTask(task);
        TaskResponse taskResponse = createTaskResponse(task);
        return ResponseEntity.created(URI.create("/tasks" + taskResponse.getTaskId())).body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(tasks);
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

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask( @RequestBody TaskResponse taskResponse) {
        Task task = new Task(taskResponse.getTaskId(),
                taskResponse.getTaskTitle(),
                taskResponse.getIsCompleted());
       taskService.updateTask(task);

        TaskResponse taskResponse1 = createTaskResponse(task);

        return ResponseEntity.ok(taskResponse1);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private TaskResponse createTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskId(task.getTaskId());
        taskResponse.setTaskTitle(task.getTaskTitle());
        taskResponse.setCompleted(task.getIsCompleted());

        return taskResponse;
}
}