package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Parent;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ParentRepository extends CrudRepository<Parent, String> {
}
