package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    boolean existsByName(String name);
}


//import com.online_shop.MAP_Labor_2_Spring.models.Category;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CategoryRepository extends CrudRepository<Category, Integer> {
//}
