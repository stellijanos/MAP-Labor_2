package org.online_shop.models;

import org.online_shop.builders.OrderBuilder;
import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.interfaces.UserObserver;
import org.online_shop.controllers.CustomControllerTools;

import java.util.ArrayList;
import java.util.List;

public class Order implements UserObserver {
    private Integer id;
    private User user;
    private ShippingAddress shippingAddress;
    private final List<OrderItem> orderItems = new ArrayList<>();
    private PaymentStrategy paymentStrategy;
    private final String date;
    private String status;
    private Float shippingFee;

    public Order(OrderBuilder orderBuilder) {
        this.user = orderBuilder.user;
        this.date = CustomControllerTools.getCurrentDateTIme();
        this.shippingFee = orderBuilder.shippingFee;
        this.shippingAddress = orderBuilder.shippingAddress;
        this.status = orderBuilder.status;
        this.paymentStrategy = orderBuilder.paymentStrategy;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public PaymentStrategy get_paymentMethod() {
        return paymentStrategy;
    }

    public void set_paymentMethod(PaymentStrategy paymentMethod) {
        this.paymentStrategy = paymentMethod;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Float shippingFee) {
        this.shippingFee = shippingFee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", shippingAddress=" + shippingAddress +
                ", orderItems=" + orderItems +
                ", paymentMethod='" + paymentStrategy + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", shippingFee=" + shippingFee +
                '}';
    }

    @Override
    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setShoppingCart(shoppingCart);
    }
}
