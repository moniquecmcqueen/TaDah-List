package com.kenzie.appserver.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.ParentService;
import com.kenzie.appserver.service.TaskService;
import net.andreinc.mockneat.MockNeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
class ParentControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        ParentService parentService;


        private final MockNeat mockNeat = MockNeat.threadLocal();

        private final ObjectMapper mapper = new ObjectMapper();

}
