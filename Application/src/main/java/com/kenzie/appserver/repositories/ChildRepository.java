package com.kenzie.appserver.repositories;

import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<ChildRecord, String> {
    ChildRecord findByChildUsername(String username);
}
