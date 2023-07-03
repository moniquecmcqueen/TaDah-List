package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
@EnableScan
public interface ChildRepository extends CrudRepository<ChildRecord, String> {
    ChildRecord findByChildUsername(String username);
    Optional<ChildRecord> findById(String taskId);

    //Child addChild(Child child);
}
