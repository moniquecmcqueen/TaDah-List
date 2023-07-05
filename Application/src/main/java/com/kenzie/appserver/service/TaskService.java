package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.repositories.TaskRepository;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private ChildService childService;
    private ParentService parentService;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(String id) {

        return taskRepository
                .findById(id)
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

    public Task addNewTask(Task task) {
        //do i need to retrieve a parent to add a task ? do i need to retrieve a child to add a task
        // Child child = new Child();
        Parent parent = parentService.findByParentUsername(task.getParentUsername()); //why did i have to add task to get my parentId in there
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setParentUsername(parent.getParentUsername());
        taskRecord.setTaskId(task.getTaskId());
        //assign child id and username does child need to be added to task records if already assocaited with parent
        taskRecord.setIsCompleted(null);
        taskRecord.setTaskTitle(task.toString()); //unsure how to set task title here

        taskRepository.save(taskRecord);


        return task;
    }


    public void deleteTask(String taskId) {

        taskRepository.deleteById(taskId);

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
