package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.composite_keys.ShoppingCartItemKey;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ShoppingCartItemRepository extends CrudRepository<ShoppingCartItem, ShoppingCartItemKey> {
    Optional<ShoppingCartItem> findByShoppingCartIdAndProductId(Long shopping_cart_id, Long product_id);
}
