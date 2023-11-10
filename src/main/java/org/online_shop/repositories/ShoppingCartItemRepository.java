package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ProductSpec;
import org.online_shop.models.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartItemRepository extends DatabaseInMemory {
    public boolean create(ShoppingCartItem shoppingCArtItem) {

        return true;
    }


    public List<ShoppingCartItem> readAll(int shoppingCartId) {
        return _shoppingCartItems.stream().filter(cartItem -> cartItem.get_shoppingCartId() == shoppingCartId).collect(Collectors.toList());
    }

    public boolean update(ShoppingCartItem shoppingCArtItem) {

        return true;
    }

    public boolean delete(int _id) {

        return true;
    }

    public boolean deleteAll() {

        return true;
    }
}
