package com.online_shop.MAP_Labor_2_Spring.repositories;


import com.online_shop.MAP_Labor_2_Spring.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findAllByCategoryId(Long id);
}
