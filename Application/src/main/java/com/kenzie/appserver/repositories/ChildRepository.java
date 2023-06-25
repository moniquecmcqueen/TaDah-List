package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<TaskRecord, String> {
}
