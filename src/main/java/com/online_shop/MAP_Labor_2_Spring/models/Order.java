package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.interfaces.PaymentStrategy;
import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Table(name = "orders")
public class Order implements UserObserver {

    private final boolean instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Getter
    @Setter
    private User user;

    @OneToOne
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    @Setter
    @Getter
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private PaymentStrategy paymentStrategy;
    @Setter
    @Getter
    private String date;
    @Setter
    @Getter
    private String status;
    @Setter
    @Getter
    private Float shippingFee;


    public Order() {
        this.instance = true;
    }

    public Order orderItems(List<OrderItem> items) throws Exception {
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
        this.setPaymentStrategy(paymentStrategy);
        return this;
    }

    public Order status(String status) throws Exception {
        if (this.paymentStrategy == null)
            throw new Exception("Order has not payment!");
        this.setStatus(status);
        return this;
    }

    @Override
    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.set$shoppingCart(shoppingCart);
    }
}
