package com.kenzie.appserver.service;


import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class ChildServiceTest {
    private ChildRepository childRepository;
    private ChildService childService;
    private TaskRepository taskRepository;

    @BeforeEach
    void setup() {
        childRepository = mock(ChildRepository.class);
        childService = new ChildService(childRepository, taskRepository);
    }

}
