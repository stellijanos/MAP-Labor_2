package com.online_shop.MAP_Labor_2_Spring;


import com.online_shop.MAP_Labor_2_Spring.controllers.UserController;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void test_createUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john@example.com");

        when(userService.createUser(user)).thenReturn(ResponseEntity.status(201).body(user));

        ResponseEntity<User> response = userController.createUser(user);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());

        User createdUser = response.getBody();
        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        assertEquals("John", createdUser.getFirstname());
    }

    @Test
    void test_getAllUsers() {
        List<User> users = new ArrayList<>();

        User u1 = new User();
        u1.setId(1L);
        u1.setFirstname("Janos");
        u1.setLastname("Jancsika");
        u1.setEmail("email1.com");

        User u2 = new User();
        u2.setId(1L);
        u2.setFirstname("Catalina");
        u2.setLastname("");
        u2.setEmail("email1.com");


        users.add(u1);
        users.add(u2);

        ResponseEntity<Iterable<User>> finalUsers = ResponseEntity.ok(users);

        when(userService.getAllUsers()).thenReturn(finalUsers);

        Iterable<User> result = userController.getAllUsers().getBody();

        assertNotNull(result);
        assertEquals(2, ((List<User>) result).size());

        assertEquals(u1.getId(), users.get(0).getId());
        assertEquals(u1.getFirstname(), users.get(0).getFirstname());
        assertEquals(u1.getLastname(), users.get(0).getLastname());
        assertEquals(u1.getEmail(), users.get(0).getEmail());


        assertEquals(u2.getId(), users.get(1).getId());
        assertEquals(u2.getFirstname(), users.get(1).getFirstname());
        assertEquals(u2.getLastname(), users.get(1).getLastname());
        assertEquals(u2.getEmail(), users.get(1).getEmail());
    }

    @Test
    void test_getUser() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john@example.com");

        when(userService.getUser(userId)).thenReturn(ResponseEntity.ok(user));

        ResponseEntity<User> response = userController.getUser(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());

        User fetchedUser = response.getBody();
        assertNotNull(fetchedUser);
        assertEquals(1L, fetchedUser.getId());
        assertEquals("John", fetchedUser.getFirstname());
    }

    @Test
    void test_updateUser() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFirstname("Updated John");
        user.setLastname("Updated Doe");
        user.setEmail("updatedjohn@example.com");

        when(userService.updateUser(user)).thenReturn(ResponseEntity.ok(user));

        ResponseEntity<User> response = userController.updateUser(user);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());

        User updatedUser = response.getBody();
        assertNotNull(updatedUser);
        assertEquals(1L, updatedUser.getId());
        assertEquals("Updated John", updatedUser.getFirstname());
    }

    @Test
    void test_deleteUser() {
        long userId = 1L;
        ResponseEntity<?> response = userController.deleteUser(userId);
        assertNull(response);
    }
}
