package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
    List<ShippingAddress> findAllByUserId(Long userId);
}
