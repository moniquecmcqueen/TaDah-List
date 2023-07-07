package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private ChildService childService;
    private ParentService parentService;

    @Autowired
    public TaskService(TaskRepository taskRepository, ParentService parentService) {
        this.parentService = parentService;
        this.taskRepository = taskRepository;
    }

    public Task findById(String taskId) {

        return taskRepository
                .findById(taskId)
                .map(task -> new Task(task.getParentUsername(), task.getChildUsername(), task.getTaskId(), task.getTaskTitle(), task.getIsCompleted()))
                .orElse(null);
    }


    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        Iterable<TaskRecord> taskIterator = taskRepository.findAll();

        for (TaskRecord record : taskIterator) {
            tasks.add(new Task(record.getParentUsername(), record.getChildUsername(), record.getTaskId(),
                    record.getTaskTitle(),
                    record.getIsCompleted()));

        }

        return tasks;
    }

    public TaskRecord addNewTask(Task task) {


            TaskRecord taskRecord = new TaskRecord();

            taskRecord.setParentUsername(task.getParentUsername());
            taskRecord.setTaskId(task.getTaskId());
            taskRecord.setChildUsername(task.getChildUsername());
            taskRecord.setIsCompleted(task.getIsCompleted());
            taskRecord.setTaskTitle(task.getTaskTitle());

            taskRepository.save(taskRecord);
            return taskRecord;
    }


    public void deleteTask(String taskId) {
       //retrieve the taskId
        Optional<TaskRecord> task = taskRepository.findById(taskId);
         // if task is found proceed to delete
        if(task.isPresent()) {
            taskRepository.deleteById(taskId);
            // } else {
            //   throw new RuntimeException("Task not found");
            // }
        }
    }

    public void updateTask(Task task) {

        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(task.getTaskId());
        taskRecord.setParentUsername(task.getParentUsername());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRecord.setIsCompleted(task.getIsCompleted());
        taskRepository.save(taskRecord);
    }
}
//    public Task markTaskCompleted(String childId) {
//        //Retrieve the child with the given id from the childservice
//        //or parent idk
//        Child child = childService.findByChildUsername(childId);
//        //how do i find my child and access his/her specific tasks
//
//        //Create a taskRecord, set the fields,
//        TaskRecord taskRecord = new TaskRecord();
//
//        taskRecord.setChildId(parent.getChildId());
//        taskRecord.setParentId(parent.getParentId());
//        taskRecord.setTaskId(taskRecord.getTaskId());
//
//// and store it in the task repo.
//        taskRepository.save(taskRecord);
////Update the set the iscomplete boolean to true
//
//        Task = new Parent(parent.getParentId(),parent.,parent.getChildId()
//                ,true,parent.g(),true);
//
