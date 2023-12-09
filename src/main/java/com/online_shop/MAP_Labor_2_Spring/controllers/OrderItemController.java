package org.online_shop.controllers;

import org.online_shop.repositories.OrderItemRepository;

public class OrderItemController {
    private OrderItemRepository _orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this._orderItemRepository = orderItemRepository;
    }
}
