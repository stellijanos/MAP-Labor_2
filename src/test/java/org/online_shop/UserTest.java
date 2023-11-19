package org.online_shop;

import org.junit.*;

import static org.junit.Assert.*;

import org.online_shop.controllers.UserController;
import org.online_shop.models.User;
import org.online_shop.repositories.UserRepository;


public class UserTest {


    @Test
    public void test_signUp() {
        UserRepository userRepository = new UserRepository();
        UserController userController = new UserController(userRepository);

        assertEquals(0, userRepository.readAll().size());

        userController.createUser("Janos", "Stelli", "stellijanos23@gmail.com", "12345");
        assertEquals(1, userRepository.readAll().size());
    }

    @Test
    public void test_updateUser() {

        UserRepository userRepository = new UserRepository();
        UserController userController = new UserController(userRepository);

        assertEquals(0, userRepository.readAll().size());

        userController.createUser("Janos", "Stelli", "stellijanos23@gmail.com", "12345");
        assertEquals(1, userRepository.readAll().size());

        User createdUser = userController.getUser("stellijanos23@gmail.com");

        assertNotNull(createdUser);

        createdUser.setFirstname("Max");
        createdUser.setLastname("Mustermann");
        createdUser.setEmail("max.mustermann@gmail.com");
        createdUser.setPassword("max123");


        assertEquals("Max", createdUser.getFirstname());
        assertEquals("Mustermann", createdUser.getLastname());
        assertEquals("max.mustermann@gmail.com", createdUser.getEmail());
        assertEquals("max123", createdUser.getPassword());

    }

    @Test
    public void test_logIn() {
        UserRepository userRepository = new UserRepository();
        UserController userController = new UserController(userRepository);
        User user = userController.getUser("stellijanos@gmail.com");

        assertNull(user.getId());
        assertNull(user.getEmail());
        assertNull(user.getFirstname());
        assertNull(user.getLastname());
        assertNull(user.getPassword());
    }
}
