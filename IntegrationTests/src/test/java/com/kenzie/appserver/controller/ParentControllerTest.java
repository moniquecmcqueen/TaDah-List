package com.kenzie.appserver.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.ChildCreateRequest;
import com.kenzie.appserver.controller.model.ParentCreateRequest;
import com.kenzie.appserver.controller.model.TaskCreateRequest;
import com.kenzie.appserver.service.ParentService;
import com.kenzie.appserver.service.TaskService;
import com.kenzie.appserver.service.model.Parent;
import com.kenzie.appserver.service.model.Task;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class ParentControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        ParentService parentService;

        @Mock
        private ParentService parentServices;


        private final MockNeat mockNeat = MockNeat.threadLocal();

        private final ObjectMapper mapper = new ObjectMapper();


        @Test
        public void addAparentTest() {
                ParentService myNewParent = mock(ParentService.class);
                String parentId = randomUUID().toString();

                // fake request
                ParentCreateRequest myRequest = new ParentCreateRequest();
                myRequest.setParentUsername("Monique");
                //task object

                Parent parent = new Parent(parentId, myRequest.getParentUsername());
                when(myNewParent.findById(String.valueOf(any(Parent.class)))).thenReturn(parent);

                ParentController parentController = new ParentController();
                ResponseEntity<Parent> response = parentController.createParent(myRequest);

                assertEquals(HttpStatus.CREATED, response.getStatusCode());

        }
        @Test
        public void getParentByIdTest_Null() throws Exception {
                //Given
                String parentIdNull = "NoRealId";

                when(parentServices.findById(parentIdNull)).thenReturn(null);


                mvc.perform(MockMvcRequestBuilders.get("/parents/{parentId", parentIdNull))
                        .andExpect(status().isNotFound());
        }

}
