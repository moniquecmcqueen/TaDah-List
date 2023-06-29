package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChildRepository;
import com.kenzie.appserver.repositories.ParentRepository;
import com.kenzie.appserver.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class ParentServiceTest {
    private ChildRepository childRepository;
    private ParentService parentService;
    private TaskRepository taskRepository;
    private ParentRepository parentRepository;

    @BeforeEach
    void setup() {
        parentRepository = mock(ParentRepository.class);
        parentService = new ParentService(taskRepository, childRepository, parentRepository);
    }
}
