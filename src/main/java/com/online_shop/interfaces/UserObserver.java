package com.online_shop.MAP_Labor_2_Spring.interfaces;

import org.online_shop.models.ShoppingCart;

public interface UserObserver {
    void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart);
}
