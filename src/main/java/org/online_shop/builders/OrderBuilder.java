package org.online_shop.builders;

import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.models.Order;
import org.online_shop.models.OrderItem;
import org.online_shop.models.ShippingAddress;
import org.online_shop.models.User;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    public Integer id;
    public User user;
    public ShippingAddress shippingAddress;
    public final List<OrderItem> orderItems = new ArrayList<>();
    public PaymentStrategy paymentStrategy;
    public String date;
    public String status;
    public Float shippingFee;

    public OrderBuilder(Integer id, User user) {
        this.id = id;
        this.user = user;
    }

    public OrderBuilder shippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public OrderBuilder addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        return this;
    }

    public OrderBuilder paymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        return this;
    }

    public OrderBuilder date(String date) {
        this.date = date;
        return this;
    }

    public OrderBuilder status(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder shippingFee(Float shippingFee) {
        this.shippingFee = shippingFee;
        return this;
    }

    public Order build() {
        return new Order(this);
    }
}
