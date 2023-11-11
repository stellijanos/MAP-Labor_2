package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ShoppingCartItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShoppingCartItemRepository extends DatabaseInMemory {
    public boolean create(ShoppingCartItem shoppingCartItem) {

        return _shoppingCartItems.add(shoppingCartItem);
    }

    public ShoppingCartItem read(Integer cartId, Integer productId) {
        for (ShoppingCartItem item : _shoppingCartItems)
            if (item.get_shoppingCartId().equals(cartId) && item.get_productId().equals(productId))
                return item;
        return new ShoppingCartItem();
    }

    public List<ShoppingCartItem> readAll(Integer shoppingCartId) {
        return _shoppingCartItems.stream().filter(cartItem -> Objects.equals(cartItem.get_shoppingCartId(), shoppingCartId)).collect(Collectors.toList());
    }

    public boolean update(ShoppingCartItem shoppingCartItem) {
        for (ShoppingCartItem item : _shoppingCartItems)
            if (Objects.equals(item.get_shoppingCartId(), shoppingCartItem.get_shoppingCartId())) {
                item.set_quantity(shoppingCartItem.get_quantity());
                return true;
            }
        return false;
    }

    public boolean delete(ShoppingCartItem shoppingCartItem) {
        return _shoppingCartItems.remove(shoppingCartItem);
    }

    public List<ShoppingCartItem> deleteAll(Integer shoppingCartId) {
        return _shoppingCartItems.stream().filter(item -> Objects.equals(item.get_shoppingCartId(), shoppingCartId)).collect(Collectors.toList());
    }
}
