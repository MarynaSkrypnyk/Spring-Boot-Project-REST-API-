//package org.example.chinesfoodcafe.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.chinesfoodcafe.entity.User;
//import org.example.chinesfoodcafe.service.UserService;
//import org.example.chinesfoodcafe.utils.Role;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(UserController.class)
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc(addFilters = false)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetUserById() {
//        Long id = 1L;
//        User user = new User("user@example.com", "password", Role.ROLE_USER);
//
//        when(userService.getUserById(id)).thenReturn(user);
//
//        ResponseEntity<User> responseEntity = userController.getUserById(id);
//
////        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(user, responseEntity);
//    }
//}
