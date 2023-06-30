package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<ChildRecord, String> {
    ChildRecord findByChildUsername(String username);
}
