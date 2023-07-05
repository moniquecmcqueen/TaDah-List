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
                .map(task -> new Task(task.getParentId(), task.getChildId(), task.getTaskId(), task.getTaskTitle(), task.getIsCompleted()))
                .orElse(null);
    }



    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        Iterable<TaskRecord> taskIterator = taskRepository.findAll();

        for(TaskRecord record : taskIterator) {
            tasks.add(new Task(record.getParentId(),record.getChildId(), record.getTaskId(),
                    record.getTaskTitle(),
                    record.getIsCompleted()));

        }

        return tasks;
    }
    public Task addNewTask(Task task) {
        //do i need to retrieve a parent to add a task ? do i need to retrieve a child to add a task
       // Child child = new Child();
        Parent parent = parentService.findByParentId(task.getParentId()); //why did i have to add task to get my parentId in there
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setParentId(parent.getParentId());
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
        taskRecord.setParentId(task.getParentId());
        taskRecord.setTaskTitle(task.getTaskTitle());
        taskRecord.setIsCompleted(task.getIsCompleted());
        taskRepository.save(taskRecord);
    }

//    public Task markTaskCompleted(String childId) {
//        //Retrieve the child with the given id from the childservice
//        //or parent idk
//        Child child = childService.findByChildId(childId);
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
//        reservedTicketService.updateReserveTicket(reservedTicket);
//
//        //Return a new PurchasedTicket object.
//        return new  PurchasedTicket(reservedTicket.getConcertId(),reservedTicket.getTicketId(),
//                reservedTicket.getDateOfReservation(),pricePaid);
//
//    }
//    public PurchasedTicket purchaseTicket(String reservedTicketId, Double pricePaid) {
//        // Your code here
//
//
//
//        //If the ticket reservation is null or if the reservation has been closed,
//        // then throw a ResponseStatusException with the HttpStatus.BAD_REQUEST status and a message
//        if(reservedTicket == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//
//        if (reservedTicket.getReservationClosed() != null && reservedTicket.getReservationClosed()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation is Closed");
//        }
//        //Create a PurchasedTicketRecord, set the fields,
//        PurchasedTicketRecord purchasedTicketRecord = new PurchasedTicketRecord();
//
//        purchasedTicketRecord.setTicketId(reservedTicketId);
//        purchasedTicketRecord.setDateOfPurchase(reservedTicket.getDateOfReservation());
//        purchasedTicketRecord.setConcertId(reservedTicket.getConcertId());
//        purchasedTicketRecord.setPricePaid(pricePaid);
//
//
//// and store it in the purchaseTicketRepository.
//        purchaseTicketRepository.save(purchasedTicketRecord);
////Update the set the reservationClosed and purchasedTicket flag on the reservedTicket to true
//
//        reservedTicket = new ReservedTicket(reservedTicket.getConcertId(),reservedTicket.getTicketId(),reservedTicket.getDateOfReservation()
//                ,true,reservedTicket.getDateReservationClosed(),true);
//
//        reservedTicketService.updateReserveTicket(reservedTicket);
//
//        //Return a new PurchasedTicket object.
//        return new  PurchasedTicket(reservedTicket.getConcertId(),reservedTicket.getTicketId(),
//                reservedTicket.getDateOfReservation(),pricePaid);
//
//    }
}





