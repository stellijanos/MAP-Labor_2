package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.models.Order;
import com.online_shop.MAP_Labor_2_Spring.repositories.*;
import org.springframework.http.ResponseEntity;

public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Order> createOrder(Long user_id, Order order) {
        return userRepository.findById(user_id)
                .map(user -> ResponseEntity.ok(orderRepository.save(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Order> getOrder(Long user_id, Long order_id) {
        return userRepository.findById(user_id)
                .map(user -> orderRepository.findById(order_id)
                        .map(order -> ResponseEntity.ok().body(order))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Iterable<Order>> getAllOrders(Long user_id) {
        return userRepository.findById(user_id)
                .map(user -> ResponseEntity.ok(orderRepository.finAllByUserId(user_id)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Order> updateOrder(Long user_id, Order order) {
        return userRepository.findById(user_id)
                .map(user -> orderRepository.findById(order.getId())
                        .map(existing -> {
                            existing.setPayment(order.getPayment());
                            existing.setStatus(order.getStatus());
                            existing.setShippingAddress(order.getShippingAddress());
                            return ResponseEntity.ok(orderRepository.save(existing));
                        }).orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteOrder(Long user_id, Long order_id) {
        return userRepository.findById(user_id)
                .map(user -> orderRepository.findById(order_id)
                        .map(order -> {
                            orderRepository.deleteById(order_id);
                            return ResponseEntity.ok("Order deleted successfully!");
                        }).orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }
}
