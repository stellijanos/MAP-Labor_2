package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    boolean existsByUserId(Long user_id);

    void deleteByUserId(Long user_id);
}
