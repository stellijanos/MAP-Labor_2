package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends CrudRepository<ShoppingCart, Integer> {
    List<ShoppingCartItem> findAllByShoppingCartId(Integer id);
}
