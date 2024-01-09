package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Endpoint for user login.
     * Handles POST requests to log in a user.
     *
     * @param user User object containing login credentials
     * @return ResponseEntity containing user information if login is successful
     */
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody final User user) {
        return userService.loginUser(user);
    }

    /**
     * Endpoint for creating a new user.
     * Handles POST requests to create a new user.
     *
     * @param user User object containing user details
     * @return ResponseEntity containing the created user information
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    /**
     * Endpoint to retrieve all users.
     * Handles GET requests to retrieve all users.
     *
     * @return ResponseEntity containing a list of all users
     */
    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Endpoint to retrieve a specific user by user_id.
     * Handles GET requests to retrieve a user by ID.
     *
     * @param user_id ID of the user to retrieve
     * @return ResponseEntity containing the user information
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable final Long user_id) {
        return userService.getUser(user_id);
    }

    /**
     * Endpoint to update user information.
     * Handles PUT requests to update user details.
     *
     * @param user User object containing updated user information
     * @return ResponseEntity containing the updated user information
     */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * Endpoint to delete a user by user_id.
     * Handles DELETE requests to delete a user by ID.
     *
     * @param user_id ID of the user to delete
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable final Long user_id) {
        return userService.deleteUser(user_id);
    }

    /**
     * Endpoint to create a shipping address for a user.
     * Handles POST requests to create a new shipping address for a user.
     *
     * @param user_id ID of the user to associate the shipping address with
     * @param shippingAddress ShippingAddress object containing address details
     * @return ResponseEntity containing the created shipping address information
     */
    @PostMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<ShippingAddress> createShippingAddress(@PathVariable final Long user_id, @RequestBody final ShippingAddress shippingAddress) {
        return userService.createShippingAddress(user_id, shippingAddress);
    }

    /**
     * Endpoint to retrieve all shipping addresses of a specific user.
     * Handles GET requests to retrieve all shipping addresses of a user by user_id.
     *
     * @param user_id ID of the user whose shipping addresses are to be retrieved
     * @return ResponseEntity containing a list of shipping addresses associated with the user
     */
    @GetMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<Iterable<ShippingAddress>> getAllShippingAddresses(@PathVariable final Long user_id) {
        return userService.getAllShippingAddresses(user_id);
    }

    /**
     * Endpoint to update a shipping address for a specific user.
     * Handles PUT requests to update a shipping address for a user by user_id and address details.
     *
     * @param user_id ID of the user whose shipping address is to be updated
     * @param shippingAddress ShippingAddress object containing updated address information
     * @return ResponseEntity containing the updated shipping address information
     */
    @PutMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@PathVariable final Long user_id, @RequestBody final ShippingAddress shippingAddress) {
        return userService.updateShippingAddress(user_id, shippingAddress);
    }

    /**
     * Endpoint to delete a specific shipping address of a user.
     * Handles DELETE requests to delete a shipping address by user_id and shipping_address_id.
     *
     * @param user_id ID of the user whose shipping address is to be deleted
     * @param shipping_address_id ID of the shipping address to be deleted
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{user_id}/shipping_addresses/{shipping_address_id}")
    public ResponseEntity<String> deleteShippingAddress(@PathVariable final Long user_id, @PathVariable final Long shipping_address_id) {
        return userService.deleteShippingAddress(user_id, shipping_address_id);
    }

    /**
     * Endpoint to delete all shipping addresses of a specific user.
     * Handles DELETE requests to delete all shipping addresses associated with a user by user_id.
     *
     * @param user_id ID of the user whose shipping addresses are to be deleted
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{user_id}/shipping_addresses")
    public ResponseEntity<String> deleteAllShippingAddress(@PathVariable final Long user_id) {
        return userService.deleteAllShippingAddresses(user_id);
    }
}
