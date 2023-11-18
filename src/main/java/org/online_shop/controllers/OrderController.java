package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.models.Order;
import org.online_shop.models.ShippingAddress;
import org.online_shop.models.User;
import org.online_shop.repositories.OrderRepository;

public class OrderController {
    private final OrderRepository _orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this._orderRepository = orderRepository;
    }

    public Response createOrder(User user, ShippingAddress shippingAddress, PaymentStrategy payment, String status, Float shippingFee) {

        Order order = new Order();
        order.set_user(user);
        order.set_shippingAddress(shippingAddress);
        order.set_paymentMethod(payment);
        order.set_status(status);
        order.set_shippingFee(shippingFee);
        order.set_id(_orderRepository.readAll().size()+1);

        return _orderRepository.create(order) ? Response.ORDER_CREATED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }
}
