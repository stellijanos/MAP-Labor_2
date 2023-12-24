package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    boolean existsByUserId(Long user_id);
    Optional<ShoppingCart> findByUserId(Long user_id);
    void deleteByUserId(Long user_id);
}
