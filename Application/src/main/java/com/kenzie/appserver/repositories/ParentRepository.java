package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ChildRecord;
import com.kenzie.appserver.repositories.model.ParentRecord;
import com.kenzie.appserver.repositories.model.TaskRecord;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface ParentRepository extends CrudRepository<ParentRecord, String> {

//    List<ParentRecord> findAll(String parentUsername);
    ParentRecord findByParentUsername(String parentUsername);

}
