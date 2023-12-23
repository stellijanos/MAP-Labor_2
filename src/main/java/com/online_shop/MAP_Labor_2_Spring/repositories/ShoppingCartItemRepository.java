package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.composite_keys.ShoppingCartItemKey;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCartItem;
import org.springframework.data.repository.CrudRepository;


public interface ShoppingCartItemRepository extends CrudRepository<ShoppingCartItem, ShoppingCartItemKey> {

}
