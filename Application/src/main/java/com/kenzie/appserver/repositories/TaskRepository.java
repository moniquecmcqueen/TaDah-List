package com.kenzie.appserver.repositories;


import com.kenzie.appserver.repositories.model.TaskTableRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface TaskRepository extends CrudRepository<TaskTableRecord, String> {

}
