package com.kenzie.appserver.controller;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.ChildUserLoginRequest;
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
    public void getChildByUsernameTest_Null() {
        // Given
        String noChildname = "no name";
        when(childService.findByChildUsername(noChildname)).thenReturn(null);
        ResponseEntity<Child> nullResponse = childController.checkChildUsername(noChildname);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,nullResponse.getStatusCode());
        Assertions.assertEquals(null,nullResponse.getBody());
        verify(childService,times(1)).findByChildUsername(noChildname);
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
    @Test
    public void createChildTest() {
        ChildUserLoginRequest childUserLoginResponse = new ChildUserLoginRequest();
        childUserLoginResponse.setChildUsername("ava");
        childUserLoginResponse.setParentUsername("mo");
        ResponseEntity<ChildUserLoginRequest> news = new ResponseEntity<>(childUserLoginResponse, HttpStatus.OK);
        ResponseEntity<ChildUserLoginResponse> expect = childController.createChild(childUserLoginResponse);
        Assertions.assertEquals(news.getStatusCode(), expect.getStatusCode());
    }
    @Test
    public void removeChildTest() throws Exception {
        String parentname = "John";
        String childname = "Snow";
        childController.removeChild(parentname,childname);
        verify(childService).removeChild(childname);
    }
}
