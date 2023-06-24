package com.kenzie.appserver;

import com.kenzie.appserver.controller.ParentController;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;


@EnableCaching
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
       // SpringApplication.run(Application.class, args);
        List<Task> todoList = new ArrayList<>();
        Task task = new Task("3434","hw",true);
        todoList.add(0,task);

    }
}
