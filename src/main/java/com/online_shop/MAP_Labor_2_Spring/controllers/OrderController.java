package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import com.online_shop.MAP_Labor_2_Spring.interfaces.PaymentStrategy;
import com.online_shop.MAP_Labor_2_Spring.models.*;
import com.online_shop.MAP_Labor_2_Spring.repositories.OrderRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderRepository _orderRepository;
    private UserRepository userRepository;


    @PostMapping("/create")
    public @ResponseBody Response createOrder(User user, ShippingAddress shippingAddress, PaymentStrategy payment, String status, Float shippingFee) {

        try {
            Order order = new Order()
                    .orderItems(new ArrayList<>())
                    .user(new User())
                    .shippingAddress(new ShippingAddress())
                    .shippingFee(0f)
                    .payment(new Cash())
                    .status("processed");
            _orderRepository.save(order);
            return Response.ORDER_CREATED_SUCCESSFULLY;
        } catch (Exception e) {
            return Response.SOMETHING_WENT_WRONG;
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody Order get(@PathVariable Integer id) {
        return _orderRepository.findById(id).orElse(new Order());
    }

    @GetMapping("/user/{id}")
    public @ResponseBody List<Order> getAll(@PathVariable String id) {
        return _orderRepository.findAllOrdersByUserId(id);
    }

    @GetMapping
    public @ResponseBody List<Order> getAll() {
        return (List<Order>) _orderRepository.findAll();
    }
}
