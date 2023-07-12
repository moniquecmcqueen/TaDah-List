package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.ParentCreateLoginRequest;
import com.kenzie.appserver.controller.model.ParentCreateLoginResponse;

import com.kenzie.appserver.service.ParentService;
import com.kenzie.appserver.service.model.Child;
import com.kenzie.appserver.service.model.Parent;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class ParentControllerTest {

        @Autowired
        private MockMvc mvc;
        @Autowired
        ParentService parentService;

        @InjectMocks
        private ParentController parentController;
        private final MockNeat mockNeat = MockNeat.threadLocal();
        private final ObjectMapper mapper = new ObjectMapper();


        @Test
        public void getParentBynameTest_Null() throws Exception {
                //Given
                String parentnameNull = "NoRealId";

                mvc.perform(MockMvcRequestBuilders.get("/parents/{parentusername}", parentnameNull))
                        .andExpect(status().isNotFound());
        }
        @Test
        public void deleteParentTest() {
                //Given
                String parentname = "12345";

                ResponseEntity<Void> response = parentController.deleteParent(parentname);
                // verify
                Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
                Assertions.assertNull(response.getBody());

        }
}
