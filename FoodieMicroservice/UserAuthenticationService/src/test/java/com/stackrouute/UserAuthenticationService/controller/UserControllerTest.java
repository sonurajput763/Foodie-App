package com.stackrouute.UserAuthenticationService.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackrouute.UserAuthenticationService.exception.EmailAlreadyExists;
import com.stackrouute.UserAuthenticationService.model.User;
import com.stackrouute.UserAuthenticationService.services.SecurityTokenGenerator;
import com.stackrouute.UserAuthenticationService.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v3/api/v1/users");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllUsers2() throws Exception {
        when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v3/api/v1/users");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testLoginUser() throws Exception {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        when(this.userService.findByUserEmailAndPassword((String) any(), (String) any())).thenReturn(user);
        when(this.securityTokenGenerator.generateToken((User) any())).thenReturn(new HashMap<>());

        User user1 = new User();
        user1.setAccountType("3");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v3/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HashMap/>"));
    }

    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        when(this.userService.saveUser((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setAccountType("3");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v3/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<User><email>jane.doe@example.org</email><password>iloveyou</password><accountType>3</accountType"
                                + "></User>"));
    }

    @Test
    void testRegisterUser2() throws Exception {
        when(this.userService.saveUser((User) any())).thenThrow(new EmailAlreadyExists());

        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v3/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Email already exist"));
    }
}

