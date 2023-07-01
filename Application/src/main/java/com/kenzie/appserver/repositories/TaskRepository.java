package com.kenzie.appserver.repositories;


import com.kenzie.appserver.repositories.model.TaskRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface TaskRepository extends CrudRepository<TaskRecord, String> {
    Optional<TaskRecord> findById(String taskId);

}
