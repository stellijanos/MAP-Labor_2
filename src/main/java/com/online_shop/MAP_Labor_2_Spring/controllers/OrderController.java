package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.*;
import com.online_shop.MAP_Labor_2_Spring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint to create a new order for a specific user.
     * Handles POST requests to create a new order for a user by user_id and order details.
     *
     * @param user_id ID of the user placing the order
     * @param order Order object containing order details
     * @return ResponseEntity containing the created order information
     */
    @PostMapping("/{user_id}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Long user_id, @RequestBody Order order) {
        return orderService.createOrder(user_id, order);
    }

    /**
     * Endpoint to retrieve a specific order of a user by order ID.
     * Handles GET requests to retrieve an order by user_id and order_id.
     *
     * @param user_id ID of the user whose order is to be retrieved
     * @param order_id ID of the order to be retrieved
     * @return ResponseEntity containing the order information
     */
    @GetMapping("/{user_id}/orders/{order_id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long user_id, @PathVariable Long order_id) {
        return orderService.getOrder(user_id, order_id);
    }

    /**
     * Endpoint to retrieve all orders of a specific user.
     * Handles GET requests to retrieve all orders of a user by user_id.
     *
     * @param user_id ID of the user whose orders are to be retrieved
     * @return ResponseEntity containing a list of orders associated with the user
     */
    @GetMapping("/{user_id}/orders")
    public ResponseEntity<Iterable<Order>> getAllOrders(@PathVariable Long user_id) {
        return orderService.getAllOrders(user_id);
    }

    /**
     * Endpoint to update an existing order for a specific user.
     * Handles PUT requests to update an order for a user by user_id and updated order details.
     *
     * @param user_id ID of the user whose order is to be updated
     * @param order Order object containing updated order information
     * @return ResponseEntity containing the updated order information
     */
    @PutMapping("/{user_id}/orders")
    public ResponseEntity<Order> updateOrder(@PathVariable Long user_id, @RequestBody Order order) {
        return orderService.updateOrder(user_id, order);
    }

    /**
     * Endpoint to delete a specific order of a user by order ID.
     * Handles DELETE requests to delete an order by user_id and order_id.
     *
     * @param user_id ID of the user whose order is to be deleted
     * @param order_id ID of the order to be deleted
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{user_id}/orders/{order_id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long user_id, @PathVariable Long order_id) {
        return orderService.deleteOrder(user_id, order_id);
    }
}
