package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
}
