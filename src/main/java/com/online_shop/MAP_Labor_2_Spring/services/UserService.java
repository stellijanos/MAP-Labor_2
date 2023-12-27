package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShippingAddressRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShoppingCartItemRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShoppingCartRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public UserService(UserRepository userRepository, ShippingAddressRepository shippingAddressRepository) {
        this.userRepository = userRepository;
        this.shippingAddressRepository = shippingAddressRepository;
    }


    public ResponseEntity<User> loginUser(User user) {

        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isEmpty())
            return ResponseEntity.notFound().build();
        User current = existing.get();
        System.out.println(user.getPassword() + ' ' + current.getPassword());
        if (!BCrypt.checkpw(user.getPassword(), current.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(current);
    }


    public ResponseEntity<User> createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<User> getUser(final Long user_id) {
        return userRepository.findById(user_id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<User> updateUser(User user) {

        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isEmpty())
            return ResponseEntity.notFound().build();

        User existingUser = optionalUser.get();

        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setPassword(user.getPassword());

        if (!existingUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        existingUser.setEmail(user.getEmail());
        return ResponseEntity.ok(userRepository.save(user));

    }

    public ResponseEntity<String> deleteUser(final Long user_id) {
        return userRepository.findById(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().body("User deleted successfully!");
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ShippingAddress> createShippingAddress(Long user_id, ShippingAddress shippingAddress) {
        return userRepository.findById(user_id)
                .map(user -> {
                    shippingAddress.setUser(user);
                    return ResponseEntity.status(HttpStatus.CREATED).body(shippingAddressRepository.save(shippingAddress));
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Iterable<ShippingAddress>> getAllShippingAddresses(Long user_id) {
        return userRepository.existsById(user_id) ? ResponseEntity.ok().body(shippingAddressRepository.findAllByUserId(user_id))
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity<ShippingAddress> updateShippingAddress(Long user_id, ShippingAddress shippingAddress) {
        if (!userRepository.existsById(user_id))
            return ResponseEntity.notFound().build();

        Optional<ShippingAddress> shippingAddressOptional = shippingAddressRepository.findById(shippingAddress.getId());
        if (shippingAddressOptional.isEmpty())
            return ResponseEntity.notFound().build();

        ShippingAddress existingAddress = shippingAddressOptional.get();
        if (!existingAddress.getUser().getId().equals(user_id))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(shippingAddressRepository.save(shippingAddress));
    }

    public ResponseEntity<String> deleteShippingAddress(Long user_id, Long shipping_address_id) {

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

    public ResponseEntity<String> deleteAllShippingAddresses(Long user_id) {
        return userRepository.findById(user_id)
                .map(user -> {
                    shippingAddressRepository.deleteAllByUserId(user_id);
                    return ResponseEntity.ok().body("Shipping addresses deleted successfully");
                }).orElse(ResponseEntity.notFound().build());
    }
}
