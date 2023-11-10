package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ShoppingCart;

public class ShoppingCartRepository extends DatabaseInMemory {
    public boolean create(ShoppingCart shoppingCart) {
        return _shoppingCarts.add(shoppingCart);
    }

    public ShoppingCart read(int userId) {
        for (ShoppingCart cart : _shoppingCarts) {
            if (cart.get_userId() == userId)
                return cart;
        }
        return new ShoppingCart();
    }

    public boolean delete(ShoppingCart shoppingCart) {
        return _shoppingCarts.remove(shoppingCart);
    }
}
