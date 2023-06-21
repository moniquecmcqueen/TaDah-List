package com.kenzie.appserver.repositories.model;

import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, String> {
}
