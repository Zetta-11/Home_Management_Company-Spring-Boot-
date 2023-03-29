package com.klimmenkov.spring.hibernate.lab_4;

import com.klimmenkov.spring.hibernate.lab_4.controller.admin.UsersController;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerTests {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    @Test
    public void testShowAllUsers() throws Exception {
        List<User> users = Arrays.asList(
                User.builder().id(1).login("test1@test.com").password("password").accountType("admin").build(),
                User.builder().id(2).login("test2@test.com").password("password").accountType("worker").build()
        );
        when(userService.getAllUsers(userService.getUserByLogin("1").getHouse())).thenReturn(users);

        mockMvc.perform(get("/adminPage/allUsers"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/all-users"))
                .andExpect(model().attribute("allUsers", users));
    }

}
