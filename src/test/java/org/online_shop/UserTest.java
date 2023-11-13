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

        createdUser.set_firstname("Max");
        createdUser.set_lastname("Mustermann");
        createdUser.set_email("max.mustermann@gmail.com");
        createdUser.set_password("max123");


        assertEquals("Max", createdUser.get_firstname());
        assertEquals("Mustermann", createdUser.get_lastname());
        assertEquals("max.mustermann@gmail.com", createdUser.get_email());
        assertEquals("max123", createdUser.get_password());

    }

    @Test
    public void test_logIn() {
        UserRepository userRepository = new UserRepository();
        UserController userController = new UserController(userRepository);
        User user = userController.getUser("stellijanos@gmail.com");

        assertNull(user.get_id());
        assertNull(user.get_email());
        assertNull(user.get_firstname());
        assertNull(user.get_lastname());
        assertNull(user.get_password());
    }
}
