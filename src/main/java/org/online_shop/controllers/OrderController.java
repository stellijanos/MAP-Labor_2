package org.online_shop.controllers;

import org.online_shop.repositories.OrderRepository;

public class OrderController {
    private OrderRepository _orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this._orderRepository = orderRepository;
    }
}
