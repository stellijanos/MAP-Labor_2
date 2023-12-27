package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {

    void deleteAllByUserId(Long user_id);
    List<ShippingAddress> findAllByUserId(Long userId);
}
