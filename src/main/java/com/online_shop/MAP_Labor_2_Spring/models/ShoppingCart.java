package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart implements UserObserver {


    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", user=" + user +
                ", shoppingCartItems=" + shoppingCartItems +
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
