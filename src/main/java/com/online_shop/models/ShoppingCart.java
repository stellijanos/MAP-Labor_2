package com.online_shop.MAP_Labor_2_Spring.models;

import org.online_shop.interfaces.UserObserver;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements UserObserver {
    private Integer id;
    private User user;
    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

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
