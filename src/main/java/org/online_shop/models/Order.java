package org.online_shop.models;

import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.interfaces.UserObserver;

import java.util.ArrayList;
import java.util.List;

public class Order implements UserObserver {
    private Integer id;
    private User user;
    private ShippingAddress shippingAddress;
    private final List<OrderItem> orderItems;
    private PaymentStrategy paymentStrategy;
    private String date;
    private String status;
    private Float shippingFee;


    public Order(List<OrderItem> items) {
        this.orderItems = items;
    }

    public Order user(User _user) throws Exception {
        if (this.orderItems == null)
            throw new Exception("Order does not exist!");
        this.setUser(_user);
        return this;
    }

    public Order shippingAddress(ShippingAddress shippingAddress) throws Exception {
        if (this.user == null)
            throw new Exception("Order has no user!");
        this.setShippingAddress(shippingAddress);
        return this;
    }

    public Order shippingFee(Float fee) throws Exception {
        if (this.shippingAddress == null)
            throw new Exception("Order has no shipping address!");
        this.setShippingFee(fee);
        return this;
    }

    public Order payment(PaymentStrategy paymentStrategy) throws Exception {
        if (this.shippingFee == null)
            throw new Exception("Order has not shipping fee!");
        this.setPaymentMethod(paymentStrategy);
        return this;
    }

    public Order status(String status) throws Exception {
        if (this.paymentStrategy == null)
            throw new Exception("Order has not payment!");
        this.setStatus(status);
        return this;
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

    public PaymentStrategy getPaymentMethod() {
        return paymentStrategy;
    }

    public void setPaymentMethod(PaymentStrategy paymentMethod) {
        this.paymentStrategy = paymentMethod;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
