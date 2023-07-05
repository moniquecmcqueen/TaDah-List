package com.kenzie.appserver.repositories;


import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface TaskRepository extends CrudRepository<TaskRecord, String> {
//List<TaskRecord> findAll(String taskId);

}
