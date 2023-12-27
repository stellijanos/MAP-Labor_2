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

    @PostMapping("/{user_id}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Long user_id, @RequestBody Order order) {
        return orderService.createOrder(user_id, order);
    }

    @GetMapping("/{user_id}/orders/{order_id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long user_id, @PathVariable Long order_id) {
        return orderService.getOrder(user_id, order_id);
    }

    @GetMapping("/{user_id}/orders")
    public ResponseEntity<Iterable<Order>> getAllOrders(@PathVariable Long user_id) {
        return orderService.getAllOrders(user_id);
    }

    @PutMapping("/{user_id}/orders")
    public ResponseEntity<Order> updateOrder(@PathVariable Long user_id, @RequestBody Order order) {
        return orderService.updateOrder(user_id, order);
    }

    @DeleteMapping("/{user_id}/orders/{order_id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long user_id, @PathVariable Long order_id) {
        return orderService.deleteOrder(user_id, order_id);
    }
}
