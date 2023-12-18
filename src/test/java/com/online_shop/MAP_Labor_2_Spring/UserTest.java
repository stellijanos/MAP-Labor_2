package com.online_shop.MAP_Labor_2_Spring;


import com.online_shop.MAP_Labor_2_Spring.controllers.UserController;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAllClients() {
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

        when(userRepository.findAll()).thenReturn(users);

        Iterable<User> result = userController.getAllUsers().getBody();

        assert result != null;
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
}
