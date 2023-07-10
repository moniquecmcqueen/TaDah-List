package com.kenzie.appserver.controller;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.ChildUserLoginResponse;
import com.kenzie.appserver.service.ChildService;
import com.kenzie.appserver.service.ParentService;
import com.kenzie.appserver.service.model.Child;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

@IntegrationTest
    public class ChildControllerTest {
        @Autowired
        private MockMvc mvc;

        @Autowired
        ParentService parentService;
        @Mock
        private ChildService childService;
        @InjectMocks
        private ChildController childController;


        private final MockNeat mockNeat = MockNeat.threadLocal();

        private final ObjectMapper mapper = new ObjectMapper();


        @Test
        public void getChildByIdTest_NoId() {
            // Given

            String noChildId = "2";

            when(childService.findByChildUsername(noChildId)).thenReturn(null);

            ResponseEntity<ChildUserLoginResponse> nullResponse = childController.getChildByUsername(noChildId);

            Assertions.assertEquals(HttpStatus.NOT_FOUND,nullResponse.getStatusCode());
            Assertions.assertEquals(null,nullResponse.getBody());
            verify(childService,times(1)).findByChildUsername(noChildId);

        }
        @Test
    public void checkUsernameTest_NonExistingName(){
            String doesntExist = " MeMe";

            when(childService.findByChildUsername(doesntExist)).thenReturn(null);

            ResponseEntity<Child> response = childController.checkChildUsername(doesntExist);

            Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
            Assertions.assertEquals(null,response.getBody());
            verify(childService, times(1)).findByChildUsername(doesntExist);

        }
    }

