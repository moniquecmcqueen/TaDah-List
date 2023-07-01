package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ChildRecord;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<ChildRecord, String> {
    ChildRecord findByChildUsername(String username);
}
