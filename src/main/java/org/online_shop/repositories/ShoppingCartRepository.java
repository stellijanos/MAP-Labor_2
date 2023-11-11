package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ShoppingCart;

import java.util.stream.Collectors;

public class ShoppingCartRepository extends DatabaseInMemory {
    public boolean create(ShoppingCart shoppingCart) {
        return _shoppingCarts.add(shoppingCart);
    }

    public ShoppingCart read(Integer userId) {
        return _shoppingCarts.stream().filter(cart -> cart.get_userId().equals(userId)).findFirst().orElse(new ShoppingCart());
    }

    public boolean delete(ShoppingCart shoppingCart) {
        return _shoppingCarts.remove(shoppingCart);
    }

    public int totalNumber() {
        return _shoppingCarts.size();
    }

}
