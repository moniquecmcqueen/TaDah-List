package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<Child, String> {
}
