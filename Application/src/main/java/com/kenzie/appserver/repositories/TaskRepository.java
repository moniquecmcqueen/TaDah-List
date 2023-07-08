package com.kenzie.appserver.repositories;



import com.kenzie.appserver.controller.model.TaskResponse;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Task;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


@EnableScan
public interface TaskRepository extends CrudRepository<TaskRecord, String> {
//    List<TaskRecord> getTasksByParentUsername(String parentUsername);
        List<TaskRecord> getTasksByChildUsername(String childUsername);

}


