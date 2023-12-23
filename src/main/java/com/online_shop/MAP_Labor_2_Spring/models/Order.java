package com.online_shop.MAP_Labor_2_Spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    private Long id;

    private String payment;

    @Column(columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime date;
    private String status;
    @Column(name="shipping_fee")
    private Float shippingFee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private ShippingAddress shippingAddress;


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

    public Order payment(String payment) throws Exception {
        if (this.shippingFee == null)
            throw new Exception("Order has not shipping fee!");
        this.setPayment(payment);
        return this;
    }

    public Order status(String status) throws Exception {
        if (this.payment == null)
            throw new Exception("Order has not payment!");
        this.setStatus(status);
        return this;
    }
}
