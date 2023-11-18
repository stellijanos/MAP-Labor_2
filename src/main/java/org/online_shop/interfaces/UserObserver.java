package org.online_shop.interfaces;

import org.online_shop.models.ShoppingCart;

public interface UserObserver {
    void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart);
}
