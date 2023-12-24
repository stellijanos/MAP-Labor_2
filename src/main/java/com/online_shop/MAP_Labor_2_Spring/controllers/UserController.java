package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShippingAddressRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import com.online_shop.MAP_Labor_2_Spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody final User user) {
        return userService.loginUser(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable final Long user_id) {
        return userService.getUser(user_id);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable final Long user_id) {
        return userService.deleteUser(user_id);
    }

    @PostMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<ShippingAddress> createShippingAddress(@PathVariable final Long user_id, @RequestBody final ShippingAddress shippingAddress) {
        return userService.createShippingAddress(user_id, shippingAddress);
    }

    @GetMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<List<ShippingAddress>> getAllShippingAddresses(@PathVariable final Long user_id) {
        return userService.getAllShippingAddresses(user_id);
    }

    @PutMapping("/{user_id}/shipping_addresses/{shipping_address_id}")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@PathVariable final Long user_id, @PathVariable final Long shipping_address_id, @RequestBody final ShippingAddress shippingAddress) {
        return userService.updateShippingAddress(user_id, shipping_address_id, shippingAddress);
    }

    @DeleteMapping("/{user_id}/shipping_addresses/{shipping_address_id}")
    public ResponseEntity<String> deleteShippingAddress(@PathVariable final Long user_id, @PathVariable final Long shipping_address_id) {
        return userService.deleteShippingAddress(user_id, shipping_address_id);
    }


    @PostMapping("users/{user_id}/shopping_cart")
    public ResponseEntity<ShoppingCart> createShoppingCart(@PathVariable Long user_id) {
        return userService.createShoppingCart(user_id);
    }

    @GetMapping("users/{user_id}/shopping_cart_items")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Long user_id) {
//        return userService.getSh
        return null;
    }
}
