package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShippingAddressRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public UserController(UserRepository userRepository, ShippingAddressRepository shippingAddressRepository) {
        this.userRepository = userRepository;
        this.shippingAddressRepository = shippingAddressRepository;

    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable final Long user_id) {
        return userRepository.findById(user_id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable final Long user_id, @RequestBody User user) {
        return userRepository.findById(user_id)
                .map(existingUser -> ResponseEntity.ok(userRepository.save(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable final Long user_id) {
        return userRepository.findById(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().body("User deleted successfully!");
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<ShippingAddress> createShippingAddress(@PathVariable final Long user_id, @RequestBody final ShippingAddress shippingAddress) {
        return userRepository.findById(user_id)
                .map(user -> {
                    shippingAddress.setUser(user);
                    return ResponseEntity.status(HttpStatus.CREATED).body(shippingAddressRepository.save(shippingAddress));
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<List<ShippingAddress>> getAllShippingAddresses(@PathVariable final Long user_id) {
        return userRepository.existsById(user_id) ? ResponseEntity.ok().body(shippingAddressRepository.findAllByUserId(user_id))
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{user_id}/shipping_addresses/{shipping_address_id}")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@PathVariable final Long user_id, @PathVariable final Long shipping_address_id, @RequestBody final ShippingAddress shippingAddress) {
        if (!userRepository.existsById(user_id))
            return ResponseEntity.notFound().build();

        Optional<ShippingAddress> shippingAddressOptional = shippingAddressRepository.findById(shipping_address_id);
        if (shippingAddressOptional.isEmpty())
            return ResponseEntity.notFound().build();

        ShippingAddress existingAddress = shippingAddressOptional.get();
        if (!existingAddress.getUser().getId().equals(user_id))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(shippingAddressRepository.save(shippingAddress));
    }

    @DeleteMapping("/{user_id}/shipping_addresses/{shipping_address_id}")
    public ResponseEntity<String> deleteShippingAddress(
            @PathVariable Long user_id,
            @PathVariable Long shipping_address_id) {

        if (!userRepository.existsById(user_id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<ShippingAddress> shippingAddressOptional = shippingAddressRepository.findById(shipping_address_id);
        if (shippingAddressOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ShippingAddress existingAddress = shippingAddressOptional.get();
        if (!existingAddress.getUser().getId().equals(user_id)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            shippingAddressRepository.delete(existingAddress);
            return ResponseEntity.ok().body("Shipping Address deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting shipping address");
        }
    }
}
