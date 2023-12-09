package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.repositories.OrderItemRepository;

public class OrderItemController {
    private OrderItemRepository _orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this._orderItemRepository = orderItemRepository;
    }
}
