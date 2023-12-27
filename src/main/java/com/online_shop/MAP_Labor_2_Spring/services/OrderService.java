package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.repositories.*;

public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ShippingAddressRepository shippingAddressRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository,
                        ShippingAddressRepository shippingAddressRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.shippingAddressRepository = shippingAddressRepository;
    }


}
