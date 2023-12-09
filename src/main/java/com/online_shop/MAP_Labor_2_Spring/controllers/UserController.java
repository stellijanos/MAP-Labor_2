package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.Env;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/create")
    public @ResponseBody Response create(
            @RequestParam(name = "token") String token,
            @RequestBody User user) {

        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return Response.INVALID_TOKEN;

        User current = userRepository.findByEmail(user.getEmail());
        if (current.getEmail() != null) {
            return Response.USER_ALREADY_EXISTS;
        }
        userRepository.save(user);
        return Response.USER_CREATE_SUCCESSFUL;
    }

    @GetMapping("/{email}")
    public @ResponseBody User read(
            @RequestParam(name = "token") String token,
            @PathVariable String email) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return new User();
        return userRepository.findByEmail(email);
    }

    @GetMapping
    public @ResponseBody Iterable<User> readAll(@RequestParam(name = "token") String token) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return new ArrayList<>();
        return userRepository.findAll();
    }

    @PutMapping("/{email}")
    public @ResponseBody Response update(@RequestParam String token, @PathVariable String email, @RequestBody User user) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return Response.INVALID_TOKEN;
        User updated = userRepository.updateByEmail(email, user);
        return updated.getEmail() != null ? Response.USER_UPDATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    @DeleteMapping("/{email}")
    public @ResponseBody Response delete(
            @RequestParam String token,
            @RequestParam String current_password,
            @RequestParam String hashed_password,
            @PathVariable String email
            ) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return Response.INVALID_TOKEN;
        if (!BCrypt.checkpw(current_password, hashed_password))
            return Response.INCORRECT_PASSWORD;
        userRepository.deleteByEmail(email);
        return Response.USER_DELETE_SUCCESSFUL;
    }
}
