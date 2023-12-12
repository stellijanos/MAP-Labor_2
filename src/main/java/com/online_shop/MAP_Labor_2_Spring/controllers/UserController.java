package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public @ResponseBody User createUser(@RequestBody final User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public @ResponseBody List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @PutMapping("/{id}")
    public @ResponseBody User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully!");
    }
}
