package com.online_shop.MAP_Labor_2_Spring.models;


import com.online_shop.MAP_Labor_2_Spring.interfaces.PaymentStrategy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Getter
@Setter
@Entity
@ToString
@Table(name = "orders")
public class Order  {

    @Transient
    private final boolean instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentStrategy payment;
    private String date;
    private String status;
    private Float shippingFee;


    public Order() {
        this.instance = true;
    }

    public Order orderItems(Set<OrderItem> items) throws Exception {
        if (!this.instance)
            throw new Exception("Order does not exist");
        this.orderItems = items;
        return this;
    }

    public Order user(User _user) throws Exception {
        if (this.orderItems == null)
            throw new Exception("Order items do not exist!");
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
        this.setPayment(paymentStrategy);
        return this;
    }

    public Order status(String status) throws Exception {
        if (this.payment == null)
            throw new Exception("Order has not payment!");
        this.setStatus(status);
        return this;
    }
}
