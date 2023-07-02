package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface ParentRepository extends CrudRepository<ParentRecord, String> {
    ParentRecord findByParentUsername(String parentUsername);
    Optional<ParentRecord> findById(String taskId);
    Child addChild(Child child);

}
