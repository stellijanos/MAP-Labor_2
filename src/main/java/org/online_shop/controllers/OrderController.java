package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.models.Order;
import org.online_shop.models.ShippingAddress;
import org.online_shop.models.User;
import org.online_shop.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private final OrderRepository _orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this._orderRepository = orderRepository;
    }

    public Response addOrder(User user, ShippingAddress shippingAddress, PaymentStrategy payment, String status, Float shippingFee) {

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(payment);
        order.setStatus(status);
        order.setShippingFee(shippingFee);

        return _orderRepository.create(order) ? Response.ORDER_CREATED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }


    public Order get(Integer id, User user) {
        return _orderRepository.read(id, user);
    }

    public List<Order> getAll(User user) {
        return _orderRepository.readAll(user);
    }

    public List<Order> getAll() {
//        return _orderRepository.readAll();
        return new ArrayList<>();
    }

}
