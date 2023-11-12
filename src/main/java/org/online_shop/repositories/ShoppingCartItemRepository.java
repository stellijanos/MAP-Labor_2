package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ShoppingCart;
import org.online_shop.models.ShoppingCartItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShoppingCartItemRepository extends DatabaseInMemory {
    public boolean create(ShoppingCartItem shoppingCartItem) {

        return _shoppingCartItems.add(shoppingCartItem);
    }

    public List<ShoppingCartItem> readAll(ShoppingCart shoppingCart) {
        return _shoppingCartItems.stream().filter(cartItem -> Objects.equals(cartItem.get_shoppingCart(), shoppingCart)).collect(Collectors.toList());
    }

    public boolean update(ShoppingCartItem shoppingCartItem) {
        return _shoppingCartItems.stream()
                .filter(item -> item.get_shoppingCart().equals(shoppingCartItem.get_shoppingCart()))
                .findFirst()
                .map(item -> {
                    item.set_quantity(shoppingCartItem.get_quantity());
                    return true;
                }).orElse(false);
    }

    public boolean delete(ShoppingCartItem shoppingCartItem) {
        return _shoppingCartItems.remove(shoppingCartItem);
    }

    public List<ShoppingCartItem> deleteAll(ShoppingCart shoppingCart) {
        return _shoppingCartItems.stream().filter(item -> Objects.equals(item.get_shoppingCart(), shoppingCart)).collect(Collectors.toList());
    }
}
